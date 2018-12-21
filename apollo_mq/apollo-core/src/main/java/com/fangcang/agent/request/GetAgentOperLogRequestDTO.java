package com.fangcang.agent.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fangcang.common.BaseDTO;

import lombok.Data;

/**
 * @Auther: wen.zhong@fangcang.com
 * @Date: 2018/5/24 11:50
 * @Description: 用户日志请求DTO
 */
@Data
public class GetAgentOperLogRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 6950403642139735775L;
    /**
     * 分销商ID
     */
    @NotNull(message = "分销商id不能为null")
    private Long agentId;

    /**
     * 用户ID
     */
    @NotNull(message = "分销商用户id不能为null")
    private Long userId;
}
