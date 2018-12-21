package com.fangcang.merchant.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.MerchantCompanyDO;
import com.fangcang.merchant.request.QueryMerchantCompanyDTO;
import com.fangcang.merchant.response.MerchantCompanyDTO;

import java.util.List;

public interface MerchantCompanyMapper extends MyMapper<MerchantCompanyDO> {

    public List<MerchantCompanyDTO> queryMerchantCompany(QueryMerchantCompanyDTO request);
}