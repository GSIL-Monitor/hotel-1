package com.fangcang.finance.financeorder.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/17.
 */
@Data
public class GetAdvancePaymentRequestDTO implements Serializable {
    private String supplyCode;
}
