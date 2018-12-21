package com.fangcang.report.mapper;

import com.fangcang.report.request.QueryOrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailSummaryDTO;

import java.util.List;

public interface OrderDetailReportMapper {

    public List<OrderDetailReportDTO> queryOrderDetail(QueryOrderDetailReportDTO requestDTO);

    public OrderDetailSummaryDTO queryOrderDetailSummary(QueryOrderDetailReportDTO requestDTO);
}
