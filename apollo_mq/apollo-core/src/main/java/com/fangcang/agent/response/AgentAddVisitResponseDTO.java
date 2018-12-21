package com.fangcang.agent.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:58
 * @Description: 分销商添加拜访记录响应DTO
 */
@Data
public class AgentAddVisitResponseDTO implements Serializable {

    private static final long serialVersionUID = 1919747315313916853L;

    /**
     * 拜访记录的ID
     */
    private Long agentVisitId;
}
