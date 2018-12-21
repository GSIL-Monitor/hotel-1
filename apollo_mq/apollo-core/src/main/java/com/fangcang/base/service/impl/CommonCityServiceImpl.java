package com.fangcang.base.service.impl;

import com.fangcang.base.domain.CommonCityDO;
import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.mapper.CommonCityMapper;
import com.fangcang.base.request.QueryMerchantCityDTO;
import com.fangcang.base.request.UpdateCommonCityDTO;
import com.fangcang.base.service.CommonCityService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommonCityServiceImpl implements CommonCityService {

    @Autowired
    private CommonCityMapper commonCityMapper;

    @Transactional
    @Override
    public ResponseDTO updateCommonCity(UpdateCommonCityDTO request) {
        if (request.getCommonCityList()!=null && request.getCommonCityList().size()>0) {
            List<CommonCityDO> insertlist=new ArrayList<>();
            List<String> deletelist=new ArrayList<>();
            for (CommonCityDTO commonCityDTO:request.getCommonCityList()){
                if (commonCityDTO.getIsCommon()==1){
                    CommonCityDO commonCityDO=new CommonCityDO();
                    commonCityDO.setCityCode(commonCityDTO.getCityCode());
                    commonCityDO.setCityName(commonCityDTO.getCityName());
                    commonCityDO.setMerchantCode(request.getMerchantCode());
                    commonCityDO.setCreator(request.getOperator());
                    commonCityDO.setCreateTime(new Date());
                    insertlist.add(commonCityDO);
                }
                deletelist.add(commonCityDTO.getCityCode());
            }
            if (deletelist.size()>0){
                Example example=new Example(CommonCityDO.class);
                Example.Criteria criteria=example.createCriteria();
                criteria.andEqualTo("merchantCode",request.getMerchantCode());
                criteria.andIn("cityCode",deletelist);
                commonCityMapper.deleteByExample(example);
            }
            if (insertlist.size()>0){
                commonCityMapper.insertList(insertlist);
            }
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public List<CommonCityDTO> queryCommonCity(String merchantCode) {
        return commonCityMapper.queryCommonCity(merchantCode);
    }

    @Override
    public PaginationSupportDTO<CommonCityDTO> queryMerchantCityForPage(QueryMerchantCityDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<CommonCityDTO> list =commonCityMapper.queryMerchantCity(request);
        PageInfo<CommonCityDTO> page = new PageInfo<CommonCityDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }
}
