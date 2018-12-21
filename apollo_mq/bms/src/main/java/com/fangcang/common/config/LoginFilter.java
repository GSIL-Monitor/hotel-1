//package com.fangcang.common.config;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by Vinney on 2018/6/8.
// */
//@Service
//public class LoginFilter extends GenericFilterBean {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(new ApolloHttpServletRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
//    }
//}
