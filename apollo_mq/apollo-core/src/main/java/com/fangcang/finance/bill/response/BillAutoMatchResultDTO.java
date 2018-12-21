package com.fangcang.finance.bill.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BillAutoMatchResultDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer totalCount;

    private Integer matchCount;

    private Integer unMatchCount;

    private Integer importCount;
}
