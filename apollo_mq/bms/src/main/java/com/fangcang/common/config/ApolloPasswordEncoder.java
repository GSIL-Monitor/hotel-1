//package com.fangcang.common.config;
//
//import com.fangcang.common.util.MD5Util;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * Created by Vinney on 2018/6/4.
// */
//public class ApolloPasswordEncoder implements PasswordEncoder {
//    @Override
//    public String encode(CharSequence charSequence) {
//        return MD5Util.encode((String)charSequence);
//    }
//
//    @Override
//    public boolean matches(CharSequence charSequence, String s) {
//        return s.equals(MD5Util.encode((String)charSequence));
//    }
//}
