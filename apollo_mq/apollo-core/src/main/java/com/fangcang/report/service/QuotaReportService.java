package com.fangcang.report.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.report.dto.HotelQuotaDTO;
import com.fangcang.report.request.QuotaReportQueryDTO;

/**
 * Created by ASUS on 2018/7/9.
 */
public interface QuotaReportService {

    /**
     * 配额报表
     * @param quotaReportQueryDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<HotelQuotaDTO>> quotaReportDetail(QuotaReportQueryDTO quotaReportQueryDTO);
}
