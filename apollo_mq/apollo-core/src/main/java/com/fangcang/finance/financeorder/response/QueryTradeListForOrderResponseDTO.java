package com.fangcang.finance.financeorder.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Vinney on 2018/7/19.
 */
@Data
public class QueryTradeListForOrderResponseDTO extends BaseDTO {
    /**
     * 财务工单号
     */
    private String financeCode;
    /**
     * 通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 币种
     */
    private String currency;

    /**
     *  工单状态
     */
    private String financeStatus;

    /**
     * 支付方式：多个方式合并成一行
     */
    private String passage;

    /**
     * 备注：多个备注合并到一起展示
     */
    private String note;

    /**
     * 通知类型 financeTypeEnum
     */
    private String financeType;
}
