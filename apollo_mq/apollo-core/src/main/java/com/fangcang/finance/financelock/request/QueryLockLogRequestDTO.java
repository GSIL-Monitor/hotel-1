package com.fangcang.finance.financelock.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class QueryLockLogRequestDTO implements Serializable {


    private static final long serialVersionUID = 3219422349583807935L;
    /**
     * 订单id
     */
    @NotNull
    private Integer orderId;


}
