package com.fangcang.order.response;

import com.fangcang.order.dto.GuestDTO;
import com.fangcang.order.dto.OrderDTO;
import com.fangcang.order.dto.OrderFinanceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建订单
 *
 * @author : zhanwang
 * @date : 2018/5/30
 */
@Data
public class AssemblyCreateOrderResponseDTO extends OrderDTO implements Serializable {
    private static final long serialVersionUID = -1654898061370874819L;

    /**
     * 入住人列表
     */
    private List<GuestDTO> guestList;

    /**
     * 供货单列表
     */
    private List<SupplyOrderResponseDTO> supplyOrderList;

    /**
     * 订单财务信息
     */
    private OrderFinanceDTO orderFinanceDTO;

    /**
     * 分销商备注
     */
    private String note;
}
