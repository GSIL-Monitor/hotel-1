package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderCustomerDTO implements Serializable{

    private String agentCode;

    private String agentName;

    private String currency;

    private List<OrderSupplierDTO> supplierDTOList;
}
