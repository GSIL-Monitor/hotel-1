package com.fangcang.base.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateExchangeDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    private String merchantCode;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal rate;

    private String operator;
}
