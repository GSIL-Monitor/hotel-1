package com.fangcang.base.service.impl;

import com.fangcang.base.domain.ExchangeDO;
import com.fangcang.base.domain.ExchangeLogDO;
import com.fangcang.base.mapper.ExchangeLogMapper;
import com.fangcang.base.mapper.ExchangeMapper;
import com.fangcang.base.request.QueryExchangeDTO;
import com.fangcang.base.request.UpdateExchangeDTO;
import com.fangcang.base.response.ExchangeDTO;
import com.fangcang.base.response.ExchangeLogDTO;
import com.fangcang.base.service.ExchangeService;
import com.fangcang.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Autowired
    private ExchangeLogMapper exchangeLogMapper;

    @Override
    public Map<String, List<ExchangeDTO>> queryAllExchange() {
        Map<String,List<ExchangeDTO>> exchangeMap = new HashMap<String,List<ExchangeDTO>>();
        List<ExchangeDO> exchangeList=exchangeMapper.selectAll();
        for(ExchangeDO exchange : exchangeList){
            String merchantCode = exchange.getMerchantCode();
            List<ExchangeDTO> exchangeDTOList = null;
            if(exchangeMap.containsKey(merchantCode)){
                exchangeDTOList = exchangeMap.get(merchantCode);
            }else{
                exchangeDTOList = new ArrayList<>();
                exchangeMap.put(merchantCode, exchangeDTOList);
            }
            ExchangeDTO exchangeDTO=new ExchangeDTO();
            exchangeDTO.setId(exchange.getId());
            exchangeDTO.setMerchantCode(exchange.getMerchantCode());
            exchangeDTO.setSourceCurrency(exchange.getSourceCurrency());
            exchangeDTO.setTargetCurrency(exchange.getTargetCurrency());
            exchangeDTO.setRate(exchange.getRate());
            exchangeDTOList.add(exchangeDTO);
        }
        return exchangeMap;
    }

    @Override
    public List<ExchangeDTO> queryExchange(QueryExchangeDTO requestDTO) {
        return exchangeMapper.queryExchange(requestDTO);
    }

    @Transactional
    @Override
    public ResponseDTO saveExchange(List<UpdateExchangeDTO> exchangeList) {
        List<ExchangeDO> insertExchangeList = new ArrayList<>();
        List<ExchangeDO> updateExchangeList = new ArrayList<>();
//        List<ExchangeIncr> insertExchangeIncrList = new ArrayList<ExchangeIncr>();
        List<ExchangeLogDO> insertExchangeLogList = new ArrayList<ExchangeLogDO>();
        for(UpdateExchangeDTO updateExchangeDTO : exchangeList) {
//          ExchangeIncr exIncr = new ExchangeIncr();
            ExchangeDO exchangeParam=new ExchangeDO();
            exchangeParam.setMerchantCode(updateExchangeDTO.getMerchantCode());
            exchangeParam.setSourceCurrency(updateExchangeDTO.getSourceCurrency());
            exchangeParam.setTargetCurrency(updateExchangeDTO.getTargetCurrency());
            ExchangeDO exchangeDO=exchangeMapper.selectOne(exchangeParam);
            //保存汇率信息
            BigDecimal oldRate=BigDecimal.ZERO;
            if(exchangeDO != null){
                oldRate=exchangeDO.getRate();
                if (oldRate.equals(updateExchangeDTO.getRate())) {
                    continue;//未做修改
                }
                exchangeDO.setRate(updateExchangeDTO.getRate());
                exchangeDO.setModifier(updateExchangeDTO.getOperator());
                exchangeDO.setModifyTime(new Date());

                //要保存的汇率已存在
//                exIncr.setOptType(OptTypeEnum.MODIFY.key);
                updateExchangeList.add(exchangeDO);
            }
            else {
                exchangeDO=new ExchangeDO();
                exchangeDO.setMerchantCode(updateExchangeDTO.getMerchantCode());
                exchangeDO.setSourceCurrency(updateExchangeDTO.getSourceCurrency());
                exchangeDO.setTargetCurrency(updateExchangeDTO.getTargetCurrency());
                exchangeDO.setRate(updateExchangeDTO.getRate());
                exchangeDO.setCreator(updateExchangeDTO.getOperator());
                exchangeDO.setCreateTime(new Date());

                //要保存的汇率不存在
//                exIncr.setOptType(OptTypeEnum.ADD.key);
                insertExchangeList.add(exchangeDO);
            }
            //保存汇率增量信息
//            exIncr.setCreator(ex.getCreateUserName());
//            exIncr.setModifier(ex.getCreateUserName());
//            exIncr.setMerchantCode(ex.getMerchantCode());
//            exIncr.setSourceCurrency(ex.getSourceCurrency());
//            exIncr.setTargetCurrency(ex.getTargetCurrency());
//            exIncr.setRate(ex.getRate());
//            insertExchangeIncrList.add(exIncr);
            //记录日志
            ExchangeLogDO el = new ExchangeLogDO();
            el.setMerchantCode(updateExchangeDTO.getMerchantCode());
            el.setNewRate(updateExchangeDTO.getRate());
            el.setOldRate(oldRate);
            el.setSourceCurrency(updateExchangeDTO.getSourceCurrency());
            el.setTargetCurrency(updateExchangeDTO.getTargetCurrency());
            el.setCreator(updateExchangeDTO.getOperator());
            el.setCreateTime(new Date());
            insertExchangeLogList.add(el);
        }
        if (!insertExchangeList.isEmpty()) {
            exchangeMapper.insertList(insertExchangeList);
        }
        if (!updateExchangeList.isEmpty()) {
            for (ExchangeDO exchangeDO:updateExchangeList){
                exchangeMapper.updateByPrimaryKeySelective(exchangeDO);
            }
        }
//        if (!insertExchangeIncrList.isEmpty()) {
//            baseDataDao.insertExchangeIncr(insertExchangeIncrList);
//        }
        if (!insertExchangeLogList.isEmpty()) {
            exchangeLogMapper.insertList(insertExchangeLogList);
        }
        return null;
    }

    @Override
    public List<ExchangeLogDTO> queryExchangeLog(QueryExchangeDTO requestDTO) {
        return exchangeLogMapper.queryExchangeLog(requestDTO);
    }
}
