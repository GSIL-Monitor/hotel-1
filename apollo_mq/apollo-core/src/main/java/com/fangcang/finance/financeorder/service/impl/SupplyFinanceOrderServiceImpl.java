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
import com.fangcang.finance.bill.service.SupplyBillPayHandle;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.enums.FinanceStatusEnum;
import com.fangcang.finance.enums.FinanceTypeEnum;
import com.fangcang.finance.enums.PassageEnum;
import com.fangcang.finance.financeorder.domain.AgentFinancePayItemAttchDO;
import com.fangcang.finance.financeorder.domain.SupplyFinanceOrderCurrencyDO;
import com.fangcang.finance.financeorder.domain.SupplyFinanceOrderDO;
import com.fangcang.finance.financeorder.domain.SupplyFinanceOrderItemDO;
import com.fangcang.finance.financeorder.domain.SupplyFinanceOrderLogDO;
import com.fangcang.finance.financeorder.domain.SupplyFinancePayItemAttchDO;
import com.fangcang.finance.financeorder.domain.SupplyFinancePayItemDO;
import com.fangcang.finance.financeorder.domain.SupplySingleBalanceDO;
import com.fangcang.finance.financeorder.mapper.SupplyFinanceOrderCurrencyMapper;
import com.fangcang.finance.financeorder.mapper.SupplyFinanceOrderItemMapper;
import com.fangcang.finance.financeorder.mapper.SupplyFinanceOrderLogMapper;
import com.fangcang.finance.financeorder.mapper.SupplyFinanceOrderMapper;
import com.fangcang.finance.financeorder.mapper.SupplyFinanceOrderSQLMapper;
import com.fangcang.finance.financeorder.mapper.SupplyFinancePayItemAttchMapper;
import com.fangcang.finance.financeorder.mapper.SupplyFinancePayItemMapper;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.ConfirmPayRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderItemDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.FinancePayItemAttchRequestDTO;
import com.fangcang.finance.financeorder.request.QueryBillRequestDTO;
import com.fangcang.finance.financeorder.request.QuerySupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.request.SupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.VoucherRequestDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderLogResponseDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.FinancePayItemAttchResponseDTO;
import com.fangcang.finance.financeorder.response.QuerySupplyFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.finance.financeorder.response.SupplyOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.UnpayResponseDTO;
import com.fangcang.finance.financeorder.response.VoucherResponseDTO;
import com.fangcang.finance.financeorder.service.SupplyFinanceOrderService;
import com.fangcang.finance.prepay.request.DebuctOrRetreatPrepayRequestDTO;
import com.fangcang.finance.prepay.service.PrepayContractService;
import com.fangcang.order.request.SupplyFinanceCallbackRequestDTO;
import com.fangcang.order.service.SupplyFinanceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
public class SupplyFinanceOrderServiceImpl implements SupplyFinanceOrderService {

    @Autowired
    private SupplyFinanceOrderMapper supplyFinanceOrderMapper;

    @Autowired
    private SupplyFinanceOrderCurrencyMapper supplyFinanceOrderCurrencyMapper;

    @Autowired
    private SupplyFinancePayItemMapper supplyFinancePayItemMapper;

    @Autowired
    private SupplyFinancePayItemAttchMapper supplyFinancePayItemAttchMapper;

    @Autowired
    private SupplyFinanceOrderSQLMapper supplyFinanceOrderSQLMapper;

    @Autowired
    private SupplyFinanceService supplyFinanceService;

    @Autowired
    private SupplyBillPayHandle supplyBillPayHandle;

    @Autowired
    private PrepayContractService prepayContractService;

    @Autowired
    private SupplyFinanceOrderLogMapper supplyFinanceOrderLogMapper;

    @Autowired
    private SupplyFinanceOrderItemMapper supplyFinanceOrderItemMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO<Integer> notifyRefundAmount(SupplyFinanceOrderRequestDTO supplyFinanceOrderRequestDTO) {
        log.debug("enter notifyRefundAmount,{}",JSON.toJSONString(supplyFinanceOrderRequestDTO));
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        if (null == supplyFinanceOrderRequestDTO){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_PARAM_IS_NULL);
            return responseDTO;
        }

        if (!StringUtil.isValidString(supplyFinanceOrderRequestDTO.getOrderCode())){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CODE_CANNOT_NULL);
            return responseDTO;
        }

        if (null == supplyFinanceOrderRequestDTO.getFinanceStatus()){
            supplyFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        }

//        supplytFinanceOrderRequestDTO.setFinanceType(FinanceTypeEnum.SUPPLIERORDERFUND.key);
        ResponseDTO<Integer> createResponseDTO = this.createFinanceOrder(supplyFinanceOrderRequestDTO);

        //记录日志
        this.saveFinanceOrderLog(createResponseDTO.getModel(),buildLogContent(supplyFinanceOrderRequestDTO.getFinanceType(),
                supplyFinanceOrderRequestDTO.getFinanceStatus(),supplyFinanceOrderRequestDTO.getCurrency(),
                supplyFinanceOrderRequestDTO.getNotifyAmount()).toString(),supplyFinanceOrderRequestDTO.getCreator());

        responseDTO.setModel(createResponseDTO.getModel());
        log.debug("exit notifyRefundAmount,{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO<Integer> notifyPayableAmount(SupplyFinanceOrderRequestDTO supplyFinanceOrderRequestDTO) {
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        if (null == supplyFinanceOrderRequestDTO){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_PARAM_IS_NULL);
            return responseDTO;
        }

        if (!StringUtil.isValidString(supplyFinanceOrderRequestDTO.getOrderCode())){
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CODE_CANNOT_NULL);
            return responseDTO;
        }

        if (null == supplyFinanceOrderRequestDTO.getFinanceStatus()){
            supplyFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        }

