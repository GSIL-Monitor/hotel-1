//package com.fangcang.common.config;
//
//import com.fangcang.common.ResponseDTO;
//import com.fangcang.common.enums.ErrorCodeEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by Vinney on 2018/6/8.
// */
//@Service
//public class ApolloAuthenticationFailureHandler implements AuthenticationFailureHandler{
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        PrintWriter out = response.getWriter();
//        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_FAILED);
//        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
//            responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_USERNAME_OR_PASSWORD_ERROR);
//        } else if (e instanceof DisabledException) {
//            responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_USERNAME_LOCKED);
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String result = objectMapper.writeValueAsString(responseDTO);
//        out.write(result);
//        out.flush();
//        out.close();
//    }
//}
