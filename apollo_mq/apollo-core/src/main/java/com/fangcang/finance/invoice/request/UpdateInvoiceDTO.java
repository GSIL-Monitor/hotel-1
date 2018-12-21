package com.fangcang.finance.invoice.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateInvoiceDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private Integer invoiceId;

    /**
     * 开票方式：1按订单开票2按账单开票3预开发票
     */
    private Integer invoiceMethod;

    /**
     * 发票名称
     */
    private String invoiceName;

    /**
     * 发票类型：1普通发票2专业发票3电子发票
     */
    private Integer invoiceType;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 发票抬头
     */
    private String title;

    /**
     * 发票内容
     */
    private String content;

    /**
     * 纳税人识别号
     */
    private String identifier;

    /**
     * 发票金额
     */
    private BigDecimal amount;

    /**
     * 开票日期
     */
    private String invoiceDate;

    /**
     * 备注
     */
    private String note;

    /**
     * 注册地址
     */
    private String companyAddress;

    /**
     * 公司电话
     */
    private String companyTelephone;

    /**
     * 开户银行
     */
    private String companyBankName;

    /**
     * 取票方式:1邮寄2上门自提
     */
    private Integer postalType;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    private String contactTelephone;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 详情地址
     */
    private String address;

    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 取票时间
     */
    private String postalDate;

    private String operator;
}
