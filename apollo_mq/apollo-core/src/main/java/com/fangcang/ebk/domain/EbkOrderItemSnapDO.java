package com.fangcang.ebk.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_ebk_order_item_snap")
@Data
@EqualsAndHashCode(callSuper = false)
public class EbkOrderItemSnapDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long ebkOrderSnapId;

    private Integer itemType;

    private String itemName;

    private Long roomtypeId;

    private String roomtypeName;

    private Long rateplanId;

    private String rateplanName;

    private Integer bedType;

    private Integer breakfastType;

    private Date checkInDate;

    private Date checkOutDate;

    private Integer nightNum;

    private BigDecimal roomNum;

    private BigDecimal salePrice;
}
