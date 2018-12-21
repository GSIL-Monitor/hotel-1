package com.travel.hotel.dlt.request.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanwang
 */
@Data
public class AddOrderReqRequestDTO extends OrderRequestDTO implements Serializable {

    private static final long serialVersionUID = 5194113278198788553L;

    /**
     * 订单申请价格明细
     */
    private List<OrderRequestPriceDTO> orderRequestPriceDTOS;
}