package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookRequestItemPriceDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Date saleDate;

    private BigDecimal roomNum;

    private BigDecimal salePrice;
}
