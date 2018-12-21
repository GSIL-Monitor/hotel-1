package com.travel.hotel.product.dao;

import com.travel.common.dto.product.query.QuotaDailyReportQueryDTO;
import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.hotel.product.entity.po.QuotaPO;
import com.travel.hotel.product.entity.po.QuotaReportPO;

import java.util.List;
import java.util.Map;

public interface QuotaPOMapper {
    int deleteByPrimaryKey(Long quotaId);

    int insert(QuotaPO record);

    int insertSelective(QuotaPO record);

    QuotaPO selectByPrimaryKey(Long quotaId);

    List<QuotaPO> selectByCondition(Map<String, Object> paramMap);

    int updateByPrimaryKeySelective(QuotaPO record);

    int updateByPrimaryKey(QuotaPO record);

    void batchDeductQuota(List<QuotaDTO> quotaDTOList);

    void batchRefundQuota(List<QuotaDTO> quotaDTOList);

    int updateByAccountIdSelective(QuotaPO record);
    //调价页面修改配额
    int updateByQuotaIdSelective(QuotaPO record);

    int deleteByAccountIdAndSaleDate(QuotaPO record);

    int addReserveQuota(QuotaPO record);

    List<QuotaReportPO> queryQuotaReport(QuotaDailyReportQueryDTO quotaDailyReportQueryDTO);

    void updateUsedQuota(Map<String, Object> paramMap);
}