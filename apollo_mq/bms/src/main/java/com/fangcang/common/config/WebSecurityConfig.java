//package com.fangcang.common.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsUtils;
//
////import com.fangcang.merchant.service.impl.ApolloAuthenticationProvider;
//
////import com.fangcang.merchant.service.impl.ApolloAuthenticationProvider;
//
////import com.fangcang.merchant.service.impl.ApolloAuthenticationProvider;
//
///**
// * Created by Vinney on 2018/6/4.
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private ApolloFilterSecurityInterceptor apolloFilterSecurityInterceptor;
//
//    @Autowired
//    private ApolloUserDetailsService apolloUserDetailsService;
//
//    @Autowired
//    private ApolloAuthenticationSuccessHandler apolloAuthenticationSuccessHandler;
//
//    @Autowired
//    private ApolloAuthenticationFailureHandler apolloAuthenticationFailureHandler;
//
//    @Autowired
//    private ApolloLogoutHandler apolloLogoutHandler;
//
//    @Autowired
//    private LoginFilter loginFilter;
//
//    @Autowired
////    private ApolloAuthenticationProvider apolloAuthenticationProvider;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(apolloUserDetailsService).passwordEncoder(new ApolloPasswordEncoder()); //user Details Service验证
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling().authenticationEntryPoint(new ApolloLoginUrlAuthenticationEntryPoint(""));
//
//        //对接系统访问这2个URL不需要权限控制。
////        http.antMatcher("/otaOrder/create").anonymous().and().antMatcher("/otaOrder/cancel").anonymous();
//
//        http.csrf().disable().authorizeRequests()
////                .antMatchers("/otaOrder/create","/otaOrder/cancel").permitAll()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()//就是这一行啦
//                .anyRequest().authenticated() //任何请求,登录后可以访问
//
//                .and().formLogin().loginPage("/apolloLogin").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
//                .permitAll()
//                .successHandler(apolloAuthenticationSuccessHandler)
//                .failureHandler(apolloAuthenticationFailureHandler)
//                .and().logout().addLogoutHandler(apolloLogoutHandler).permitAll()//注销行为任意访问
//                ;
////                .and().sessionManagement().invalidSessionUrl("/apolloLogin2").maximumSessions(1).maxSessionsPreventsLogin(false)
////                .expiredUrl("/apolloLogin2");
//
//        //在UsernamePasswordAuthenticationFilter之前加入loginFilter
//        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(apolloFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//    }
//}
