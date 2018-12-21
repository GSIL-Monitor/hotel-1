package com.fangcang.finance.bill.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.domain.CheckOutCurrencyDO;
import com.fangcang.finance.bill.mapper.SupplyCreditOrderMapper;
import com.fangcang.finance.bill.request.QueryCheckOutCurrencyDTO;
import com.fangcang.finance.bill.request.QueryCheckOutDTO;
import com.fangcang.finance.bill.request.UpdateOrderFinanceDTO;
import com.fangcang.finance.bill.response.CheckOutDTO;
import com.fangcang.finance.bill.response.CheckOutOrderDTO;
import com.fangcang.finance.bill.response.CheckOutOrderItemDTO;
import com.fangcang.finance.bill.service.SupplyCreditOrderService;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplyCreditOrderServiceImpl implements SupplyCreditOrderService {

    @Autowired
    private SupplyCreditOrderMapper supplyCreditOrderMapper;

    @Override
    public ResponseDTO updateBatchOrderFinance(List<UpdateOrderFinanceDTO> list) {
        for (UpdateOrderFinanceDTO updateOrderFinanceDTO:list){
            supplyCreditOrderMapper.updateOrderFinance(updateOrderFinanceDTO);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public PaginationSupportDTO<CheckOutDTO> querySupplyCheckOut(QueryCheckOutDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<CheckOutDTO> list =supplyCreditOrderMapper.querySupplyCheckOut(requestDTO);
        PageInfo<CheckOutDTO> page = new PageInfo<CheckOutDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());

        if(list.size()>0){
            List<String> orgCodeList=new ArrayList<>();
            Map<String,CheckOutDTO> checkOutDTOMap=new HashMap();
            for (CheckOutDTO checkOutDTO:list){
                orgCodeList.add(checkOutDTO.getOrgCode());
                checkOutDTO.setMultipleCurrencyList(new ArrayList<>());
                checkOutDTOMap.put(checkOutDTO.getOrgCode(),checkOutDTO);
            }
            QueryCheckOutCurrencyDTO queryCheckOutCurrencyDTO=new QueryCheckOutCurrencyDTO();
            BeanUtils.copyProperties(requestDTO,queryCheckOutCurrencyDTO);
            queryCheckOutCurrencyDTO.setOrgCodelist(orgCodeList);
            List<CheckOutCurrencyDO> checkOutCurrencyDOList =supplyCreditOrderMapper.querySupplyCheckOutCurrency(queryCheckOutCurrencyDTO);
            for (CheckOutCurrencyDO checkOutCurrencyDO:checkOutCurrencyDOList){
                checkOutDTOMap.get(checkOutCurrencyDO.getOrgCode()).getMultipleCurrencyList()
                        .add(new MultipleCurrencyAmountDTO(checkOutCurrencyDO.getCurrency(),checkOutCurrencyDO.getAmount(),null,null));
            }
        }
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<CheckOutOrderDTO> queryCheckOutOrder(QueryCheckOutDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<CheckOutOrderDTO> list =supplyCreditOrderMapper.queryCheckOutOrder(requestDTO);
        PageInfo<CheckOutOrderDTO> page = new PageInfo<CheckOutOrderDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<CheckOutOrderItemDTO> queryCheckOutOrderItem(List<String> orderCodeList) {
        return null;
    }
}
