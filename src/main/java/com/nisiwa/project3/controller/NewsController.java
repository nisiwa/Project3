package com.nisiwa.project3.controller;

import com.nisiwa.project3.VO.CommentVo;
import com.nisiwa.project3.bean.News;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.CommentService;
import com.nisiwa.project3.service.NewsService;
import com.nisiwa.project3.service.UploadPhoto2OOS;
import com.nisiwa.project3.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-11 17:15
 */
@Controller
public class NewsController {
    @Autowired
    UploadPhoto2OOS uploadPhoto2OOS;

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping({"uploadImage", "news/uploadImage"})
    @ResponseBody
    public HashMap uploadImage(MultipartFile file) {
        LoggerFactory.getLogger(this.getClass()).info("---------------" + file.getOriginalFilename());
        HashMap<Object, Object> map = new HashMap<>();
        String fileUrl = null;
        try {
            fileUrl = uploadPhoto2OOS.uploadPhoto(file);
            map.put("code", 0);
            map.put("msg", fileUrl);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("code", 1);
        map.put("msg", "上传失败");
        return map;
    }


    @RequestMapping("/user/addNews")
    @ResponseBody
    public HashMap addNews(String image, String title, String link, HttpServletRequest request) {
        HashMap<Object, Object> map = new HashMap<>();
        LoggerFactory.getLogger(this.getClass()).info("---------------image:" + image + "title:" + title + "link:" + link) ;
        News news = new News();
        User user = (User) request.getSession().getAttribute("user");
        news.setImage(image);
        news.setUserId(user.getId());
        news.setLink(link);
        news.setLikeCount(0);
        news.setCommentCount(0);
        news.setCreatedDate(new Date());
        news.setTitle(title);
        if (newsService.insert(news)) {
            map.put("code", 0);
        }
        return map;
    }

    @RequestMapping("news/{newsId}")
    public String getNews(@PathVariable int newsId, Model model, HttpServletRequest request) {
        News news = newsService.selectNewsById(newsId);
        User user = userService.selectUserById(news.getUserId());
        model.addAttribute("news", news);
        model.addAttribute("like", news.getLikeCount());
        model.addAttribute("owner", user);
        List<CommentVo> commentVoList = commentService.queryCommentVosByNewsId(newsId);
//        for (CommentVo commentVo : commentVoList) {
//            LoggerFactory.getLogger(this.getClass()).info("user:" + commentVo.getUser());
//            LoggerFactory.getLogger(this.getClass()).info("comment:" + commentVo.getComment());
//        }

//        其实size为1时容器中无元素
        if (commentVoList.size() == 1) {
            commentVoList = new ArrayList<>();
        }
        model.addAttribute("comments", commentVoList);
        return "detail";
    }

}
