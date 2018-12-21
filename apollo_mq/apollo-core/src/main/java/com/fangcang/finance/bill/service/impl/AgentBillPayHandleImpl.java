package com.fangcang.finance.bill.service.impl;

import com.fangcang.agent.request.DeductAgentCreditLineRequestDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.domain.AgentBillCurrencyDO;
import com.fangcang.finance.bill.domain.AgentBillDO;
import com.fangcang.finance.bill.mapper.AgentBillCurrencyMapper;
import com.fangcang.finance.bill.mapper.AgentBillMapper;
import com.fangcang.finance.bill.mapper.AgentBillOrderMapper;
import com.fangcang.finance.bill.request.CancelBillFiannceOrderDTO;
import com.fangcang.finance.bill.request.ConfirmBillFiannceOrderDTO;
import com.fangcang.finance.bill.request.QueryBillCurrencyDTO;
import com.fangcang.finance.bill.request.UpdateBillOrderFinanceDTO;
import com.fangcang.finance.bill.service.AgentBillPayHandle;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.enums.BillStatusEnum;
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
public class AgentBillPayHandleImpl implements AgentBillPayHandle {

    @Autowired
    private AgentBillMapper agentBillMapper;

    @Autowired
    private AgentBillCurrencyMapper agentBillCurrencyMapper;

    @Autowired
    private AgentBillOrderMapper agentBillOrderMapper;

    @Autowired
    private AgentService agentService;

    @Override
    @Transactional
    public ResponseDTO confirmBillFiannceOrder(@Valid ConfirmBillFiannceOrderDTO request) {
        log.info("confirmBillFiannceOrder param:"+request);
        AgentBillDO agentBillParam=new AgentBillDO();
        agentBillParam.setBillCode(request.getBillCode());
        AgentBillDO agentBillDO=agentBillMapper.selectOne(agentBillParam);

        //更新结算金额
        boolean isFinishCheckOut=true;
        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(agentBillDO.getId());
        List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.select(agentBillCurrencyParam);
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:request.getMultipleCurrencyList()){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    agentBillCurrencyDO.setPaidinAmount(multipleCurrencyAmountDTO.getPaidinAmount());
                    agentBillCurrencyMapper.updateByPrimaryKeySelective(agentBillCurrencyDO);
                }
            }
            BigDecimal paidinAmount=agentBillCurrencyDO.getPaidinAmount()==null?BigDecimal.ZERO:agentBillCurrencyDO.getPaidinAmount();
            if (agentBillCurrencyDO.getReceivableAmount().compareTo(paidinAmount)!=0){
                isFinishCheckOut=false;
            }
        }
        //如果已结算完成，则更新订单结算状态
        if (isFinishCheckOut){
            agentBillParam.setBillStatus(BillStatusEnum.CHECKOUT.key);
            agentBillParam.setId(agentBillDO.getId());
            agentBillParam.setModifier(request.getOperator());
            agentBillParam.setModifyTime(new Date());
            agentBillMapper.updateByPrimaryKeySelective(agentBillParam);

            UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
            updateBillOrderFinanceDTO.setBillId(agentBillDO.getId());
            updateBillOrderFinanceDTO.setSettlementStatus(1);
            updateBillOrderFinanceDTO.setUpdateSettlementAmount(1);
            agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

            //非单结订单返还额度
            QueryBillCurrencyDTO queryBillCurrencyDTO=new QueryBillCurrencyDTO();
            queryBillCurrencyDTO.setBillId(agentBillDO.getId());
            queryBillCurrencyDTO.setBalanceMethodType(2);
            List<MultipleCurrencyAmountDTO> unSingleCurrencyAmountDTOList=agentBillOrderMapper.queryAgentBillCurrency(queryBillCurrencyDTO);
            for (MultipleCurrencyAmountDTO unSingleCurrencyAmountDTO:unSingleCurrencyAmountDTOList){
                DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO=new DeductAgentCreditLineRequestDTO();
                agentCreditLineRequestDTO.setAgentCode(agentBillDO.getOrgCode());
                agentCreditLineRequestDTO.setDebuctAmount(unSingleCurrencyAmountDTO.getReceivableAmount());
                agentCreditLineRequestDTO.setOperateType(2);
                agentCreditLineRequestDTO.setMerchantCode(agentBillDO.getMerchantCode());
                agentCreditLineRequestDTO.setOrderCode(agentBillDO.getBillCode());
                agentCreditLineRequestDTO.setCurrency(unSingleCurrencyAmountDTO.getCurrency());
                agentCreditLineRequestDTO.setOperator(request.getOperator());
                agentService.deductAgentCreditLine(agentCreditLineRequestDTO);
            }
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO cancelBillFiannceOrder(@Valid CancelBillFiannceOrderDTO request) {
        log.info("cancelBillFiannceOrder param:"+request);
        AgentBillDO agentBillParam=new AgentBillDO();
        agentBillParam.setBillCode(request.getBillCode());
        AgentBillDO agentBillDO=agentBillMapper.selectOne(agentBillParam);

        //更新已通知金额
        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(agentBillDO.getId());
        List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.select(agentBillCurrencyParam);
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:request.getMultipleCurrencyList()){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    agentBillCurrencyDO.setNoticeAmount(multipleCurrencyAmountDTO.getNoticeAmount());
                    agentBillCurrencyMapper.updateByPrimaryKeySelective(agentBillCurrencyDO);
                }
            }
            if (request.getMultipleCurrencyList().size()==0){
                agentBillCurrencyDO.setNoticeAmount(BigDecimal.ZERO);
                agentBillCurrencyMapper.updateByPrimaryKeySelective(agentBillCurrencyDO);
            }
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }
}
