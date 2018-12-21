package com.fangcang.merchant.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.MerchantDO;
import com.fangcang.merchant.dto.MerchantDTO;
import com.fangcang.merchant.request.QueryMerchantDTO;

public interface MerchantMapper extends MyMapper<MerchantDO>{

    public MerchantDTO queryMerchant(QueryMerchantDTO request);
}
