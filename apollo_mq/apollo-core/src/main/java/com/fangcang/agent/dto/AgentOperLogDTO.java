package com.fangcang.agent.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class AgentOperLogDTO implements Serializable {

    private static final long serialVersionUID = -8374649153207476654L;
    
    private Long agentOperLogId;

    /*
     * 分销商ID
     */
    private Long operAgentId;
    
    /*
     * 分销商用户ID
     */
    private Long operAgentUserId;
    
    /*
     * 日志描述
     */
    private String content;
    
    /*
     * 操作者
     */
    private String creator;
    
    /*
     * 操作时间
     */
    private Date createTime;
}
