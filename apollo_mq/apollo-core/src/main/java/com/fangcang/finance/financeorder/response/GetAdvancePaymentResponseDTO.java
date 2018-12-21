package com.fangcang.finance.financeorder.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Vinney on 2018/7/17.
 */
@Data
public class GetAdvancePaymentResponseDTO implements Serializable {
    private String validBeginDate;
    private String validEndDate;
    private String hotelName;
    /**
     * 预付款余额
     */
    private BigDecimal prepayBalance;
    private String currency;
    /**
     * 预付款合约ID
     */
    private Integer contractId;
}
