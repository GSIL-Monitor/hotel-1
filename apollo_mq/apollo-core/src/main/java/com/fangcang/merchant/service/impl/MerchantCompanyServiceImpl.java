package com.fangcang.merchant.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.merchant.domain.MerchantCompanyApplyDO;
import com.fangcang.merchant.domain.MerchantCompanyDO;
import com.fangcang.merchant.mapper.MerchantCompanyApplyMapper;
import com.fangcang.merchant.mapper.MerchantCompanyMapper;
import com.fangcang.merchant.request.AddMerchantCompanyDTO;
import com.fangcang.merchant.request.QueryMerchantCompanyDTO;
import com.fangcang.merchant.request.UpdateMerchantCompanyApplyDTO;
import com.fangcang.merchant.request.UpdateMerchantCompanyDTO;
import com.fangcang.merchant.response.MerchantCompanyApplyDTO;
import com.fangcang.merchant.response.MerchantCompanyDTO;
import com.fangcang.merchant.service.MerchantCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantCompanyServiceImpl implements MerchantCompanyService {

    @Autowired
    private MerchantCompanyMapper merchantCompanyMapper;

    @Autowired
    private MerchantCompanyApplyMapper merchantCompanyApplyMapper;

    @Override
    @Transactional
    public ResponseDTO addMerchantCompany(@Valid AddMerchantCompanyDTO request) {
        MerchantCompanyDO merchantCompanyDO=new MerchantCompanyDO();
        BeanUtils.copyProperties(request,merchantCompanyDO);
        merchantCompanyDO.setCreateTime(new Date());
        merchantCompanyDO.setCreator(request.getOperator());
        merchantCompanyMapper.insert(merchantCompanyDO);
        if (request.getApplySupply()==1 && request.getSupplyIdList()!=null && request.getSupplyIdList().size()>0){
            List<MerchantCompanyApplyDO> companyApplyDOList=new ArrayList<>();
            for (Integer supplyId:request.getSupplyIdList()){
                MerchantCompanyApplyDO merchantCompanyApplyDO=new MerchantCompanyApplyDO();
                merchantCompanyApplyDO.setCompanyId(merchantCompanyDO.getId());
                merchantCompanyApplyDO.setSupplyId(supplyId);
                merchantCompanyApplyDO.setCreator(request.getOperator());
                merchantCompanyApplyDO.setCreateTime(new Date());
                companyApplyDOList.add(merchantCompanyApplyDO);
            }
            merchantCompanyApplyMapper.insertList(companyApplyDOList);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO updateMerchantCompany(@Valid UpdateMerchantCompanyDTO request) {
        MerchantCompanyDO merchantCompanyDO=new MerchantCompanyDO();
        BeanUtils.copyProperties(request,merchantCompanyDO);
        merchantCompanyDO.setModifyTime(new Date());
        merchantCompanyDO.setModifier(request.getOperator());
        merchantCompanyMapper.updateByPrimaryKeySelective(merchantCompanyDO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO updateMerchantCompanyApply(@Valid UpdateMerchantCompanyApplyDTO request) {
        MerchantCompanyDO merchantCompanyParam=new MerchantCompanyDO();
        merchantCompanyParam.setId(request.getCompanyId());
        merchantCompanyParam.setApplySupply(request.getApplySupply());
        merchantCompanyParam.setModifyTime(new Date());
        merchantCompanyParam.setModifier(request.getOperator());
        merchantCompanyMapper.updateByPrimaryKeySelective(merchantCompanyParam);
        MerchantCompanyApplyDO merchantCompanyApplyParam=new MerchantCompanyApplyDO();
        merchantCompanyApplyParam.setCompanyId(request.getCompanyId());
        merchantCompanyApplyMapper.delete(merchantCompanyApplyParam);
        if (request.getApplySupply()==1 && request.getSupplyIdList()!=null && request.getSupplyIdList().size()>0){
            List<MerchantCompanyApplyDO> companyApplyDOList=new ArrayList<>();
            for (Integer supplyId:request.getSupplyIdList()){
                MerchantCompanyApplyDO merchantCompanyApplyDO=new MerchantCompanyApplyDO();
                merchantCompanyApplyDO.setCompanyId(request.getCompanyId());
                merchantCompanyApplyDO.setSupplyId(supplyId);
                merchantCompanyApplyDO.setCreator(request.getOperator());
                merchantCompanyApplyDO.setCreateTime(new Date());
                companyApplyDOList.add(merchantCompanyApplyDO);
            }
            merchantCompanyApplyMapper.insertList(companyApplyDOList);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public List<MerchantCompanyDTO> queryMerchantCompany(QueryMerchantCompanyDTO request) {
        List<MerchantCompanyDTO> merchantCompanyDTOList=merchantCompanyMapper.queryMerchantCompany(request);
        //组装适用供应商信息
        if (merchantCompanyDTOList.size()>0){
            List<Integer> companyIdList=new ArrayList<>();
            Map<Integer,MerchantCompanyDTO> merchantCompanyDTOMap=new HashMap<>();
            for (MerchantCompanyDTO companyDTO:merchantCompanyDTOList){
                companyIdList.add(companyDTO.getId());
                companyDTO.setCompanyApplyDTOList(new ArrayList<>());
                merchantCompanyDTOMap.put(companyDTO.getId(),companyDTO);
            }
            List<MerchantCompanyApplyDTO> companyApplyDTOList=merchantCompanyApplyMapper.queryMerchantCompanyApply(companyIdList);
            for (MerchantCompanyApplyDTO companyApplyDTO:companyApplyDTOList){
                merchantCompanyDTOMap.get(companyApplyDTO.getCompanyId()).getCompanyApplyDTOList().add(companyApplyDTO);
            }
        }
        return merchantCompanyDTOList;
    }
}
