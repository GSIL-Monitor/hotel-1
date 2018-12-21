package com.fangcang.finance.financeorder.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.dto.PayItemAttchDTO;
import com.fangcang.finance.dto.PayItemDTO;
import com.fangcang.finance.financeorder.domain.PayItemAttchDO;
import com.fangcang.finance.financeorder.domain.PayItemDO;
import com.fangcang.finance.financeorder.mapper.PayItemAttchMapper;
import com.fangcang.finance.financeorder.mapper.PayItemMapper;
import com.fangcang.finance.financeorder.request.QueryPayItemDTO;
import com.fangcang.finance.financeorder.service.PayItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayItemServiceImpl implements PayItemService {

    @Autowired
    private PayItemMapper payItemMapper;

    @Autowired
    private PayItemAttchMapper payItemAttchMapper;

    @Override
    @Transactional
    public ResponseDTO savePayItem(PayItemDTO requestDTO) {
        PayItemDO payItemDO=new PayItemDO();
        BeanUtils.copyProperties(requestDTO,payItemDO);
        payItemDO.setId(null);
        payItemDO.setArrivalDate(DateUtil.stringToDateWithHms(requestDTO.getArrivalDate()));
        payItemMapper.insert(payItemDO);
        if (requestDTO.getPayItemAttchDTOList()!=null && requestDTO.getPayItemAttchDTOList().size()>0){
            List<PayItemAttchDO> payItemAttchDOList=new ArrayList<>();
            for (PayItemAttchDTO payItemAttchDTO:requestDTO.getPayItemAttchDTOList()){
                PayItemAttchDO payItemAttchDO=new PayItemAttchDO();
                BeanUtils.copyProperties(payItemAttchDTO,payItemAttchDO);
                payItemAttchDO.setPayItemId(payItemDO.getId());
                payItemAttchDOList.add(payItemAttchDO);
            }
            payItemAttchMapper.insertList(payItemAttchDOList);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public List<PayItemDTO> queryPayItem(QueryPayItemDTO requestDTO) {
        List<PayItemDTO> payItemDTOList=payItemMapper.queryPayItem(requestDTO);
        if (payItemDTOList!=null && payItemDTOList.size()>0){
            List<Integer> payItemIdList=new ArrayList<>();
            Map<Integer,PayItemDTO> payItemDTOMap=new HashMap();
            for (PayItemDTO payItemDTO:payItemDTOList){
                payItemIdList.add(payItemDTO.getId());
                payItemDTOMap.put(payItemDTO.getId(),payItemDTO);
                payItemDTO.setPayItemAttchDTOList(new ArrayList<>());
            }
            List<PayItemAttchDTO> payItemAttchDTOList=payItemAttchMapper.queryPayItemAttch(payItemIdList);
            for (PayItemAttchDTO payItemAttchDTO:payItemAttchDTOList){
                payItemDTOMap.get(payItemAttchDTO.getPayItemId()).getPayItemAttchDTOList().add(payItemAttchDTO);
            }
        }
        return payItemDTOList;
    }
}
