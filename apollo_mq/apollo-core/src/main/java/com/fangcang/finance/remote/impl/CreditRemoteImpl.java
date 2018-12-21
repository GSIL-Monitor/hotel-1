package com.fangcang.finance.remote.impl;

import com.fangcang.agent.request.DeductAgentCreditLineRequestDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.request.QueryCheckOutDTO;
import com.fangcang.finance.bill.request.UpdateOrderFinanceDTO;
import com.fangcang.finance.bill.response.CheckOutOrderDTO;
import com.fangcang.finance.bill.service.AgentCreditOrderService;
import com.fangcang.finance.bill.service.SupplyCreditOrderService;
import com.fangcang.finance.enums.AccountStatusEnum;
import com.fangcang.finance.remote.CreditRemote;
import com.fangcang.finance.remote.request.CreditOrderPayDTO;
import com.fangcang.supplier.request.DeductSupplierCreditLineRequestDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class CreditRemoteImpl implements CreditRemote {

    @Autowired
    private AgentService agentService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private AgentCreditOrderService agentCreditOrderService;

    @Autowired
    private SupplyCreditOrderService supplyCreditOrderService;

    @Override
    @Transactional
    public ResponseDTO orderPay(@Valid CreditOrderPayDTO request) {
        log.info("orderPay param:"+request);
        ResponseDTO responseDTO=null;
        DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO=new DeductAgentCreditLineRequestDTO();
        agentCreditLineRequestDTO.setAgentCode(request.getOrgCode());
        agentCreditLineRequestDTO.setDebuctAmount(request.getAmount());
        agentCreditLineRequestDTO.setOperateType(1);
        agentCreditLineRequestDTO.setMerchantCode(request.getMerchantCode());
        agentCreditLineRequestDTO.setOrderCode(request.getOrderCode());
        agentCreditLineRequestDTO.setCurrency(request.getCurrency());
        agentCreditLineRequestDTO.setOperator(request.getOperator());
        responseDTO=agentService.deductAgentCreditLine(agentCreditLineRequestDTO);

        if (responseDTO.getResult()== ResultCodeEnum.SUCCESS.code){
            //更新结算状态
            QueryCheckOutDTO queryCheckOutDTO=new QueryCheckOutDTO();
            queryCheckOutDTO.setOrderCode(request.getOrderCode());
            queryCheckOutDTO.setMerchantCode(request.getMerchantCode());
            PaginationSupportDTO<CheckOutOrderDTO> paginationSupportDTO=agentCreditOrderService.queryCheckOutOrder(queryCheckOutDTO);
            CheckOutOrderDTO checkOutOrderDTO=paginationSupportDTO.getItemList().get(0);
            if (checkOutOrderDTO.getAccountStatus()!=AccountStatusEnum.HOLD.key){
                if (BigDecimal.ZERO.compareTo(checkOutOrderDTO.getReceivableAmount())==0){
                    agentCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CHECKED.key,1,null))));
                }else{
                    agentCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CANCHECK.key,0,null))));
                }
            }
        }
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO orderRefund(@Valid CreditOrderPayDTO request) {
        log.info("orderRefund param:"+request);
        ResponseDTO responseDTO=null;
        DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO=new DeductAgentCreditLineRequestDTO();
        agentCreditLineRequestDTO.setAgentCode(request.getOrgCode());
        agentCreditLineRequestDTO.setDebuctAmount(request.getAmount());
        agentCreditLineRequestDTO.setOperateType(2);
        agentCreditLineRequestDTO.setMerchantCode(request.getMerchantCode());
        agentCreditLineRequestDTO.setOrderCode(request.getOrderCode());
        agentCreditLineRequestDTO.setCurrency(request.getCurrency());
        agentCreditLineRequestDTO.setOperator(request.getOperator());
        responseDTO=agentService.deductAgentCreditLine(agentCreditLineRequestDTO);

        if (responseDTO.getResult()== ResultCodeEnum.SUCCESS.code){
            //更新结算状态
            QueryCheckOutDTO queryCheckOutDTO=new QueryCheckOutDTO();
            queryCheckOutDTO.setOrderCode(request.getOrderCode());
            queryCheckOutDTO.setMerchantCode(request.getMerchantCode());
            PaginationSupportDTO<CheckOutOrderDTO> paginationSupportDTO=agentCreditOrderService.queryCheckOutOrder(queryCheckOutDTO);
            CheckOutOrderDTO checkOutOrderDTO=paginationSupportDTO.getItemList().get(0);
            if (checkOutOrderDTO.getAccountStatus()!=AccountStatusEnum.HOLD.key){
                if (BigDecimal.ZERO.compareTo(checkOutOrderDTO.getReceivableAmount())==0){
                    agentCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CHECKED.key,1,null))));
                }else{
                    agentCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CANCHECK.key,0,null))));
                }
            }
        }
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO supplierOrderPay(@Valid CreditOrderPayDTO request) {
        log.info("supplierOrderPay param:"+request);
        ResponseDTO responseDTO=null;
        DeductSupplierCreditLineRequestDTO supplierCreditLineRequestDTO=new DeductSupplierCreditLineRequestDTO();
        supplierCreditLineRequestDTO.setSupplierCode(request.getOrgCode());
        supplierCreditLineRequestDTO.setDebuctAmount(request.getAmount());
        supplierCreditLineRequestDTO.setOperateType(1);
        supplierCreditLineRequestDTO.setMerchantCode(request.getMerchantCode());
        supplierCreditLineRequestDTO.setOrderCode(request.getOrderCode());
        supplierCreditLineRequestDTO.setCurrency(request.getCurrency());
        supplierCreditLineRequestDTO.setOperator(request.getOperator());
        responseDTO=supplyService.deductSupplierCreditLine(supplierCreditLineRequestDTO);

        if (responseDTO.getResult()== ResultCodeEnum.SUCCESS.code){
            //更新结算状态
            QueryCheckOutDTO queryCheckOutDTO=new QueryCheckOutDTO();
            queryCheckOutDTO.setOrderCode(request.getOrderCode());
            queryCheckOutDTO.setMerchantCode(request.getMerchantCode());
            PaginationSupportDTO<CheckOutOrderDTO> paginationSupportDTO=supplyCreditOrderService.queryCheckOutOrder(queryCheckOutDTO);
            CheckOutOrderDTO checkOutOrderDTO=paginationSupportDTO.getItemList().get(0);
            if (checkOutOrderDTO.getAccountStatus()!=AccountStatusEnum.HOLD.key){
                if (BigDecimal.ZERO.compareTo(checkOutOrderDTO.getReceivableAmount())==0){
                    supplyCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CHECKED.key,1,null))));
                }else{
                    supplyCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CANCHECK.key,0,null))));
                }
            }
        }
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO supplierOrderRefund(@Valid CreditOrderPayDTO request) {
        log.info("supplierOrderRefund param:"+request);
        ResponseDTO responseDTO=null;
        DeductSupplierCreditLineRequestDTO supplierCreditLineRequestDTO=new DeductSupplierCreditLineRequestDTO();
        supplierCreditLineRequestDTO.setSupplierCode(request.getOrgCode());
        supplierCreditLineRequestDTO.setDebuctAmount(request.getAmount());
        supplierCreditLineRequestDTO.setOperateType(2);
        supplierCreditLineRequestDTO.setMerchantCode(request.getMerchantCode());
        supplierCreditLineRequestDTO.setOrderCode(request.getOrderCode());
        supplierCreditLineRequestDTO.setCurrency(request.getCurrency());
        supplierCreditLineRequestDTO.setOperator(request.getOperator());
        responseDTO=supplyService.deductSupplierCreditLine(supplierCreditLineRequestDTO);

        if (responseDTO.getResult()== ResultCodeEnum.SUCCESS.code){
            //更新结算状态
            QueryCheckOutDTO queryCheckOutDTO=new QueryCheckOutDTO();
            queryCheckOutDTO.setOrderCode(request.getOrderCode());
            queryCheckOutDTO.setMerchantCode(request.getMerchantCode());
            PaginationSupportDTO<CheckOutOrderDTO> paginationSupportDTO=supplyCreditOrderService.queryCheckOutOrder(queryCheckOutDTO);
            CheckOutOrderDTO checkOutOrderDTO=paginationSupportDTO.getItemList().get(0);
            if (checkOutOrderDTO.getAccountStatus()!=AccountStatusEnum.HOLD.key){
                if (BigDecimal.ZERO.compareTo(checkOutOrderDTO.getReceivableAmount())==0){
                    supplyCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CHECKED.key,1,null))));
                }else{
                    supplyCreditOrderService.updateBatchOrderFinance(new ArrayList<>(Arrays.asList(
                            new UpdateOrderFinanceDTO(request.getOrderCode(), AccountStatusEnum.CANCHECK.key,0,null))));
                }
            }
        }
        return responseDTO;
    }
}
