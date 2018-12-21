package com.fangcang.agent.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/4 11:01
 * @Description: 分销商主账号(master=1)信息
 */
@Data
public class MasterUserInfoDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3656167881394778107L;

    private Long agentUserId;

    private Long agentId;

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
     * 主账号master值一定为1
     */
    private Integer master;

}
