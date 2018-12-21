package com.fangcang.finance.financeorder.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Vinney on 2018/7/19.
 */
@Data
public class QuerySupplyFinanceOrderRequestDTO implements Serializable{

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空！")
    private String orderCode;

    /**
     * 多个订单/账单一起查询
     */
    private List<String>  orderCodeList;

    /**
     * 类型
     */
    private Integer financeType;

    private Integer financeStatus;


    /**
     * 查询多个状态使用此List
     */
    private List<Integer> financeStatusList;

}
