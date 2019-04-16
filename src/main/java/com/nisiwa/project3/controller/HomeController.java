package com.nisiwa.project3.controller;

import com.nisiwa.project3.VO.VO;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.LikeService;
import com.nisiwa.project3.service.NewsService;
import com.nisiwa.project3.service.UserService;
import com.nisiwa.project3.utils.Md5Util;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: nisiwa
 * @date: 2019-04-10 16:34
 */
@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    LikeService likeService;

    @RequestMapping("login")
    @ResponseBody
    public HashMap login(String username, String password, boolean rember, Model model, HttpServletRequest request) {
        HashMap<Object, Object> map = new HashMap<>();
        LoggerFactory.getLogger(this.getClass()).info("username:"+username + "password:"+password+"rember"+rember);

        User user = userService.selectByName(username);
        String saltPwd = Md5Util.getMD5(user.getSalt() + password);
        if (user.getPassword().equals(saltPwd)) {
            map.put("code", 0);
            map.put("msgpwd", "登录成功");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return map;
        }
        map.put("code", 1);
        map.put("msgwd", "登录失败");
        return map;
    }

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<VO> vos = newsService.queryAll();
        if (user != null) {
            model.addAttribute("user", user);
            for (VO vo : vos) {
                String newsId = vo.getNews().getId().toString();
                // User voUser = vo.getUser();
                // int likeFlag = likeService.userLikeNews(newsId, voUser);
                int likeFlag = likeService.userLikeNews(newsId, user);
                vo.setLike(likeFlag);
            }
        } else {
            model.addAttribute("pop", 0);
        }
        model.addAttribute("cur_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        model.addAttribute("vos", vos);

        return "home";
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping("reg")
    @ResponseBody
    public HashMap register(String username, String password, HttpServletRequest request) {
        HashMap<Object, Object> map = new HashMap<>();

        if (userService.selectByName(username) != null) {
            map.put("code", 1);
            map.put("msgname", "用户名已被注册");
            return map;
        }

        User user = new User();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String pwdSalt = Md5Util.getMD5(salt + password);
        user.setName(username);
        user.setPassword(pwdSalt);
        user.setSalt(salt);
        user.setHeadUrl("http://images.nowcoder.com/head/" + (int) Math.random() * 1000 + "t.png");

        HttpSession session = request.getSession();

        if (userService.addUser(user)) {
            map.put("code", 0);
            LoggerFactory.getLogger(this.getClass()).info("user:" + user);
            session.setAttribute("user", user);
        }
        return map;
    }

    @RequestMapping("user/{userId}")
    public String userInfo(@PathVariable int userId, Model model) {
        User user = userService.selectUserById(userId);
        model.addAttribute("user", user);
        return "personal";
    }


}
