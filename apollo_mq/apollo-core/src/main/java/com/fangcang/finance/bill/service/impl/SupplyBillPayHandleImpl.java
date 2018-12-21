package com.fangcang.finance.bill.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.domain.SupplyBillCurrencyDO;
import com.fangcang.finance.bill.domain.SupplyBillDO;
import com.fangcang.finance.bill.mapper.SupplyBillCurrencyMapper;
import com.fangcang.finance.bill.mapper.SupplyBillMapper;
import com.fangcang.finance.bill.mapper.SupplyBillOrderMapper;
import com.fangcang.finance.bill.request.CancelBillFiannceOrderDTO;
import com.fangcang.finance.bill.request.ConfirmBillFiannceOrderDTO;
import com.fangcang.finance.bill.request.QueryBillCurrencyDTO;
import com.fangcang.finance.bill.request.UpdateBillOrderFinanceDTO;
import com.fangcang.finance.bill.service.SupplyBillPayHandle;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.enums.BillStatusEnum;
import com.fangcang.supplier.request.DeductSupplierCreditLineRequestDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SupplyBillPayHandleImpl implements SupplyBillPayHandle {

    @Autowired
    private SupplyBillMapper supplyBillMapper;

    @Autowired
    private SupplyBillCurrencyMapper supplyBillCurrencyMapper;

    @Autowired
    private SupplyBillOrderMapper supplyBillOrderMapper;

    @Autowired
    private SupplyService supplyService;

    @Override
    @Transactional
    public ResponseDTO confirmBillFiannceOrder(@Valid ConfirmBillFiannceOrderDTO request) {
        log.info("confirmBillFiannceOrder param:"+request);
        SupplyBillDO supplyBillParam=new SupplyBillDO();
        supplyBillParam.setBillCode(request.getBillCode());
        SupplyBillDO supplyBillDO=supplyBillMapper.selectOne(supplyBillParam);

        //更新结算金额
        boolean isFinishCheckOut=true;
        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(supplyBillDO.getId());
        List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.select(supplyBillCurrencyParam);
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:request.getMultipleCurrencyList()){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    supplyBillCurrencyDO.setPaidinAmount(multipleCurrencyAmountDTO.getPaidinAmount());
                    supplyBillCurrencyMapper.updateByPrimaryKeySelective(supplyBillCurrencyDO);
                }
            }
            BigDecimal paidinAmount=supplyBillCurrencyDO.getPaidinAmount()==null?BigDecimal.ZERO:supplyBillCurrencyDO.getPaidinAmount();
            if (supplyBillCurrencyDO.getReceivableAmount().compareTo(paidinAmount)!=0){
                isFinishCheckOut=false;
            }
        }
        //如果已结算完成，则更新订单结算状态
        if (isFinishCheckOut){
            supplyBillParam.setBillStatus(BillStatusEnum.CHECKOUT.key);
            supplyBillParam.setId(supplyBillDO.getId());
            supplyBillParam.setModifier(request.getOperator());
            supplyBillParam.setModifyTime(new Date());
            supplyBillMapper.updateByPrimaryKeySelective(supplyBillParam);

            UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
            updateBillOrderFinanceDTO.setBillId(supplyBillDO.getId());
            updateBillOrderFinanceDTO.setSettlementStatus(1);
            updateBillOrderFinanceDTO.setUpdateSettlementAmount(1);
            supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

            //非单结订单返还额度
            QueryBillCurrencyDTO queryBillCurrencyDTO=new QueryBillCurrencyDTO();
            queryBillCurrencyDTO.setBillId(supplyBillDO.getId());
            queryBillCurrencyDTO.setBalanceMethodType(2);
            List<MultipleCurrencyAmountDTO> unSingleCurrencyAmountDTOList=supplyBillOrderMapper.querySupplyBillCurrency(queryBillCurrencyDTO);
            for (MultipleCurrencyAmountDTO unSingleCurrencyAmountDTO:unSingleCurrencyAmountDTOList){
                DeductSupplierCreditLineRequestDTO supplierCreditLineRequestDTO=new DeductSupplierCreditLineRequestDTO();
                supplierCreditLineRequestDTO.setSupplierCode(supplyBillDO.getOrgCode());
                supplierCreditLineRequestDTO.setDebuctAmount(unSingleCurrencyAmountDTO.getReceivableAmount());
                supplierCreditLineRequestDTO.setOperateType(2);
                supplierCreditLineRequestDTO.setMerchantCode(supplyBillDO.getMerchantCode());
                supplierCreditLineRequestDTO.setOrderCode(supplyBillDO.getBillCode());
                supplierCreditLineRequestDTO.setCurrency(unSingleCurrencyAmountDTO.getCurrency());
                supplierCreditLineRequestDTO.setOperator(request.getOperator());
                supplyService.deductSupplierCreditLine(supplierCreditLineRequestDTO);
            }
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO cancelBillFiannceOrder(@Valid CancelBillFiannceOrderDTO request) {
        log.info("cancelBillFiannceOrder param:"+request);
        SupplyBillDO supplyBillParam=new SupplyBillDO();
        supplyBillParam.setBillCode(request.getBillCode());
        SupplyBillDO supplyBillDO=supplyBillMapper.selectOne(supplyBillParam);

        //更新已通知金额
        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(supplyBillDO.getId());
        List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.select(supplyBillCurrencyParam);
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:request.getMultipleCurrencyList()){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    supplyBillCurrencyDO.setNoticeAmount(multipleCurrencyAmountDTO.getNoticeAmount());
                    supplyBillCurrencyMapper.updateByPrimaryKeySelective(supplyBillCurrencyDO);
                }
            }
            if (request.getMultipleCurrencyList().size()==0){
                supplyBillCurrencyDO.setNoticeAmount(BigDecimal.ZERO);
                supplyBillCurrencyMapper.updateByPrimaryKeySelective(supplyBillCurrencyDO);
            }
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }
}
