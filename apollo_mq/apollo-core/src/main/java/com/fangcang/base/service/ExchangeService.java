package com.fangcang.base.service;

import com.fangcang.base.request.QueryExchangeDTO;
import com.fangcang.base.request.UpdateExchangeDTO;
import com.fangcang.base.response.ExchangeDTO;
import com.fangcang.base.response.ExchangeLogDTO;
import com.fangcang.common.ResponseDTO;

import java.util.List;
import java.util.Map;

public interface ExchangeService {

    /**
     * 查询汇率 Map<商家编码,汇率>
     * @return
     */
    public Map<String,List<ExchangeDTO>> queryAllExchange();

    /**
     * 条件查询
     */
    public List<ExchangeDTO> queryExchange(QueryExchangeDTO requestDTO);

    /**
     * 保存/更新汇率
     * @param exchangeList
     */
    public ResponseDTO saveExchange(List<UpdateExchangeDTO> exchangeList);

    /**
     * 根据汇率id查询汇率修改日期
     */
    public List<ExchangeLogDTO> queryExchangeLog(QueryExchangeDTO requestDTO);
}
