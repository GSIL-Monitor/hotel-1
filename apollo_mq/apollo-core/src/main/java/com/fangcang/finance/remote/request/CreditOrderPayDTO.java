package com.fangcang.finance.remote.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CreditOrderPayDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 机构编码
     */
    @NotNull(message = "orgCode不能为null")
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 金额
     */
    @NotNull
    private BigDecimal amount;

    /**
     * 操作人
     */
    private String operator;
}
