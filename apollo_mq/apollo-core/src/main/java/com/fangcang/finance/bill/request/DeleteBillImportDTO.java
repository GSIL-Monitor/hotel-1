package com.fangcang.finance.bill.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeleteBillImportDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 导入批次
     */
    @NotNull
    private Integer importNo;

    private String operator;
}
