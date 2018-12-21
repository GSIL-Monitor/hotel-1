package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeChannelOrderCodeRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -5549356102446168478L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 客户订单号
     */
    private String customerOrderCode;

    /**
     * 渠道订单状态
     */
    private String channelOrderStatus;

}
