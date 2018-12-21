package com.fangcang.ebk.mapper;

import com.fangcang.ebk.request.QueryCheckInReportDTO;
import com.fangcang.ebk.request.QuerySupplyHotelDTO;
import com.fangcang.ebk.response.CheckInReportDTO;
import com.fangcang.ebk.response.CheckInReportSummaryDTO;
import com.fangcang.ebk.response.SupplyHotelDTO;

import java.util.List;

public interface CheckInReportMapper {

    public List<CheckInReportDTO> queryCheckInReport(QueryCheckInReportDTO requestDTO);

    public CheckInReportSummaryDTO queryCheckInReportSummary(QueryCheckInReportDTO requestDTO);

    public List<SupplyHotelDTO> querySupplyHotel(QuerySupplyHotelDTO requestDTO);
}
