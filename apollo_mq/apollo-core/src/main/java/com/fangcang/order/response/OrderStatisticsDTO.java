package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderStatisticsDTO implements Serializable{

    private Integer unTreatedCount;

    private Integer myUnTreatedCount;

    private Integer cancelRequestCount;

    private Integer todayNewCount;
}
