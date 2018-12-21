package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 19:49
 * @Description:
 */
@Data
public class SingleUserResponseDTO implements Serializable {


    private static final long serialVersionUID = 6434488674919218018L;
    /**
     * 供应商ID
     */
    private Long supplyId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录名
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
     * 是否有效
     */
    private Integer isActive;
}
