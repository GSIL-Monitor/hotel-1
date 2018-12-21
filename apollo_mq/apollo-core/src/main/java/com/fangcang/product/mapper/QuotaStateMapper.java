package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.QuotaStateDO;
import com.fangcang.product.request.QuotaStateQueryDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/24.
 */
public interface QuotaStateMapper extends MyMapper<QuotaStateDO> {

    /**
     * 批量插入数据
     * @param quotaStateList
     */
    public void batchInsertQuotaState(List<QuotaStateDO> quotaStateList);

    /**
     * 批量新增或修改配额房态
     * @param quotaStateList
     */
    public void batchSaveOrUpdateQuotaState(List<QuotaStateDO> quotaStateList);

    /**
     * 查看配额池房态
     * @param quotaStateQueryDTO
     * @return
     */
    public List<QuotaStateDO> queryQuotaStateDailyInfo(QuotaStateQueryDTO quotaStateQueryDTO);

    /**
     * 动态查询配额池房态
     * @param quotaStateQueryDTO
     * @return
     */
    public List<QuotaStateDO> dynamicQueryQuotaStateDailyInfo(QuotaStateQueryDTO quotaStateQueryDTO);
}
