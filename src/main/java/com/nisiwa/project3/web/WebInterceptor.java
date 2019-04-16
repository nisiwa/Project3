package com.nisiwa.project3.web;

import com.nisiwa.project3.bean.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: nisiwa
 * @date: 2019-04-10 19:27
 */
// @Component
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("I SEE YOU".equals(request.getAttribute("flag"))) {
            return true;
        }
        User user = (User) request.getSession().getAttribute("user");
        String[] split = request.getRequestURL().toString().split("/");
        String last = split[split.length - 1];
        String option = null;
        if (split.length > 4) {
            option = split[3];
        }
        if (user != null || "login".equals(last) || "reg".equals(last) || "news".equals(option)) {
            return true;
        }
        request.setAttribute("flag", "I SEE YOU");
        // 因为getRequestDispatcher("/")即localhost:8080/没在上面的if中，所以加了flag，不加的话/会一直重定向
        request.getRequestDispatcher("/").forward(request, response);
        // response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*LoggerFactory.getLogger(this.getClass()).info("-----------------into postHandle----------------");
        LoggerFactory.getLogger(this.getClass()).info("-----------------url----------------"+ request.getRequestURL());*/
        // yes!!
        if (modelAndView != null) {
            LoggerFactory.getLogger(this.getClass()).info("-----------------into postHandle----------------");
            LoggerFactory.getLogger(this.getClass()).info("-----------------url:"+ request.getRequestURL());
            modelAndView.addObject("contextPath", request.getContextPath());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}