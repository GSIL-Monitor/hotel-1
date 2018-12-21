package com.fangcang.b2b.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 10:02
 * @Description: B2B登录
 */
@Data
public class AgentLoginDTO implements Serializable {

    private static final long serialVersionUID = -4144726586490907122L;

    /**
     * 账号
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;
}
