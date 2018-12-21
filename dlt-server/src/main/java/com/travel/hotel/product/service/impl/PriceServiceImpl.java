package com.travel.hotel.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.common.dto.product.request.PriceQueryDTO;
import com.travel.common.enums.BreakfastEnum;
import com.travel.hotel.product.dao.PricePOMapper;
import com.travel.hotel.product.entity.PriceDTO;
import com.travel.hotel.product.entity.po.PricePO;
import com.travel.hotel.product.service.PriceService;

/**
 *   2018/1/24.
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PricePOMapper pricePOMapper;

    @Override
    public int batchAddPrice(List<PricePO> pricePOList) {
        for (PricePO po : pricePOList){
            pricePOMapper.insertSelective(po);
        }
        return 1;
    }

    @Override
    public int deleteByPricePlanIdAndSaleDate(Long pricePlanId, Date saleDate) {
        PricePO po = new PricePO();
        po.setPriceplanId(pricePlanId);
        po.setSaleDate(saleDate);
        return pricePOMapper.deleteByPricePlanIdAndSaleDate(po);
    }

    @Override
    public List<PricePO> queryPriceByCondition(Map<String,Object> param) {
        return pricePOMapper.selectByCondition(param);
    }

	@Override
	public PriceDTO queryPriceByCondition(PriceQueryDTO priceQuery) {
		PriceDTO price = new PriceDTO();
		PricePO p = this.pricePOMapper.selectPriceByCondition(priceQuery);
		if (null == p) {
			return null;
		}
		BeanUtils.copyProperties(p, price);
		if (StringUtils.isNotBlank(price.getBreakfastNum())) {
			price.setBreakfastNumText(BreakfastEnum.getValueByKey(price.getBreakfastNum()));
		}
		return price;
	}

    @Override
    public int updatePriceByCondition(PricePO po) {
        return this.pricePOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public int batchUpdatePriceById(List<PricePO> poList) {
        poList.forEach(po -> pricePOMapper.updateByPrimaryKeySelective(po));
        return 1;
    }
}
