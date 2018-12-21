package com.travel.finance.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.hotel.product.entity.po.ExchangeRateLogPO;
import com.travel.hotel.product.entity.po.ExchangeRatePO;

import java.util.List;

/**
 *   2018/3/5.
 */
public interface ExchangeRateService {

    List<ExchangeRatePO> queryList();

    int updateExchangeRate(ExchangeRatePO exchangeRatePO);

    PaginationDTO<ExchangeRateLogPO> queryLogForPage(ExchangeRateLogPO exchangeRateLogPO);
}
