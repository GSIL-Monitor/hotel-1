//package com.fangcang.common.config;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//import java.util.Iterator;
//
///**
// * Created by Vinney on 2018/5/29.
// */
//@Service
//@Slf4j
//public class ApolloSecurityAccessDecisionManager implements AccessDecisionManager {
//
//    /**
//     *
//     * @param authentication  授权的登录账号，里面有角色列表
//     * @param object  httpRequest
//     * @param configAttributes 当前的访问的URL，需要的角色列表。
//     * @throws AccessDeniedException
//     * @throws InsufficientAuthenticationException
//     */
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        if(null== configAttributes || configAttributes.size() <=0) {
//            return;
//        }
//
//        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
//        String path = request.getServerName();
//
//        log.info("authentication:"+ JSONObject.toJSONString(authentication));
//        log.info("Principal:"+ JSONObject.toJSONString(authentication.getPrincipal()));
//        ApolloUserDetails user = (ApolloUserDetails)authentication.getPrincipal();
//        log.info("登录账号:"+user.getUsername());
//
//        //TODO 如果是超级管理员，不需要校验权限，直接全部资源都能访问。
//
//        ConfigAttribute c;
//        String needRole;
//        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
//            c = iter.next();
//            needRole = c.getAttribute();
//            for(GrantedAuthority ga : authentication.getAuthorities()) {
//                if(needRole.trim().equals(ga.getAuthority())) {
////                    request.getSession().setAttribute("domain",path);
////                    request.getSession().setAttribute("merchantcode","M10000001");
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("no right");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
