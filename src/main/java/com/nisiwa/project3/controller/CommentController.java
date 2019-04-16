package com.nisiwa.project3.controller;

import com.nisiwa.project3.bean.Comment;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.CommentService;
import com.nisiwa.project3.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author: nisiwa
 * @date: 2019-04-11 18:32
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    NewsService newsService;

//    不需要返回JSon数据，应该是返回到Detail页面
    @RequestMapping("addComment")
    public ModelAndView addComment(int newsId, String content, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setNewsId(newsId);
        comment.setUserId(user.getId());
        comment.setCreatedDate(new Date());
        // 添加评论的时候应该更新一下文章的评论数
        if (commentService.insert(comment) && newsService.updateNewsCommentCountById(newsId)) {
            modelAndView.setViewName("redirect:/news/"+newsId);
        }
        return modelAndView;
    }
}
