package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderSupplierDTO implements Serializable{

    private String supplyCode;

    private String supplyName;

    private String currency;
}
