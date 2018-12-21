package com.travel.hotel.order.entity.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderPO {
    private Long orderId;

    private String orderCode;

    private String supplyCode;

    private String supplyName;

    private String agentCode;

    private String agentName;

    private String settlement;

    private String confirmNo;

    private String customerOrderCode;

    private String orderState;

    private String payMethod;

    private Integer payState;

    private String baseCurrency;

    private BigDecimal basePrice;

    private String saleCurrency;

    private BigDecimal salePrice;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private String channelCode;

    private String childChannelCode;

    private String channelState;

    private Integer financeLockStatus;

    private String financeLocker;

    private String guestName;

    private String guestFile;

    private BigDecimal receivable;

    private BigDecimal payable;

    private BigDecimal actualReceived;

    private BigDecimal actualPaied;
    
    private BigDecimal agentCommission;
    
    private BigDecimal supplyCommission;

    private Date checkinDate;

    private Date checkoutDate;
    
    private String remark;
    
    private String deptNo;
    
    private String deptName;
    
    private String roomNo;
    
    private String maker;
    
    private List<OrderProductPO> orderProductPOList;

    private List<OrderRestrictPO> orderRestrictPOList;
}