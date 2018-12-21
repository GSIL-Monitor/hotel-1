package com.fangcang.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.request.QueryOrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailSummaryDTO;

public interface OrderDetailReportService {

    /**
     * 订单明细报表
     * @param requestDTO
     * @return
     */
    public PaginationSupportDTO<OrderDetailReportDTO> queryOrderDetail(QueryOrderDetailReportDTO requestDTO);

    /**
     * 订单明细汇总
     * @param requestDTO
     * @return
     */
    public OrderDetailSummaryDTO queryOrderDetailSummary(QueryOrderDetailReportDTO requestDTO);
}
