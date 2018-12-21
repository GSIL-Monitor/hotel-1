package com.fangcang.agent.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description:    分销商拜访记录管理
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/6/2 16:11
*/
@Data
public class AgentVisitDO {
    private Long agentVisitId;

    private Long agentId;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 拜访人
     */
    private String visitor;

    /**
     * 拜访时间
     */
    private Date visitTime;

    /**
     * 拜访内容
     */
    private String visitContent;

    private Date createTime;

    private String creator;

    private String agentName;
}