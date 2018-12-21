package com.fangcang.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.InitData;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.util.StringUtil;
import com.fangcang.merchant.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 2018/6/9.
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Collection<String> needNotLoginUrlList = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        log.debug("servletPath:" + servletPath);

        if (null == needNotLoginUrlList){
            initNeedNotLogin();
        }

        //如果是无需登录的则直接访问即可
        if(needNotLoginUrlList.contains(servletPath)){
            return true;
        }

        //创建初始化控制器
        BaseController bc = null;
        if (handler instanceof HandlerMethod) {
            Object bean=((HandlerMethod) handler).getBean();
            if (bean instanceof BasicErrorController){
                return super.preHandle(request, response, handler);
            }else{
                bc = (BaseController) ((HandlerMethod) handler).getBean();
            }
        } else {
            return super.preHandle(request, response, handler);
        }

        UserDTO userDTO = bc.getCacheUser();
        if(userDTO==null){
            ResponseDTO res = new ResponseDTO(ErrorCodeEnum.LOGIN_ERROR_NOT_LOGIN);

            String type = request.getHeader("x-requested-with");// XMLHttpRequest
            if ("XMLHttpRequest".equalsIgnoreCase(type)
                    || (StringUtil.isValidString(request.getContentType()) && request.getContentType().indexOf("json")>0)) {// ajax请求
                // 指定允许其他域名访问
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(res));
            }
            return false;
        }

        if (InitData.NEED_CHECK_URL_LIST.contains(request.getServletPath())){
            log.info("needCheckUrl:"+InitData.NEED_CHECK_URL_LIST);
            if (!checkAuthority(servletPath,userDTO)) {
                ResponseDTO res = new ResponseDTO(ErrorCodeEnum.LOGIN_ERROR_HAS_NO_RIGHT);

                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(res));
                return false;
            }
        }
        return super.preHandle(request, response, handler);

    }

    private void initNeedNotLogin() {
        needNotLoginUrlList = new ArrayList<>();
        needNotLoginUrlList.add("/otaOrder/create");
        needNotLoginUrlList.add("/otaOrder/cancel");
        needNotLoginUrlList.add("/otaOrder/orderInfo");
        needNotLoginUrlList.add("/otaOrder/addOrderRequest");
        needNotLoginUrlList.add("/otaOrder/changeChannelOrderCode");
        needNotLoginUrlList.add("/login");
    }

    /**
     * @return false:没有权限，true：有权限
     */
    private boolean checkAuthority(String url,UserDTO userDTO) {
        log.info("当前登录url:"+url);
        log.info("当前用户拥有的菜单权限："+userDTO.getResourceList());
        //当前请求非登录，且是在需要校验的URL中，且此用户有权限访问当前的URL，则通过校验。
        return (!url.equals("/login") && null != userDTO && CollectionUtils.isNotEmpty(userDTO.getResourceList()) && userDTO.getResourceList().contains(url));
    }
}
