package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class LockOrderRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -5005377526226085849L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 加解锁类型，1：加锁，2：解锁
     */
    @NotNull
    private Integer lockType;

    /**
     * 操作人全名
     */
    private String operator;

}
