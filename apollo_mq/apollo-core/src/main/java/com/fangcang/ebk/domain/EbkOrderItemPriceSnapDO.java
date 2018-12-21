package com.fangcang.ebk.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_ebk_order_item_price_snap")
@Data
@EqualsAndHashCode(callSuper = false)
public class EbkOrderItemPriceSnapDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long ebkOrderSnapId;

    private Long orderItemId;

    private Date saleDate;

    private BigDecimal roomNum;

    private BigDecimal salePrice;
}
