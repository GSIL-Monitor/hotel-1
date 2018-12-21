package com.travel.common.dto.order.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author  2018/1/10
 */
@Data
public class PreBookingResponseDTO implements Serializable {

    private static final long serialVersionUID = -1212763191724590339L;

    private Long hotelId;

    private Long roomTypeId;

    private Long pricePlanId;

    private String pricePlanName;

    private String channelCode;

    private Integer payMethod;

    private String saleCurrency;

    private BigDecimal totalSalePrice;

    private String baseCurrency;

    private BigDecimal totalBasePrice;

    private BigDecimal rate;

    private Integer canBooking;

    private Integer isImmediateConfirm;

    private List<PreBookingDayItemDTO> preBookingDayItemDTOList;

}

