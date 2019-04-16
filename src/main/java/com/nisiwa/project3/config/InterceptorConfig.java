package com.nisiwa.project3.config;

import com.nisiwa.project3.web.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: nisiwa
 * @date: 2019-04-10 19:40
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new WebInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/fonts/**");
        registration.excludePathPatterns("/images/**");
        registration.excludePathPatterns("/scripts/**");
        registration.excludePathPatterns("/styles/**");
    }
}
