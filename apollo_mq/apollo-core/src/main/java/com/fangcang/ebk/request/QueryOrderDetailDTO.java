package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryOrderDetailDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String supplyOrderCode;
}
