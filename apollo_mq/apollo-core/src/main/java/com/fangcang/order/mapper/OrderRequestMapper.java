package com.fangcang.order.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.order.domain.OrderRequestDO;
import com.fangcang.order.response.OrderRequestCountDTO;

import java.util.List;

public interface OrderRequestMapper extends MyMapper<OrderRequestDO> {

    List<OrderRequestCountDTO> queryOrderRequestCount(List<Integer> orderIdList);

    OrderRequestCountDTO queryOrderRequestStatistics(String merchantCode);
}