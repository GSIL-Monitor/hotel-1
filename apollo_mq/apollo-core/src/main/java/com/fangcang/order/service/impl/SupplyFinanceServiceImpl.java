package com.fangcang.order.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.finance.enums.FinanceStatusEnum;
import com.fangcang.finance.enums.FinanceTypeEnum;
import com.fangcang.finance.financeorder.request.QuerySupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QuerySupplyFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.finance.financeorder.service.SupplyFinanceOrderService;
import com.fangcang.order.domain.OrderFinanceDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.SupplyFinanceDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.mapper.OrderFinanceMapper;
import com.fangcang.order.mapper.SupplyFinanceMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.request.SupplyFinanceCallbackRequestDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.order.service.SupplyFinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/26
 */
@Service
@Slf4j
public class SupplyFinanceServiceImpl implements SupplyFinanceService {

    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private SupplyFinanceMapper supplyFinanceMapper;
    @Resource
    private OrderFinanceMapper orderFinanceMapper;

    @Resource
    private OrderCommonService orderCommonService;
    @Resource
    private SupplyFinanceOrderService supplyFinanceOrderService;


    @Override
    public ResponseDTO<Integer> notifyPayableAmount(SupplyFinanceOrderRequestDTO requestDTO) {
        log.info("notifyPayableAmount param: {}", requestDTO);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. 校验
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setSupplyOrderCode(requestDTO.getOrderCode());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单不存在");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOS.get(0);
        // 2. 通知财务收款
        requestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
        requestDTO.setOrgCode(supplyOrderDO.getSupplyCode());
        requestDTO.setOrgName(supplyOrderDO.getSupplyName());
        requestDTO.setFinanceType(FinanceTypeEnum.SUPPLIERORDERPAY.key);
        responseDTO = supplyFinanceOrderService.notifyPayableAmount(requestDTO);
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        StringBuilder content = new StringBuilder();
        content.append("供货单（" + supplyOrderDO.getSupplyOrderCode() + "）通知财务收款，通知金额：" + (requestDTO.getNotifyAmount()) + "，通知结果：" + (responseDTO.getResult().equals(Constant.YES) ? "成功" : "失败"));
        if (responseDTO.getResult().equals(Constant.NO)) {
            content.append("失败原因：" + responseDTO.getFailReason());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getCreateTime());
        orderCommonService.addOperationLog(logDO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<Integer> notifyReceiptAmount(SupplyFinanceOrderRequestDTO requestDTO) {
        log.info("notifyReceiptAmount param: {}", requestDTO);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. 校验
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setSupplyOrderCode(requestDTO.getOrderCode());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单不存在");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOS.get(0);
        // 2. 通知财务收款
        requestDTO.setOrgCode(supplyOrderDO.getSupplyCode());
        requestDTO.setOrgName(supplyOrderDO.getSupplyName());
        requestDTO.setFinanceType(FinanceTypeEnum.SUPPLIERORDERFUND.key);
        responseDTO = supplyFinanceOrderService.notifyRefundAmount(requestDTO);
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        StringBuilder content = new StringBuilder();
        content.append("供货单通知财务收款，通知金额：" + (requestDTO.getNotifyAmount()) + "，通知结果：" + (responseDTO.getResult().equals(Constant.YES) ? "成功" : "失败"));
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
    public ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(QuerySupplyFinanceOrderRequestDTO requestDTO) {
        return supplyFinanceOrderService.queryTradeListForOrder(requestDTO.getOrderCode());
    }

    @Override
    public ResponseDTO<BigDecimal> maxNotifyPayableAmount(QuerySupplyFinanceOrderRequestDTO requestDTO) {
        log.info("maxNotifyPayableAmount param: {}", requestDTO);
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        // 1. 校验
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setSupplyOrderCode(requestDTO.getOrderCode());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单不存在");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOS.get(0);
        // 2. 最多能通知的付款金额=供货单金额-已通知付款金额+已通知收款金额
        BigDecimal maxNotifyAmount = BigDecimal.ZERO;
        maxNotifyAmount = maxNotifyAmount.add(supplyOrderDO.getPayableAmount());
        List<Integer> financeStatusList = new ArrayList<>();
        financeStatusList.add(FinanceStatusEnum.NEW.key);
        financeStatusList.add(FinanceStatusEnum.CHECKOUT.key);
        requestDTO.setFinanceStatusList(financeStatusList);
        ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> listResponseDTO = supplyFinanceOrderService.queryPayItemList(requestDTO);
        if (!CollectionUtils.isEmpty(listResponseDTO.getModel())) {
            for (QuerySupplyFinanceOrderResponseDTO financeOrder : listResponseDTO.getModel()) {
                if (financeOrder.getFinanceType() == 3) {
                    maxNotifyAmount = maxNotifyAmount.subtract(financeOrder.getNotifyAmount());
                } else if (financeOrder.getFinanceType() == 4) {
                    maxNotifyAmount = maxNotifyAmount.add(financeOrder.getNotifyAmount());
                }
            }
        }
        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(maxNotifyAmount);
        return responseDTO;
    }

    @Override
    public ResponseDTO<BigDecimal> maxNotifyReceiptAmount(QuerySupplyFinanceOrderRequestDTO requestDTO) {
        log.info("maxNotifyReceiptAmount param: {}", requestDTO);
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        // 1. 校验
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setSupplyOrderCode(requestDTO.getOrderCode());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单不存在");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOS.get(0);
        // 2. 最多能通知的收款金额=订单已结算金额-已通知收款金额
        SupplyFinanceDO supplyFinanceQuery = new SupplyFinanceDO();
        supplyFinanceQuery.setSupplyOrderId(supplyOrderDO.getId());
        SupplyFinanceDO supplyFinanceDO = supplyFinanceMapper.select(supplyFinanceQuery).get(0);
        BigDecimal maxNotifyAmount = BigDecimal.ZERO;
        maxNotifyAmount = maxNotifyAmount.add(supplyFinanceDO.getSettlementAmount());
        requestDTO.setFinanceType(4);
        List<Integer> financeStatusList = new ArrayList<>();
        financeStatusList.add(FinanceStatusEnum.NEW.key);
        financeStatusList.add(FinanceStatusEnum.CHECKOUT.key);
        requestDTO.setFinanceStatusList(financeStatusList);
        ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> listResponseDTO = supplyFinanceOrderService.queryPayItemList(requestDTO);
        if (!CollectionUtils.isEmpty(listResponseDTO.getModel())) {
            for (QuerySupplyFinanceOrderResponseDTO financeOrder : listResponseDTO.getModel()) {
                maxNotifyAmount = maxNotifyAmount.subtract(financeOrder.getNotifyAmount());
            }
        }
        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(maxNotifyAmount);
        return responseDTO;
    }

    @Override
    public ResponseDTO supplyFinanceCallback(SupplyFinanceCallbackRequestDTO requestDTO) {
        log.info("supplyFinanceCallback param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO<>();
        // 1. 校验
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setSupplyOrderCode(requestDTO.getSupplyOrderCode());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单不存在");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOS.get(0);
        // 2. 更新供货单支付状态、结算状态、结算金额
        orderCommonService.updateSupplyFinanceInfo(supplyOrderDO.getId(), supplyOrderDO.getPayableAmount(), requestDTO.getSettlementAmount(), requestDTO.getReceiptAmount());
        // 3. 订单加财务锁
        OrderFinanceDO orderFinanceUpdate = new OrderFinanceDO();
        orderFinanceUpdate.setFinanceLockStatus(Byte.valueOf("1"));
        Example example = new Example(OrderFinanceDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", supplyOrderDO.getOrderId());
        orderFinanceMapper.updateByExampleSelective(orderFinanceUpdate, example);
        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        StringBuilder content = new StringBuilder();
        content.append("供货单（" + supplyOrderDO.getSupplyOrderCode() + "）财务回传结果， 已结算金额：" + requestDTO.getSettlementAmount());
        if (requestDTO.getReceiptAmount() != null) {
            content.append("，已收款金额：" + requestDTO.getReceiptAmount());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }
}
