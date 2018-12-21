package com.fangcang.finance.report.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelSheet("对账结算明细")
public class OrderCheckDTO implements Serializable{

    @ExcelColumn(name = "分销商编码",orderNum = "1")
    private String agentCode;

    @ExcelColumn(name = "分销商名称",orderNum = "2")
    private String agentName;

    @ExcelColumn(name = "订单号",orderNum = "3")
    private String orderCode;

    @ExcelColumn(name = "客户单号",orderNum = "4")
    private String customerOrderCode;

    private Integer orderId;

    @ExcelColumn(name = "酒店名称",orderNum = "5")
    private String hotelName;

    @ExcelColumn(name = "房型名称",orderNum = "6")
    private String roomTypeName;

    @ExcelColumn(name = "价格计划",orderNum = "7")
    private String rateplanName;

    @ExcelColumn(name = "客户名称",orderNum = "8")
    private String guest;

    @ExcelColumn(name = "入住日期",orderNum = "9")
    private String checkInDate;

    @ExcelColumn(name = "离店日期",orderNum = "10")
    private String checkOutDate;

    @ExcelColumn(name = "房间数",orderNum = "11")
    private Integer roomNum;

    @ExcelColumn(name = "间夜",orderNum = "12")
    private Integer roomNightNum;

    @ExcelColumn(name = "创建时间",orderNum = "13")
    private String createTime;

    @ExcelColumn(name = "结算方式",orderNum = "14",replace = {"月结_1", "半月结_2","周结_3","单结_4","日结_5"})
    private Integer balanceMethod;

    @ExcelColumn(name = "币种",orderNum = "15")
    private String currency;

    @ExcelColumn(name = "应收",orderNum = "16")
    private BigDecimal receivableAmount;

    @ExcelColumn(name = "实收",orderNum = "17")
    private BigDecimal settlementAmount;

    @ExcelColumn(name = "应付",orderNum = "18")
    private BigDecimal payableAmount;

    @ExcelColumn(name = "对账状态",orderNum = "19",replace = {"未对账_0", "已对账_1"})
    private Integer accountStatus;

    @ExcelColumn(name = "结算状态",orderNum = "20",replace = {"未结算_0", "已结算_1"})
    private Integer settlementStatus;

    @ExcelColumn(name = "结算日期",orderNum = "21")
    private String settlementDate;
}
