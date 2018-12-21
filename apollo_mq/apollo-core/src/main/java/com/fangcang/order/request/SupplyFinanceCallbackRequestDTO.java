package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author zhanwang
 */
@Data
public class SupplyFinanceCallbackRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -7022499044306098764L;
    /**
     * 供货单编码
     */
    @NotNull(message = "供货单编码不能为空！")
    private String supplyOrderCode;

    /**
     * 累计已结算金额
     */
    @NotNull
    private BigDecimal settlementAmount;

    /**
     * 累计已收款金额
     */
    private BigDecimal receiptAmount;

}
