//package com.fangcang.common.config;
//
//import com.fangcang.common.constant.Constant;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.util.Enumeration;
//import java.util.Map;
//
///**
// * Created by Vinney on 2018/6/8.
// */
//public class ApolloHttpServletRequestWrapper extends HttpServletRequestWrapper {
//
//    public ApolloHttpServletRequestWrapper(HttpServletRequest request){
//        super(request);
//    }
//    @Override
//    public String getParameter(String name) {
//        if (Constant.USERNAME.equals(name)){
//            return getServerName()+ Constant.DOMAIN_AND_USER_SEPARATOR + super.getParameter(name);
//        }
//        return super.getParameter(name);
//    }
//    @Override
//    public Map getParameterMap() {
//        return super.getParameterMap();
//    }
//    @Override
//    public Enumeration getParameterNames() {
//        return super.getParameterNames();
//    }
//    @Override
//    public String[] getParameterValues(String name) {
//        return super.getParameterValues(name);
//    }
//}
