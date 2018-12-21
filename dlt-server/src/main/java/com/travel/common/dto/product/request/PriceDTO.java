package com.travel.common.dto.product.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *   2018/1/24.
 */
@Data
public class PriceDTO implements Serializable {


    private static final long serialVersionUID = 3090656462511010529L;
    private String baseCurrency;
    private BigDecimal basePrice;

    private String saleBCurrency;
    private BigDecimal saleBPrice;

    private String saleCCurrency;
    private BigDecimal saleCPrice;

    private BigDecimal baseToSaleBRate;
    private BigDecimal baseToSaleCRate;
    
    private BigDecimal agentCommission;
    private BigDecimal supplyCommission;
}
