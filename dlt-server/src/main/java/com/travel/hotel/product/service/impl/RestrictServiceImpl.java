package com.travel.hotel.product.service.impl;

import com.travel.common.utils.DateUtil;
import com.travel.hotel.product.dao.HtlRestrictPOMapper;
import com.travel.hotel.product.entity.po.HtlRestrictPO;
import com.travel.hotel.product.service.RestrictService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/24.
 */
@Service
public class RestrictServiceImpl implements RestrictService {

    @Autowired
    private HtlRestrictPOMapper htlRestrictPOMapper;

    @Override
    public int batchAddRestrict(List<HtlRestrictPO> htlRestrictPOList) {

        if (CollectionUtils.isEmpty(htlRestrictPOList)){
            return 0;
        }

        htlRestrictPOList.forEach(po -> htlRestrictPOMapper.insertSelective(po));

        return 1;
    }

    @Override
    public int batchUpdateRestrict(List<HtlRestrictPO> htlRestrictPOList) {
        htlRestrictPOList.forEach(htlRestrictPO -> htlRestrictPOMapper.updateByPrimaryKeySelective(htlRestrictPO));
        return 1;
    }

    @Override
    public int deleteByPricePlanIdAndSaleDate(Long pricePlanId, Date saleDate) {
        HtlRestrictPO po = new HtlRestrictPO();
        po.setPriceplanId(pricePlanId);
        po.setSaleDate(saleDate);
        return htlRestrictPOMapper.deleteByPricePlanIdAndSaleDate(po);
    }

    @Override
    public List<HtlRestrictPO> queryListByPricePlanIdAndSaleDate(Long pricePlanId, Date beginDate, Date endDate) {
        Map paramMap = new HashMap();
        paramMap.put("priceplanId",pricePlanId);
        paramMap.put("checkInDate",beginDate);
        paramMap.put("checkOutDate", DateUtil.getAfterDate(endDate));
        return htlRestrictPOMapper.selectByCondition(paramMap);
    }
}
