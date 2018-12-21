//package com.fangcang.common.config;
//
//import com.fangcang.merchant.dto.UserDTO;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by Vinney on 2018/6/14.
// */
//public class ApolloUserDetails extends UserDTO implements Serializable,UserDetails {
//
//    private List<? extends GrantedAuthority> authorities;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<? extends GrantedAuthority> authorities){
//        this.authorities = authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
