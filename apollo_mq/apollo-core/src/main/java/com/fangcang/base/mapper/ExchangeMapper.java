package com.fangcang.base.mapper;

import com.fangcang.base.domain.ExchangeDO;
import com.fangcang.base.request.QueryExchangeDTO;
import com.fangcang.base.response.ExchangeDTO;
import com.fangcang.common.MyMapper;

import java.util.List;

public interface ExchangeMapper extends MyMapper<ExchangeDO>{

    public List<ExchangeDTO> queryExchange(QueryExchangeDTO requestDTO);
}
