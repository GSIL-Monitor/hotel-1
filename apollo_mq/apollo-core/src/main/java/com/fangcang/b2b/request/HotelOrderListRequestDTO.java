package com.fangcang.b2b.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class HotelOrderListRequestDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = -1474618191193158784L;

    /**
     * 订单状态 1 待付款  2 待确认 3 已确认 4 已支付  不传则查全部
     */
    private Integer orderStatus;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 分销商编码
     */
    private String agentCode;
}
