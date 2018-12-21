package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 10:45
 * @Description: 单个分销商相关请求DTO
 */
@Data
public class SingleAgentRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1023217579387428791L;

    /**
     * 分销商ID
     */
    private Long agentId;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 是否常用
     * 1-常用，0-不常用
     */
    private Integer frequentlyUse;

    /**
     * 是否停用
     * 1-启用；0-停用
     */
    private Integer isActive;

    /**
     * 用户ID
     */
    private Long userId;
}
