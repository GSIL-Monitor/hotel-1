package com.fangcang.finance.financeorder.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Vinney on 2018/7/10.
 */
@Data
public class FinanceOrderRequestDTO extends BaseDTO implements Serializable {

    /**
     * 工单ID
     */
    @NotNull(message = "工单ID不能为空")
    private Integer financeOrderId;

    @NotNull(message = "工单类型不能空")
    private Integer financeType;


    /**
     * 工单号
     */
    private String financeCode;

    /**
     * 通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 操作人
     */
    private String operator;

    @NotNull(message = "订单/账单编号不能为空")
    private String orderCode;

}
