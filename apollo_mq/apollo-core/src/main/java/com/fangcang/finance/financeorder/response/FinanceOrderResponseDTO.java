package com.fangcang.finance.financeorder.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Vinney on 2018/7/10.
 */
@Data
public class FinanceOrderResponseDTO implements Serializable {

    /**
     * 工单ID
     */
    private Long financeOrderId;

    /**
     * 工单号
     */
    private String financeCode;
    /**
     *工单类型
     */
    private Integer financeType;

    /**
     * 工单状态
     */
    private Integer financeStatus;

    /**
     * 业务单号
     */
    private String orderCode;

    /**
     * 通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 币种
     */
    private String currency;

}
