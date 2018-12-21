package com.fangcang.finance.bill.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet("自动对账异常单")
public class SupplyBillImportDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 确认号
     */
    @ExcelColumn(name="确认号",orderNum = "5")
    private String confirmNo;

    /**
     * 订单金额
     */
    @ExcelColumn(name = "订单金额" ,orderNum = "4")
    private BigDecimal orderSum;

    private String currency;

    private Integer orderId;

    private String orderCode;

    /**
     * 酒店名称
     */
    @ExcelColumn(name = "酒店名称" ,orderNum = "0")
    private String hotelName;

    private String roomTypeName;

    private String ratePlanName;

    /**
     * 入住人
     */
    @ExcelColumn(name = "入住人" ,orderNum = "3")
    private String guest;

    /**
     * 入住日期
     */
    @ExcelColumn(name = "入住日期" ,orderNum = "1")
    private String checkInDate;

    /**
     * 离店日期
     */
    @ExcelColumn(name = "离店日期" ,orderNum = "2")
    private String checkOutDate;

    private Integer nightNum;

    private Integer roomNum;

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
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Integer balanceMethod;

    /**
     * 是否匹配（1已匹配、2未匹配）
     */
    private Integer isMatch;

    @ExcelColumn(name = "原因" ,orderNum = "6")
    private String failReason;
}
