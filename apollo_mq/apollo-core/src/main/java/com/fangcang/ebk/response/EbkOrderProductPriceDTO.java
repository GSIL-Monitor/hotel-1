package com.fangcang.ebk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbkOrderProductPriceDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    private Long orderItemId;

    private String saleDate;

    private BigDecimal roomNum;

    private BigDecimal salePrice;
}
