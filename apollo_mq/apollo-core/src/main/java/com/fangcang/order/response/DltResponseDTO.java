package com.fangcang.order.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhanwang
 */
@Getter
@Setter
@ToString
public class DltResponseDTO implements Serializable {

    private static final long serialVersionUID = -4625256735807023612L;
    /**
     * 结果(1：成功 0：失败)
     */
    protected Integer isSuccess;

    /**
     * 失败原因
     */
    protected String failureReason;

    /**
     * 订单号
     */
    protected String orderCode;

    /**
     * 订单状态
     */
    protected String orderState;


}
