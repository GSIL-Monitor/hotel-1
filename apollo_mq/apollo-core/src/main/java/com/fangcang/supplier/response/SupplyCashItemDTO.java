package com.fangcang.supplier.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelSheet("现金账户明细")
public class SupplyCashItemDTO implements Serializable{

    private Integer id;

    private Integer supplyId;

    @ExcelColumn(name = "类型",orderNum = "3",replace = {"转入_1", "转出_2","结算_3"})
    private Integer type;

    private Integer rechargeType;

    private BigDecimal amount;

    @ExcelColumn(name = "余额",orderNum = "6")
    private BigDecimal balance;

    @ExcelColumn(name = "收入",orderNum = "4")
    private BigDecimal inAmount;

    @ExcelColumn(name = "支出",orderNum = "5")
    private BigDecimal outAmount;

    @ExcelColumn(name = "内容",orderNum = "2")
    private String content;

    @ExcelColumn(name = "备注",orderNum = "7")
    private String note;

    @ExcelColumn(name = "操作人",orderNum = "8")
    private String creator;

    @ExcelColumn(name = "日期",orderNum = "1")
    private String createTime;
}
