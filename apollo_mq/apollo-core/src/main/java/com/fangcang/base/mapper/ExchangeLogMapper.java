package com.fangcang.base.mapper;

import com.fangcang.base.domain.ExchangeLogDO;
import com.fangcang.base.request.QueryExchangeDTO;
import com.fangcang.base.response.ExchangeLogDTO;
import com.fangcang.common.MyMapper;

import java.util.List;

public interface ExchangeLogMapper extends MyMapper<ExchangeLogDO>{

    public List<ExchangeLogDTO> queryExchangeLog(QueryExchangeDTO requestDTO);
}
