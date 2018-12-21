package com.fangcang.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.request.QueryOrderExceptionDTO;
import com.fangcang.report.response.OrderExceptionDTO;

public interface OrderExceptionReportService {

    /**
     * 异常单报表
     * @param requestDTO
     * @return
     */
    public PaginationSupportDTO<OrderExceptionDTO> queryOrderException(QueryOrderExceptionDTO requestDTO);
}
