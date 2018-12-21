package com.travel.common.dto.order.response;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author  2018/1/10
 */
@Data
public class CreateOrderResponseDTO implements Serializable {

    private static final long serialVersionUID = -3383091759122377107L;

    private String orderCode;
    private Boolean isImmediateConfirm;

}
