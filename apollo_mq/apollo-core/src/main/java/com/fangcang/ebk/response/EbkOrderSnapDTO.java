package com.fangcang.ebk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbkOrderSnapDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    private String supplyOrderCode;

    private Integer isGroupon;

    private Long hotelId;

    private String hotelName;

    private Long roomtypeId;

    private String roomtypeName;

    private Long rateplanId;

    private String rateplanName;

    private String checkInDate;

    private String checkOutDate;

    private Integer nightNum;

    private Integer roomNum;

    private String guest;

    private String guestAttachUrl;

    private BigDecimal orderSum;

    private BigDecimal originalOrderSum;

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

    private String ebkRequestId;

    private String creator;

    private String createTime;

    private List<EbkOrderProductDTO> productList;

    private List<EbkOrderProductDTO> additionList;

    private List<EbkOrderProductDTO> derateList;
}
