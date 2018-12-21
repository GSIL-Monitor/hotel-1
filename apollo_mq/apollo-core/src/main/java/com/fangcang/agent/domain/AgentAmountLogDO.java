package com.fangcang.agent.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
* @Description:    分销商额度调整日志DO
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/6/8 17:07
*/
@Data
public class AgentAmountLogDO {

    private Long id;

    /**
     * 对应的分销商ID
     */
    private Long agentId;

    /**
     * 修改前额度
     */
    private BigDecimal amount;

    /**
     * 修改后的额度
     */
    private BigDecimal amoutMotified;

    private String creator;

    private Date createTime;
}