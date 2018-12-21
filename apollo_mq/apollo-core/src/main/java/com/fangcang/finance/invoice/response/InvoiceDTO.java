package com.fangcang.finance.invoice.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelSheet("发票列表")
public class InvoiceDTO implements Serializable{

    private Integer id;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    @ExcelColumn(name = "客户名称",orderNum = "2")
    private String orgName;

    /**
     * 开票方式：1按订单开票2按账单开票
     */
    private Integer invoiceMethod;

    /**
     * 发票名称
     */
    @ExcelColumn(name = "发票名称",orderNum = "1")
    private String invoiceName;

    /**
     * 发票类型：1普通发票2专业发票3电子发票
     */
    @ExcelColumn(name = "发票类型",orderNum = "4",replace = {"普通发票_1", "专业发票_2","电子发票_3"})
    private Integer invoiceType;

    /**
     * 发票代码
     */
    @ExcelColumn(name = "发票代码",orderNum = "7")
    private String invoiceCode;

    /**
     * 发票抬头
     */
    @ExcelColumn(name = "发票抬头",orderNum = "5")
    private String title;

    /**
     * 发票内容
     */
    @ExcelColumn(name = "发票内容",orderNum = "6")
    private String content;

    /**
     * 纳税人识别号
     */
    @ExcelColumn(name = "纳税人识别号",orderNum = "8")
    private String identifier;

    /**
     * 币种
     */
    @ExcelColumn(name = "币种",orderNum = "9")
    private String currency;

    /**
     * 发票金额
     */
    @ExcelColumn(name = "发票金额",orderNum = "10")
    private BigDecimal amount;

    /**
     * 开票日期
     */
    @ExcelColumn(name = "开票日期",orderNum = "3")
    private String invoiceDate;

    /**
     * 发票状态
     */
    @ExcelColumn(name = "发票状态",orderNum = "11")
    private Integer invoiceStatus;

    /**
     * 备注
     */
    @ExcelColumn(name = "备注",orderNum = "15")
    private String note;

    /**
     * 注册地址
     */
    @ExcelColumn(name = "注册地址",orderNum = "12")
    private String companyAddress;

    /**
     * 公司电话
     */
    @ExcelColumn(name = "公司电话",orderNum = "13")
    private String companyTelephone;

    /**
     * 开户银行
     */
    @ExcelColumn(name = "开户银行",orderNum = "14")
    private String companyBankName;

    /**
     * 取票方式:1邮寄2上门自提
     */
    @ExcelColumn(name = "取票方式",orderNum = "16",replace = {"邮寄_1", "上门自提_2"})
    private Integer postalType;

    /**
     * 联系人
     */
    @ExcelColumn(name = "联系人",orderNum = "17")
    private String contacter;

    /**
     * 联系电话
     */
    @ExcelColumn(name = "联系电话",orderNum = "18")
    private String contactTelephone;

    /**
     * 国家
     */
    @ExcelColumn(name = "国家",orderNum = "19")
    private String country;

    /**
     * 省份
     */
    @ExcelColumn(name = "省份",orderNum = "20")
    private String province;

    /**
     * 城市
     */
    @ExcelColumn(name = "城市",orderNum = "21")
    private String city;

    /**
     * 详情地址
     */
    @ExcelColumn(name = "详情地址",orderNum = "22")
    private String address;

    /**
     * 快递公司
     */
    @ExcelColumn(name = "快递公司",orderNum = "23")
    private String expressCompany;

    /**
     * 快递单号
     */
    @ExcelColumn(name = "快递单号",orderNum = "24")
    private String expressNo;

    /**
     * 取票时间
     */
    @ExcelColumn(name = "取票时间",orderNum = "25")
    private String postalDate;

    private String creator;

    private String createTime;
}
