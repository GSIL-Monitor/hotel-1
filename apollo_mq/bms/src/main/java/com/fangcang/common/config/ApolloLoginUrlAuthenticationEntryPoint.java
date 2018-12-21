//package com.fangcang.common.config;
//
//import com.fangcang.common.ResponseDTO;
//import com.fangcang.common.enums.ErrorCodeEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by Vinney on 2018/6/14.
// */
//@Slf4j
//public class ApolloLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
//
//    public ApolloLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
//        super(loginFormUrl);
//    }
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException, ServletException {
//
//        log.error(""+request.getPathInfo(),authException);
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        PrintWriter out = response.getWriter();
//        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_NOT_LOGIN);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String result = objectMapper.writeValueAsString(responseDTO);
//        out.write(result);
//        out.flush();
//        out.close();
//
//    }
//}
