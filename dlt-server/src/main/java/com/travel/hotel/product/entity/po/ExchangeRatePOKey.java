package com.travel.hotel.product.entity.po;

import lombok.Data;

@Data
public class ExchangeRatePOKey {
    private String sourceCurrency;

    private String targetCurrency;

    public ExchangeRatePOKey() {
    }

    public ExchangeRatePOKey(String sourceCurrency, String targetCurrency) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }
}