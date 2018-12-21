package com.fangcang.supplier.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 15:53
 * @Description: 公司账号信息
 */
@Data
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 3559189483383269388L;

    /**
     * 用户ID
     */
    private Long supplyUserId;

    /**
     * 账号
     */
    private String userName;

    /**
     *
     密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 0-停用；1-启用
     */
    private Integer isActive;

    /**
     * 电话号码
     */
    private String phone;
}
