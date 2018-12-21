package com.fangcang.agent.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 01:16
 * @Description: 查询分销商列表响应DTO
 */
@Data
public class AgentListResponseDTO implements Serializable {

    private static final long serialVersionUID = 7631354659141335312L;

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
     * 我司业务经理ID
     */
    private Long merchantBM;

    /**
     * 我司产品经理名称
     */
    private String merchantBMName;

    /**
     * 1-常用；0-不常用
     */
    private Integer frequentlyUse;

    /**
     * 1-启用；0-停用
     */
    private Integer isActive;

    /**
     * 总额度
     */
    private BigDecimal amount;

    /**
     * 已用额度
     */
    private BigDecimal usedAmount;

    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;

    /**
     * 剩余额度
     */
    private BigDecimal remainingAmount;

    /**
     * 客户等级(1 一般 2白银 3 黄金 4 铂金 5 钻石)
     */
    private Integer customerLevel;

    /**
     * 结算方式(MONTH(1, "月结"), HALFMONTH(2, "半月结"), WEEK(3, "周结"), SINGLE(4, "单结"), DAY(5, "日结");)
     */
    private Integer billingMethod;
}
