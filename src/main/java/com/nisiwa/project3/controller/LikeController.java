package com.nisiwa.project3.controller;

import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: nisiwa
 * @date: 2019-04-12 17:55
 */
@Controller
public class LikeController {
    @Autowired
    LikeService likeService;

    /**
     *
     * @param newsId 代表news的主键
     * @return JSon形式的返回值，code表示状态，msg为点赞数
     */
    @RequestMapping("like")
    @ResponseBody
    public HashMap like(String newsId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", likeService.getLikeCount(newsId, user, "like"));
        return map;
    }


    /**
     *
     * @param newsId 代表news的主键
     * @return JSon形式的返回值，code表示状态，msg为点赞数
     */
    @RequestMapping("dislike")
    @ResponseBody
    public HashMap dislike(String newsId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", likeService.getLikeCount(newsId, user, "dislike"));
        return map;
    }



}
