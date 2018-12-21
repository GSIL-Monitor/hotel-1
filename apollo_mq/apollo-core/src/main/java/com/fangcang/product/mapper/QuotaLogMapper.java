package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.QuotaLogDO;

import java.util.List;

/**
 * Created by ASUS on 2018/6/5.
 */
public interface QuotaLogMapper extends MyMapper<QuotaLogDO>{

    /**
     * 批量保存配额房态操作日志
     * @param quotaLogDOList
     */
    public void batchSaveQuotaLog(List<QuotaLogDO> quotaLogDOList);
}
