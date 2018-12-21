package com.fangcang.agent.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 01:18
 * @Description: 分销商信息DTO
 */
@Data
public class AgentInfoDTO implements Serializable {

    private static final long serialVersionUID = -6064995337661946960L;

    private Long agentId;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 联系人姓名
     */
    private String realName;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 我司产品经理名称
     */
    private Long merchantPM;

    /**
     * 1-常用；0-不常用
     */
    private Integer frequentlyUse;

    /**
     * 1-启用；0-停用
     */
    private Integer isActive;
}
