package com.fangcang.finance.bill.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillOrderItemExportDTO extends BillOrderItemDTO{

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 间夜
     */
    private Integer roomNight;

    /**
     * 客户单号
     */
    private String customerOrderCode;

    /**
     * 应收
     */
    private BigDecimal receivableAmount;

    /**
     * 实收
     */
    private BigDecimal paidinAmount;

    /**
     * 本次要收
     */
    private BigDecimal currPaidinAmount;

    /**
     * 订单创建时间
     */
    private String orderCreateDate;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Integer balanceMethod;
}
