package com.fangcang.report.response;

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
@ExcelSheet("订单明细报表")
public class OrderDetailReportDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @ExcelColumn(name = "序号",orderNum = "1")
    private Integer rowNum;

    private Long orderId;

    @ExcelColumn(name = "订单编码",orderNum = "2")
    private String orderCode;

    @ExcelColumn(name = "团号",orderNum = "3")
    private String groupNo;

    @ExcelColumn(name = "酒店名称",orderNum = "4")
    private String hotelName;

    @ExcelColumn(name = "产品类型",orderNum = "5")
    private String itemName;

    @ExcelColumn(name = "房型",orderNum = "6")
    private String roomTypeName;

    @ExcelColumn(name = "产品名称",orderNum = "7")
    private String productName;

    @ExcelColumn(name = "下单日期",orderNum = "8")
    private String createTime;

    @ExcelColumn(name = "入住日期",orderNum = "9")
    private String checkInDate;

    @ExcelColumn(name = "离店日期",orderNum = "10")
    private String checkOutDate;

    @ExcelColumn(name = "间数/份数",orderNum = "11")
    private String roomNum;

    @ExcelColumn(name = "晚数",orderNum = "12")
    private Integer roomNight;

    @ExcelColumn(name = "间夜",orderNum = "13")
    private Integer roomNightNum;

    @ExcelColumn(name = "入住人",orderNum = "14")
    private String guest;

    @ExcelColumn(name = "订单状态",orderNum = "15",replace = {"新单_1", "处理中_2","已确认_3","已取消_4"})
    private Integer orderStatus;

    @ExcelColumn(name = "订单结算方式",orderNum = "16",replace = {"月结_1", "半月结_2","周结_3","单结_4","日结_5"})
    private Integer balanceMethod;

    @ExcelColumn(name = "下单人",orderNum = "17")
    private String creator;

    @ExcelColumn(name = "订单归属人",orderNum = "18")
    private String belongName;

    @ExcelColumn(name = "客户经理",orderNum = "19")
    private String merchantBm;

    @ExcelColumn(name = "产品经理",orderNum = "20")
    private String merchantPm;

    @ExcelColumn(name = "运营员",orderNum = "21")
    private String merchantOp;

    @ExcelColumn(name = "销售渠道",orderNum = "22")
    private String channelCode;

    @ExcelColumn(name = "客户单号",orderNum = "23")
    private String customerOrderCode;

    @ExcelColumn(name = "分销商编码",orderNum = "24")
    private String agentCode;

    @ExcelColumn(name = "分销商名称",orderNum = "25")
    private String agentName;

    @ExcelColumn(name = "供货单号",orderNum = "26")
    private String supplyOrderCode;

    @ExcelColumn(name = "供货结果",orderNum = "27",replace = {"未发单_1", "已发单_2","已确认_3","不确认_4"})
    private Integer supplyStatus;

    @ExcelColumn(name = "供货单结算方式",orderNum = "28",replace = {"月结_1", "半月结_2","周结_3","单结_4","日结_5"})
    private Integer supplyBalanceMethod;

    @ExcelColumn(name = "酒店确认号",orderNum = "29")
    private String confirmNo;

    @ExcelColumn(name = "供应商编码",orderNum = "30")
    private String supplyCode;

    @ExcelColumn(name = "供应商名称",orderNum = "31")
    private String supplyName;

    @ExcelColumn(name = "售卖币种",orderNum = "32")
    private String saleCurrency;

    @ExcelColumn(name = "售价小计",orderNum = "33")
    private BigDecimal salePriceSum;

    @ExcelColumn(name = "底价币种",orderNum = "34")
    private String baseCurrency;

    @ExcelColumn(name = "底价小计",orderNum = "35")
    private BigDecimal basePriceSum;

    @ExcelColumn(name = "订单应收",orderNum = "36")
    private BigDecimal receivableAmount;

    @ExcelColumn(name = "订单已收",orderNum = "37")
    private BigDecimal paidinAmount;

    @ExcelColumn(name = "供货单应付",orderNum = "38")
    private BigDecimal payableAmount;

    @ExcelColumn(name = "供货单已付",orderNum = "39")
    private BigDecimal paidoutAmount;

    @ExcelColumn(name = "利润",orderNum = "40")
    private BigDecimal profit;

    @ExcelColumn(name = "内部备注",orderNum = "41")
    private String note;
}
