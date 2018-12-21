//package com.fangcang.common.config;
//
//import com.fangcang.common.ResponseDTO;
//import com.fangcang.common.constant.Constant;
//import com.fangcang.common.enums.ErrorCodeEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by Vinney on 2018/6/13.
// */
//@Service
//public class ApolloLogoutHandler implements LogoutHandler {
//    @Override
//    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletRequest.getSession().removeAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_MERCHANTCODE);
//        httpServletRequest.getSession().removeAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER);
//
//        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        PrintWriter out = null;
//        try {
//            out = httpServletResponse.getWriter();
//            ObjectMapper objectMapper = new ObjectMapper();
//            String s = objectMapper.writeValueAsString(responseDTO);
//            out.write(s);
//            out.flush();
//        } catch (IOException e) {
//
//        } finally {
//            if (out != null){
//                out.close();
//            }
//        }
//    }
//}
