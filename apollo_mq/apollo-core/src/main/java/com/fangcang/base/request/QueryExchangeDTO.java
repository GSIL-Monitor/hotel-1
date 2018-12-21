package com.fangcang.base.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryExchangeDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String merchantCode;

    private String sourceCurrency;

    private String targetCurrency;
}
