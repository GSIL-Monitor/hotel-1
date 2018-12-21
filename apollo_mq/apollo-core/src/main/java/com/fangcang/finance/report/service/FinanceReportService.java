package com.fangcang.finance.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.finance.report.request.QueryCheckReportDTO;
import com.fangcang.finance.report.response.OrderCheckDTO;
import com.fangcang.finance.report.response.SupplyOrderCheckDTO;

public interface FinanceReportService {

    /**
     * 分销商对账结算明细
     * @param request
     * @return
     */
    PaginationSupportDTO<OrderCheckDTO> queryAgentCheckReport(QueryCheckReportDTO request);

    /**
     * 供应商对账结算明细
     * @param request
     * @return
     */
    PaginationSupportDTO<SupplyOrderCheckDTO> querySupplyCheckReport(QueryCheckReportDTO request);
}
