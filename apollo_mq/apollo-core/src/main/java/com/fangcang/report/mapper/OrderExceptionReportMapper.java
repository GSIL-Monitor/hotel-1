package com.fangcang.report.mapper;

import com.fangcang.report.request.QueryOrderExceptionDTO;
import com.fangcang.report.response.OrderExceptionDTO;

import java.util.List;

public interface OrderExceptionReportMapper {

    public List<OrderExceptionDTO> queryOrderException(QueryOrderExceptionDTO requestDTO);
}
