package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vinney on 2018/9/13.
 */
@Data
public class OrderLockResponseDTO implements Serializable {

    private Long orderId;

    private String orderCode;

    /**
     * 所担任账号
     */
    private String lockUser;

    /**
     * 锁单人名称
     */
    private String lockName;

    /**
     * 锁单时间
     */
    private Date lockTime;
}
