package com.fangcang.merchant.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.MerchantCompanyApplyDO;
import com.fangcang.merchant.response.MerchantCompanyApplyDTO;

import java.util.List;

public interface MerchantCompanyApplyMapper extends MyMapper<MerchantCompanyApplyDO> {

    public List<MerchantCompanyApplyDTO> queryMerchantCompanyApply(List<Integer> companyIdList);
}