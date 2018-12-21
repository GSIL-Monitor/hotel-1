package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:50
 * @Description: 单个用户请求DTO
 */
@Data
public class AgentUserRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 6950403642139735775L;
    /**
     * 分销商ID
     */
    @NotNull(message = "分销商id不能为null")
    private Long agentId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户名不能为null")
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
     * 商家id
     */
    private Long merchantId;

    /**
     * 电话号码
     */
    private String phone;
}
