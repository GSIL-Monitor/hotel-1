package com.fangcang.report.mapper;

import com.fangcang.report.request.PrepayContractReportQueryDTO;
import com.fangcang.report.response.PrepayContractReportResponseDTO;

import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/6
 */
public interface PrepayContractReportMapper {

    /**
     * 预付款使用情况（预付款报表）
     *
     * @param requestDTO
     * @return
     */
    List<PrepayContractReportResponseDTO> prepayContractReport(PrepayContractReportQueryDTO requestDTO);

}
