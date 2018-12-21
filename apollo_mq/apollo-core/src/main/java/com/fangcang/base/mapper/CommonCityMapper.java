package com.fangcang.base.mapper;

import com.fangcang.b2b.request.QueryMerchantAllCityDTO;
import com.fangcang.base.domain.CommonCityDO;
import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.request.QueryMerchantCityDTO;
import com.fangcang.common.MyMapper;

import java.util.List;

public interface CommonCityMapper extends MyMapper<CommonCityDO>{

    public List<CommonCityDTO> queryCommonCity(String merchantCode);

    public List<CommonCityDTO> queryMerchantCity(QueryMerchantCityDTO request);

    public List<CommonCityDTO> queryB2BMerchantCity(QueryMerchantAllCityDTO queryMerchantAllCityDTO);
}
