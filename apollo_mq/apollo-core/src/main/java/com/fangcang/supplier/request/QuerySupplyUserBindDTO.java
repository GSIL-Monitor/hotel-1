package com.fangcang.supplier.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuerySupplyUserBindDTO implements Serializable {
    private static final long serialVersionUID = -215872351210667206L;

    private String merchantCode;

    private String openId;

    private String supplyCode;
}
