package com.fangcang.agent.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 01:10
 * @Description: 保存分销商响应DTO
 */
@Data
public class AddAgentResponseDTO implements Serializable {


    private static final long serialVersionUID = -2370242365234055410L;

    /**
     * 新增时返回分销商ID，编辑是没有
     */
    private Long agentId;

    private String agentCode;

    private String agentName;
}
