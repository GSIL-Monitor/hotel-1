package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateConfirmNoDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String supplyOrderCode;

    private String confirmNo;

    private String operator;

    private String operatorName;
}
