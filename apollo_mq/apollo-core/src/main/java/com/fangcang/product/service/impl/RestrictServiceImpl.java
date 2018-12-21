package com.fangcang.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.product.domain.RestrictDO;
import com.fangcang.product.dto.RestrictDTO;
import com.fangcang.product.mapper.RestrictMapper;
import com.fangcang.product.service.RestrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by Vinney on 2018/10/17.
 */
@Slf4j
@Service
public class RestrictServiceImpl implements RestrictService {

    @Autowired
    private RestrictMapper restrictMapper;

    @Override
    public void saveRestrictByRatePlanId(RestrictDTO restrictDTO) {

        if (null == restrictDTO.getRatePlanId()){
            log.error("保存条款失败，价格计划ID为空。{}", JSON.toJSONString(restrictDTO));
            return ;
        }

        Example selectExample = new Example(RestrictDO.class);
        Example.Criteria deleteCriteria = selectExample.createCriteria();
        deleteCriteria.andEqualTo("ratePlanId",restrictDTO.getRatePlanId());
        List<RestrictDO> existRestrictDOList = restrictMapper.selectByExample(selectExample);

        RestrictDO restrictDO = PropertyCopyUtil.transfer(restrictDTO,RestrictDO.class);
        if (!CollectionUtils.isEmpty(existRestrictDOList) && existRestrictDOList.size() > 0){
            restrictDO.setModifyDate(new Date());
            Example updateExample = new Example(RestrictDO.class);
            Example.Criteria updateCriteria = updateExample.createCriteria();
            updateCriteria.andEqualTo("ratePlanId",restrictDTO.getRatePlanId());
            restrictMapper.updateByExampleSelective(restrictDO,updateExample);
        } else{
            restrictDO.setCreateTime(new Date());
            restrictMapper.insert(restrictDO);
        }
    }

    @Override
    public RestrictDTO queryRestrictByRatePlanId(RestrictDTO restrictDTO) {
        RestrictDTO resultDTO = new RestrictDTO();
        Example selectExample = new Example(RestrictDO.class);
        Example.Criteria selectCriteria = selectExample.createCriteria();
        selectCriteria.andEqualTo("ratePlanId",restrictDTO.getRatePlanId());
        List<RestrictDO> existRestrictDOList = restrictMapper.selectByExample(selectExample);
        if (!CollectionUtils.isEmpty(existRestrictDOList)){
            resultDTO = PropertyCopyUtil.transfer(existRestrictDOList.get(0),RestrictDTO.class);
        }
        return resultDTO;
    }

    @Override
    public void deleteRestrictByRatePlanId(Long ratePlanId) {
        if (null == ratePlanId){
            log.error("删除条款错误:价格计划为空。{}",JSON.toJSONString(ratePlanId));
            return ;
        }

        Example deleteExample = new Example(RestrictDO.class);
        Example.Criteria deleteCriteria = deleteExample.createCriteria();
        deleteCriteria.andEqualTo("ratePlanId",ratePlanId);
        restrictMapper.deleteByExample(deleteExample);
    }
}
