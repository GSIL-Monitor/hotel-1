package com.fangcang.supplier.request;

import com.fangcang.finance.dto.PayItemDTO;
import lombok.Data;

@Data
public class SupplyCashRechargeDTO extends PayItemDTO{

    /**
     * 供应商id
     */
    private Long supplyId;

    /**
     * 充值类型：1线下支付，2押金转入
     */
    private Integer rechargeType;

    private String operator;
}
