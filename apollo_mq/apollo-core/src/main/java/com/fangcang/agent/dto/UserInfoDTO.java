package com.fangcang.agent.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 01:07
 * @Description: 分销商登录B2B网的账号信息
 */
@Data
public class UserInfoDTO implements Serializable {


    private static final long serialVersionUID = -4406985200791517448L;

    private Long agentUserId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 是否启用 1-启用；0-停用
     */
    private Integer isActive;

    /**
     * 电话号码
     */
    private String phone;
}
