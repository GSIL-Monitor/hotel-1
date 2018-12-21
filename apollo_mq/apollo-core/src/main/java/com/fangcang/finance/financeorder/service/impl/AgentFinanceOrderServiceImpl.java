package com.fangcang.finance.financeorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.finance.bill.request.CancelBillFiannceOrderDTO;
import com.fangcang.finance.bill.request.ConfirmBillFiannceOrderDTO;
import com.fangcang.finance.bill.service.AgentBillPayHandle;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.enums.FinanceStatusEnum;
import com.fangcang.finance.enums.FinanceTypeEnum;
import com.fangcang.finance.enums.PassageEnum;
import com.fangcang.finance.financeorder.domain.AgentFinanceOrderCurrencyDO;
import com.fangcang.finance.financeorder.domain.AgentFinanceOrderDO;
import com.fangcang.finance.financeorder.domain.AgentFinanceOrderItemDO;
import com.fangcang.finance.financeorder.domain.AgentFinanceOrderLogDO;
import com.fangcang.finance.financeorder.domain.AgentFinancePayItemAttchDO;
import com.fangcang.finance.financeorder.domain.AgentFinancePayItemDO;
import com.fangcang.finance.financeorder.domain.AgentSingleBalanceDO;
import com.fangcang.finance.financeorder.mapper.AgentFinanceOrderCurrencyMapper;
import com.fangcang.finance.financeorder.mapper.AgentFinanceOrderItemMapper;
import com.fangcang.finance.financeorder.mapper.AgentFinanceOrderLogMapper;
import com.fangcang.finance.financeorder.mapper.AgentFinanceOrderMapper;
import com.fangcang.finance.financeorder.mapper.AgentFinanceOrderSQLMapper;
import com.fangcang.finance.financeorder.mapper.AgentFinancePayItemAttchMapper;
import com.fangcang.finance.financeorder.mapper.AgentFinancePayItemMapper;
import com.fangcang.finance.financeorder.request.AgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.ConfirmPayRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderItemDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.FinancePayItemAttchRequestDTO;
import com.fangcang.finance.financeorder.request.QueryAgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.request.VoucherRequestDTO;
import com.fangcang.finance.financeorder.response.AgentOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderLogResponseDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.FinancePayItemAttchResponseDTO;
import com.fangcang.finance.financeorder.response.QueryAgentFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.finance.financeorder.response.VoucherResponseDTO;
import com.fangcang.finance.financeorder.service.AgentFinanceOrderService;
import com.fangcang.order.request.OrderFinanceCallbackRequestDTO;
import com.fangcang.order.service.OrderFinanceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vinney on 2018/7/17.
 */
@Slf4j
@Service
public class AgentFinanceOrderServiceImpl implements AgentFinanceOrderService {

    @Autowired
    private AgentFinanceOrderMapper agentFinanceOrderMapper;

    @Autowired
    private AgentFinanceOrderCurrencyMapper agentFinanceOrderCurrencyMapper;

    @Autowired
    private AgentFinancePayItemMapper agentFinancePayItemMapper;

    @Autowired
    private AgentFinancePayItemAttchMapper agentFinancePayItemAttchMapper;

    @Autowired
    private AgentFinanceOrderSQLMapper agentFinanceOrderSQLMapper;

    @Autowired
    private OrderFinanceService orderFinanceService;

    @Autowired
    private AgentBillPayHandle agentBillPayHandle;

    @Autowired
    private AgentFinanceOrderLogMapper agentFinanceOrderLogMapper;

    @Autowired
    private AgentFinanceOrderItemMapper agentFinanceOrderItemMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO<Integer> notifyReceivableAmount(@Validated AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO) {
        log.debug("enter notifyReceivableAmount:{}",JSON.toJSONString(agentFinanceOrderRequestDTO));
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        if (null == agentFinanceOrderRequestDTO){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_PARAM_IS_NULL);
            return responseDTO;
        }

        if (!StringUtil.isValidString(agentFinanceOrderRequestDTO.getOrderCode())){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CODE_CANNOT_NULL);
            return responseDTO;
        }

        if (null == agentFinanceOrderRequestDTO.getFinanceStatus()){
            agentFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        }

