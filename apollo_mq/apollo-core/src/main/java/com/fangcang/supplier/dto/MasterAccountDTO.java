package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/4 10:58
 * @Description: master=1的账号信息
 */
@Data
public class MasterAccountDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -6799115502883321345L;

    /**
     * 用户ID
     */
    private Long supplyUserId;

    private Long supplyId;

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
     * 0-停用；1-启用
     */
    private Integer isActive;

    /**
     * 主账号master值一定为1
     */
    private Integer master;
}
