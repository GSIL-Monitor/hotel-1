package com.fangcang.finance.bill.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.bill.domain.SupplyBillCurrencyDO;
import com.fangcang.finance.bill.domain.SupplyBillImportDO;
import com.fangcang.finance.bill.domain.SupplyBillOrderDO;
import com.fangcang.finance.bill.mapper.SupplyBillCurrencyMapper;
import com.fangcang.finance.bill.mapper.SupplyBillImportMapper;
import com.fangcang.finance.bill.mapper.SupplyBillOrderMapper;
import com.fangcang.finance.bill.request.AddMatchedOrderDTO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.DeleteBillImportDTO;
import com.fangcang.finance.bill.request.InsertBillOrderItemDTO;
import com.fangcang.finance.bill.request.QuerySupplyBillImportDTO;
import com.fangcang.finance.bill.request.UpdateBillOrderFinanceDTO;
import com.fangcang.finance.bill.response.SupplyBillImportDTO;
import com.fangcang.finance.bill.service.SupplyBillAutoMatchService;
import com.fangcang.finance.enums.AccountStatusEnum;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.LockOrderItemRequestDTO;
import com.fangcang.finance.financelock.service.FinanceLockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SupplyBillAutoMatchServiceImpl implements SupplyBillAutoMatchService {

    @Autowired
    private SupplyBillOrderMapper supplyBillOrderMapper;

    @Autowired
    private SupplyBillImportMapper supplyBillImportMapper;

    @Autowired
    private SupplyBillCurrencyMapper supplyBillCurrencyMapper;

    @Autowired
    private FinanceLockService financeLockService;

    @Override
    public PaginationSupportDTO<SupplyBillImportDTO> querySupplyBillImport(QuerySupplyBillImportDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<SupplyBillImportDTO> list =supplyBillImportMapper.querySupplyBillImport(requestDTO);
        PageInfo<SupplyBillImportDTO> page = new PageInfo<SupplyBillImportDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    @Transactional
    public ResponseDTO autoMatchCustomerOrder(InputStream in,String operator){
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<SupplyBillImportDO> supplyBillImportDOList=new ArrayList<>();
        ExcelUtils.readExcel(in,SupplyBillImportDO.class,supplyBillImportDOList);
        if (supplyBillImportDOList.size()>0){
            Map<String,SupplyBillImportDO> distinctMap=new HashMap<>();
            Integer importNo=supplyBillImportMapper.queryImportNo();
            for (SupplyBillImportDO supplyBillImportDO:supplyBillImportDOList){
                String key=supplyBillImportDO.getHotelName()+
                        supplyBillImportDO.getCheckinDate()+
                        supplyBillImportDO.getCheckoutDate()+
                        supplyBillImportDO.getGuest()+
                        supplyBillImportDO.getOrderSum();
                if (distinctMap.containsKey(key)){
                    continue;
                }
                supplyBillImportDO.setImportNo(importNo);
                supplyBillImportDO.setCreator(operator);
                supplyBillImportDO.setCreateTime(new Date());
                distinctMap.put(key,supplyBillImportDO);
            }
            supplyBillImportMapper.insertList(new ArrayList<>(distinctMap.values()));
            supplyBillImportMapper.updateAutoMatchResult(importNo);
            responseDTO.setModel(importNo);
        }else{
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("导入的文件没有订单");
        }
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO addMatchedOrderToBill(@Valid AddMatchedOrderDTO requestDTO){
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        supplyBillImportMapper.addMatchedOrderToBill(requestDTO);
        InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
        insertBillOrderItemDTO.setBillId(requestDTO.getBillId());
        insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
        supplyBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
        supplyBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
        supplyBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

        //计算账单应收金额
        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        supplyBillCurrencyMapper.delete(supplyBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBillCurrency(billIdDTO);

        //标记该订单已导入账单
        SupplyBillImportDO supplyBillImportParam=new SupplyBillImportDO();
        supplyBillImportParam.setBillId(requestDTO.getBillId());
        Example example=new Example(SupplyBillImportDO.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("importNo",requestDTO.getImportNo());
        criteria.andEqualTo("isMatch",1);
        supplyBillImportMapper.updateByExampleSelective(supplyBillImportParam,example);

        //更新结算状态为已纳入账单
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.HOLD.key);
        supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        //将订单加锁
        SupplyBillOrderDO supplyBillOrderParam=new SupplyBillOrderDO();
        List<SupplyBillOrderDO> supplyBillOrderDOList=supplyBillOrderMapper.select(supplyBillOrderParam);
        FinanceLockOrderRequestDTO financeLockOrderRequestDTO=new FinanceLockOrderRequestDTO();
        financeLockOrderRequestDTO.setOrderList(new ArrayList<>());
        for (SupplyBillOrderDO supplyBillOrderDO:supplyBillOrderDOList){
            LockOrderItemRequestDTO lockOrderItemRequestDTO=new LockOrderItemRequestDTO();
            lockOrderItemRequestDTO.setOrderId(supplyBillOrderDO.getOrderId());
            lockOrderItemRequestDTO.setLockType(1);
            financeLockOrderRequestDTO.getOrderList().add(lockOrderItemRequestDTO);
        }
        financeLockOrderRequestDTO.setCreator(requestDTO.getOperator());
        financeLockOrderRequestDTO.setCreateTime(new Date());
        financeLockOrderRequestDTO.setModifier(requestDTO.getOperator());
        financeLockOrderRequestDTO.setModifyTime(new Date());
        financeLockService.lockOrder(financeLockOrderRequestDTO);
        return responseDTO;
    }

    public ResponseDTO deleteSupplyBillImport(@Valid DeleteBillImportDTO request){
        supplyBillImportMapper.deleteSupplyBillImport(request.getImportNo());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }
}
