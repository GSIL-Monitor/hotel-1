package com.fangcang.finance.invoice.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelSheet("未开发票列表")
public class UnInvoiceSupplyOrderDTO implements Serializable {

    /**
     * 订单号
     */
    @ExcelColumn(name = "供货单编码",orderNum = "1")
    private String orderCode;

    /**
     * 酒店名称
     */
    @ExcelColumn(name = "酒店名称",orderNum = "2")
    private String hotelName;

    /**
     * 房型名称
     */
    @ExcelColumn(name = "房型名称",orderNum = "3")
    private String roomTypeName;

    /**
     * 价格计划名称
     */
    @ExcelColumn(name = "价格计划",orderNum = "4")
    private String ratePlanName;

    /**
     * 入住人
     */
    @ExcelColumn(name = "入住人",orderNum = "5")
    private String guest;

    /**
     * 入住日期
     */
    @ExcelColumn(name = "入住日期",orderNum = "6")
    private String checkInDate;

    /**
     * 离店日期
     */
    @ExcelColumn(name = "离店日期",orderNum = "7")
    private String checkOutDate;

    /**
     * 间夜
     */
    @ExcelColumn(name = "间夜",orderNum = "8")
    private Integer roomNight;

    /**
     * 订单创建时间
     */
    @ExcelColumn(name = "订单创建时间",orderNum = "9")
    private String orderCreateDate;

    /**
     * 确认号
     */
    @ExcelColumn(name = "确认号",orderNum = "10")
    private String confirmNo;

    /**
     * 币种
     */
    @ExcelColumn(name = "币种",orderNum = "11")
    private String currency;

    /**
     * 应付
     */
    @ExcelColumn(name = "应付",orderNum = "12")
    private BigDecimal receivableAmount;

    /**
     * 开票金额
     */
    @ExcelColumn(name = "开票金额",orderNum = "13")
    private BigDecimal invoiceAmount;
}
