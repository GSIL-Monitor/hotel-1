package com.fangcang.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.report.mapper.ProfitReportMapper;
import com.fangcang.report.request.ProfitReportQueryDTO;
import com.fangcang.report.response.ProfitReportResponseDTO;
import com.fangcang.report.response.ProfitSummaryDTO;
import com.fangcang.report.service.ProfitReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/17
 */
@Service
public class ProfitReportServiceImpl implements ProfitReportService {

    @Resource
    private ProfitReportMapper profitReportMapper;

    @Override
    public PaginationSupportDTO<ProfitReportResponseDTO> profitReport(ProfitReportQueryDTO requestDTO) {

        // 1. 分页查询
        Page<ProfitReportResponseDTO> page = null;
        if (requestDTO.getGroupType() == 1) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByCity(requestDTO));
        } else if (requestDTO.getGroupType() == 2) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByAgent(requestDTO));
        } else if (requestDTO.getGroupType() == 3) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByHotel(requestDTO));
        } else if (requestDTO.getGroupType() == 4) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByChannel(requestDTO));
        } else if (requestDTO.getGroupType() == 5) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByMerchantPM(requestDTO));
        } else if (requestDTO.getGroupType() == 6) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByMerchantBM(requestDTO));
        } else if (requestDTO.getGroupType() == 7) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByHotelAgent(requestDTO));
        } else if (requestDTO.getGroupType() == 8) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByHotelChannel(requestDTO));
        } else if (requestDTO.getGroupType() == 9) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByMerchantPMHotel(requestDTO));
        } else if (requestDTO.getGroupType() == 10) {
            page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                             .doSelectPage(() -> profitReportMapper.profitReportGroupByMerchantBMHotel(requestDTO));
        }

        // 2. 组装响应对象
        if (page == null) {
            return null;
        }
        List<ProfitReportResponseDTO> itemList = page.getResult();
        if (!CollectionUtils.isEmpty(itemList)) {
            for (ProfitReportResponseDTO item : itemList) {
                item.setChannelName(ChannelTypeEnum.getValueByKey(item.getChannelCode()));
                item.setStartDate(requestDTO.getStartDate());
                item.setEndDate(requestDTO.getEndDate());
                item.setAvgProfit(item.getProfit().divide(new BigDecimal(item.getRoomNight()), 2, RoundingMode.HALF_UP));
            }
        }
        PaginationSupportDTO<ProfitReportResponseDTO> pageResponse = new PaginationSupportDTO<>();
        pageResponse.setItemList(itemList);
        pageResponse.setPageSize(page.getPageSize());
        pageResponse.setTotalCount(page.getTotal());
        pageResponse.setTotalPage(page.getPages());
        pageResponse.setCurrentPage(page.getPageNum());
        return pageResponse;
    }

    @Override
    public ProfitSummaryDTO profitReportSum(ProfitReportQueryDTO requestDTO) {
        // 1. 查汇总数据
        ProfitSummaryDTO profitSummaryDTO = profitReportMapper.profitReportSum(requestDTO);
        if (profitSummaryDTO == null) {
            profitSummaryDTO = new ProfitSummaryDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            profitSummaryDTO.setAvgProfit(profitSummaryDTO.getProfitSum().divide(new BigDecimal(profitSummaryDTO.getRoomNightSum()), 2, RoundingMode.HALF_UP));
        }
        return profitSummaryDTO;
    }
}
