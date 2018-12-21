package com.fangcang.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.constant.InitData;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.merchant.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vinney on 2018/6/21.
 */
@Slf4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果当前请求的URL不在需要校验权限的菜单中，则不用校验权限了。
        if (request.getServletPath().equals("/login")) {
            return super.preHandle(request, response, handler);
        }

        //创建初始化控制器
//        BaseController bc = null;
//        if (handler instanceof HandlerMethod) {
//            //log.info("handler:"+handler);
//            bc = (BaseController) ((HandlerMethod) handler).getBean();
//        } else {
//            //bc = (BaseController)handler;
//            return super.preHandle(request, response, handler);
//        }
//        log.info("CacheUser1:"+bc.getCacheUser().toString());
//        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER);
//        log.info("CacheUser2:"+userDTO.toString());

        if (InitData.NEED_CHECK_URL_LIST.contains(request.getServletPath())){
            log.info("needCheckUrl:"+InitData.NEED_CHECK_URL_LIST);
            if (!checkAuthority(request)) {
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

    /**
     * @param request
     * @return false:没有权限，true：有权限
     */
    private boolean checkAuthority(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER);
        String url = request.getServletPath();
        log.info("当前登录url:"+url);
        log.info("当前用户拥有的菜单权限："+userDTO.getResourceList());
        //当前请求非登录，且是在需要校验的URL中，且此用户有权限访问当前的URL，则通过校验。
        return (!url.equals("/login") && null != userDTO && CollectionUtils.isNotEmpty(userDTO.getResourceList()) && userDTO.getResourceList().contains(url));
    }
}