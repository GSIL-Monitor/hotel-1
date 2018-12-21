package com.fangcang.finance.financelock.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanwang
 */
@Data
public class FinanceLockLogResponseDTO implements Serializable {
    private static final long serialVersionUID = 2536347438241319301L;


    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单内容
     */
    private String content;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private String operatorTime;

}