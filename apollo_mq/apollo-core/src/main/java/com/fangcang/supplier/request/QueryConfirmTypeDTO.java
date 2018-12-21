package com.fangcang.supplier.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryConfirmTypeDTO implements Serializable{

    private String supplyCode;

    private String week;

    private Integer time;
}
