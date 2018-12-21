package com.travel.common.dto.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.travel.common.dto.AttachmentDTO;

/**
 *
 * @author  2018/1/10
 */
@Data
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = -3065657039981660928L;

    private Long orderId;

    private String orderCode;

    private String supplyCode;

    private String supplyName;

    private String agentCode;

    private String agentName;

    private String settlement;

    private String confirmNo;
    
    private String roomNo;

    private String customerOrderCode;

    private String orderState;
    
    private String orderStateText;

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

    private String channelState;
    
    private String channelOrderStateText;

    private Integer financeLockStatus;

    private String financeLocker;

    private String guestName;

    private String guestFile;

    private BigDecimal receivable;

    private BigDecimal payable;

    private BigDecimal actualReceived;

    private BigDecimal actualPaied;
    
    private String remark;
    
    private String deptNo;
    
    private String deptName;
    
    private String maker;

    private List<OrderRestrictDTO> orderRestrictDTOList;

    private List<OrderProductDTO> orderProductDTOList;

    private OrderAppealDTO orderAppealDTO;
    
    private List<AttachmentDTO> attachmentList;
}
