package com.fangcang.agent.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fangcang.common.BaseDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class GetCreditLinesListResponseDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -8598094737211529410L;

    /**
     * 分销商ID
     */
    private String agentId;
    /**
     * 客户名称
     */
    private String agentName;

    /**
     * 客户编码
     */
    private String agentCode;

    /**
     * 客户联系人账号
     */
    private String agentUserName;

    /**
     * 客户联系人姓名
     */
    private String agentRealName;

    /**
     * 客户联系电话
     */
    private String phone;

    /**
     * 我方负责人
     */
    private String username;
    
    private String realName;

    /**
     * 总额度
     */
    private BigDecimal amount;
    
    /**
     * 剩余额度
     */
    private BigDecimal remainingAmount;
    
    /**
     * 是否有效
     */
    private Integer isActive;
}
