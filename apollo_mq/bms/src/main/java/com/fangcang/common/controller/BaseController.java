package com.fangcang.common.controller;

import com.fangcang.common.constant.Constant;
import com.fangcang.merchant.dto.UserDTO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class BaseController implements Serializable {
    private static final long serialVersionUID = 7680335961152813170L;

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取已登录用户的会员信息
     */
    public UserDTO getCacheUser() {
        if (getSession()==null || getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER)==null){
            return null;
        }
        return (UserDTO) getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER);
    }

    public String getMerchantCode(){
        if (getSession()==null || getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_MERCHANTCODE)==null){
            return null;
        }
        return (String) getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_MERCHANTCODE);
    }

    public String getFullName(){
        UserDTO cacheUser = this.getCacheUser();
        if(null != cacheUser){
            return cacheUser.getRealName() + "(" + cacheUser.getUsername() + ")";
        }
        return "";
    }
}
