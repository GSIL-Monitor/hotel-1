package com.fangcang.common.config;

import com.fangcang.common.interceptor.AuthorityInterceptor;
import com.fangcang.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Vinney on 2018/6/21.
 */

@Configuration
public class ApolloWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/druid/**");
//        registry.addInterceptor(authorityInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
//        excludePathPatterns("/seengene/login")
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return  new LoginInterceptor();
    }

    @Bean
    public AuthorityInterceptor authorityInterceptor(){
        return new AuthorityInterceptor();
    }
}
