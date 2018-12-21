package com.fangcang.ebk.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.ebk.request.QueryCheckInReportDTO;
import com.fangcang.ebk.request.QuerySupplyHotelDTO;
import com.fangcang.ebk.response.CheckInReportSummaryDTO;
import com.fangcang.ebk.response.SupplyHotelResponseDTO;

public interface CheckInReportService {

    /**
     * 查询在住报表
     * @param requestDTO
     * @return
     */
    public CheckInReportSummaryDTO queryCheckInReport(QueryCheckInReportDTO requestDTO);

    /**
     * 查询供应商有价格计划的酒店
     * @param requestDTO
     * @return
     */
    public ResponseDTO<SupplyHotelResponseDTO> querySupplyHotel(QuerySupplyHotelDTO requestDTO);
}
