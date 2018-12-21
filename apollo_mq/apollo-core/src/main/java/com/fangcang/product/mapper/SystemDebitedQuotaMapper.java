package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.SystemDebitedQuotaDO;

import java.util.List;

/**
 * Created by ASUS on 2018/6/5.
 */
public interface SystemDebitedQuotaMapper extends MyMapper<SystemDebitedQuotaDO> {

    /**
     * 查询扣配额列表
     * @param systemDebitedQuotaDO
     * @return
     */
    public List<SystemDebitedQuotaDO> dynamicQuerySystemDebitedQuota(SystemDebitedQuotaDO systemDebitedQuotaDO);

    /**
     * 批量保存
     * @param systemDebitedQuotaDOList
     */
    public void batchSaveSystemDebitedQuota(List<SystemDebitedQuotaDO> systemDebitedQuotaDOList);
}
