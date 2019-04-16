package com.nisiwa.project3.service;

import com.nisiwa.project3.bean.User;

public interface LikeService {
    int getLikeCount(String newsId, User user, String flag);

    int userLikeNews(String newsId, User user);
}
