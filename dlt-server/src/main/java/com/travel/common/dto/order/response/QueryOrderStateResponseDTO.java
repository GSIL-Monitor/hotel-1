package com.travel.common.dto.order.response;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author  2018/1/10
 */
@Data
public class QueryOrderStateResponseDTO implements Serializable {

    private static final long serialVersionUID = -2753446356433985873L;

    private String orderCode;

    private String orderState;

    private String channelCode;

    private String customerOrderCode;

    private String channelState;

}
