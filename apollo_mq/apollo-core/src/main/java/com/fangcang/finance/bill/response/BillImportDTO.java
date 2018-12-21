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
public class BillImportDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 渠道编码
     */
    @ExcelColumn(name = "渠道编码" ,orderNum = "0")
    private String channelCode;

    /**
     * 客户单号
     */
    @ExcelColumn(name = "客户单号" ,orderNum = "1")
    private String customerOrderCode;

    /**
     * 订单金额
     */
    @ExcelColumn(name = "订单金额" ,orderNum = "2")
    private BigDecimal orderSum;

    private String currency;

    private Integer orderId;

    @ExcelColumn(name = "订单号" ,orderNum = "4")
    private String orderCode;

    private String hotelName;

    private String roomTypeName;

    private String ratePlanName;

    private String guest;

    private String checkInDate;

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

    @ExcelColumn(name = "原因" ,orderNum = "3")
    private String failReason;
}
