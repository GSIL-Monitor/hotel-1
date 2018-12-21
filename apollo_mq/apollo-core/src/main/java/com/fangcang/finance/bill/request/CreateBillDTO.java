package com.fangcang.finance.bill.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateBillDTO implements Serializable {
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
    @NotNull
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 账单名称
     */
    private String billName;

    /**
     * 是否自动对账（0手动，1自动）
     */
    private Integer isAuto=0;

    /**
     * 导入批次号
     */
    private Integer importNo;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 操作人
     */
    private String operator;
}
