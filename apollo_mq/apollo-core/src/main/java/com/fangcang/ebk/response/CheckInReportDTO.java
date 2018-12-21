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
@ExcelSheet("在住间夜报表")
public class CheckInReportDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 日期
     */
    @ExcelColumn(name = "日期",orderNum = "2")
    private String saleDate;

    /**
     * 间夜
     */
    @ExcelColumn(name = "间夜",orderNum = "3")
    private Integer roomNight;

    /**
     * 散房间夜
     */
    @ExcelColumn(name = "散房间夜",orderNum = "4")
    private Integer looseRoomNight;

    /**
     * 团房间夜
     */
    @ExcelColumn(name = "团房间夜",orderNum = "5")
    private Integer grouponRoomNight;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 酒店名称
     */
    @ExcelColumn(name = "酒店名称",orderNum = "6")
    private String hotelName;
}
