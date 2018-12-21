package com.travel.common.dto.order.request;

import java.util.Date;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 *
 * @author  2018/1/10
 */
@Data
public class QueryOrderRequestDTO extends GenericQueryDTO {


    private static final long serialVersionUID = -6013153956553791538L;

    private Integer hotelId;

    private String hotelName;

    private Long orderId;

    private String orderCode;

    private String customerOrderCode;

    private String orderState;
    
    private String orderStateText;

    private String payState;

    private String supplyCode;
    
    private String supplyName;

    private String agentCode;
    
    private String agentName;

    private String payMethod;

    private String channelCode;

    private String channelState;

    private String guestName;
    
    private Integer dateType;
    
    private String deptName;
    
    private String creator;

    private String startDate;

    private String endDate;

}
