package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class OrderCancelRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3658746802692537976L;

    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 退改费
     */
    private BigDecimal changeFee;
    /**
     * 取消原因
     */
    private String cancelReason;

}