//        supplyFinanceOrderRequestDTO.setFinanceType(FinanceTypeEnum.SUPPLIERORDERPAY.key);
        ResponseDTO<Integer> createResponseDTO = this.createFinanceOrder(supplyFinanceOrderRequestDTO);

        //记录日志
        this.saveFinanceOrderLog(createResponseDTO.getModel(),buildLogContent(supplyFinanceOrderRequestDTO.getFinanceType(),
                supplyFinanceOrderRequestDTO.getFinanceStatus(),supplyFinanceOrderRequestDTO.getCurrency(),
                supplyFinanceOrderRequestDTO.getNotifyAmount()).toString(),supplyFinanceOrderRequestDTO.getCreator());

        responseDTO.setModel(createResponseDTO.getModel());

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(String orderCode) {
        List<QueryTradeListForOrderResponseDTO> queryTradeListForOrderResponseDTOList = new ArrayList<>();

        QuerySupplyFinanceOrderRequestDTO querySupplyFinanceOrderRequestDTO = new QuerySupplyFinanceOrderRequestDTO();
        querySupplyFinanceOrderRequestDTO.setOrderCode(orderCode);
        ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> queryPayItemListResponse = this.queryPayItemList(querySupplyFinanceOrderRequestDTO);

        List<QuerySupplyFinanceOrderResponseDTO> supplyFinanceOrderPayItemResponseList = queryPayItemListResponse.getModel();
        QueryTradeListForOrderResponseDTO queryTradeListForOrderResponseDTO = null;
        for (QuerySupplyFinanceOrderResponseDTO tempDTO : supplyFinanceOrderPayItemResponseList){
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
    public ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> queryPayItemList(@Validated QuerySupplyFinanceOrderRequestDTO querySupplyFinanceOrderRequestDTO) {
        ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> response = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        List<QuerySupplyFinanceOrderResponseDTO> supplyFinanceOrderPayItemResponseList = new ArrayList<>();
        QuerySupplyFinanceOrderResponseDTO supplyFinanceOrderPayItemResponse = null;

        Example supplyFinanceOrderCondition = new Example(SupplyFinanceOrderDO.class);
        Example.Criteria supplyFinanceOrderCriteria = supplyFinanceOrderCondition.createCriteria();
        if (StringUtil.isValidString(querySupplyFinanceOrderRequestDTO.getOrderCode())){
            supplyFinanceOrderCriteria.andEqualTo("orderCode", querySupplyFinanceOrderRequestDTO.getOrderCode());
        }
        if (!CollectionUtils.isEmpty(querySupplyFinanceOrderRequestDTO.getOrderCodeList())){
            supplyFinanceOrderCriteria.andIn("orderCode", querySupplyFinanceOrderRequestDTO.getOrderCodeList());
        }

        if (null != querySupplyFinanceOrderRequestDTO.getFinanceType() && querySupplyFinanceOrderRequestDTO.getFinanceType() > 0){
            supplyFinanceOrderCriteria.andEqualTo("financeType", querySupplyFinanceOrderRequestDTO.getFinanceType());
        }

        if (null != querySupplyFinanceOrderRequestDTO.getFinanceStatus()){
            supplyFinanceOrderCriteria.andEqualTo("financeStatus",querySupplyFinanceOrderRequestDTO.getFinanceStatus());
        }

        if (!CollectionUtils.isEmpty(querySupplyFinanceOrderRequestDTO.getFinanceStatusList())){
            supplyFinanceOrderCriteria.andIn("financeStatus",querySupplyFinanceOrderRequestDTO.getFinanceStatusList());
        }

        //工单列表
        List<SupplyFinanceOrderDO> supplyFinanceOrderDOList = supplyFinanceOrderMapper.selectByExample(supplyFinanceOrderCondition);

        if (CollectionUtils.isEmpty(supplyFinanceOrderDOList)){
            response.setModel(supplyFinanceOrderPayItemResponseList);
            return response;
        }

        for (SupplyFinanceOrderDO supplyFinanceOrderDO : supplyFinanceOrderDOList){
            supplyFinanceOrderPayItemResponse = new QuerySupplyFinanceOrderResponseDTO();
            supplyFinanceOrderPayItemResponse.setFinanceCode(supplyFinanceOrderDO.getFinanceCode());
            supplyFinanceOrderPayItemResponse.setFinanceStatus(supplyFinanceOrderDO.getFinanceStatus());
            supplyFinanceOrderPayItemResponse.setFinanceType(supplyFinanceOrderDO.getFinanceType());
            supplyFinanceOrderPayItemResponse.setOrderCode(supplyFinanceOrderDO.getOrderCode());
            supplyFinanceOrderPayItemResponse.setFinanceOrderId(supplyFinanceOrderDO.getId());

            //工单金额：金额和币种字段
            Example supplyFinanceOrderCurrencyCondition = new Example(SupplyFinanceOrderCurrencyDO.class);
            Example.Criteria supplyFinanceOrderCurrencyCriteria = supplyFinanceOrderCurrencyCondition.createCriteria();
            supplyFinanceOrderCurrencyCriteria.andEqualTo("financeOrderId",supplyFinanceOrderDO.getId());
            List<SupplyFinanceOrderCurrencyDO> agentFinanceOrderCurrencyList = supplyFinanceOrderCurrencyMapper.selectByExample(supplyFinanceOrderCurrencyCondition);
            if (!CollectionUtils.isEmpty(agentFinanceOrderCurrencyList)){
                supplyFinanceOrderPayItemResponse.setCurrency(agentFinanceOrderCurrencyList.get(0).getCurrency());
                supplyFinanceOrderPayItemResponse.setNotifyAmount(agentFinanceOrderCurrencyList.get(0).getReceivableAmount());
            }

            //转账记录:
            Example supplyFinancePayItemCondition = new Example(SupplyFinancePayItemDO.class);
            Example.Criteria supplyFinancePayItemCriteria = supplyFinancePayItemCondition.createCriteria();
            supplyFinancePayItemCriteria.andEqualTo("financeOrderId",supplyFinanceOrderDO.getId());
            List<SupplyFinancePayItemDO> agentFinancePayItemDOList = supplyFinancePayItemMapper.selectByExample(supplyFinancePayItemCondition);
            List<VoucherResponseDTO> voucherList = PropertyCopyUtil.transferArray(agentFinancePayItemDOList,VoucherResponseDTO.class);
            supplyFinanceOrderPayItemResponse.setVoucherList(voucherList);

            supplyFinanceOrderPayItemResponseList.add(supplyFinanceOrderPayItemResponse);
        }

        response.setModel(supplyFinanceOrderPayItemResponseList);
        return response;
    }

    @Override
    public PaginationSupportDTO<SupplyOrderListResponseDTO> getUnpaid(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        singleBalanceQueryDTO.setFinanceOrderStatus(1);
        PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupport = this.getFinanceOrderPagination(singleBalanceQueryDTO);
        return paginationSupport;
    }

    @Override
    public List<UnpayResponseDTO> exportUnpayOrder(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        SupplySingleBalanceDO supplySingleBalanceDO=new SupplySingleBalanceDO();
        BeanUtils.copyProperties(singleBalanceQueryDTO,supplySingleBalanceDO);
        return supplyFinanceOrderSQLMapper.exportUnpayOrder(supplySingleBalanceDO);
    }

    @Override
    public List<UnpayResponseDTO> exportUnpayBill(QueryBillRequestDTO requestDTO) {
        return supplyFinanceOrderSQLMapper.exportUnpayBill(requestDTO);
    }

    @Override
    public PaginationSupportDTO<SupplyOrderListResponseDTO> getPaid(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        singleBalanceQueryDTO.setFinanceOrderStatus(2);
        PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupport = this.getFinanceOrderPagination(singleBalanceQueryDTO);
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<SupplyOrderListResponseDTO> getUnfinished(SingleBalanceQueryDTO singleBalanceQueryDTO) {
        singleBalanceQueryDTO.setFinanceOrderStatus(3);
        PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupport = this.getFinanceOrderPagination(singleBalanceQueryDTO);
        return paginationSupport;
    }

    @Override
    public void cancelFinanceOrder(FinanceOrderRequestDTO financeOrderRequestDTO) {
        Example supplyFinanceOrderExample = new Example(SupplyFinanceOrderDO.class);
        Example.Criteria supplyFinanceOrderCriteria = supplyFinanceOrderExample.createCriteria();
        supplyFinanceOrderCriteria.andEqualTo("id",financeOrderRequestDTO.getFinanceOrderId());
        SupplyFinanceOrderDO updateFinanceOrderDO = new SupplyFinanceOrderDO();
        updateFinanceOrderDO.setFinanceStatus(FinanceStatusEnum.CANCELED.key);
        supplyFinanceOrderMapper.updateByExampleSelective(updateFinanceOrderDO,supplyFinanceOrderExample);

        if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key == financeOrderRequestDTO.getFinanceType()){
            orderFinanceCallBack(financeOrderRequestDTO.getFinanceType(),financeOrderRequestDTO.getOrderCode(),financeOrderRequestDTO.getOperator(),2);
        }

        //记录日志
        this.saveFinanceOrderLog(financeOrderRequestDTO.getFinanceOrderId(),buildLogContent(financeOrderRequestDTO.getFinanceType(),
                FinanceStatusEnum.CANCELED.key,null,
                financeOrderRequestDTO.getNotifyAmount()).toString(),financeOrderRequestDTO.getCreator());

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer confirmFinanceOrder(ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO) {
        //1-更新工单和多币种表：更新工单状态。（因为工单是在通知财务收付款的时候创建的）
        SupplyFinanceOrderDO supplyFinanceOrderDO = new SupplyFinanceOrderDO();
        supplyFinanceOrderDO.setId(confirmFinanceOrderRequestDTO.getFinanceOrderId());
        supplyFinanceOrderDO.setFinanceStatus(FinanceStatusEnum.CHECKOUT.key);
        supplyFinanceOrderDO.setModifier(confirmFinanceOrderRequestDTO.getModifier());
        supplyFinanceOrderDO.setModifyTime(confirmFinanceOrderRequestDTO.getModifyTime());
        supplyFinanceOrderMapper.updateByPrimaryKeySelective(supplyFinanceOrderDO);

        //2-更新工单明细（几个银行）:先删除，再添加
        Example supplyFinancePayItemExample = new Example(SupplyFinancePayItemDO.class);
        Example.Criteria supplyFinancePayItemCriteria = supplyFinancePayItemExample.createCriteria();
        supplyFinancePayItemCriteria.andEqualTo("financeOrderId",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        supplyFinancePayItemMapper.deleteByExample(supplyFinancePayItemExample);

        //旧的itemId
        List<Integer> payItemIdList = new ArrayList<>();
        //附件：key=新的itemId,value=附件
        Map<Integer,List<FinancePayItemAttchRequestDTO>> attchMap = new HashMap<>();

        //扣还预付款
        List<DebuctOrRetreatPrepayRequestDTO> debuctOrRetreatPrepayRequestDTOList = new ArrayList<>();

        List<VoucherRequestDTO> financePayItemDTOLsit = confirmFinanceOrderRequestDTO.getVoucherList();
        for (VoucherRequestDTO vDTO : financePayItemDTOLsit){
            SupplyFinancePayItemDO tempItemDO = PropertyCopyUtil.transfer(vDTO,SupplyFinancePayItemDO.class);
            tempItemDO.setModifyTime(confirmFinanceOrderRequestDTO.getModifyTime());
            tempItemDO.setModifier(confirmFinanceOrderRequestDTO.getModifier());
            tempItemDO.setCreator(confirmFinanceOrderRequestDTO.getCreator());
            tempItemDO.setCreateTime(confirmFinanceOrderRequestDTO.getCreateTime());
            tempItemDO.setFinanceOrderId(confirmFinanceOrderRequestDTO.getFinanceOrderId());
            supplyFinancePayItemMapper.insert(tempItemDO);

            //附件 key=新的itemId；value=附件列表
            attchMap.put(tempItemDO.getId(),vDTO.getFinancePayItemAttchList());
            //旧的itemId集合
            payItemIdList.add(vDTO.getId());

            if (PassageEnum.PREPAY.key == vDTO.getPassage()){
                debuctOrRetreatPrepayRequestDTOList.add(buildPrepayRequest(vDTO,confirmFinanceOrderRequestDTO));
            }
        }

        //3-更新附件：先根据payItemId删除，再添加（有可能在订单通知的时候有附加，在收款的时候，把附件删除或者新增了）
        List<SupplyFinancePayItemAttchDO> allAttchDOList = new ArrayList<>();
        for(Map.Entry<Integer,List<FinancePayItemAttchRequestDTO>> entry : attchMap.entrySet()){
            List<SupplyFinancePayItemAttchDO> attchDOList = PropertyCopyUtil.transferArray(entry.getValue(),SupplyFinancePayItemAttchDO.class);
            if (attchDOList!=null){
                attchDOList.forEach(tempAttchDO -> {
                    tempAttchDO.setPayItemId(entry.getKey());
                });
                allAttchDOList.addAll(attchDOList);
            }
        }

        Example supplyFinancePayItemAttchExample = new Example(SupplyFinancePayItemAttchDO.class);
        Example.Criteria supplyFinancePayItemAttchCriteria = supplyFinancePayItemAttchExample.createCriteria();
        supplyFinancePayItemAttchCriteria.andIn("payItemId",payItemIdList);
        supplyFinancePayItemAttchMapper.deleteByExample(supplyFinancePayItemAttchExample);
        if (!CollectionUtils.isEmpty(allAttchDOList)){
            supplyFinancePayItemAttchMapper.insertList(allAttchDOList);
        }

        //如果是使用预付款
        debuctOrRetreatPrepay(debuctOrRetreatPrepayRequestDTOList);

        //回写订单/账单的结算状态和结算金额
        this.orderFinanceCallBack(confirmFinanceOrderRequestDTO.getFinanceType(),confirmFinanceOrderRequestDTO.getOrderCode(),confirmFinanceOrderRequestDTO.getCreator(),1);

        //记录日志
        this.saveFinanceOrderLog(confirmFinanceOrderRequestDTO.getFinanceOrderId(),buildLogContent(confirmFinanceOrderRequestDTO.getFinanceType(),
                FinanceStatusEnum.CHECKOUT.key,confirmFinanceOrderRequestDTO.getCurrency(),
                confirmFinanceOrderRequestDTO.getNotifyAmount()).toString(),confirmFinanceOrderRequestDTO.getCreator());

        return 1;
    }

    private void debuctOrRetreatPrepay(List<DebuctOrRetreatPrepayRequestDTO> debuctOrRetreatPrepayRequestDTOList){
        if (CollectionUtils.isEmpty(debuctOrRetreatPrepayRequestDTOList)){
            log.info("使用预付款扣退配额时，参数为空，无需执行");
            return ;
        }

        debuctOrRetreatPrepayRequestDTOList.forEach(
                tempRequest -> {
                    ResponseDTO responseTemp = prepayContractService.debuctOrRetreatPrepay(tempRequest);
                    if (responseTemp.getResult() != 1){
                        log.error("调用扣还预付款接口返回失败，请求：{}，返回：{}",JSON.toJSONString(tempRequest),JSON.toJSONString(responseTemp));
                        throw new ServiceException(responseTemp.getFailReason());
                    }
                });

    }

    private DebuctOrRetreatPrepayRequestDTO buildPrepayRequest(VoucherRequestDTO vDTO, ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO) {

        if (vDTO.getPassage() != PassageEnum.PREPAY.key){
            log.info("不是预付款类型，不需要组装，{}",JSON.toJSONString(confirmFinanceOrderRequestDTO));
            return null;
        }

        DebuctOrRetreatPrepayRequestDTO requestDTO = new DebuctOrRetreatPrepayRequestDTO();
        requestDTO.setAmount(vDTO.getAmount());
        requestDTO.setContractId(vDTO.getContractId());
        requestDTO.setType(getPrepayType(confirmFinanceOrderRequestDTO.getFinanceType()));
        requestDTO.setOrderType(getOrderType(confirmFinanceOrderRequestDTO.getFinanceType()));
        requestDTO.setBillName(confirmFinanceOrderRequestDTO.getBillName());
        requestDTO.setFinanceOrderId(String.valueOf(confirmFinanceOrderRequestDTO.getFinanceOrderId()));
        requestDTO.setNote(vDTO.getNote());
        requestDTO.setOrderCode(confirmFinanceOrderRequestDTO.getOrderCode());
        requestDTO.setCreator(confirmFinanceOrderRequestDTO.getCreator());
        requestDTO.setCreateTime(confirmFinanceOrderRequestDTO.getCreateTime());
        StringBuffer contentBuffer = new StringBuffer();
        if (StringUtil.isValidString(confirmFinanceOrderRequestDTO.getBillName())){
            contentBuffer.append(confirmFinanceOrderRequestDTO.getBillName());
        }
        if (StringUtil.isValidString(confirmFinanceOrderRequestDTO.getOrderCode())){
            if (contentBuffer.length()>0) {
                contentBuffer.append(",");
            }
            contentBuffer.append(confirmFinanceOrderRequestDTO.getOrderCode());
        }
        requestDTO.setContent(contentBuffer.toString());
        return requestDTO;
    }

    private Integer getOrderType(Integer financeType) {
        //订单
        Integer order = Integer.valueOf(1) ;
        //工单
        Integer financeOrder = Integer.valueOf(2) ;
        switch (financeType){
            case 4 : return financeOrder;//退还
            case 3 : return financeOrder;//扣
            case 6 : return order;
            default: return null;
        }

    }

    private Integer getPrepayType(Integer financeType) {
        //退款
        Integer debuct = Integer.valueOf(1) ;
        //扣
        Integer retreat = Integer.valueOf(2) ;
        switch (financeType){
            case 4 : return retreat;//退还
            case 3 : return debuct;//扣
            case 6 : return debuct;
            default: return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO<Integer> confirmPay(ConfirmPayRequestDTO confirmPayRequestDTO) {
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        //1-创建已确认的工单，
        SupplyFinanceOrderRequestDTO supplyFinanceOrderRequestDTO = PropertyCopyUtil.transfer(confirmPayRequestDTO,SupplyFinanceOrderRequestDTO.class);
        if (null == supplyFinanceOrderRequestDTO.getFinanceStatus()){
            supplyFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.CHECKOUT.key);
        }
        ResponseDTO<Integer> financeOrderResponseDTO = this.notifyRefundAmount(supplyFinanceOrderRequestDTO);

        //扣还预付款
        List<DebuctOrRetreatPrepayRequestDTO> debuctOrRetreatPrepayRequestDTOList = new ArrayList<>();
        //2使用预付款支付需要扣还
        for (VoucherRequestDTO vDTO : confirmPayRequestDTO.getVoucherList()){
            DebuctOrRetreatPrepayRequestDTO debuctOrRetreatPrepayRequestDTO = this.buildPrepayRequest(vDTO,PropertyCopyUtil.transfer(confirmPayRequestDTO,ConfirmFinanceOrderRequestDTO.class));
            if (null != debuctOrRetreatPrepayRequestDTO){
                debuctOrRetreatPrepayRequestDTOList.add(debuctOrRetreatPrepayRequestDTO);
            }
        }

        this.debuctOrRetreatPrepay(debuctOrRetreatPrepayRequestDTOList);

        // 3-回写订单结算金额和结算状态
        orderFinanceCallBack(confirmPayRequestDTO.getFinanceType(),confirmPayRequestDTO.getOrderCode(),confirmPayRequestDTO.getCreator(),1);

//        //记录日志  通知收款接口已经记录了。这里就不再记录日志了。
//        this.saveFinanceOrderLog(financeOrderResponseDTO.getModel(),
//                buildLogContent(confirmPayRequestDTO.getFinanceType(),confirmPayRequestDTO.getFinanceStatus(),confirmPayRequestDTO.getCurrency(),confirmPayRequestDTO.getNotifyAmount()).toString(),
//                confirmPayRequestDTO.getCreator());

        responseDTO.setModel(financeOrderResponseDTO.getModel());

        return responseDTO;
    }

    @Override
    public ConfirmFinanceOrderResponseDTO queryFinanceOrderDetail(ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO) {

        Example financeOrderExample = new Example(SupplyFinanceOrderDO.class);
        Example.Criteria financeOrderCriteria = financeOrderExample.createCriteria();
        financeOrderCriteria.andEqualTo("id",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        SupplyFinanceOrderDO financeOrderDO = supplyFinanceOrderMapper.selectOneByExample(financeOrderExample);

        Example currencyExample = new Example(SupplyFinanceOrderCurrencyDO.class);
        Example.Criteria currencyCriteria = currencyExample.createCriteria();
        currencyCriteria.andEqualTo("financeOrderId",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        SupplyFinanceOrderCurrencyDO currencyDO = supplyFinanceOrderCurrencyMapper.selectOneByExample(currencyExample);

        Example payItemExample = new Example(SupplyFinancePayItemDO.class);
        Example.Criteria payItemCriteria = payItemExample.createCriteria();
        payItemCriteria.andEqualTo("financeOrderId",confirmFinanceOrderRequestDTO.getFinanceOrderId());
        List<SupplyFinancePayItemDO> supplyFinancePayItemDOList = supplyFinancePayItemMapper.selectByExample(payItemExample);
        List<VoucherResponseDTO> voucherList = PropertyCopyUtil.transferArray(supplyFinancePayItemDOList,VoucherResponseDTO.class);

        for (VoucherResponseDTO payItemTemp : voucherList){
            Example attchExample = new Example(AgentFinancePayItemAttchDO.class);
            Example.Criteria attchCriteria = attchExample.createCriteria();
            attchCriteria.andEqualTo("payItemId",payItemTemp.getId());
            List<SupplyFinancePayItemAttchDO> attchDOList = supplyFinancePayItemAttchMapper.selectByExample(attchExample);
            payItemTemp.setFinancePayItemAttchList(PropertyCopyUtil.transferArray(attchDOList, FinancePayItemAttchResponseDTO.class));
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
        //2.1查询所有工单
        QuerySupplyFinanceOrderRequestDTO querySupplyFinanceOrderRequestDTO = new QuerySupplyFinanceOrderRequestDTO();
        querySupplyFinanceOrderRequestDTO.setOrderCode(orderCode);
        ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> queryPayItemResponse = this.queryPayItemList(querySupplyFinanceOrderRequestDTO);
        List<QuerySupplyFinanceOrderResponseDTO> supplyFinanceOrderResponseDTOList = queryPayItemResponse.getModel();

        //确认工单
        if (1 == operateType){
            //订单回写
            confirmSingleOrderFinanceCallBack(financeType,orderCode,operator,supplyFinanceOrderResponseDTOList);

            //账单回写
            confirmBillOrderFinanceCallBack(financeType,orderCode, operator, supplyFinanceOrderResponseDTOList);
        }
        //取消工单
        else if (2 == operateType){
            cancelBillOrderCallBack(financeType,orderCode,operator,supplyFinanceOrderResponseDTOList);
        }

    }

    /**
     *  确认工单时：回调账单的确认工单接口
     * @param orderCode
     * @param operator
     * @param supplyFinanceOrderResponseDTOList
     */
    private void confirmBillOrderFinanceCallBack(Integer financeType,String orderCode, String operator, List<QuerySupplyFinanceOrderResponseDTO> supplyFinanceOrderResponseDTOList) {
        if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key != financeType.intValue()){
            log.info("非挂账的工单确认不需要执行账单的回调，请求参数:{},{},{}",FinanceTypeEnum.getValueByKey(financeType),orderCode,JSON.toJSONString(supplyFinanceOrderResponseDTOList));
            return;
        }

//        //所有已结算的工单总额
//        BigDecimal totalPaid = new BigDecimal("0");
//        for (QuerySupplyFinanceOrderResponseDTO financeOrderResponseTemp : supplyFinanceOrderResponseDTOList){
//            if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key == financeOrderResponseTemp.getFinanceType().intValue()
//                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){
//                totalPaid = totalPaid.add(financeOrderResponseTemp.getNotifyAmount());
//            }
//        }
//
//        List<MultipleCurrencyAmountDTO> multipleCurrencyList = new ArrayList<>();
//        MultipleCurrencyAmountDTO multipleCurrencyAmountDTO = new MultipleCurrencyAmountDTO();
//        multipleCurrencyAmountDTO.setPaidinAmount(totalPaid);
//        multipleCurrencyAmountDTO.setCurrency("CNY");
//        multipleCurrencyList.add(multipleCurrencyAmountDTO);
//        ConfirmBillFiannceOrderDTO confirmBillFiannceOrderDTO = new ConfirmBillFiannceOrderDTO();
//        confirmBillFiannceOrderDTO.setBillCode(orderCode);
//        confirmBillFiannceOrderDTO.setOperator(operator);
//        confirmBillFiannceOrderDTO.setMultipleCurrencyList(multipleCurrencyList);

        //获取已确认的工单
        Map<String, List<QuerySupplyFinanceOrderResponseDTO>> financeOrderResponseMap = getSupplyCheckoutFinanceOrderMap(supplyFinanceOrderResponseDTOList);

        Map<String, BigDecimal> multipleMap = groupByCurrencyMap(financeOrderResponseMap);

        ConfirmBillFiannceOrderDTO confirmBillFiannceOrderDTO = buildConfirmBillFiannceOrderDTO(orderCode, operator, multipleMap);

        ResponseDTO callBackResponseDTO = supplyBillPayHandle.confirmBillFiannceOrder(confirmBillFiannceOrderDTO);
        if (null == callBackResponseDTO || null ==  callBackResponseDTO.getResult() || 1 != callBackResponseDTO.getResult()){
            log.error("账单回写确认结果失败，请求参数：{}，返回结果：{}", JSON.toJSONString(confirmBillFiannceOrderDTO),
                    JSON.toJSONString(callBackResponseDTO));
            throw new ServiceException("工单确认失败(账单类型)");
        }
    }


    /**
     * 取消工单时：回调账单的取消工单接口
     * @param orderCode 供货单号
     * @param operator
     * @param supplyFinanceOrderResponseDTOList
     */
    private void cancelBillOrderCallBack(Integer financeType,String orderCode, String operator, List<QuerySupplyFinanceOrderResponseDTO> supplyFinanceOrderResponseDTOList) {

        if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key != financeType.intValue()){
            log.info("非挂账的工单取消时无需回调账单，请求参数：{},{},{}",FinanceTypeEnum.getValueByKey(financeType),orderCode,JSON.toJSONString(supplyFinanceOrderResponseDTOList));
            return;
        }

//        //所有已结算的工单总额
//        BigDecimal totalNoticed = new BigDecimal("0");
//        for (QuerySupplyFinanceOrderResponseDTO financeOrderResponseTemp : supplyFinanceOrderResponseDTOList){
//            //所有未取消的工单之和
//            if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key == financeOrderResponseTemp.getFinanceType().intValue()
//                    && FinanceStatusEnum.CANCELED.key != financeOrderResponseTemp.getFinanceStatus()){
//                totalNoticed = totalNoticed.add(financeOrderResponseTemp.getNotifyAmount());
//            }
//        }
//
//        List<MultipleCurrencyAmountDTO> multipleCurrencyList = new ArrayList<>();
//        MultipleCurrencyAmountDTO multipleCurrencyAmountDTO = new MultipleCurrencyAmountDTO();
//        multipleCurrencyAmountDTO.setNoticeAmount(totalNoticed);
//        multipleCurrencyAmountDTO.setCurrency("CNY");
//        multipleCurrencyList.add(multipleCurrencyAmountDTO);
//
//        CancelBillFiannceOrderDTO cancelBillFiannceOrderDTO = new CancelBillFiannceOrderDTO();
//        cancelBillFiannceOrderDTO.setBillCode(orderCode);
//        cancelBillFiannceOrderDTO.setOperator(operator);
//        cancelBillFiannceOrderDTO.setMultipleCurrencyList(multipleCurrencyList);

        //所有未取消的工单
        Map<String, List<QuerySupplyFinanceOrderResponseDTO>> financeOrderResponseMap = getSupplyNotCanceledFinanceOrderMap(supplyFinanceOrderResponseDTOList);

        Map<String, BigDecimal> multipleMap = groupByCurrencyMap(financeOrderResponseMap);

        CancelBillFiannceOrderDTO cancelBillFiannceOrderDTO = buildCancelBillFiannceOrderDTO(orderCode, operator, multipleMap);

        ResponseDTO callBackResponseDTO = supplyBillPayHandle.cancelBillFiannceOrder(cancelBillFiannceOrderDTO);
        if (1 != callBackResponseDTO.getResult()){
            log.error("账单回写确认结果失败，请求参数：{}，返回结果：{}", JSON.toJSONString(cancelBillFiannceOrderDTO),
                    JSON.toJSONString(callBackResponseDTO));
            throw new ServiceException("工单确认失败(账单类型)");
        }
    }

    private void confirmSingleOrderFinanceCallBack(Integer financeType,String orderCode,String operator, List<QuerySupplyFinanceOrderResponseDTO> supplyFinanceOrderResponseDTOList) {
        if (FinanceTypeEnum.SUPPLIERORDERFUND.key != financeType.intValue() && FinanceTypeEnum.SUPPLIERORDERPAY.key != financeType.intValue()){
            log.info("不是单结订单无需执行单结挂账，请求参数：{},{},{}",FinanceTypeEnum.getValueByKey(financeType),orderCode,JSON.toJSONString(supplyFinanceOrderResponseDTOList));
            return;
        }
        //2.2计算总的实收，和总的退款。结算金额=总实收-总退款。都必须是已结结算的工单
        BigDecimal totalPaid = new BigDecimal("0");
        BigDecimal totalRefund = new BigDecimal("0");
        for (QuerySupplyFinanceOrderResponseDTO financeOrderResponseTemp : supplyFinanceOrderResponseDTOList){
            if (FinanceTypeEnum.SUPPLIERORDERPAY.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){
                totalPaid = totalPaid.add(financeOrderResponseTemp.getNotifyAmount());
            }
            else if (FinanceTypeEnum.SUPPLIERORDERFUND.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){
                totalRefund = totalRefund.add(financeOrderResponseTemp.getNotifyAmount());
            }
        }

        BigDecimal totalSettlement = totalPaid.subtract(totalRefund);

        SupplyFinanceCallbackRequestDTO orderFinanceCallbackRequestDTO = new SupplyFinanceCallbackRequestDTO();
        orderFinanceCallbackRequestDTO.setSupplyOrderCode(orderCode);
        orderFinanceCallbackRequestDTO.setSettlementAmount(totalSettlement);
        orderFinanceCallbackRequestDTO.setReceiptAmount(totalRefund);
        orderFinanceCallbackRequestDTO.setModifier(operator);
        orderFinanceCallbackRequestDTO.setModifyTime(DateUtil.getCurrentDate());
        ResponseDTO callBackResponseDTO = supplyFinanceService.supplyFinanceCallback(orderFinanceCallbackRequestDTO);
        if (1 != callBackResponseDTO.getResult()){
            log.error("确认支付时，回写供货单结算状态和结算金额失败：{}；请求参数为{}",callBackResponseDTO.getFailReason(), JSON.toJSONString(orderFinanceCallbackRequestDTO));
            throw new ServiceException("回写确认结果和结算状态失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<Integer> createFinanceOrder(SupplyFinanceOrderRequestDTO supplyFinanceOrderRequestDTO) {
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        SupplyFinanceOrderDO agentFinanceOrderDO = PropertyCopyUtil.transfer(supplyFinanceOrderRequestDTO, SupplyFinanceOrderDO.class);

        supplyFinanceOrderMapper.insert(agentFinanceOrderDO);
        Integer financeOrderId = agentFinanceOrderDO.getId();
        if (null == financeOrderId){
            log.error("通知财务收款失败：{}",JSON.toJSONString(supplyFinanceOrderRequestDTO));
            throw new ServiceException(ErrorCodeEnum.FINANCE_ORDER_ID_CREATE_ERROR.errorDesc);
        }

        SupplyFinanceOrderCurrencyDO agentFinanceOrderCurrencyDO = new SupplyFinanceOrderCurrencyDO();
        agentFinanceOrderCurrencyDO.setFinanceOrderId(agentFinanceOrderDO.getId());
        agentFinanceOrderCurrencyDO.setCurrency(supplyFinanceOrderRequestDTO.getCurrency());
        agentFinanceOrderCurrencyDO.setReceivableAmount(supplyFinanceOrderRequestDTO.getNotifyAmount());
        agentFinanceOrderCurrencyDO.setCreator(supplyFinanceOrderRequestDTO.getCreator());
        agentFinanceOrderCurrencyDO.setCreateTime(supplyFinanceOrderRequestDTO.getCreateTime());
        agentFinanceOrderCurrencyDO.setModifyTime(supplyFinanceOrderRequestDTO.getModifyTime());
        agentFinanceOrderCurrencyDO.setModifier(supplyFinanceOrderRequestDTO.getModifier());
        supplyFinanceOrderCurrencyMapper.insert(agentFinanceOrderCurrencyDO);

        if (supplyFinanceOrderRequestDTO.getFinanceOrderItemDTOList()==null){
            supplyFinanceOrderRequestDTO.setFinanceOrderItemDTOList(new ArrayList<>());
        }
        if ((supplyFinanceOrderRequestDTO.getFinanceType()==FinanceTypeEnum.ORDERPAY.key ||
                supplyFinanceOrderRequestDTO.getFinanceType()==FinanceTypeEnum.ORDERREFUND.key)
                && supplyFinanceOrderRequestDTO.getFinanceOrderItemDTOList().size()==0){
            FinanceOrderItemDTO financeOrderItemDTO=new FinanceOrderItemDTO();
            financeOrderItemDTO.setOrderCode(supplyFinanceOrderRequestDTO.getOrderCode());
            financeOrderItemDTO.setCurrency(supplyFinanceOrderRequestDTO.getCurrency());
            financeOrderItemDTO.setAmount(supplyFinanceOrderRequestDTO.getNotifyAmount());
            supplyFinanceOrderRequestDTO.getFinanceOrderItemDTOList().add(financeOrderItemDTO);
        }
        if (supplyFinanceOrderRequestDTO.getFinanceOrderItemDTOList().size()>0){
            List<SupplyFinanceOrderItemDO> agentFinanceOrderItemDOList=new ArrayList<>();
            for (FinanceOrderItemDTO financeOrderItemDTO:supplyFinanceOrderRequestDTO.getFinanceOrderItemDTOList()){
                SupplyFinanceOrderItemDO supplyFinanceOrderItemDO=new SupplyFinanceOrderItemDO();
                supplyFinanceOrderItemDO.setOrderCode(financeOrderItemDTO.getOrderCode());
                supplyFinanceOrderItemDO.setCurrency(financeOrderItemDTO.getCurrency());
                supplyFinanceOrderItemDO.setAmount(financeOrderItemDTO.getAmount());
                supplyFinanceOrderItemDO.setFinanceOrderId(agentFinanceOrderDO.getId());
                supplyFinanceOrderItemDO.setCreator(supplyFinanceOrderRequestDTO.getCreator());
                supplyFinanceOrderItemDO.setCreateTime(new Date());
                agentFinanceOrderItemDOList.add(supplyFinanceOrderItemDO);
            }
            supplyFinanceOrderItemMapper.insertList(agentFinanceOrderItemDOList);
        }

        this.createVoucher(financeOrderId, supplyFinanceOrderRequestDTO.getVoucherList());

        responseDTO.setModel(agentFinanceOrderDO.getId());
        return responseDTO;
    }



    private PaginationSupportDTO<SupplyOrderListResponseDTO> getFinanceOrderPagination(SingleBalanceQueryDTO singleBalanceQueryDTO){

        //1-分页查询订单列表
        Page<SupplySingleBalanceDO> page = this.queryFinanceOrderForPage(singleBalanceQueryDTO);

        //2-查询工单列表
        List<SupplyOrderListResponseDTO> agentOrderListResponseDTOList = getSupplyOrderListResponseDTOList(page);

        //3组装返回数据
        PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupport = buildAgentOrderListResponseDTOPaginationSupportDTO(page, agentOrderListResponseDTOList);

        return paginationSupport;
    }

    private List<SupplyOrderListResponseDTO> getSupplyOrderListResponseDTOList(Page<SupplySingleBalanceDO> page) {
        List<SupplyOrderListResponseDTO> agentOrderListResponseDTOList = new ArrayList<>();
        for (SupplySingleBalanceDO  tempDO : page.getResult()){
            //2查询工单列表：通知金额，币种，状态，工单ID。
            QuerySupplyFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO = new QuerySupplyFinanceOrderRequestDTO();
            queryAgentFinanceOrderRequestDTO.setOrderCode(tempDO.getOrderCode());
            ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> payItemResponDTO = this.queryPayItemList(queryAgentFinanceOrderRequestDTO);

            List<FinanceOrderResponseDTO> financeOrderList = PropertyCopyUtil.transferArray(payItemResponDTO.getModel(),FinanceOrderResponseDTO.class);

            SupplyOrderListResponseDTO agentOrderListResponseDTO = PropertyCopyUtil.transfer(tempDO,SupplyOrderListResponseDTO.class);
            agentOrderListResponseDTO.setOrderId(tempDO.getOrderId());
            agentOrderListResponseDTO.setSupplyOrderCode(tempDO.getOrderCode());
            agentOrderListResponseDTO.setSupplyCode(tempDO.getSupplyCode());
            agentOrderListResponseDTO.setShouldPay(tempDO.getPayableAmount());
            agentOrderListResponseDTO.setHasPaid(tempDO.getSettlementAmount());
            agentOrderListResponseDTO.setFinanceOrderList(financeOrderList);
            agentOrderListResponseDTO.setCurrency(tempDO.getSaleCurrency());
            agentOrderListResponseDTO.setOrderFinanceStatus(null == tempDO.getAccountStatus() ? 0 :tempDO.getAccountStatus().intValue());
            agentOrderListResponseDTOList.add(agentOrderListResponseDTO);
        }
        return agentOrderListResponseDTOList;
    }

    private PaginationSupportDTO<SupplyOrderListResponseDTO> buildAgentOrderListResponseDTOPaginationSupportDTO(Page<SupplySingleBalanceDO> page, List<SupplyOrderListResponseDTO> agentOrderListResponseDTOList) {
        PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupport = new PaginationSupportDTO<SupplyOrderListResponseDTO>();
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
     */
    private void createVoucher(Integer financeOrderId,List<VoucherRequestDTO> voucherList){
        if (CollectionUtils.isEmpty(voucherList)){
            return ;
        }

        for (VoucherRequestDTO voucherRequestDTO : voucherList){
            SupplyFinancePayItemDO  agentFinancePayItemDO = PropertyCopyUtil.transfer(voucherRequestDTO,SupplyFinancePayItemDO.class);
            agentFinancePayItemDO.setFinanceOrderId(financeOrderId);
            supplyFinancePayItemMapper.insert(agentFinancePayItemDO);
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

        List<SupplyFinancePayItemAttchDO> agentFinancePayItemAttchDOList = PropertyCopyUtil.transferArray(financePayItemAttchList,SupplyFinancePayItemAttchDO.class);

        agentFinancePayItemAttchDOList.forEach(agentFinancePayItemAttchDO1 -> {agentFinancePayItemAttchDO1.setPayItemId(payItemId);});

        supplyFinancePayItemAttchMapper.insertList(agentFinancePayItemAttchDOList);
    }

    private Page<SupplySingleBalanceDO> queryFinanceOrderForPage(SingleBalanceQueryDTO singleBalanceQueryDTO){

        Page<SupplySingleBalanceDO> page = null;

        SupplySingleBalanceDO queryDO = new SupplySingleBalanceDO();
        queryDO.setSupplyCode(singleBalanceQueryDTO.getOrgCode());
        queryDO.setSupplyName(singleBalanceQueryDTO.getOrgName());
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


        String orderBy = "d.id desc";
        //查询待收款
        if (1 == singleBalanceQueryDTO.getFinanceOrderStatus()){
            page = PageHelper.startPage(singleBalanceQueryDTO.getCurrentPage(), singleBalanceQueryDTO.getPageSize(), orderBy)
                    .doSelectPage(() -> supplyFinanceOrderSQLMapper.queryUnpaid(queryDO));
        }
        //查询已收款
        else if (2 == singleBalanceQueryDTO.getFinanceOrderStatus()){
            page = PageHelper.startPage(singleBalanceQueryDTO.getCurrentPage(), singleBalanceQueryDTO.getPageSize(), orderBy)
                    .doSelectPage(() -> supplyFinanceOrderSQLMapper.queryPaid(queryDO));
        }
        //查询未完成
        else if (3 == singleBalanceQueryDTO.getFinanceOrderStatus()){
            page = PageHelper.startPage(singleBalanceQueryDTO.getCurrentPage(), singleBalanceQueryDTO.getPageSize(), orderBy)
                    .doSelectPage(() -> supplyFinanceOrderSQLMapper.queryUnfinished(queryDO));
        }
        return page;
    }


    private StringBuffer buildLogContent(Integer financeType,Integer financeStatus,String currency,BigDecimal amount) {
        String defaultCurrency = StringUtil.isValidString(currency) ? currency : "CNY";
        StringBuffer contentBuffer = new StringBuffer();
        if (FinanceTypeEnum.SUPPLIERORDERPAY.key == financeType || FinanceTypeEnum.SUPPLIERBILLSATTLE.key == financeType){
            if (financeStatus == FinanceStatusEnum.NEW.key){
                contentBuffer.append("通知付款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CHECKOUT.key){
                contentBuffer.append("直接付款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CANCELED.key){
                contentBuffer.append("作废付款");
            }
        } else if (FinanceTypeEnum.SUPPLIERORDERFUND.key == financeType){
            if (financeStatus == FinanceStatusEnum.NEW.key){
                contentBuffer.append("通知收款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CHECKOUT.key){
                contentBuffer.append("直接收款").append(amount).append(defaultCurrency);
            } else if (financeStatus == FinanceStatusEnum.CANCELED.key){
                contentBuffer.append("作废收款");
            }
        }
        return contentBuffer;
    }

    private void saveFinanceOrderLog(Integer financeOrderId,String content,String operator){
        SupplyFinanceOrderLogDO supplyFinanceOrderLogDO = new SupplyFinanceOrderLogDO();
        supplyFinanceOrderLogDO.setFinanceOrderId(financeOrderId);
        supplyFinanceOrderLogDO.setContent(content);
        supplyFinanceOrderLogDO.setCreator(operator);
        supplyFinanceOrderLogDO.setCreateTime(DateUtil.getCurrentDate());
        supplyFinanceOrderLogMapper.insert(supplyFinanceOrderLogDO);
    }

    private List<FinanceOrderLogResponseDTO> queryFinanceOrderLog(Integer financeOrderId){
        Example example = new Example(SupplyFinanceOrderLogDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("financeOrderId",financeOrderId);
        List<SupplyFinanceOrderLogDO> doList = supplyFinanceOrderLogMapper.selectByExample(example);

        List<FinanceOrderLogResponseDTO> logDTOList = new ArrayList<>();
        FinanceOrderLogResponseDTO dto = null;
        for (SupplyFinanceOrderLogDO tempDO : doList){
            dto = new FinanceOrderLogResponseDTO();
            dto.setOperator(tempDO.getCreator());
            dto.setFinanceOrderId(tempDO.getFinanceOrderId());
            dto.setOperateTime(DateUtil.dateToStringWithHms(tempDO.getCreateTime()));
            dto.setContent(tempDO.getContent());

            logDTOList.add(dto);
        }
        return logDTOList;
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
    private Map<String, BigDecimal> groupByCurrencyMap(Map<String, List<QuerySupplyFinanceOrderResponseDTO>> financeOrderResponseMap) {
        Map<String,BigDecimal> multipleMap = new HashMap();
        for (Map.Entry<String,List<QuerySupplyFinanceOrderResponseDTO>> entry : financeOrderResponseMap.entrySet()){
            String currency = entry.getKey();
            List<QuerySupplyFinanceOrderResponseDTO> currentReceivedList = entry.getValue();
            if (CollectionUtils.isEmpty(currentReceivedList)){
                continue;
            }

            BigDecimal totalAmount = new BigDecimal("0");
            for (QuerySupplyFinanceOrderResponseDTO temDTO : currentReceivedList){
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
    private Map<String, List<QuerySupplyFinanceOrderResponseDTO>> getSupplyCheckoutFinanceOrderMap(List<QuerySupplyFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {
        Map<String,List<QuerySupplyFinanceOrderResponseDTO>> financeOrderResponseMap = new HashMap();
        for (QuerySupplyFinanceOrderResponseDTO financeOrderResponseTemp : agentFinanceOrderResponseDTOList){
            if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CHECKOUT.key == financeOrderResponseTemp.getFinanceStatus() ){

                if (financeOrderResponseMap.containsKey(financeOrderResponseTemp.getCurrency())){
                    financeOrderResponseMap.get(financeOrderResponseTemp.getCurrency()).add(financeOrderResponseTemp);
                } else{
                    List<QuerySupplyFinanceOrderResponseDTO> financeOrderResponseDTOTempList = new ArrayList<>();
                    financeOrderResponseDTOTempList.add(financeOrderResponseTemp);
                    financeOrderResponseMap.put(financeOrderResponseTemp.getCurrency(),financeOrderResponseDTOTempList);
                }
            }
        }
        return financeOrderResponseMap;
    }

    private Map<String, List<QuerySupplyFinanceOrderResponseDTO>> getSupplyNotCanceledFinanceOrderMap(List<QuerySupplyFinanceOrderResponseDTO> agentFinanceOrderResponseDTOList) {
        Map<String,List<QuerySupplyFinanceOrderResponseDTO>> financeOrderResponseMap = new HashMap();
        for (QuerySupplyFinanceOrderResponseDTO financeOrderResponseTemp : agentFinanceOrderResponseDTOList){
            if (FinanceTypeEnum.SUPPLIERBILLSATTLE.key == financeOrderResponseTemp.getFinanceType().intValue()
                    && FinanceStatusEnum.CANCELED.key != financeOrderResponseTemp.getFinanceStatus() ){

                if (financeOrderResponseMap.containsKey(financeOrderResponseTemp.getCurrency())){
                    financeOrderResponseMap.get(financeOrderResponseTemp.getCurrency()).add(financeOrderResponseTemp);
                } else{
                    List<QuerySupplyFinanceOrderResponseDTO> financeOrderResponseDTOTempList = new ArrayList<>();
                    financeOrderResponseDTOTempList.add(financeOrderResponseTemp);
                    financeOrderResponseMap.put(financeOrderResponseTemp.getCurrency(),financeOrderResponseDTOTempList);
                }
            }
        }
        return financeOrderResponseMap;
    }

}
