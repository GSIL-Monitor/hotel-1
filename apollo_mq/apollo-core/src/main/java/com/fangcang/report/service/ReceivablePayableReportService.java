package com.fangcang.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.request.PayableReportQueryDTO;
import com.fangcang.report.request.ReceivableReportQueryDTO;
import com.fangcang.report.response.PayableReportResponseDTO;
import com.fangcang.report.response.PayableSummaryDTO;
import com.fangcang.report.response.ReceivableReportResponseDTO;
import com.fangcang.report.response.ReceivableSummaryDTO;

/**
 * 应收、应付报表
 *
 * @author zhanwang
 */
public interface ReceivablePayableReportService {


    /**
     * 应收报表
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<ReceivableReportResponseDTO> receivableReport(ReceivableReportQueryDTO requestDTO);

    /**
     * 应收报表汇总数据
     *
     * @param requestDTO
     * @return
     */
    ReceivableSummaryDTO receivableReportSum(ReceivableReportQueryDTO requestDTO);

    /**
     * 应付报表
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<PayableReportResponseDTO> payableReport(PayableReportQueryDTO requestDTO);

    /**
     * 应付报表汇总数据
     *
     * @param requestDTO
     * @return
     */
    PayableSummaryDTO payableReportSum(PayableReportQueryDTO requestDTO);
}
