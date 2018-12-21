package com.fangcang.common.config;

import com.fangcang.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
//public class LoginInterceptroConfigurer extends WebMvcConfigurationSupport {
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/product/**").excludePathPatterns("/login/**");
//        super.addInterceptors(registry);
//    }
//}