package com.fangcang.merchant.service;

import com.fangcang.merchant.dto.MerchantDTO;
import com.fangcang.merchant.request.QueryMerchantDTO;

import java.util.List;
import java.util.Map;

public interface MerchantService {

    /**
     * 查询商家
     * @param request
     * @return
     */
    public MerchantDTO queryMerchant(QueryMerchantDTO request);

    Map<String,String> queryAllMerchantMap();

    List<MerchantDTO> queryAllMerchant();
}
