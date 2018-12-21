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
public class OrderFinanceCallbackRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 809722306858249809L;
    /**
     * 订单编码
     */
    @NotNull(message = "订单编码不能为空！")
    private String orderCode;

    /**
     * 累计已结算金额
     */
    @NotNull
    private BigDecimal settlementAmount;

    /**
     * 累计已退款金额
     */
    private BigDecimal refundAmount;

}
