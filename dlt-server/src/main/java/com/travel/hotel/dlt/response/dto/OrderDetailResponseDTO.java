package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情响应对象
 *
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class OrderDetailResponseDTO extends OrderDTO implements Serializable {

    private static final long serialVersionUID = 6268290460283173177L;

    /**
     * 供货单列表
     */
    private List<SupplyOrderResponseDTO> supplyOrderList;

    /**
     * 订单附件
     */
    private List<OrderAttachGroupResponseDTO> orderAttachGroups;

    /**
     * 订单备注
     */
    private List<OrderNoteGroupResponseDTO> orderNoteGroups;

    /**
     * 订单操作日志
     */
    private List<OrderOperationLogDTO> orderOperationLogList;

    /**
     * 订单减免金额
     */
    private BigDecimal derateAmount;


}
