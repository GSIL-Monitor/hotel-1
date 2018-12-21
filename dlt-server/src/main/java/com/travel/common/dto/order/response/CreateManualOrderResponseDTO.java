package com.travel.common.dto.order.response;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author  2018/1/10
 */
@Data
public class CreateManualOrderResponseDTO  implements Serializable {

    private static final long serialVersionUID = 7125466375608910590L;
    private String orderCode;

}
