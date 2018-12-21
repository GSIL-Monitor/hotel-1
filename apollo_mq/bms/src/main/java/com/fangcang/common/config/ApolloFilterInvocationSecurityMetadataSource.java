//package com.fangcang.common.config;
//
//import com.fangcang.merchant.dto.RoleResourceDTO;
//import com.fangcang.merchant.service.ResourceService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by Vinney on 2018/5/28.
// */
//@Service
//@Slf4j
//public class ApolloFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//    @Autowired
//    private ResourceService resourceService;
//
//    /**
//     * 所有资源列表<br/>
//     * key:资源的URL;
//     * value:能够访问此URL的角色列表
//     */
//    private HashMap<String, Collection<ConfigAttribute>> map =null;
//
//    /**
//     * 加载所有的资源。资源URL有哪些角色可以访问这个资源的URL。
//     */
//    public void loadResourceDefine(){
//        map = new HashMap<>();
//        Collection<ConfigAttribute> roleList;
//        ConfigAttribute roleConfig;
//        List<RoleResourceDTO> roleResourceDTOList = resourceService.queryRoleResourceList();
//        for (RoleResourceDTO dto : roleResourceDTOList){
//            String url = dto.getUrlPattern();
//            roleConfig = new SecurityConfig(dto.getRoleCode());
//            if (null == map.get(url)){
//                roleList = new ArrayList<>();
//                roleList.add(roleConfig);
//                map.put(url,roleList);
//            } else {
//                map.get(url).add(roleConfig);
//            }
//        }
//    }
//
//
//    /**
//     * 根据请求的URL,找到能访问此URL的角色列表
//     * @param object
//     * @return
//     * @throws IllegalArgumentException
//     */
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
//
//        //TODO 后面这里需要改下：如果数据库有更新，这里就取不到最新的。
//        if (null == map){
//            loadResourceDefine();
//        }
//
//        log.info("当前请求的URL："+request.getServletPath()+request.getPathInfo());
//
//        AntPathRequestMatcher matcher;
//        String resUrl;
//        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
//            resUrl = iter.next();
//            matcher = new AntPathRequestMatcher(resUrl);
//            /* **
//             * if的内部，是根据request中的URL，与上面的数据库中的资源resUrl匹配，看看数据库中是否有配置此URL的权限。
//             * 如果有：则取出访问此URL的角色列表
//             * 如果没有：则直接返回空。
//             */
//            if(matcher.matches(request)) {
//                log.info("当前请求的URL："+request.getServletPath()+request.getPathInfo()+"成功匹配！");
//                return map.get(resUrl);//key:资源URL,value:资源名称
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
