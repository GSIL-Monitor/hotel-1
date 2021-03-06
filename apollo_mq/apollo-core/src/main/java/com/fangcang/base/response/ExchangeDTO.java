package com.fangcang.base.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExchangeDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    private String merchantCode;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal rate;
}
