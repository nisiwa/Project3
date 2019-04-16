package com.nisiwa.project3.VO;

import com.nisiwa.project3.bean.News;
import com.nisiwa.project3.bean.User;

/**
 * @author: nisiwa
 * @date: 2019-04-10 16:58
 */
public class VO {
    News news;
    User user;
    int like;

    public VO() {
    }

    public VO(News news, User user, int like) {
        this.news = news;
        this.user = user;
        this.like = like;
    }


    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "VO{" +
                "news=" + news +
                ", user=" + user +
                ", like=" + like +
                '}';
    }
}
