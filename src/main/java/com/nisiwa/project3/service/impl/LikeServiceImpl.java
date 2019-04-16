package com.nisiwa.project3.service.impl;

import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.LikeService;
import com.nisiwa.project3.service.NewsService;
import com.nisiwa.project3.utils.JedisUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author: nisiwa
 * @date: 2019-04-12 21:37
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    NewsService newsService;

    /**
     *
     * @param newsId news的Id
     * @param user  session中保存的登录用户
     * @param flag  标识是like还是dislike，只能为"like"或"dislike"
     * @return 处理后得到的likeCount
     */
    @Override
    public int getLikeCount(String newsId, User user, String flag) {
        Jedis jedisFromPool = JedisUtils.getJedisFromPool();
        // 使用news_like_{newsId}作为Redis中like set的key
        String likeKey = "news_like_" + newsId;
        // 使用news_dislike_{newsId}作为Redis中dislike set的key
        String dislikeKey = "news_dislike_" + newsId;
        Set<String> likeMember = jedisFromPool.smembers(likeKey);
        Set<String> dislikeMember = jedisFromPool.smembers(dislikeKey);
        int likeCount = likeMember.size() - dislikeMember.size();
        String toKey = null;
        String opKey = null;
        int factor = 0;
        if ("like".equals(flag)) {
            opKey = dislikeKey;
            toKey = likeKey;
            factor = 1;
        } else if ("dislike".equals(flag)){
            opKey = likeKey;
            toKey = dislikeKey;
            factor = -1;
        }
        if (jedisFromPool.sadd(toKey, ""+user.getId()) == 1) {
            likeCount = likeCount + factor;
            if (newsService.updateNewsLikeCountById(Integer.parseInt(newsId), likeCount)) {
                LoggerFactory.getLogger(this.getClass()).info("更新成功");
            }
        } else if (jedisFromPool.sismember(opKey, user.getId()+"")) {
            // 这样的话就是两张表都有，就删除吧
            jedisFromPool.srem(opKey, user.getId()+"");
            // jedisFromPool.srem(toKey, user.getId()+"");
            // 然后再进行添加（这样就能添加了）
            // jedisFromPool.sadd(opKey, user.getId()+"");
            // 其实有种想法就是只删除一个就好呢

            // 致命错误，未做likeCount以及MySQL的更新
            likeCount = likeCount + factor;
            if (newsService.updateNewsLikeCountById(Integer.parseInt(newsId), likeCount)) {
                LoggerFactory.getLogger(this.getClass()).info("更新成功");
            }
        }
        jedisFromPool.close();
        return likeCount;
    }

    @Override
    public int userLikeNews(String newsId, User user) {
        Jedis jedisFromPool = JedisUtils.getJedisFromPool();
        Set<String> likeMembers = jedisFromPool.smembers("news_like_" + newsId);
        Set<String> dislikeMembers = jedisFromPool.smembers("news_dislike_" + newsId);
        boolean likeFlag = likeMembers.contains(user.getId()+"");
        boolean dislikeFlag = dislikeMembers.contains(user.getId()+"");
        try {
            // 点了like又点了dislike（这个判断要先放在前面）
            if (likeFlag && dislikeFlag) {
                return 0;
            } else if (dislikeFlag) {
                return -1;
            } else if (likeFlag) {
                return 1;
            } else {
                return 0;
            }
        } finally {
            jedisFromPool.close();
        }
    }
}
