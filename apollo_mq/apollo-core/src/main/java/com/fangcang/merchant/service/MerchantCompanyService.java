package com.fangcang.merchant.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.merchant.response.MerchantCompanyDTO;
import com.fangcang.merchant.request.AddMerchantCompanyDTO;
import com.fangcang.merchant.request.QueryMerchantCompanyDTO;
import com.fangcang.merchant.request.UpdateMerchantCompanyApplyDTO;
import com.fangcang.merchant.request.UpdateMerchantCompanyDTO;

import java.util.List;

public interface MerchantCompanyService {

    /**
     * 添加商家公司文件
     */
    public ResponseDTO addMerchantCompany(AddMerchantCompanyDTO request);

    /**
     * 更新商家公司文件
     */
    public ResponseDTO updateMerchantCompany(UpdateMerchantCompanyDTO request);

    /**
     * 修改适用供应商
     */
    public ResponseDTO updateMerchantCompanyApply(UpdateMerchantCompanyApplyDTO request);

    /**
     * 查询商家公司文件
     */
    public List<MerchantCompanyDTO> queryMerchantCompany(QueryMerchantCompanyDTO request);
}
