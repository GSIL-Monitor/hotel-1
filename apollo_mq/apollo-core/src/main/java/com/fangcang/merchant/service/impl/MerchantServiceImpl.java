package com.fangcang.merchant.service.impl;

import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.merchant.domain.MerchantDO;
import com.fangcang.merchant.dto.MerchantDTO;
import com.fangcang.merchant.mapper.MerchantMapper;
import com.fangcang.merchant.request.QueryMerchantDTO;
import com.fangcang.merchant.service.MerchantService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public MerchantDTO queryMerchant(QueryMerchantDTO request){
        if (request.getMerchantId()==null && StringUtil.isValidString(request.getMerchantCode())
                && StringUtil.isValidString(request.getDomain())
                && StringUtil.isValidString(request.getEbkDomain())){
            throw new ParameterException("参数不正确");
        }
        return merchantMapper.queryMerchant(request);
    }

    @Override
    public Map<String, String> queryAllMerchantMap() {
        Map<String,String> merchantMap = new HashedMap();
        List<MerchantDTO> merchantDTOList = this.queryAllMerchant();
        for (MerchantDTO dto : merchantDTOList){
            merchantMap.put(dto.getMerchantCode(),dto.getMerchantName());
        }
        return merchantMap;
    }

    @Override
    public List<MerchantDTO> queryAllMerchant() {
        List<MerchantDO> merchantDOList = merchantMapper.selectAll();
        return PropertyCopyUtil.transferArray(merchantDOList,MerchantDTO.class);
    }
}
