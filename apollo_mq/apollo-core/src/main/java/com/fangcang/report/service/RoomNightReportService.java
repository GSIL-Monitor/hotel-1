package com.fangcang.report.service;

import com.fangcang.report.request.QueryRoomNightReportDTO;
import com.fangcang.report.response.RoomNightSummaryDTO;

public interface RoomNightReportService {

    /**
     * 间夜统计报表
     * @param requestDTO
     * @return
     */
    public RoomNightSummaryDTO queryRoomNightReport(QueryRoomNightReportDTO requestDTO);
}
