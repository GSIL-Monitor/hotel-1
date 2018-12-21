package com.fangcang.finance.bill.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.bill.domain.AgentBillCurrencyDO;
import com.fangcang.finance.bill.domain.AgentBillImportDO;
import com.fangcang.finance.bill.domain.AgentBillOrderDO;
import com.fangcang.finance.bill.mapper.AgentBillCurrencyMapper;
import com.fangcang.finance.bill.mapper.AgentBillImportMapper;
import com.fangcang.finance.bill.mapper.AgentBillOrderMapper;
import com.fangcang.finance.bill.request.AddMatchedOrderDTO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.DeleteBillImportDTO;
import com.fangcang.finance.bill.request.InsertBillOrderItemDTO;
import com.fangcang.finance.bill.request.QueryBillImportDTO;
import com.fangcang.finance.bill.request.UpdateBillOrderFinanceDTO;
import com.fangcang.finance.bill.response.BillImportDTO;
import com.fangcang.finance.bill.service.AgentBillAutoMatchService;
import com.fangcang.finance.enums.AccountStatusEnum;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.LockOrderItemRequestDTO;
import com.fangcang.finance.financelock.service.FinanceLockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class AgentBillAutoMatchServiceImpl implements AgentBillAutoMatchService {

    @Autowired
    private AgentBillOrderMapper agentBillOrderMapper;

    @Autowired
    private AgentBillImportMapper agentBillImportMapper;

    @Autowired
    private AgentBillCurrencyMapper agentBillCurrencyMapper;

    @Autowired
    private FinanceLockService financeLockService;

    @Override
    public PaginationSupportDTO<BillImportDTO> queryAgentBillImport(QueryBillImportDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<BillImportDTO> list =agentBillImportMapper.queryAgentBillImport(requestDTO);
        PageInfo<BillImportDTO> page = new PageInfo<BillImportDTO>(list);

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
        List<AgentBillImportDO> agentBillImportDOList=new ArrayList<>();
        ExcelUtils.readExcel(in,AgentBillImportDO.class,agentBillImportDOList);
        if (agentBillImportDOList.size()>0){
            Map<String,AgentBillImportDO> distinctMap=new HashMap<>();
            Integer importNo=agentBillImportMapper.queryImportNo();
            for (AgentBillImportDO agentBillImportDO:agentBillImportDOList){
                String key=agentBillImportDO.getChannelCode()+agentBillImportDO.getCustomerOrderCode()+agentBillImportDO.getOrderSum();
                if (distinctMap.containsKey(key)){
                    continue;
                }
                agentBillImportDO.setImportNo(importNo);
                agentBillImportDO.setCreator(operator);
                agentBillImportDO.setCreateTime(new Date());
                distinctMap.put(key,agentBillImportDO);
            }
            agentBillImportMapper.insertList(new ArrayList<>(distinctMap.values()));
            agentBillImportMapper.updateAutoMatchResult(importNo);
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
        agentBillImportMapper.addMatchedOrderToBill(requestDTO);
        InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
        insertBillOrderItemDTO.setBillId(requestDTO.getBillId());
        insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillCanceledOrder(insertBillOrderItemDTO);
        agentBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

        //计算账单应收金额
        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        agentBillCurrencyMapper.delete(agentBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBillCurrency(billIdDTO);

        //标记该订单已导入账单
        AgentBillImportDO agentBillImportParam=new AgentBillImportDO();
        agentBillImportParam.setBillId(requestDTO.getBillId());
        Example example=new Example(AgentBillImportDO.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("importNo",requestDTO.getImportNo());
        criteria.andEqualTo("isMatch",1);
        agentBillImportMapper.updateByExampleSelective(agentBillImportParam,example);

        //更新结算状态为已纳入账单
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.HOLD.key);
        agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        //将订单加锁
        AgentBillOrderDO agentBillOrderParam=new AgentBillOrderDO();
        List<AgentBillOrderDO> agentBillOrderDOList=agentBillOrderMapper.select(agentBillOrderParam);
        FinanceLockOrderRequestDTO financeLockOrderRequestDTO=new FinanceLockOrderRequestDTO();
        financeLockOrderRequestDTO.setOrderList(new ArrayList<>());
        for (AgentBillOrderDO agentBillOrderDO:agentBillOrderDOList){
            LockOrderItemRequestDTO lockOrderItemRequestDTO=new LockOrderItemRequestDTO();
            lockOrderItemRequestDTO.setOrderId(agentBillOrderDO.getOrderId());
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

    public ResponseDTO deleteAgentBillImport(@Valid DeleteBillImportDTO request){
        agentBillImportMapper.deleteAgentBillImport(request.getImportNo());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }
}
