package com.fangcang.agent.dto;

import lombok.Data;

import java.io.Serializable;

/**
* @Description:    分销商银行卡信息
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/29 16:02
*/
@Data
public class AgentBankCardDTO implements Serializable {


    private static final long serialVersionUID = 7577776222182492674L;

    /**
     * 分销商Id
     */
    private Long agentId;

    /**
     * 银行卡id
     */
    private Integer bankCardId;

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
}
