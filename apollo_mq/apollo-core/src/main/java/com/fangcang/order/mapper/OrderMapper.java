package com.fangcang.order.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.request.OrderListRequestDTO;

import java.util.List;

public interface OrderMapper extends MyMapper<OrderDO> {

    List<OrderDO> queryOrderList(OrderListRequestDTO requestDTO);
}