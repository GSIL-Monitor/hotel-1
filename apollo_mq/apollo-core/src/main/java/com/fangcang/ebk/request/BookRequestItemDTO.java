package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BookRequestItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 客房1，附加项2，减免政策3
     */
    private Integer itemType;

    private String itemName;

    private Long roomtypeId;

    private String roomtypeName;

    private Long rateplanId;

    private String rateplanName;

    private String bedType;

    private Integer breakfastType;

    private Date checkInDate;

    private Date checkOutDate;

    private Integer nightNum;

    private BigDecimal roomNum;

    private BigDecimal salePrice;

    private List<BookRequestItemPriceDTO> itemPriceDTOList;
}
