package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BookRequestDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long supplyRequestId;

    private String supplyOrderCode;

    private Integer isGroupon;

    private Long hotelId;

    private String hotelName;

    private Long roomtypeId;

    private String roomtypeName;

    private Long rateplanId;

    private String rateplanName;

    private Date checkInDate;

    private Date checkOutDate;

    private Integer roomNum;

    private String guest;

    private String guestAttachUrl;

    private BigDecimal orderSum;

    private String currency;

    private String specialRequirement;

    private String supplyCode;

    private String supplyName;

    private String merchantCode;

    private String merchantName;

    private String merchantRemark;

    private Integer balanceMethod;

    private String guide;

    private String guidePhone;

    private String operator;

    public List<BookRequestItemDTO> itemDTOList;
}
