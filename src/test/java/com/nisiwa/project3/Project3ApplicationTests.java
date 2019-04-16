package com.nisiwa.project3;

import com.nisiwa.project3.VO.VO;
import com.nisiwa.project3.service.NewsService;
import com.nisiwa.project3.utils.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Project3ApplicationTests {
    @Autowired
    NewsService newsService;


    @Test
    public void contextLoads() {
        List<VO> vos = newsService.queryAll();
        for (VO vo : vos) {
            System.out.println(vo);
        }
    }

    @Test
    public void testSalt() {
        String password = "123123";
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        LoggerFactory.getLogger(this.getClass()).info("salt:" + salt);
        String md5 = Md5Util.getMD5(salt + password);
        LoggerFactory.getLogger(this.getClass()).info("md5:" + md5);
    }

    @Test
    public void testRedis() {
        JedisPool jedisPool = new JedisPool();
        Jedis resource = jedisPool.getResource();

        resource.set("key", "value");
        String ip = resource.get("key");
        LoggerFactory.getLogger(this.getClass()).info("--------ip:" + ip);

    }

}
