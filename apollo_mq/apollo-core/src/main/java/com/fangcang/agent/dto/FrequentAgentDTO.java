package com.fangcang.agent.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 20:11
 * @Description:常用分销商
 */
@Data
public class FrequentAgentDTO implements Serializable {


    private static final long serialVersionUID = 3616646019956999198L;
    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 币种
     */
    private String currency;

    /**
     * 分销商id
     */
    private Long agentId;

}
