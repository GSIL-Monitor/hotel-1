package com.travel.common.dto.order.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author  2018/1/10
 */
@Data
public class CancelOrderRequestDTO implements Serializable {


    private static final long serialVersionUID = 2469627253965302360L;

    private String orderCode;

    private String customerOrderCode;

    private String channelCode;

}
