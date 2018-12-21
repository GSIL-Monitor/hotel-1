package com.fangcang.ebk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbkOrderProductSnapDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    private Integer itemType;

    private String itemName;

    private Long roomtypeId;

    private String roomtypeName;

    private Long rateplanId;

    private String rateplanName;

    private String bedType;

    private Integer breakfastType;

    private String checkInDate;

    private String checkOutDate;

    private Integer nightNum;

    private BigDecimal roomNum;

    private BigDecimal salePrice;

    private BigDecimal salePriceSum;
}
