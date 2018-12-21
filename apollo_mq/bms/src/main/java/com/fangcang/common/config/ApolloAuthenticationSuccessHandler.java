//package com.fangcang.common.config;
//
//import com.alibaba.fastjson.JSON;
//import com.fangcang.common.ResponseDTO;
//import com.fangcang.common.constant.Constant;
//import com.fangcang.common.enums.ErrorCodeEnum;
//import com.fangcang.merchant.dto.UserDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by Vinney on 2018/6/4.
// */
//@Service
//@Slf4j
//public class ApolloAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//
//        log.info("authentication="+ JSON.toJSONString(authentication));
//        log.info("Principal="+ JSON.toJSONString(authentication.getPrincipal()));
//        ApolloUserDetails user = (ApolloUserDetails)authentication.getPrincipal();
//        //System.out.println(new Date()+",ip="+getIpAddress(request)+";用户名："+user.getUsername()+" 登录成功！");
//        //session 中存放用户信息
//        this.setAttributeToSession(request,user);
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        //保存登录日志
//        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
//        responseDTO.setModel(user);
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String s = objectMapper.writeValueAsString(responseDTO);
//        out.write(s);
//        out.flush();
//        out.close();
//    }
//
//    public void setAttributeToSession(HttpServletRequest request,UserDTO user){
//        request.getSession().setAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER,user);
//        request.getSession().setAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_MERCHANTCODE,user.getMerchantCode());
//    }
//
//    public String getIpAddress(HttpServletRequest request){
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//}
