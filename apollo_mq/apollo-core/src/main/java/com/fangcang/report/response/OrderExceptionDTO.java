package com.fangcang.report.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelSheet("异常订单表")
public class OrderExceptionDTO implements Serializable{

    private static final long serialVersionUID = -6147279044482949578L;

    private Long orderId;

    @ExcelColumn(name = "订单编码",orderNum = "1")
    private String orderCode;

    @ExcelColumn(name = "酒店名称",orderNum = "2")
    private String hotelName;

    @ExcelColumn(name = "房型",orderNum = "3")
    private String roomTypeName;

    @ExcelColumn(name = "价格计划",orderNum = "4")
    private String rateplanName;

    @ExcelColumn(name = "下单日期",orderNum = "5")
    private String createTime;

    @ExcelColumn(name = "入住日期",orderNum = "6")
    private String checkInDate;

    @ExcelColumn(name = "离店日期",orderNum = "7")
    private String checkOutDate;

    @ExcelColumn(name = "间数",orderNum = "8")
    private String roomNum;

    @ExcelColumn(name = "晚数",orderNum = "9")
    private Integer roomNight;

    @ExcelColumn(name = "入住人",orderNum = "10")
    private String guest;

    @ExcelColumn(name = "订单状态",orderNum = "11",replace = {"新单_1", "处理中_2","已确认_3","已取消_4"})
    private Integer orderStatus;

    @ExcelColumn(name = "订单结算方式",orderNum = "12",replace = {"月结_1", "半月结_2","周结_3","单结_4","日结_5"})
    private Integer balanceMethod;

    @ExcelColumn(name = "下单人",orderNum = "13")
    private String creator;

    @ExcelColumn(name = "订单归属人",orderNum = "14")
    private String belongName;

    @ExcelColumn(name = "客户经理",orderNum = "15")
    private String merchantBm;

    @ExcelColumn(name = "销售渠道",orderNum = "16")
    private String channelCode;

    @ExcelColumn(name = "客户单号",orderNum = "17")
    private String customerOrderCode;

    @ExcelColumn(name = "分销商编码",orderNum = "18")
    private String agentCode;

    @ExcelColumn(name = "分销商名称",orderNum = "19")
    private String agentName;

    @ExcelColumn(name = "供货单号",orderNum = "20")
    private String supplyOrderCode;

    @ExcelColumn(name = "供货结果",orderNum = "21",replace = {"未发单_1", "已发单_2","已确认_3","不确认_4"})
    private Integer supplyStatus;

    @ExcelColumn(name = "供货单结算方式",orderNum = "22",replace = {"月结_1", "半月结_2","周结_3","单结_4","日结_5"})
    private Integer supplyBalanceMethod;

    @ExcelColumn(name = "酒店确认号",orderNum = "23")
    private String confirmNo;

    @ExcelColumn(name = "供应商编码",orderNum = "24")
    private String supplyCode;

    @ExcelColumn(name = "供应商名称",orderNum = "25")
    private String supplyName;

    @ExcelColumn(name = "售卖币种",orderNum = "26")
    private String saleCurrency;

    @ExcelColumn(name = "订单其他应收",orderNum = "27")
    private BigDecimal orderExceptionAmount;

    @ExcelColumn(name = "底价币种",orderNum = "28")
    private String baseCurrency;

    @ExcelColumn(name = "供货单其他应付",orderNum = "29")
    private BigDecimal supplyOrderExceptionAmount;

    @ExcelColumn(name = "内部备注",orderNum = "30")
    private String note;
}
