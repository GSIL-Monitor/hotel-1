package com.fangcang.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.request.ProfitReportQueryDTO;
import com.fangcang.report.response.ProfitReportResponseDTO;
import com.fangcang.report.response.ProfitSummaryDTO;

/**
 * 利润报表
 *
 * @author zhanwang
 */
public interface ProfitReportService {


    /**
     * 利润报表
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<ProfitReportResponseDTO> profitReport(ProfitReportQueryDTO requestDTO);

    /**
     * 利润报表汇总数据
     *
     * @param requestDTO
     * @return
     */
    ProfitSummaryDTO profitReportSum(ProfitReportQueryDTO requestDTO);


}
