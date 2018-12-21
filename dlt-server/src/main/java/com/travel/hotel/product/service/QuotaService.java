package com.travel.hotel.product.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.query.QuotaDailyReportQueryDTO;
import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.common.dto.product.response.QuotaDailyReportResponseDTO;
import com.travel.hotel.product.entity.po.QuotaPO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 配额公式：          总配额 = 已用配额 + 在售配额
 *
 * 下单扣配额：        总配额不变   已用配额增加   在售配额减少（如果有预留配额先从预留配额中减。没有则减在售配额）
 * 撤单还配额：        总配额不变   已用配额减少   在售配额增加

 * 增加在售配额：      总配额加     已用配额不变   在售配额加
 * 减少在售配额：      总配额减     已用配额不变   在售配额减

 * 增加扣留配额：      总配额不变   已用配额不变   在售减
 * 减少扣留配额：      总配额不变   已用配额不变   在售加

 * 设置预留配额：      总配额不变   已用配额不变   在售减
 * 释放预留配额：      总配额不变   已用配额不变   在售加
 *
 *   2018/1/24.
 */
public interface QuotaService {

    /**
     * 新增之前，先根据价格计划ID和售卖日期进行删除
     * @param quotaPOList
     * @return
     */
    public int batchAddQuota(List<QuotaPO> quotaPOList);

    /**
     * 根据配额账号ID和售卖日期删除
     * @param accountId
     * @param saleDate
     * @return
     */
    int deleteByAccountIdAndSaleDate(Long accountId ,Date saleDate);

//    /**
//     * （下单时）调整已用配额（增加、减少），在售配额需要变更（减少，增加）
//     * @param quotaPO
//     * @param quotaOperateEnum
//     */
//    void modifyUsedQuotaNum(QuotaPO quotaPO, QuotaOperateEnum quotaOperateEnum);
//
    /**
     * 下单时，批量（多产品多天）扣配额
     * @param quotaDTOList 多产品多天
     */
    String batchDeductQuota(List<QuotaDTO> quotaDTOList);

    /**
     * 撤单时，批量（多产品多天）退配额
     * @param quotaDTOList 多产品多天
     */
    void batchRefundQuota(List<QuotaDTO> quotaDTOList);

    /**
     * 撤单时，退配额
     * @param orderCode 订单号
     */
    void refundQuota(String orderCode);

    List<QuotaPO> queryByCondition(Map<String, Object> paramMap);

    int updateQuotaById(QuotaPO po);

    int batchUpdateQuotaById(List<QuotaPO> poList);

    int updateByAccountIdSelective(QuotaPO po);

    int addReserveQuota(QuotaPO po);

    PaginationDTO<QuotaDailyReportResponseDTO> queryQuotaReportForPage(QuotaDailyReportQueryDTO quotaDailyReportQueryDTO);

    QuotaDailyReportResponseDTO queryQuotaReport(QuotaDailyReportQueryDTO quotaDailyReportQueryDTO);
}
