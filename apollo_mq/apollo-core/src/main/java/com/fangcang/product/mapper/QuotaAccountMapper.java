package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.QuotaAccountDO;
import com.fangcang.product.request.QuotaAccountQueryDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/22.
 */
public interface QuotaAccountMapper extends MyMapper<QuotaAccountDO>{

    /**
     *新增配额账号
     * @param quotaAccountDO
     * @return
     */
    public Integer insertQuotaAccount(QuotaAccountDO quotaAccountDO);

    /**
     * 修改配额账号
     * @param quotaAccountDO
     * @return
     */
    public Integer updateQuotaAccount(QuotaAccountDO quotaAccountDO);

    /**
     * 批量新增配额账号
     * @param quotaAccountDOList
     * @return
     */
    public void batchSaveQuotaAccount(List<QuotaAccountDO> quotaAccountDOList);

    /**
     * 动态查询配额账号
     * @param quotaAccountQueryDTO
     * @return
     */
    public List<QuotaAccountDO> dynamicQueryQuotaAccount(QuotaAccountQueryDTO quotaAccountQueryDTO);
}