//        agentFinanceOrderRequestDTO.setFinanceType(FinanceTypeEnum.ORDERPAY.key);
        ResponseDTO<Integer> createFinanceOrderResponse = this.createFinanceOrder(agentFinanceOrderRequestDTO);

        //记录日志
        this.saveFinanceOrderLog(createFinanceOrderResponse.getModel(),buildLogContent(agentFinanceOrderRequestDTO.getFinanceType(),
                agentFinanceOrderRequestDTO.getFinanceStatus(),agentFinanceOrderRequestDTO.getCurrency(),
                agentFinanceOrderRequestDTO.getNotifyAmount()).toString(),agentFinanceOrderRequestDTO.getCreator());

        responseDTO.setModel(createFinanceOrderResponse.getModel());
        log.debug("exit notifyReceivableAmount:{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }

    private StringBuffer buildLogContent(Integer financeType,Integer financeStatus,String currency,BigDecimal amount) {
        String defaultCurrency = StringUtil.isValidString(currency) ? currency : "CNY";
        StringBuffer contentBuffer = new StringBuffer();
        if (FinanceTypeEnum.ORDERPAY.key == financeType || FinanceTypeEnum.AGENTBILLSATTLE.key == financeType){
            if (financeStatus == FinanceStatusEnum.NEW.key){
                contentBuffer.append("通知收款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CHECKOUT.key){
                contentBuffer.append("直接收款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CANCELED.key){
                contentBuffer.append("作废收款");
            }
        } else if (FinanceTypeEnum.ORDERREFUND.key == financeType){
            if (financeStatus == FinanceStatusEnum.NEW.key){
                contentBuffer.append("通知退款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CHECKOUT.key){
                contentBuffer.append("直接退款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CANCELED.key){
                contentBuffer.append("作废退款");
            }
        }
        return contentBuffer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO<Integer> notifyRefundAmount(AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO) {
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        if (null == agentFinanceOrderRequestDTO){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_PARAM_IS_NULL);
            return responseDTO;
        }

        if (!StringUtil.isValidString(agentFinanceOrderRequestDTO.getOrderCode())){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CODE_CANNOT_NULL);
            return responseDTO;
        }

        if (null == agentFinanceOrderRequestDTO.getFinanceStatus()){
            agentFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        }
//        agentFinanceOrderRequestDTO.setFinanceType(FinanceTypeEnum.ORDERREFUND.key);
        ResponseDTO<Integer> createFinanceOrderResponse = this.createFinanceOrder(agentFinanceOrderRequestDTO);

        //记录日志
        this.saveFinanceOrderLog(createFinanceOrderResponse.getModel(),buildLogContent(agentFinanceOrderRequestDTO.getFinanceType(),
                agentFinanceOrderRequestDTO.getFinanceStatus(),agentFinanceOrderRequestDTO.getCurrency(),
                agentFinanceOrderRequestDTO.getNotifyAmount()).toString(),agentFinanceOrderRequestDTO.getCreator());

        responseDTO.setModel(createFinanceOrderResponse.getModel());

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(String orderCode) {
        List<QueryTradeListForOrderResponseDTO> queryTradeListForOrderResponseDTOList = new ArrayList<>();

        QueryAgentFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO = new QueryAgentFinanceOrderRequestDTO();
        queryAgentFinanceOrderRequestDTO.setOrderCode(orderCode);
        ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> queryPayItemListResponse = this.queryPayItemList(queryAgentFinanceOrderRequestDTO);

        List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderPayItemResponseList = queryPayItemListResponse.getModel();
        QueryTradeListForOrderResponseDTO queryTradeListForOrderResponseDTO = null;
        for (QueryAgentFinanceOrderResponseDTO tempDTO : agentFinanceOrderPayItemResponseList){
            queryTradeListForOrderResponseDTO = new QueryTradeListForOrderResponseDTO();
            queryTradeListForOrderResponseDTO.setFinanceStatus(FinanceStatusEnum.getValueByKey(tempDTO.getFinanceStatus()));
            queryTradeListForOrderResponseDTO.setFinanceCode(tempDTO.getFinanceCode());
            queryTradeListForOrderResponseDTO.setNotifyAmount(tempDTO.getNotifyAmount());
            queryTradeListForOrderResponseDTO.setCurrency(tempDTO.getCurrency());
            queryTradeListForOrderResponseDTO.setCreateTime(tempDTO.getCreateTime());
            queryTradeListForOrderResponseDTO.setCreator(tempDTO.getCreator());
            queryTradeListForOrderResponseDTO.setModifier(tempDTO.getModifier());
            queryTradeListForOrderResponseDTO.setModifyTime(tempDTO.getModifyTime());
            queryTradeListForOrderResponseDTO.setFinanceType(FinanceTypeEnum.getValueByKey(tempDTO.getFinanceType()));
            StringBuffer noteBuffer = new StringBuffer();
            StringBuffer passageBuffer = new StringBuffer();
            for (VoucherResponseDTO voucherResponseDTO : tempDTO.getVoucherList()){
                noteBuffer.append(voucherResponseDTO.getNote()).append(";");
                passageBuffer.append(PassageEnum.getValueByKey(voucherResponseDTO.getPassage())).append(";");
            }

            queryTradeListForOrderResponseDTO.setPassage(passageBuffer.substring(0,passageBuffer.length()));
            queryTradeListForOrderResponseDTO.setNote(noteBuffer.substring(0,noteBuffer.length()));

            queryTradeListForOrderResponseDTOList.add(queryTradeListForOrderResponseDTO);
        }

        ResponseDTO<List<QueryTradeListForOrderResponseDTO>> response = new ResponseDTO<>();
        response.setModel(queryTradeListForOrderResponseDTOList);
        return response;
    }

    @Override
    public ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> queryPayItemList(@Validated QueryAgentFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO) {
        ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> response = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderPayItemResponseList = new ArrayList<>();
        QueryAgentFinanceOrderResponseDTO agentFinanceOrderPayItemResponse = null;

        Example agentFinanceOrderCondition = new Example(AgentFinanceOrderDO.class);
        Example.Criteria agentFinanceOrderCriteria = agentFinanceOrderCondition.createCriteria();
        if (StringUtil.isValidString(queryAgentFinanceOrderRequestDTO.getOrderCode())){
            agentFinanceOrderCriteria.andEqualTo("orderCode", queryAgentFinanceOrderRequestDTO.getOrderCode());
        }
        if (!CollectionUtils.isEmpty(queryAgentFinanceOrderRequestDTO.getOrderCodeList())){
            agentFinanceOrderCriteria.andIn("orderCode", queryAgentFinanceOrderRequestDTO.getOrderCodeList());
        }

        if (null != queryAgentFinanceOrderRequestDTO.getFinanceType() && queryAgentFinanceOrderRequestDTO.getFinanceType() > 0){
            agentFinanceOrderCriteria.andEqualTo("financeType", queryAgentFinanceOrderRequestDTO.getFinanceType());
        }

        if (null != queryAgentFinanceOrderRequestDTO.getFinanceStatus()){
            agentFinanceOrderCriteria.andEqualTo("financeStatus",queryAgentFinanceOrderRequestDTO.getFinanceStatus());
        }

        if (!CollectionUtils.isEmpty(queryAgentFinanceOrderRequestDTO.getFinanceStatusList())){
            agentFinanceOrderCriteria.andIn("financeStatus",queryAgentFinanceOrderRequestDTO.getFinanceStatusList());
        }

        //工单列表
        List<AgentFinanceOrderDO> agentFinanceOrderDOList = agentFinanceOrderMapper.selectByExample(agentFinanceOrderCondition);

        if (CollectionUtils.isEmpty(agentFinanceOrderDOList)){
            response.setModel(agentFinanceOrderPayItemResponseList);
            return response;
        }

        for (AgentFinanceOrderDO agentFinanceOrderDO : agentFinanceOrderDOList){
            agentFinanceOrderPayItemResponse = new QueryAgentFinanceOrderResponseDTO();
            agentFinanceOrderPayItemResponse.setFinanceCode(agentFinanceOrderDO.getFinanceCode());
            agentFinanceOrderPayItemResponse.setFinanceStatus(agentFinanceOrderDO.getFinanceStatus());
            agentFinanceOrderPayItemResponse.setFinanceType(agentFinanceOrderDO.getFinanceType());
            agentFinanceOrderPayItemResponse.setOrderCode(agentFinanceOrderDO.getOrderCode());
            agentFinanceOrderPayItemResponse.setFinanceOrderId(agentFinanceOrderDO.getId());

            //工单金额：金额和币种字段
            Example agentFinanceOrderCurrencyCondition = new Example(AgentFinanceOrderCurrencyDO.class);
            Example.Criteria agentFinanceOrderCurrencyCriteria = agentFinanceOrderCurrencyCondition.createCriteria();
            agentFinanceOrderCurrencyCriteria.andEqualTo("financeOrderId",agentFinanceOrderDO.getId());
            List<AgentFinanceOrderCurrencyDO> agentFinanceOrderCurrencyList = agentFinanceOrderCurrencyMapper.selectByExample(agentFinanceOrderCurrencyCondition);
            if (!CollectionUtils.isEmpty(agentFinanceOrderCurrencyList)){
                agentFinanceOrderPayItemResponse.setCurrency(agentFinanceOrderCurrencyList.get(0).getCurrency());
                agentFinanceOrderPayItemResponse.setNotifyAmount(agentFinanceOrderCurrencyList.get(0).getReceivableAmount());
            }

            //转账记录:
            Example agentFinancePayItemCondition = new Example(AgentFinancePayItemDO.class);
            Example.Criteria agentFinancePayItemCriteria = agentFinancePayItemCondition.createCriteria();
            agentFinancePayItemCriteria.andEqualTo("financeOrderId",agentFinanceOrderDO.getId());
            List<AgentFinancePayItemDO> agentFinancePayItemDOList = agentFinancePayItemMapper.selectByExample(agentFinancePayItemCondition);
            List<VoucherResponseDTO> voucherList = PropertyCopyUtil.transferArray(agentFinancePayItemDOList,VoucherResponseDTO.class);
            agentFinanceOrderPayItemResponse.setVoucherList(voucherList);

            agentFinanceOrderPayItemResponseList.add(agentFinanceOrderPayItemResponse);
        }

        response.setModel(agentFinanceOrderPayItemResponseList);
        return response;
    }

    @Override
    public PaginationSupportDTO<AgentOrderListResponseDTO> getUnreceived(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        singleBalanceQueryDTO.setFinanceOrderStatus(1);
        PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupport = this.getFinanceOrderPagination(singleBalanceQueryDTO);
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<AgentOrderListResponseDTO> getReceived(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        singleBalanceQueryDTO.setFinanceOrderStatus(2);
        PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupport = this.getFinanceOrderPagination(singleBalanceQueryDTO);
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<AgentOrderListResponseDTO> getUnfinished(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        singleBalanceQueryDTO.setFinanceOrderStatus(3);
        PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupport = this.getFinanceOrderPagination(singleBalanceQueryDTO);
        return paginationSupport;
    }

    @Override
    public void cancelFinanceOrder(FinanceOrderRequestDTO financeOrderRequestDTO) {
        log.debug("enter cancelFinanceOrder:{}",JSON.toJSONString(financeOrderRequestDTO));
        Example agentFinanceOrderExample = new Example(AgentFinanceOrderDO.class);
        Example.Criteria agentFinanceOrderCriteria = agentFinanceOrderExample.createCriteria();
        agentFinanceOrderCriteria.andEqualTo("id",financeOrderRequestDTO.getFinanceOrderId());
        AgentFinanceOrderDO updateFinanceOrderDO = new AgentFinanceOrderDO();
        updateFinanceOrderDO.setFinanceStatus(FinanceStatusEnum.CANCELED.key);
        agentFinanceOrderMapper.updateByExampleSelective(updateFinanceOrderDO,agentFinanceOrderExample);

        if (FinanceTypeEnum.AGENTBILLSATTLE.key == financeOrderRequestDTO.getFinanceType()){
            orderFinanceCallBack(financeOrderRequestDTO.getFinanceType(),financeOrderRequestDTO.getOrderCode(),financeOrderRequestDTO.getOperator(),2);
        }

        //记录日志
        this.saveFinanceOrderLog(financeOrderRequestDTO.getFinanceOrderId(),
                buildLogContent(financeOrderRequestDTO.getFinanceType(),FinanceStatusEnum.CANCELED.key,null,null).toString(),
                financeOrderRequestDTO.getCreator());

        log.debug("exit cancelFinanceOrder");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer confirmFinanceOrder(ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO) {
        //1-更新工单和多币种表：更新工单状态。（因为工单是在通知财务收付款的时候创建的）
        AgentFinanceOrderDO agentFinanceOrderDO = new AgentFinanceOrderDO();
        agentFinanceOrderDO.setId(confirmFinanceOrderRequestDTO.getFinanceOrderId());
        agentFinanceOrderDO.setFinanceStatus(FinanceStatusEnum.CHECKOUT.key);
        agentFinanceOrderDO.setModifier(confirmFinanceOrderRequestDTO.getModifier());
        agentFinanceOrderDO.setModifyTime(confirmFinanceOrderRequestDTO.getModifyTime());
        agentFinanceOrderMapper.updateByPrimaryKeySelective(agentFinanceOrderDO);


        //2-更新工单明细（几个银行）:先删除，再添加
        Example agentFinancePayItemExample = new Example(AgentFinancePayItemDO.class);
        Example.Criteria agentFinancePayItemCriteria = agentFinancePayItemExample.createCriteria();
        agentFinancePayItemCriteria.andEqualTo("financeOrderId",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        agentFinancePayItemMapper.deleteByExample(agentFinancePayItemExample);

        //旧的itemId
        List<Integer> payItemIdList = new ArrayList<>();
        //附件：key=新的itemId,value=附件
        Map<Integer,List<FinancePayItemAttchRequestDTO>> attchMap = new HashMap<>();
        List<VoucherRequestDTO> financePayItemDTOLsit = confirmFinanceOrderRequestDTO.getVoucherList();
        for (VoucherRequestDTO vDTO : financePayItemDTOLsit){
            AgentFinancePayItemDO tempItemDO = PropertyCopyUtil.transfer(vDTO,AgentFinancePayItemDO.class);
            tempItemDO.setModifyTime(confirmFinanceOrderRequestDTO.getModifyTime());
            tempItemDO.setModifier(confirmFinanceOrderRequestDTO.getModifier());
            tempItemDO.setCreator(confirmFinanceOrderRequestDTO.getCreator());
            tempItemDO.setCreateTime(confirmFinanceOrderRequestDTO.getCreateTime());
            tempItemDO.setFinanceOrderId(confirmFinanceOrderRequestDTO.getFinanceOrderId());
            agentFinancePayItemMapper.insert(tempItemDO);

            //附件 key=新的itemId；value=附件列表
            attchMap.put(tempItemDO.getId(),vDTO.getFinancePayItemAttchList());
            //旧的itemId集合
            payItemIdList.add(vDTO.getId());
        }

        //3-更新附件：先根据payItemId删除，再添加（有可能在订单通知的时候有附加，在收款的时候，把附件删除或者新增了）
        List<AgentFinancePayItemAttchDO> allAttchDOList = new ArrayList<>();
        for(Map.Entry<Integer,List<FinancePayItemAttchRequestDTO>> entry : attchMap.entrySet()){
            List<AgentFinancePayItemAttchDO> attchDOList = PropertyCopyUtil.transferArray(entry.getValue(),AgentFinancePayItemAttchDO.class);
            if (attchDOList!=null){
                attchDOList.forEach(tempAttchDO -> {
                    tempAttchDO.setPayItemId(entry.getKey());
                });
                allAttchDOList.addAll(attchDOList);
            }
        }

        Example agentFinancePayItemAttchExample = new Example(AgentFinancePayItemAttchDO.class);
        Example.Criteria agentFinancePayItemAttchCriteria = agentFinancePayItemAttchExample.createCriteria();
        agentFinancePayItemAttchCriteria.andIn("payItemId",payItemIdList);
        agentFinancePayItemAttchMapper.deleteByExample(agentFinancePayItemAttchExample);
        if (!CollectionUtils.isEmpty(allAttchDOList)){
            agentFinancePayItemAttchMapper.insertList(allAttchDOList);
        }

        //FIXME 账单和订单的回写是分开的
        this.orderFinanceCallBack(confirmFinanceOrderRequestDTO.getFinanceType(),confirmFinanceOrderRequestDTO.getOrderCode(),confirmFinanceOrderRequestDTO.getCreator(),1);

        //记录日志
        this.saveFinanceOrderLog(confirmFinanceOrderRequestDTO.getFinanceOrderId(),buildLogContent(confirmFinanceOrderRequestDTO.getFinanceType(),
                FinanceStatusEnum.CHECKOUT.key,confirmFinanceOrderRequestDTO.getCurrency(),
                confirmFinanceOrderRequestDTO.getNotifyAmount()).toString(),confirmFinanceOrderRequestDTO.getCreator());
        return 1;
    }

    @Override
    public ResponseDTO<Integer> confirmPay(ConfirmPayRequestDTO confirmPayRequestDTO) {
        log.debug("enter confirmPay：{}",JSON.toJSONString(confirmPayRequestDTO));
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        //1-创建已确认的工单，
        AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO = PropertyCopyUtil.transfer(confirmPayRequestDTO,AgentFinanceOrderRequestDTO.class);
        if (null == agentFinanceOrderRequestDTO.getFinanceStatus()){
            agentFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.CHECKOUT.key);
        }
        ResponseDTO<Integer> financeOrderResponseDTO = this.notifyReceivableAmount(agentFinanceOrderRequestDTO);

        // 2-回写订单结算金额和结算状态
        orderFinanceCallBack(confirmPayRequestDTO.getFinanceType(),confirmPayRequestDTO.getOrderCode(),confirmPayRequestDTO.getCreator(),1);

        responseDTO.setModel(financeOrderResponseDTO.getModel());

//        //记录日志 通知收付款已经记录日志了。
//        this.saveFinanceOrderLog(financeOrderResponseDTO.getModel(),buildLogContent(agentFinanceOrderRequestDTO.getFinanceType(),
//                agentFinanceOrderRequestDTO.getFinanceStatus(),agentFinanceOrderRequestDTO.getCurrency(),
//                agentFinanceOrderRequestDTO.getNotifyAmount()).toString(),agentFinanceOrderRequestDTO.getCreator());
//
//        log.debug("exit confirmPay：{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }

    @Override
    public ConfirmFinanceOrderResponseDTO queryFinanceOrderDetail(ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO) {

        Example financeOrderExample = new Example(AgentFinanceOrderDO.class);
        Example.Criteria financeOrderCriteria = financeOrderExample.createCriteria();
        financeOrderCriteria.andEqualTo("id",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        AgentFinanceOrderDO financeOrderDO = agentFinanceOrderMapper.selectOneByExample(financeOrderExample);

        Example currencyExample = new Example(AgentFinanceOrderCurrencyDO.class);
        Example.Criteria currencyCriteria = currencyExample.createCriteria();
        currencyCriteria.andEqualTo("financeOrderId",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        AgentFinanceOrderCurrencyDO currencyDO = agentFinanceOrderCurrencyMapper.selectOneByExample(currencyExample);

        Example payItemExample = new Example(AgentFinancePayItemDO.class);
        Example.Criteria payItemCriteria = payItemExample.createCriteria();
        payItemCriteria.andEqualTo("financeOrderId",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        List<AgentFinancePayItemDO> agentFinancePayItemDOList = agentFinancePayItemMapper.selectByExample(payItemExample);
        List<VoucherResponseDTO> voucherList = PropertyCopyUtil.transferArray(agentFinancePayItemDOList,VoucherResponseDTO.class);

        for (VoucherResponseDTO payItemTemp : voucherList){
            Example attchExample = new Example(AgentFinancePayItemAttchDO.class);
            Example.Criteria attchCriteria = attchExample.createCriteria();
            attchCriteria.andEqualTo("payItemId",payItemTemp.getId());
            List<AgentFinancePayItemAttchDO> attchDOList = agentFinancePayItemAttchMapper.selectByExample(attchExample);
            payItemTemp.setFinancePayItemAttchList(PropertyCopyUtil.transferArray(attchDOList, FinancePayItemAttchResponseDTO.class));

            payItemTemp.setArrivalDate(payItemTemp.getArrivalDate());
        }

        ConfirmFinanceOrderResponseDTO responseDTO = PropertyCopyUtil.transfer(financeOrderDO,ConfirmFinanceOrderResponseDTO.class);
        responseDTO.setFinanceOrderId(financeOrderDO.getId());
        responseDTO.setCurrency(currencyDO.getCurrency());
        responseDTO.setNotifyAmount(currencyDO.getReceivableAmount());
        responseDTO.setVoucherList(voucherList);

        //查询操作日志
        responseDTO.setFinanceOrderlogList(this.queryFinanceOrderLog(confirmFinanceOrderRequestDTO.getFinanceOrderId()));

        return responseDTO;
    }

    /**
     *
     * @param financeType  工单类型：单结和挂账
     * @param orderCode  订单号或者账单号
     * @param operator  操作人
     * @param operateType  操作类型：1-确认工单，2-取消工单
     */
    private void orderFinanceCallBack(Integer financeType,String orderCode,String operator,Integer operateType) {

        log.debug("enter orderFinanceCallBack,{},{},{},{}",financeType,orderCode,operator,operateType);

        //2.1查询所有工单
        QueryAgentFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO = new QueryAgentFinanceOrderRequestDTO();
        queryAgentFinanceOrderRequestDTO.setOrderCode(orderCode);
        ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> queryPayItemResponse = this.queryPayItemList(queryAgentFinanceOrderRequestDTO);
        List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList = queryPayItemResponse.getModel();

        //确认工单
        if (1 == operateType){
            //订单回写
            confirmSingleOrderFinanceCallBack(financeType,orderCode,operator, agentFinanceOrderResponseDTOList);

            //账单回写
            confirmBillOrderFinanceCallBack(financeType,orderCode, operator, agentFinanceOrderResponseDTOList);
        }
        //取消工单
        else if (2 == operateType){
            cancelBillOrderCallBack(financeType,orderCode,operator,agentFinanceOrderResponseDTOList);
        }

        log.debug("exit orderFinanceCallBack.");

    }

    /**
     *  确认工单时：回调账单的确认工单接口
     * @param orderCode
     * @param operator
     * @param agentFinanceOrderResponseDTOList
     */
    private void confirmBillOrderFinanceCallBack(Integer financeType,String orderCode, String operator, List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {
        if (FinanceTypeEnum.AGENTBILLSATTLE.key != financeType.intValue()){
            log.info("非挂账的工单确认不需要执行，请求参数：{},{},{}",FinanceTypeEnum.getValueByKey(financeType),orderCode,JSON.toJSONString(agentFinanceOrderResponseDTOList));
            return;
        }

        //获取已确认的工单
        Map<String, List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseMap = getAgentCheckoutFinanceOrderMap(agentFinanceOrderResponseDTOList);

        Map<String, BigDecimal> multipleMap = groupByCurrencyMap(financeOrderResponseMap);

        ConfirmBillFiannceOrderDTO confirmBillFiannceOrderDTO = buildConfirmBillFiannceOrderDTO(orderCode, operator, multipleMap);

        ResponseDTO callBackResponseDTO = agentBillPayHandle.confirmBillFiannceOrder(confirmBillFiannceOrderDTO);
        if (null == callBackResponseDTO || null == callBackResponseDTO.getResult() || 1 != callBackResponseDTO.getResult()){
            log.error("账单回写确认结果失败，请求参数：{}，返回结果：{}", JSON.toJSONString(confirmBillFiannceOrderDTO),
                    JSON.toJSONString(callBackResponseDTO));
            throw new ServiceException("工单确认失败(账单类型)");
        }
    }

    private ConfirmBillFiannceOrderDTO buildConfirmBillFiannceOrderDTO(String orderCode, String operator, Map<String, BigDecimal> multipleMap) {
        List<MultipleCurrencyAmountDTO> multipleCurrencyList = new ArrayList<>();
        MultipleCurrencyAmountDTO multipleCurrencyAmountDTO = null;
        for(Map.Entry<String,BigDecimal> amountEntry: multipleMap.entrySet()){
            multipleCurrencyAmountDTO = new MultipleCurrencyAmountDTO();
            multipleCurrencyAmountDTO.setPaidinAmount(amountEntry.getValue());
            multipleCurrencyAmountDTO.setCurrency(amountEntry.getKey());
            multipleCurrencyList.add(multipleCurrencyAmountDTO);
        }

        ConfirmBillFiannceOrderDTO confirmBillFiannceOrderDTO = new ConfirmBillFiannceOrderDTO();
        confirmBillFiannceOrderDTO.setBillCode(orderCode);
        confirmBillFiannceOrderDTO.setOperator(operator);
        confirmBillFiannceOrderDTO.setMultipleCurrencyList(multipleCurrencyList);
        return confirmBillFiannceOrderDTO;
    }

    private CancelBillFiannceOrderDTO buildCancelBillFiannceOrderDTO(String orderCode, String operator, Map<String, BigDecimal> multipleMap) {
        List<MultipleCurrencyAmountDTO> multipleCurrencyList = new ArrayList<>();
        MultipleCurrencyAmountDTO multipleCurrencyAmountDTO = null;
        for(Map.Entry<String,BigDecimal> amountEntry: multipleMap.entrySet()){
            multipleCurrencyAmountDTO = new MultipleCurrencyAmountDTO();
            multipleCurrencyAmountDTO.setNoticeAmount(amountEntry.getValue());
            multipleCurrencyAmountDTO.setCurrency(amountEntry.getKey());
            multipleCurrencyList.add(multipleCurrencyAmountDTO);
        }

        CancelBillFiannceOrderDTO cancelBillFiannceOrderDTO = new CancelBillFiannceOrderDTO();
        cancelBillFiannceOrderDTO.setBillCode(orderCode);
        cancelBillFiannceOrderDTO.setOperator(operator);
        cancelBillFiannceOrderDTO.setMultipleCurrencyList(multipleCurrencyList);
        return cancelBillFiannceOrderDTO;
    }

    /**
     * 按照币种分组汇总金额
     * @param financeOrderResponseMap
     * @return Map<币种，总金额>
     */
    private Map<String, BigDecimal> groupByCurrencyMap(Map<String, List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseMap) {
        Map<String,BigDecimal> multipleMap = new HashMap<>();
        for (Map.Entry<String,List<QueryAgentFinanceOrderResponseDTO>> entry : financeOrderResponseMap.entrySet()){
            String currency = entry.getKey();
            List<QueryAgentFinanceOrderResponseDTO> currentReceivedList = entry.getValue();
            if (CollectionUtils.isEmpty(currentReceivedList)){
                continue;
            }

            BigDecimal totalAmount = new BigDecimal("0");
            for (QueryAgentFinanceOrderResponseDTO temDTO : currentReceivedList){
                totalAmount = totalAmount.add(temDTO.getNotifyAmount());
            }
            multipleMap.put(currency,totalAmount);
        }
        return multipleMap;
    }

    /**
     *
     * @param agentFinanceOrderResponseDTOList
     * @return Map<币种,List<工单>> </>
     */
    private Map<String, List<QueryAgentFinanceOrderResponseDTO>> getAgentCheckoutFinanceOrderMap(List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {
        Map<String,List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseMap = new HashMap();
        for (QueryAgentFinanceOrderResponseDTO financeOrderResponseTemp : agentFinanceOrderResponseDTOList){
            if (FinanceTypeEnum.AGENTBILLSATTLE.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){

                if (financeOrderResponseMap.containsKey(financeOrderResponseTemp.getCurrency())){
                    financeOrderResponseMap.get(financeOrderResponseTemp.getCurrency()).add(financeOrderResponseTemp);
                } else{
                    List<QueryAgentFinanceOrderResponseDTO> financeOrderResponseDTOTempList = new ArrayList<>();
                    financeOrderResponseDTOTempList.add(financeOrderResponseTemp);
                    financeOrderResponseMap.put(financeOrderResponseTemp.getCurrency(),financeOrderResponseDTOTempList);
                }
            }
        }
        return financeOrderResponseMap;
    }

    private Map<String, List<QueryAgentFinanceOrderResponseDTO>> getAgentNotCanceledFinanceOrderMap(List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {
        Map<String,List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseMap = new HashMap();
        for (QueryAgentFinanceOrderResponseDTO financeOrderResponseTemp : agentFinanceOrderResponseDTOList){
            if (FinanceTypeEnum.AGENTBILLSATTLE.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CANCELED.key != financeOrderResponseTemp.getFinanceStatus() ){

                if (financeOrderResponseMap.containsKey(financeOrderResponseTemp.getCurrency())){
                    financeOrderResponseMap.get(financeOrderResponseTemp.getCurrency()).add(financeOrderResponseTemp);
                } else{
                    List<QueryAgentFinanceOrderResponseDTO> financeOrderResponseDTOTempList = new ArrayList<>();
                    financeOrderResponseDTOTempList.add(financeOrderResponseTemp);
                    financeOrderResponseMap.put(financeOrderResponseTemp.getCurrency(),financeOrderResponseDTOTempList);
                }
            }
        }
        return financeOrderResponseMap;
    }


    /**
     * 取消工单时：回调账单的取消工单接口
     * @param orderCode
     * @param operator
     * @param agentFinanceOrderResponseDTOList
     */
    private void cancelBillOrderCallBack(Integer financeType,String orderCode, String operator, List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {

        if (FinanceTypeEnum.AGENTBILLSATTLE.key != financeType.intValue()){
            log.info("非挂账的工单取消时无需回调账单，请求参数：{},{},{}",FinanceTypeEnum.getValueByKey(financeType),orderCode,JSON.toJSONString(agentFinanceOrderResponseDTOList));
            return;
        }

        //所有未取消的工单
        Map<String, List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseMap = getAgentNotCanceledFinanceOrderMap(agentFinanceOrderResponseDTOList);

        Map<String, BigDecimal> multipleMap = groupByCurrencyMap(financeOrderResponseMap);

        CancelBillFiannceOrderDTO cancelBillFiannceOrderDTO = buildCancelBillFiannceOrderDTO(orderCode, operator, multipleMap);

        ResponseDTO callBackResponseDTO = agentBillPayHandle.cancelBillFiannceOrder(cancelBillFiannceOrderDTO);
        if (1 != callBackResponseDTO.getResult()){
            log.error("账单回写确认结果失败，请求参数：{}，返回结果：{}", JSON.toJSONString(cancelBillFiannceOrderDTO),
                    JSON.toJSONString(callBackResponseDTO));
            throw new ServiceException("工单确认失败(账单类型)");
        }
    }

    private void confirmSingleOrderFinanceCallBack(Integer financeType,String orderCode,String operator, List<QueryAgentFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {
        if (FinanceTypeEnum.ORDERREFUND.key != financeType.intValue() && FinanceTypeEnum.ORDERPAY.key != financeType.intValue()){
            log.info("不是单结订单无需执行，请求参数：{},{},{}",FinanceTypeEnum.getValueByKey(financeType),orderCode,JSON.toJSONString(agentFinanceOrderResponseDTOList));
            return;
        }
        //2.2计算总的实收，和总的退款。结算金额=总实收-总退款。都必须是已结结算的工单
        BigDecimal totalReceived = new BigDecimal("0");
        BigDecimal totalRefund = new BigDecimal("0");
        for (QueryAgentFinanceOrderResponseDTO financeOrderResponseTemp : agentFinanceOrderResponseDTOList){
            if (FinanceTypeEnum.ORDERPAY.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){
                totalReceived = totalReceived.add(financeOrderResponseTemp.getNotifyAmount());
            }
            else if (FinanceTypeEnum.ORDERREFUND.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){
                totalRefund = totalRefund.add(financeOrderResponseTemp.getNotifyAmount());
            }
        }

        BigDecimal totalSettlement = totalReceived.subtract(totalRefund);

        OrderFinanceCallbackRequestDTO orderFinanceCallbackRequestDTO = new OrderFinanceCallbackRequestDTO();
        orderFinanceCallbackRequestDTO.setOrderCode(orderCode);
        orderFinanceCallbackRequestDTO.setSettlementAmount(totalSettlement);
        orderFinanceCallbackRequestDTO.setRefundAmount(totalRefund);
        orderFinanceCallbackRequestDTO.setModifier(operator);
        orderFinanceCallbackRequestDTO.setModifyTime(DateUtil.getCurrentDate());
        ResponseDTO callBackResponseDTO = orderFinanceService.orderFinanceCallback(orderFinanceCallbackRequestDTO);
        if (1 != callBackResponseDTO.getResult()){
            log.error("确认支付时，回写订单结算状态和结算金额失败：{}；请求参数为{}",callBackResponseDTO.getFailReason(), JSON.toJSONString(orderFinanceCallbackRequestDTO));
            throw new ServiceException("回写确认结果和结算状态失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<Integer> createFinanceOrder(AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO) {
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        AgentFinanceOrderDO agentFinanceOrderDO = PropertyCopyUtil.transfer(agentFinanceOrderRequestDTO, AgentFinanceOrderDO.class);

        agentFinanceOrderMapper.insert(agentFinanceOrderDO);
        Integer financeOrderId = agentFinanceOrderDO.getId();
        if (null == financeOrderId){
            log.error("通知财务收款失败：{}",JSON.toJSONString(agentFinanceOrderRequestDTO));
            throw new ServiceException(ErrorCodeEnum.FINANCE_ORDER_ID_CREATE_ERROR.errorDesc);
        }

        AgentFinanceOrderCurrencyDO agentFinanceOrderCurrencyDO = new AgentFinanceOrderCurrencyDO();
        agentFinanceOrderCurrencyDO.setFinanceOrderId(agentFinanceOrderDO.getId());
        agentFinanceOrderCurrencyDO.setCurrency(agentFinanceOrderRequestDTO.getCurrency());
        agentFinanceOrderCurrencyDO.setReceivableAmount(agentFinanceOrderRequestDTO.getNotifyAmount());
        agentFinanceOrderCurrencyDO.setCreator(agentFinanceOrderRequestDTO.getCreator());
        agentFinanceOrderCurrencyDO.setCreateTime(agentFinanceOrderRequestDTO.getCreateTime());
        agentFinanceOrderCurrencyDO.setModifyTime(agentFinanceOrderRequestDTO.getModifyTime());
        agentFinanceOrderCurrencyDO.setModifier(agentFinanceOrderRequestDTO.getModifier());
        agentFinanceOrderCurrencyMapper.insert(agentFinanceOrderCurrencyDO);

        if (agentFinanceOrderRequestDTO.getFinanceOrderItemDTOList()==null){
            agentFinanceOrderRequestDTO.setFinanceOrderItemDTOList(new ArrayList<>());
        }
        if ((agentFinanceOrderRequestDTO.getFinanceType()==FinanceTypeEnum.ORDERPAY.key ||
                agentFinanceOrderRequestDTO.getFinanceType()==FinanceTypeEnum.ORDERREFUND.key)
            && agentFinanceOrderRequestDTO.getFinanceOrderItemDTOList().size()==0){
            FinanceOrderItemDTO financeOrderItemDTO=new FinanceOrderItemDTO();
            financeOrderItemDTO.setOrderCode(agentFinanceOrderRequestDTO.getOrderCode());
            financeOrderItemDTO.setCurrency(agentFinanceOrderRequestDTO.getCurrency());
            financeOrderItemDTO.setAmount(agentFinanceOrderRequestDTO.getNotifyAmount());
            agentFinanceOrderRequestDTO.getFinanceOrderItemDTOList().add(financeOrderItemDTO);
        }
        if (agentFinanceOrderRequestDTO.getFinanceOrderItemDTOList().size()>0){
            List<AgentFinanceOrderItemDO> agentFinanceOrderItemDOList=new ArrayList<>();
            for (FinanceOrderItemDTO financeOrderItemDTO:agentFinanceOrderRequestDTO.getFinanceOrderItemDTOList()){
                AgentFinanceOrderItemDO agentFinanceOrderItemDO=new AgentFinanceOrderItemDO();
                agentFinanceOrderItemDO.setOrderCode(financeOrderItemDTO.getOrderCode());
                agentFinanceOrderItemDO.setCurrency(financeOrderItemDTO.getCurrency());
                agentFinanceOrderItemDO.setAmount(financeOrderItemDTO.getAmount());
                agentFinanceOrderItemDO.setFinanceOrderId(agentFinanceOrderDO.getId());
                agentFinanceOrderItemDO.setCreator(agentFinanceOrderRequestDTO.getCreator());
                agentFinanceOrderItemDO.setCreateTime(new Date());
                agentFinanceOrderItemDOList.add(agentFinanceOrderItemDO);
            }
            agentFinanceOrderItemMapper.insertList(agentFinanceOrderItemDOList);
        }

        this.createVoucher(financeOrderId, agentFinanceOrderRequestDTO.getVoucherList());

        responseDTO.setModel(agentFinanceOrderDO.getId());
        return responseDTO;
    }



    private PaginationSupportDTO<AgentOrderListResponseDTO> getFinanceOrderPagination(SingleBalanceQueryDTO singleBalanceQueryDTO){

        //1-分页查询订单列表
        Page<AgentSingleBalanceDO> page = this.queryFinanceOrderForPage(singleBalanceQueryDTO);

        //2-查询工单列表
        List<AgentOrderListResponseDTO> agentOrderListResponseDTOList = getAgentOrderListResponseDTOList(page);

        //3组装返回数据
        PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupport = buildAgentOrderListResponseDTOPaginationSupportDTO(page, agentOrderListResponseDTOList);

        return paginationSupport;
    }

    private List<AgentOrderListResponseDTO> getAgentOrderListResponseDTOList(Page<AgentSingleBalanceDO> page) {
        List<AgentOrderListResponseDTO> agentOrderListResponseDTOList = new ArrayList<>();
        for (AgentSingleBalanceDO  tempDO : page.getResult()){
            //2查询工单列表：通知金额，币种，状态，工单ID。
            QueryAgentFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO = new QueryAgentFinanceOrderRequestDTO();
            queryAgentFinanceOrderRequestDTO.setOrderCode(tempDO.getOrderCode());
            ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> payItemResponDTO = this.queryPayItemList(queryAgentFinanceOrderRequestDTO);

            List<FinanceOrderResponseDTO> financeOrderList = PropertyCopyUtil.transferArray(payItemResponDTO.getModel(),FinanceOrderResponseDTO.class);

            AgentOrderListResponseDTO agentOrderListResponseDTO = PropertyCopyUtil.transfer(tempDO,AgentOrderListResponseDTO.class);
            agentOrderListResponseDTO.setOrderId(tempDO.getId());
            agentOrderListResponseDTO.setShouldReceive(tempDO.getReceivableAmount());
            agentOrderListResponseDTO.setHasReveived(tempDO.getHasReceivedAmount());
            agentOrderListResponseDTO.setFinanceOrderList(financeOrderList);
            agentOrderListResponseDTO.setCurrency(tempDO.getSaleCurrency());
            agentOrderListResponseDTO.setOrderFinanceStatus(null == tempDO.getAccountStatus() ?  0 : tempDO.getAccountStatus().intValue());
            agentOrderListResponseDTOList.add(agentOrderListResponseDTO);
        }
        return agentOrderListResponseDTOList;
    }

    private PaginationSupportDTO<AgentOrderListResponseDTO> buildAgentOrderListResponseDTOPaginationSupportDTO(Page<AgentSingleBalanceDO> page, List<AgentOrderListResponseDTO> agentOrderListResponseDTOList) {
        PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupport = new PaginationSupportDTO<AgentOrderListResponseDTO>();
        paginationSupport.setItemList(agentOrderListResponseDTOList);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    /**
     * 创建工单明细
     * @param financeOrderId
     * @param voucherList
     */
    private void createVoucher(Integer financeOrderId,List<VoucherRequestDTO> voucherList){
        if (CollectionUtils.isEmpty(voucherList)){
            return ;
        }

        for (VoucherRequestDTO voucherRequestDTO : voucherList){
            AgentFinancePayItemDO  agentFinancePayItemDO = PropertyCopyUtil.transfer(voucherRequestDTO,AgentFinancePayItemDO.class);
            agentFinancePayItemDO.setFinanceOrderId(financeOrderId);
            agentFinancePayItemMapper.insert(agentFinancePayItemDO);
            this.savePayItemAttch(agentFinancePayItemDO.getId(),voucherRequestDTO.getFinancePayItemAttchList());
        }
    }

    /**
     * 保存凭证附件
     * @param payItemId
     * @param financePayItemAttchList
     */
    private void savePayItemAttch(Integer payItemId, List<FinancePayItemAttchRequestDTO> financePayItemAttchList){
        if (CollectionUtils.isEmpty(financePayItemAttchList)){
            return ;
        }

        List<AgentFinancePayItemAttchDO> agentFinancePayItemAttchDOList = PropertyCopyUtil.transferArray(financePayItemAttchList,AgentFinancePayItemAttchDO.class);

        agentFinancePayItemAttchDOList.forEach(agentFinancePayItemAttchDO1 -> {agentFinancePayItemAttchDO1.setPayItemId(payItemId);});

        agentFinancePayItemAttchMapper.insertList(agentFinancePayItemAttchDOList);
    }

    private Page<AgentSingleBalanceDO> queryFinanceOrderForPage(SingleBalanceQueryDTO singleBalanceQueryDTO){

        Page<AgentSingleBalanceDO> page = null;

        AgentSingleBalanceDO queryDO = new AgentSingleBalanceDO();
        queryDO.setAgentCode(singleBalanceQueryDTO.getOrgCode());
        queryDO.setAgentName(singleBalanceQueryDTO.getOrgName());
        queryDO.setOrderCode(singleBalanceQueryDTO.getOrderCode());
        queryDO.setHotelName(singleBalanceQueryDTO.getHotelName());
        queryDO.setHotelId(singleBalanceQueryDTO.getHotelId());
        queryDO.setGuestNames(singleBalanceQueryDTO.getGuestName());

        if (null != singleBalanceQueryDTO.getDateQueryType()
                && StringUtil.isValidString(singleBalanceQueryDTO.getStartDate())
                && StringUtil.isValidString(singleBalanceQueryDTO.getEndDate())){
            //入住
            if (singleBalanceQueryDTO.getDateQueryType() == 1){
                queryDO.setCheckinStartDate(DateUtil.stringToDate(singleBalanceQueryDTO.getStartDate()));
                queryDO.setCheckinEndDate(DateUtil.stringToDate(singleBalanceQueryDTO.getEndDate()));
            }
            //离店
            else if (singleBalanceQueryDTO.getDateQueryType() == 2){
                queryDO.setCheckoutStartDate(DateUtil.stringToDate(singleBalanceQueryDTO.getStartDate()));
                queryDO.setCheckoutEndDate(DateUtil.stringToDate(singleBalanceQueryDTO.getEndDate()));
            }
            //下单
            else if (singleBalanceQueryDTO.getDateQueryType() == 3){
                queryDO.setCreateStartDate(DateUtil.stringToDate(singleBalanceQueryDTO.getStartDate()));
                queryDO.setCreateEndDate(DateUtil.getDate(DateUtil.stringToDate(singleBalanceQueryDTO.getEndDate()),1,0));
            }
        }


        String orderBy = "id desc";
        //查询待收款
        if (1 == singleBalanceQueryDTO.getFinanceOrderStatus()){
            page = PageHelper.startPage(singleBalanceQueryDTO.getCurrentPage(), singleBalanceQueryDTO.getPageSize(), orderBy)
                    .doSelectPage(() -> agentFinanceOrderSQLMapper.queryUnreceived(queryDO));
        }
        //查询已收款
        else if (2 == singleBalanceQueryDTO.getFinanceOrderStatus()){
            page = PageHelper.startPage(singleBalanceQueryDTO.getCurrentPage(), singleBalanceQueryDTO.getPageSize(), orderBy)
                    .doSelectPage(() -> agentFinanceOrderSQLMapper.queryReceived(queryDO));
        }
        //查询未完成
        else if (3 == singleBalanceQueryDTO.getFinanceOrderStatus()){
            page = PageHelper.startPage(singleBalanceQueryDTO.getCurrentPage(), singleBalanceQueryDTO.getPageSize(), orderBy)
                    .doSelectPage(() -> agentFinanceOrderSQLMapper.queryUnfinished(queryDO));
        }
        return page;
    }

    private void saveFinanceOrderLog(Integer financeOrderId,String content,String operator){
        AgentFinanceOrderLogDO agentFinanceOrderLogDO = new AgentFinanceOrderLogDO();
        agentFinanceOrderLogDO.setFinanceOrderId(financeOrderId);
        agentFinanceOrderLogDO.setContent(content);
        agentFinanceOrderLogDO.setCreator(operator);
        agentFinanceOrderLogDO.setCreateTime(DateUtil.getCurrentDate());
        agentFinanceOrderLogMapper.insert(agentFinanceOrderLogDO);
    }

    private List<FinanceOrderLogResponseDTO> queryFinanceOrderLog(Integer financeOrderId){

        List<FinanceOrderLogResponseDTO> logDTOList = new ArrayList<>();

        Example example = new Example(AgentFinanceOrderLogDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("financeOrderId",financeOrderId);
        List<AgentFinanceOrderLogDO> doList = agentFinanceOrderLogMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(doList)){
            log.info("没有工单日志，{}",JSON.toJSONString(financeOrderId));
            return logDTOList;
        }
        FinanceOrderLogResponseDTO dto = null;
        for (AgentFinanceOrderLogDO tempDO : doList){
            dto = new FinanceOrderLogResponseDTO();
            dto.setOperator(tempDO.getCreator());
            dto.setFinanceOrderId(tempDO.getFinanceOrderId());
            dto.setOperateTime(DateUtil.dateToStringWithHms(tempDO.getCreateTime()));
            dto.setContent(tempDO.getContent());

            logDTOList.add(dto);
        }
        return logDTOList;
    }

}
