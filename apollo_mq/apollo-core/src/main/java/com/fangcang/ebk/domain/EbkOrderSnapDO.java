package com.fangcang.ebk.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_ebk_order_snap")
@Data
@EqualsAndHashCode(callSuper = false)
public class EbkOrderSnapDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long ebkRequestId;

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
}
