package com.fangcang.finance.financeorder.response;

import com.fangcang.common.util.excel.ExcelColumn;
import com.fangcang.common.util.excel.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelSheet("现金账户明细")
public class UnpayResponseDTO implements Serializable {

    /**
     * 供应商名称
     */
    @ExcelColumn(name = "供应商名称",orderNum = "1")
    private String supplyName;

    /**
     * 供货单号
     */
    @ExcelColumn(name = "供货单号",orderNum = "2")
    private String orderCode;

    /**
     * 应付
     */
    @ExcelColumn(name = "应付",orderNum = "3")
    private BigDecimal payableAmount;

    /**
     * 应收
     */
    @ExcelColumn(name = "应收",orderNum = "4")
    private BigDecimal receivableAmount;
}
