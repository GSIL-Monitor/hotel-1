package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SupplyOrderDeductCreditDTO implements Serializable {
    private static final long serialVersionUID = 5382928139944571686L;

    private BigDecimal deductAmount;
}
