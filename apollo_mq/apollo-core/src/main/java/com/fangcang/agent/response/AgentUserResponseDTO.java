package com.fangcang.agent.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:47
 * @Description: 单个用户响应DTO
 */
@Data
public class AgentUserResponseDTO implements Serializable {

    private static final long serialVersionUID = 3117696110568065338L;

    /**
     * 分销商ID
     */
    private Long agentId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String realName;

    /**
     * 是否有效
     */
    private Integer isActive;
}
