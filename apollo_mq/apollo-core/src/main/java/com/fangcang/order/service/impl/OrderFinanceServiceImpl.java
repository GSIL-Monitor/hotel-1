package com.fangcang.order.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.finance.enums.FinanceStatusEnum;
import com.fangcang.finance.enums.FinanceTypeEnum;
import com.fangcang.finance.financeorder.request.AgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.QueryAgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QueryAgentFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.finance.financeorder.service.AgentFinanceOrderService;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderFinanceDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.mapper.OrderFinanceMapper;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.request.OrderFinanceCallbackRequestDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.order.service.OrderFinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/20
 */
@Service
@Slf4j
public class OrderFinanceServiceImpl implements OrderFinanceService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderFinanceMapper orderFinanceMapper;

    @Resource
    private AgentFinanceOrderService financeOrderService;
    @Resource
    private OrderCommonService orderCommonService;

    @Override
    public ResponseDTO<Integer> notifyReceivableAmount(AgentFinanceOrderRequestDTO requestDTO) {
        log.info("notifyReceivableAmount param: {}", requestDTO);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. 校验
        OrderDO orderQuery = new OrderDO();
        orderQuery.setOrderCode(requestDTO.getOrderCode());
        List<OrderDO> orderDOS = orderMapper.select(orderQuery);
        if (CollectionUtils.isEmpty(orderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        OrderDO orderDO = orderDOS.get(0);
        // 2. 通知财务收款
        requestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        requestDTO.setOrgCode(orderDO.getAgentCode());
        requestDTO.setOrgName(orderDO.getAgentName());
        requestDTO.setFinanceType(FinanceTypeEnum.ORDERPAY.key);
        responseDTO = financeOrderService.notifyReceivableAmount(requestDTO);
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        StringBuilder content = new StringBuilder();
        content.append("订单通知财务收款，通知金额：" + (requestDTO.getNotifyAmount()) + "，通知结果：" + (responseDTO.getResult().equals(Constant.YES) ? "成功" : "失败"));
        if (responseDTO.getResult().equals(Constant.NO)) {
            content.append("，失败原因：" + responseDTO.getFailReason());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getCreateTime());
        orderCommonService.addOperationLog(logDO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Integer> notifyRefundAmount(AgentFinanceOrderRequestDTO requestDTO) {
        log.info("notifyRefundAmount param: {}", requestDTO);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. 校验
        OrderDO orderQuery = new OrderDO();
        orderQuery.setOrderCode(requestDTO.getOrderCode());
        List<OrderDO> orderDOS = orderMapper.select(orderQuery);
        if (CollectionUtils.isEmpty(orderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        OrderDO orderDO = orderDOS.get(0);
        // 2. 通知财务收款
        requestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        requestDTO.setOrgCode(orderDO.getAgentCode());
        requestDTO.setOrgName(orderDO.getAgentName());
        requestDTO.setFinanceType(FinanceTypeEnum.ORDERREFUND.key);
        responseDTO = financeOrderService.notifyRefundAmount(requestDTO);
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        StringBuilder content = new StringBuilder();
        content.append("订单通知财务退款，通知金额：" + (requestDTO.getNotifyAmount()) + "，通知结果：" + (responseDTO.getResult().equals(Constant.YES) ? "成功" : "失败"));
        if (responseDTO.getResult().equals(Constant.NO)) {
            content.append("，失败原因：" + responseDTO.getFailReason());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getCreateTime());
        orderCommonService.addOperationLog(logDO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(QueryAgentFinanceOrderRequestDTO requestDTO) {
        return financeOrderService.queryTradeListForOrder(requestDTO.getOrderCode());
    }

    @Override
    public ResponseDTO<BigDecimal> maxNotifyReceivableAmount(QueryAgentFinanceOrderRequestDTO requestDTO) {
        log.info("maxNotifyReceivableAmount param: {}", requestDTO);
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        // 1. 校验
        OrderDO orderQuery = new OrderDO();
        orderQuery.setOrderCode(requestDTO.getOrderCode());
        List<OrderDO> orderDOS = orderMapper.select(orderQuery);
        if (CollectionUtils.isEmpty(orderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        OrderDO orderDO = orderDOS.get(0);
        // 2. 最多能通知收款金额 = 订单应收-已通知收款金额+已通知退款金额
        BigDecimal maxNotifyAmount = BigDecimal.ZERO;
        maxNotifyAmount = maxNotifyAmount.add(orderDO.getReceivableAmount());
        List<Integer> financeStatusList = new ArrayList<>();
        financeStatusList.add(FinanceStatusEnum.NEW.key);
        financeStatusList.add(FinanceStatusEnum.CHECKOUT.key);
        requestDTO.setFinanceStatusList(financeStatusList);
        ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseDTO = financeOrderService.queryPayItemList(requestDTO);
        if (!CollectionUtils.isEmpty(financeOrderResponseDTO.getModel())) {
            for (QueryAgentFinanceOrderResponseDTO financeOrder : financeOrderResponseDTO.getModel()) {
                if (financeOrder.getFinanceType() == 1) {
                    maxNotifyAmount = maxNotifyAmount.subtract(financeOrder.getNotifyAmount());
                } else if (financeOrder.getFinanceType() == 2) {
                    maxNotifyAmount = maxNotifyAmount.add(financeOrder.getNotifyAmount());
                }
            }
        }
        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(maxNotifyAmount);
        return responseDTO;
    }

    @Override
    public ResponseDTO<BigDecimal> maxNotifyRefundAmount(QueryAgentFinanceOrderRequestDTO requestDTO) {
        log.info("maxNotifyRefundAmount param: {}", requestDTO);
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        // 1. 校验
        OrderDO orderQuery = new OrderDO();
        orderQuery.setOrderCode(requestDTO.getOrderCode());
        List<OrderDO> orderDOS = orderMapper.select(orderQuery);
        if (CollectionUtils.isEmpty(orderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        // 2. 最多能通知退款金额 = 订单结算金额-已通知退款金额
        OrderFinanceDO orderFinanceQuery = new OrderFinanceDO();
        orderFinanceQuery.setOrderCode(requestDTO.getOrderCode());
        OrderFinanceDO orderFinanceDO = orderFinanceMapper.select(orderFinanceQuery).get(0);
        BigDecimal maxNotifyAmount = BigDecimal.ZERO;
        maxNotifyAmount = maxNotifyAmount.add(orderFinanceDO.getSettlementAmount());
        requestDTO.setFinanceType(2);
        List<Integer> financeStatusList = new ArrayList<>();
        financeStatusList.add(FinanceStatusEnum.NEW.key);
        financeStatusList.add(FinanceStatusEnum.CHECKOUT.key);
        requestDTO.setFinanceStatusList(financeStatusList);
        ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> financeOrderResponseDTO = financeOrderService.queryPayItemList(requestDTO);
        if (!CollectionUtils.isEmpty(financeOrderResponseDTO.getModel())) {
            for (QueryAgentFinanceOrderResponseDTO financeOrder : financeOrderResponseDTO.getModel()) {
                maxNotifyAmount = maxNotifyAmount.subtract(financeOrder.getNotifyAmount());
            }
        }
        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(maxNotifyAmount);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO orderFinanceCallback(OrderFinanceCallbackRequestDTO requestDTO) {
        log.info("orderFinanceCallback param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO<>();
        // 1. 校验
        OrderDO orderQuery = new OrderDO();
        orderQuery.setOrderCode(requestDTO.getOrderCode());
        List<OrderDO> orderDOS = orderMapper.select(orderQuery);
        if (CollectionUtils.isEmpty(orderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        OrderDO orderDO = orderDOS.get(0);
        // 2. 更新订单支付状态、结算状态、结算金额
        orderCommonService.updateOrderFinanceInfo(orderDO.getId(), orderDO.getReceivableAmount(), requestDTO.getSettlementAmount(), requestDTO.getRefundAmount());
        // 3. 订单加财务锁
        OrderFinanceDO orderFinanceUpdate = new OrderFinanceDO();
        orderFinanceUpdate.setFinanceLockStatus(Byte.valueOf("1"));
        Example example = new Example(OrderFinanceDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderDO.getId());
        orderFinanceMapper.updateByExampleSelective(orderFinanceUpdate, example);
        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        StringBuilder content = new StringBuilder();
        content.append("订单财务回传结果， 已结算金额：" + requestDTO.getSettlementAmount());
        if (requestDTO.getRefundAmount() != null) {
            content.append("，已退款金额：" + requestDTO.getRefundAmount());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }


}
