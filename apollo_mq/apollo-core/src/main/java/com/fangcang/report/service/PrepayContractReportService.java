package com.fangcang.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.request.PrepayContractReportQueryDTO;
import com.fangcang.report.response.PrepayContractReportResponseDTO;

public interface PrepayContractReportService {

    /**
     * 预付款使用情况（预付款报表）
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<PrepayContractReportResponseDTO> prepayContractReport(PrepayContractReportQueryDTO requestDTO);
}
