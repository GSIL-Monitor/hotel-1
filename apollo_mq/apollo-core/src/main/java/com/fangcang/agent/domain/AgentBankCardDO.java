package com.fangcang.agent.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description:    分销商银行卡信息
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/29 13:04
*/
@Data
public class AgentBankCardDO {
    
    private Long bankCardId;

    /**
     * 开户行
     */
    private String openingBank;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 账号
     */
    private String accountNumber;

    private Long agentId;

    private Date createTime;

    private String creator;

    private String modifier;

    private Date modifyTime;

}