package com.fangcang.finance.report.mapper;

import com.fangcang.finance.report.request.QueryCheckReportDTO;
import com.fangcang.finance.report.response.OrderCheckDTO;
import com.fangcang.finance.report.response.SupplyOrderCheckDTO;

import java.util.List;

public interface FinanceReportMapper {

    /**
     * 分销商对账结算明细
     * @param request
     * @return
     */
    List<OrderCheckDTO> queryAgentCheckReport(QueryCheckReportDTO request);

    /**
     * 供应商对账结算明细
     * @param request
     * @return
     */
    List<SupplyOrderCheckDTO> querySupplyCheckReport(QueryCheckReportDTO request);
}
