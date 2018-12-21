package com.fangcang.ebk.response;

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
@ExcelSheet("EBK订单报表")
public class EbkOrderSimpleDTO implements Serializable{
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    /**
     * 供货单号
     */
    @ExcelColumn(name = "供货单号",orderNum = "2")
    private String supplyOrderCode;

    /**
     * ebk订单状态（1新单，2修改申请，3取消申请，4已确认，5已取消）
     */
    @ExcelColumn(name = "订单状态",orderNum = "3",replace = {"新单_1", "修改申请_2","取消申请_3","已确认_4","已取消_5"})
    private Integer orderStatus;

    /**
     * 是否团房（1是团房，0散房）
     */
    @ExcelColumn(name = "是否团房",orderNum = "4",replace = {"团房_1", "散房_0"})
    private Integer isGroupon;

    /**
     * 总金额
     */
    @ExcelColumn(name = "总金额",orderNum = "5")
    private BigDecimal orderSum;

    /**
     *  币种
     */
    @ExcelColumn(name = "币种",orderNum = "6")
    private String currency;

    /**
     * 酒店名称
     */
    @ExcelColumn(name = "酒店",orderNum = "7")
    private String hotelName;

    /**
     * 房型名称
     */
    @ExcelColumn(name = "房型",orderNum = "8")
    private String roomtypeName;

    /**
     * 价格计划名称
     */
    @ExcelColumn(name = "价格计划",orderNum = "9")
    private String rateplanName;

    /**
     * 入住日期
     */
    @ExcelColumn(name = "入住日期",orderNum = "10")
    private String checkInDate;

    /**
     * 离店日期
     */
    @ExcelColumn(name = "离店日期",orderNum = "11")
    private String checkOutDate;

    /**
     * 晚数
     */
    @ExcelColumn(name = "晚数",orderNum = "12")
    private Integer nightNum;

    /**
     * 间数
     */
    @ExcelColumn(name = "间数",orderNum = "13")
    private Integer roomNum;

    /**
     * 入住人
     */
    @ExcelColumn(name = "入住人",orderNum = "14")
    private String guest;

    /**
     * 处理人
     */
    @ExcelColumn(name = "处理人",orderNum = "15")
    private String belongName;

    /**
     * 锁单人
     */
    private String lockName;

    /**
     * 最新的请求id
     */
    private Long requestId;

    /**
     * 确认号
     */
    @ExcelColumn(name = "确认号",orderNum = "16")
    private String confirmNo;

    /**
     * 导游
     */
    @ExcelColumn(name = "导游",orderNum = "17")
    private String guide;

    /**
     * 导游手机号
     */
    @ExcelColumn(name = "导游手机号",orderNum = "18")
    private String guidePhone;

    /**
     * 供货单创建时间
     */
    @ExcelColumn(name = "创建时间",orderNum = "19")
    private String createTime;
}
