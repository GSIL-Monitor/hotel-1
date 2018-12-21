package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SupplyDepositItemDTO implements Serializable{

    private Integer id;

    private Integer supplyId;

    private Integer type;

    private Integer rechargeType;

    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal inAmount;

    private BigDecimal outAmount;

    private String content;

    private String note;

    private String creator;

    private String createTime;
}
