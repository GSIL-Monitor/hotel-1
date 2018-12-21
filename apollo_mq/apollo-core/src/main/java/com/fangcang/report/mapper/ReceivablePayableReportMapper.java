package com.fangcang.report.mapper;

import com.fangcang.report.request.PayableReportQueryDTO;
import com.fangcang.report.request.ReceivableReportQueryDTO;
import com.fangcang.report.response.PayableReportResponseDTO;
import com.fangcang.report.response.PayableSummaryDTO;
import com.fangcang.report.response.ReceivableReportResponseDTO;
import com.fangcang.report.response.ReceivableSummaryDTO;

import java.util.List;

/**
 * @author zhanwang
 */
public interface ReceivablePayableReportMapper {

    /**
     * 应收报表
     *
     * @param requestDTO
     * @return
     */
    List<ReceivableReportResponseDTO> receivableReport(ReceivableReportQueryDTO requestDTO);

    /**
     * 应收报表总计
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
    List<PayableReportResponseDTO> payableReport(PayableReportQueryDTO requestDTO);

    /**
     * 应付报表总计
     *
     * @param requestDTO
     * @return
     */
    PayableSummaryDTO payableReportSum(PayableReportQueryDTO requestDTO);
}
