package com.fangcang.report.mapper;

import com.fangcang.report.request.ProfitReportQueryDTO;
import com.fangcang.report.response.ProfitReportResponseDTO;
import com.fangcang.report.response.ProfitSummaryDTO;

import java.util.List;

/**
 * @author zhanwang
 */
public interface ProfitReportMapper {

    /**
     * 按城市分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByCity(ProfitReportQueryDTO requestDTO);

    /**
     * 按分销商分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByAgent(ProfitReportQueryDTO requestDTO);

    /**
     * 按酒店分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByHotel(ProfitReportQueryDTO requestDTO);

    /**
     * 按渠道分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByChannel(ProfitReportQueryDTO requestDTO);

    /**
     * 按产品经理分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByMerchantPM(ProfitReportQueryDTO requestDTO);

    /**
     * 按业务经理分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByMerchantBM(ProfitReportQueryDTO requestDTO);

    /**
     * 按酒店+分销商分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByHotelAgent(ProfitReportQueryDTO requestDTO);

    /**
     * 按酒店+渠道分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByHotelChannel(ProfitReportQueryDTO requestDTO);

    /**
     * 按产品经理+酒店分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByMerchantPMHotel(ProfitReportQueryDTO requestDTO);

    /**
     * 按业务经理+酒店分组
     *
     * @param requestDTO
     * @return
     */
    List<ProfitReportResponseDTO> profitReportGroupByMerchantBMHotel(ProfitReportQueryDTO requestDTO);

    /**
     * 利润报表汇总
     *
     * @param requestDTO
     * @return
     */
    ProfitSummaryDTO profitReportSum(ProfitReportQueryDTO requestDTO);
}
