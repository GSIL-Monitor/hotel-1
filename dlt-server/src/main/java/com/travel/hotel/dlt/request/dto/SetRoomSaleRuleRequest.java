package com.travel.hotel.dlt.request.dto;

import com.travel.hotel.dlt.request.base.BaseRequest;
import com.travel.hotel.dlt.request.base.Requestor;
import lombok.Data;

import java.util.List;

/**
 * 设置售卖规则-响应实体--请求实体
 */
@Data
public class SetRoomSaleRuleRequest extends BaseRequest {

    private Requestor requestor;
    private Integer supplierId;
    private Long hotelId;
    private List<RoomType> roomTypeList;
    private Boolean setCtripDailyRule;
    private List<SaleRule> saleRuleList;
}
