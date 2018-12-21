//package com.fangcang.common.config;
//
//import com.fangcang.common.constant.Constant;
//import com.fangcang.common.util.PropertyCopyUtil;
//import com.fangcang.common.util.StringUtil;
//import com.fangcang.merchant.domain.UserDO;
//import com.fangcang.merchant.dto.ResourceDTO;
//import com.fangcang.merchant.dto.RoleDTO;
//import com.fangcang.merchant.service.RoleService;
//import com.fangcang.merchant.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Vinney on 2018/6/14.
// */
//@Service
//@Slf4j
//public class ApolloUserDetailsService implements UserDetailsService {
//
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!StringUtil.isValidString(username)){
//            throw new UsernameNotFoundException(username + " is null ");
//        }
//
//        String domain = null;
//        if (username.contains(Constant.DOMAIN_AND_USER_SEPARATOR)){
//            String[] domainAndUser= username.split(Constant.DOMAIN_AND_USER_SEPARATOR_SPLIT);
//            domain = domainAndUser[0];
//            username = domainAndUser[1];
//        }
//
//        UserDO user = userService.queryUserByDomainAndUsername(domain,username);
//        log.info("domain="+domain+",username="+username);
//
//        if (user != null) {
//            List<Long> roleIdList = new ArrayList<>();
//            List<RoleDTO> roleDTOList = roleService.getRoleListByUserId(user.getUserId());
//            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
//            for (RoleDTO roleDTO : roleDTOList) {
//                if (roleDTO != null && roleDTO.getRoleCode()!=null) {
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleDTO.getRoleCode());
//                    grantedAuthorities.add(grantedAuthority);
//                    roleIdList.add(roleDTO.getRoleId());
//                }
//            }
//
//            List<ResourceDTO> resourceDTOList = roleService.queryMenuByRoleId(roleIdList);
//            Map<String,List<ResourceDTO>> menuMap =  roleService.queryMenuMap(resourceDTOList);
//
//            ApolloUserDetails apolloUserDetails = PropertyCopyUtil.transfer(user,ApolloUserDetails.class);
//            apolloUserDetails.setAuthorities(grantedAuthorities);
//            apolloUserDetails.setMenuMap(menuMap);
//            return apolloUserDetails;
//        } else {
//            throw new UsernameNotFoundException(username + " do not exist!");
//        }
//    }
//}
