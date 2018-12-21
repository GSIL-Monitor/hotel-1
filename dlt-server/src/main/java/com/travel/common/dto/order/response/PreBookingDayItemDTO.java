package com.travel.common.dto.order.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author  2018/1/10
 */
@Data
public class PreBookingDayItemDTO implements Serializable {

    private static final long serialVersionUID = -1457399920851596114L;

    private String saleDate;

    private BigDecimal salePrice;

    private String saleCurrency;

    private BigDecimal basePrice;

    private String baseCurrency;

    private String breakfastNum;

    private String roomState;

    private Integer overDraft;

    private Integer quotaNum;

}
