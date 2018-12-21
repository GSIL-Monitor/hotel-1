package com.fangcang.base.service;

import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.request.QueryMerchantCityDTO;
import com.fangcang.base.request.UpdateCommonCityDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;

import java.util.List;

public interface CommonCityService {

    /**
     * 保存常用城市
     * @param request
     * @return
     */
    public ResponseDTO updateCommonCity(UpdateCommonCityDTO request);

    /**
     * 查询商家所有常用城市
     * @param merchantCode
     * @return
     */
    public List<CommonCityDTO> queryCommonCity(String merchantCode);

    /**
     * 分页查询商家城市
     * @return
     */
    public PaginationSupportDTO<CommonCityDTO> queryMerchantCityForPage(QueryMerchantCityDTO request);
}
