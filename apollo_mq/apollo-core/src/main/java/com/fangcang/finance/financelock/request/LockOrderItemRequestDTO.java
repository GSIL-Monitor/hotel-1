package com.fangcang.finance.financelock.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class LockOrderItemRequestDTO implements Serializable {


    private static final long serialVersionUID = -4342221175157705873L;
    /**
     * 订单Id
     */
    private Integer orderId;
    /**
     * 1加锁，2解锁
     */
    private Integer lockType;


}
