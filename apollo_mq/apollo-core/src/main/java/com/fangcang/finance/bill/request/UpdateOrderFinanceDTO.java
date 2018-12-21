package com.fangcang.finance.bill.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderFinanceDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String orderCode;

    private Integer accountStatus;

    private Integer settlementStatus;

    private BigDecimal settlementAmount;
}
