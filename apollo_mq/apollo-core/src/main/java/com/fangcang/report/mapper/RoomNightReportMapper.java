package com.fangcang.report.mapper;

import com.fangcang.report.request.QueryRoomNightReportDTO;
import com.fangcang.report.response.RoomNightDailyReportDTO;
import com.fangcang.report.response.RoomNightReportDTO;
import com.fangcang.report.response.RoomNightSummaryDTO;

import java.util.List;

public interface RoomNightReportMapper {

    public List<RoomNightReportDTO> queryRoomNightReport(QueryRoomNightReportDTO requestDTO);

    public List<RoomNightDailyReportDTO> queryRoomNightDailyReport(QueryRoomNightReportDTO requestDTO);

    public RoomNightSummaryDTO queryRoomNightSummary(QueryRoomNightReportDTO requestDTO);
}
