package com.travel.finance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.PageConvert;
import com.travel.finance.service.ExchangeRateService;
import com.travel.hotel.product.dao.ExchangeRateLogPOMapper;
import com.travel.hotel.product.dao.ExchangeRatePOMapper;
import com.travel.hotel.product.entity.PricePlanDTO;
import com.travel.hotel.product.entity.po.ExchangeRateLogPO;
import com.travel.hotel.product.entity.po.ExchangeRatePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *   2018/3/5.
 */
@Service("exchangeRateService")
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRatePOMapper exchangeRatePOMapper;

    @Autowired
    private ExchangeRateLogPOMapper exchangeRateLogPOMapper;

    @Override
    public List<ExchangeRatePO> queryList() {
        return exchangeRatePOMapper.selectAll();
    }

    @Transactional
    @Override
    public int updateExchangeRate(ExchangeRatePO exchangeRatePO) {
        int result = exchangeRatePOMapper.updateByPrimaryKeySelective(exchangeRatePO);
        ExchangeRateLogPO exchangeRateLogPO = BeanCopy.copyProperties(exchangeRatePO,ExchangeRateLogPO.class);
        exchangeRateLogPOMapper.insert(exchangeRateLogPO);
        return result;
    }

    @Override
    public PaginationDTO<ExchangeRateLogPO> queryLogForPage(ExchangeRateLogPO queryPO) {
        PageHelper.startPage(queryPO.getCurrentPage(), queryPO.getPageSize());
        List<ExchangeRateLogPO> logList =  exchangeRateLogPOMapper.selectByCondition(queryPO);
        PageInfo<ExchangeRateLogPO> pageInfo = new PageInfo<ExchangeRateLogPO>(logList);
        return PageConvert.getPaginationSupport(pageInfo);
    }


}
