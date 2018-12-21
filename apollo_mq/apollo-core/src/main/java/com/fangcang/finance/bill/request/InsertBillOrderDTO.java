package com.fangcang.finance.bill.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InsertBillOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 日期类型（1下单日期，2入住日期，3离店日期）
     */
    private Integer dateType;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 订单号list
     */
    private List<Integer> orderIdList;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 操作人
     */
    private String operator;
}
