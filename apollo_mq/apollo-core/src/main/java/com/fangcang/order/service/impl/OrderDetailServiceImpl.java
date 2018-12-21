package com.fangcang.order.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.order.SupplyStatusEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.order.domain.DeratePolicyDO;
import com.fangcang.order.domain.OrderAttachDO;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderFinanceDO;
import com.fangcang.order.domain.OrderNoteDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.SupplyAdditionChargeDO;
import com.fangcang.order.domain.SupplyFinanceDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.domain.SupplyProductDO;
import com.fangcang.order.domain.SupplyProductPriceDO;
import com.fangcang.order.dto.OrderAttachDTO;
import com.fangcang.order.dto.OrderFinanceDTO;
import com.fangcang.order.dto.OrderNoteDTO;
import com.fangcang.order.dto.OrderOperationLogDTO;
import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.dto.SupplyFinanceDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.mapper.DeratePolicyMapper;
import com.fangcang.order.mapper.OrderAttachMapper;
import com.fangcang.order.mapper.OrderFinanceMapper;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.mapper.OrderNoteMapper;
import com.fangcang.order.mapper.OrderOperationLogMapper;
import com.fangcang.order.mapper.SupplyAdditionChargeMapper;
import com.fangcang.order.mapper.SupplyFinanceMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.mapper.SupplyProductMapper;
import com.fangcang.order.mapper.SupplyProductPriceMapper;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.UnlockRequestDTO;
import com.fangcang.order.response.DeratePolicyResponseDTO;
import com.fangcang.order.response.OrderAttachGroupResponseDTO;
import com.fangcang.order.response.OrderCustomerDTO;
import com.fangcang.order.response.OrderDetailResponseDTO;
import com.fangcang.order.response.OrderNoteGroupResponseDTO;
import com.fangcang.order.response.OrderSupplierDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.response.SupplyProductResponseDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.order.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单详情查询服务
 *
 * @author : zhanwang
 * @date : 2018/5/24
 */
@Service
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private SupplyProductMapper supplyProductMapper;
    @Resource
    private SupplyProductPriceMapper productPriceMapper;
    @Resource
    private SupplyAdditionChargeMapper additionChargeMapper;
    @Resource
    private OrderAttachMapper orderAttachMapper;
    @Resource
    private OrderNoteMapper orderNoteMapper;
    @Resource
    private OrderOperationLogMapper operationLogMapper;
    @Resource
    private DeratePolicyMapper deratePolicyMapper;
    @Resource
    private OrderFinanceMapper orderFinanceMapper;
    @Resource
    private SupplyFinanceMapper supplyFinanceMapper;


    @Resource
    private OrderCommonService orderCommonService;

    @Override
    public ResponseDTO<OrderDetailResponseDTO> orderInfo(OrderDetailRequestDTO requestDTO) {
        // 1. 查订单
        ResponseDTO<OrderDetailResponseDTO> responseDTO = new ResponseDTO<>();
        if (requestDTO.getOrderId() == null && StringUtils.isEmpty(requestDTO.getOrderCode())) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("请求参数不正确");
            return responseDTO;
        }
        OrderDO orderDO = null;
        if (requestDTO.getOrderId() != null) {
            orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        } else {
            OrderDO orderQuery = new OrderDO();
            orderQuery.setOrderCode(requestDTO.getOrderCode());
            List<OrderDO> orderDOS = orderMapper.select(orderQuery);
            if (!CollectionUtils.isEmpty(orderDOS)) {
                orderDO = orderDOS.get(0);
                requestDTO.setOrderId(orderDO.getId());
            }
        }
        if (orderDO == null) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        if (StringUtils.isNotEmpty(requestDTO.getMerchantCode()) && !StringUtils.equals(orderDO.getMerchantCode(), requestDTO.getMerchantCode())) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("非法操作，不能查看其它商家订单");
            return responseDTO;
        }
        OrderDetailResponseDTO orderDetailResponse = PropertyCopyUtil.transfer(orderDO, OrderDetailResponseDTO.class);
        orderDetailResponse.setOrderId(orderDO.getId());
        orderDetailResponse.setCheckinDate(DateUtil.dateToString(orderDO.getCheckinDate()));
        orderDetailResponse.setCheckoutDate(DateUtil.dateToString(orderDO.getCheckoutDate()));
        orderDetailResponse.setCreateTime(DateUtil.dateToStringWithHms(orderDO.getCreateTime()));

        // 2. 查询所有非不确认供货单下单的减免政策金额
        Example example = new Example(SupplyOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", requestDTO.getOrderId());
        criteria.andNotEqualTo("supplyStatus", SupplyStatusEnum.UN_CONFIRM.key);
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(supplyOrderDOList)) {
            BigDecimal derateAmount = BigDecimal.ZERO;
            for (SupplyOrderDO supplyOrderDO : supplyOrderDOList) {
                DeratePolicyDO deratePolicyQuery = new DeratePolicyDO();
                deratePolicyQuery.setSupplyOrderId(supplyOrderDO.getId());
                List<DeratePolicyDO> deratePolicyDOList = deratePolicyMapper.select(deratePolicyQuery);
                if (!CollectionUtils.isEmpty(deratePolicyDOList)) {
                    for (DeratePolicyDO deratePolicyDO : deratePolicyDOList) {
                        derateAmount = derateAmount.add(deratePolicyDO.getSalePriceSum());
                    }
                }
            }
            orderDetailResponse.setDerateAmount(derateAmount);
        }

        // 3. 订单是否锁定
        Integer isLock = 0;
        if (StringUtils.isNotEmpty(orderDO.getLockUser()) && !StringUtils.equals(orderDO.getLockUser(), requestDTO.getCreator())) {
            isLock = 1;
        }
        orderDetailResponse.setIsLock(isLock);

        // 4. 查订单财务状态
        OrderFinanceDO orderFinanceQuery = new OrderFinanceDO();
        orderFinanceQuery.setOrderId(requestDTO.getOrderId());
        List<OrderFinanceDO> orderFinanceDOS = orderFinanceMapper.select(orderFinanceQuery);
        if (!CollectionUtils.isEmpty(orderFinanceDOS)) {
            OrderFinanceDTO orderFinanceDTO = PropertyCopyUtil.transfer(orderFinanceDOS.get(0), OrderFinanceDTO.class);
            orderDetailResponse.setOrderFinanceDTO(orderFinanceDTO);
        }

        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(orderDetailResponse);
        return responseDTO;
    }

    /**
     * 查询订单的客户信息
     * @param requestDTO
     * @return
     */
    @Override
    public ResponseDTO<OrderCustomerDTO> queryOrderCustomer(OrderDetailRequestDTO requestDTO){
        // 1. 查订单
        ResponseDTO<OrderCustomerDTO> responseDTO = new ResponseDTO<>(ResultCodeEnum.SUCCESS.code);
        if (requestDTO.getOrderId() == null && StringUtils.isEmpty(requestDTO.getOrderCode())) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("请求参数不正确");
            return responseDTO;
        }
        OrderDO orderDO = null;
        if (requestDTO.getOrderId() != null) {
            orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        } else {
            OrderDO orderQuery = new OrderDO();
            orderQuery.setOrderCode(requestDTO.getOrderCode());
            List<OrderDO> orderDOS = orderMapper.select(orderQuery);
            if (!CollectionUtils.isEmpty(orderDOS)) {
                orderDO = orderDOS.get(0);
                requestDTO.setOrderId(orderDO.getId());
            }
        }
        if (orderDO == null) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        OrderCustomerDTO customerDTO=new OrderCustomerDTO();
        customerDTO.setAgentCode(orderDO.getAgentCode());
        customerDTO.setAgentName(orderDO.getAgentName());
        customerDTO.setCurrency(orderDO.getSaleCurrency());
        List<OrderSupplierDTO> orderSupplierDTOList=supplyOrderMapper.queryOrderSupplier(orderDO.getId());
        customerDTO.setSupplierDTOList(orderSupplierDTOList);
        responseDTO.setModel(customerDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<SupplyOrderResponseDTO>> productInfo(OrderDetailRequestDTO requestDTO) {
        // 0. 校验参数
        ResponseDTO validResult = validateParam(requestDTO);
        if (validResult.getResult().equals(ResultCodeEnum.FAILURE.code)) {
            return validResult;
        }
        // 1.查供货单明细
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setOrderId(requestDTO.getOrderId());
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.select(supplyOrderQuery);
        List<SupplyOrderResponseDTO> supplyOrderList = new ArrayList<>();
        for (SupplyOrderDO supplyOrderDO : supplyOrderDOList) {
            SupplyOrderResponseDTO supplyOrderResponseDTO = PropertyCopyUtil.transfer(supplyOrderDO, SupplyOrderResponseDTO.class);
            supplyOrderResponseDTO.setCheckinDate(DateUtil.dateToString(supplyOrderDO.getCheckinDate()));
            supplyOrderResponseDTO.setCheckoutDate(DateUtil.dateToString(supplyOrderDO.getCheckoutDate()));
            // 2. 供货单财务信息
            SupplyFinanceDO supplyFinanceQuery = new SupplyFinanceDO();
            supplyFinanceQuery.setSupplyOrderId(supplyOrderDO.getId());
            List<SupplyFinanceDO> supplyFinanceDOS = supplyFinanceMapper.select(supplyFinanceQuery);
            if (!CollectionUtils.isEmpty(supplyFinanceDOS)) {
                SupplyFinanceDTO supplyFinanceDTO = PropertyCopyUtil.transfer(supplyFinanceDOS.get(0), SupplyFinanceDTO.class);
                supplyOrderResponseDTO.setSupplyFinanceDTO(supplyFinanceDTO);
            }
            // 3. 查产品明细
            List<SupplyProductResponseDTO> supplyProductList = new ArrayList<>();
            SupplyProductDO supplyProductQuery = new SupplyProductDO();
            supplyProductQuery.setSupplyOrderId(supplyOrderDO.getId());
            List<SupplyProductDO> supplyProductDOList = supplyProductMapper.select(supplyProductQuery);
            for (SupplyProductDO supplyProductDO : supplyProductDOList) {
                SupplyProductResponseDTO supplyProductDTO = PropertyCopyUtil.transfer(supplyProductDO, SupplyProductResponseDTO.class);
                supplyProductDTO.setSupplyProductId(supplyProductDO.getId());
                supplyProductDTO.setCheckinDate(DateUtil.dateToString(supplyProductDO.getCheckinDate()));
                supplyProductDTO.setCheckoutDate(DateUtil.dateToString(supplyProductDO.getCheckoutDate()));
                // 4. 查产品价格明细
                SupplyProductPriceDO productPriceQuery = new SupplyProductPriceDO();
                productPriceQuery.setSupplyProductId(supplyProductDO.getId());
                List<SupplyProductPriceDO> productPriceDOList = productPriceMapper.select(productPriceQuery);
                List<SupplyProductPriceDTO> productPriceDTOList = new ArrayList<>();
                for (SupplyProductPriceDO productPriceDO : productPriceDOList) {
                    SupplyProductPriceDTO productPriceDTO = PropertyCopyUtil.transfer(productPriceDO, SupplyProductPriceDTO.class);
                    productPriceDTO.setSaleDate(DateUtil.dateToString(productPriceDO.getSaleDate()));
                    productPriceDTOList.add(productPriceDTO);
                }
                supplyProductDTO.setProductPriceDTOList(productPriceDTOList);
                supplyProductList.add(supplyProductDTO);
            }
            supplyOrderResponseDTO.setSupplyOrderId(supplyOrderDO.getId());
            supplyOrderResponseDTO.setSupplyProductList(supplyProductList);
            // 5.查附加项明细
            List<SupplyAdditionChargeDTO> supplyAdditionChargeList = new ArrayList<>();
            SupplyAdditionChargeDO additionChargeQuery = new SupplyAdditionChargeDO();
            additionChargeQuery.setSupplyOrderId(supplyOrderDO.getId());
            additionChargeQuery.setAdditionType(Byte.valueOf("1"));
            List<SupplyAdditionChargeDO> supplyAdditionChargeDOList = additionChargeMapper.select(additionChargeQuery);
            for (SupplyAdditionChargeDO supplyAdditionChargeDO : supplyAdditionChargeDOList) {
                SupplyAdditionChargeDTO supplyAdditionChargeDTO = PropertyCopyUtil.transfer(supplyAdditionChargeDO, SupplyAdditionChargeDTO.class);
                supplyAdditionChargeDTO.setSupplyAdditionChargeId(supplyAdditionChargeDO.getId());
                supplyAdditionChargeList.add(supplyAdditionChargeDTO);
            }
            supplyOrderResponseDTO.setSupplyAdditionChargeList(supplyAdditionChargeList);
            // 6. 查减免政策
            List<DeratePolicyResponseDTO> deratePolicyResponseDTOList = orderCommonService.queryDeratePolicyList(supplyOrderDO.getId());
            supplyOrderResponseDTO.setDerateList(deratePolicyResponseDTOList);

            supplyOrderList.add(supplyOrderResponseDTO);
        }

        ResponseDTO<List<SupplyOrderResponseDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(supplyOrderList);
        return responseDTO;
    }


    @Override
    public ResponseDTO<List<OrderAttachGroupResponseDTO>> attachInfo(OrderDetailRequestDTO requestDTO) {
        // 0. 校验参数
        ResponseDTO validResult = validateParam(requestDTO);
        if (validResult.getResult().equals(ResultCodeEnum.FAILURE.code)) {
            return validResult;
        }

        // 查附件
        OrderAttachDO orderAttachQuery = new OrderAttachDO();
        orderAttachQuery.setOrderId(requestDTO.getOrderId());
        List<OrderAttachDO> orderAttachDOList = orderAttachMapper.select(orderAttachQuery);
        Map<Byte, List<OrderAttachDTO>> attachMap = new LinkedHashMap<>();
        for (OrderAttachDO orderAttachDO : orderAttachDOList) {
            OrderAttachDTO orderAttachDTO = PropertyCopyUtil.transfer(orderAttachDO, OrderAttachDTO.class);
            orderAttachDTO.setOrderAttachId(orderAttachDO.getId());
            orderAttachDTO.setCreateTime(DateUtil.dateToStringWithHms(orderAttachDO.getCreateTime()));

            if (attachMap.containsKey(orderAttachDO.getType())) {
                attachMap.get(orderAttachDO.getType()).add(orderAttachDTO);
            } else {
                List<OrderAttachDTO> attachDTOList = new ArrayList<>();
                attachDTOList.add(orderAttachDTO);
                attachMap.put(orderAttachDO.getType(), attachDTOList);
            }
        }
        List<OrderAttachGroupResponseDTO> orderAttachGroups = new ArrayList<>();
        for (Map.Entry<Byte, List<OrderAttachDTO>> attachEntry : attachMap.entrySet()) {
            OrderAttachGroupResponseDTO attachGroup = new OrderAttachGroupResponseDTO();
            attachGroup.setType(attachEntry.getKey().intValue());
            attachGroup.setOrderAttachList(attachEntry.getValue());
            orderAttachGroups.add(attachGroup);
        }

        ResponseDTO<List<OrderAttachGroupResponseDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderAttachGroups);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderNoteGroupResponseDTO>> noteInfo(OrderDetailRequestDTO requestDTO) {
        // 0. 校验参数
        ResponseDTO validResult = validateParam(requestDTO);
        if (validResult.getResult().equals(ResultCodeEnum.FAILURE.code)) {
            return validResult;
        }

        OrderNoteDO orderNoteQuery = new OrderNoteDO();
        orderNoteQuery.setOrderId(requestDTO.getOrderId());
        List<OrderNoteDO> orderNoteDOList = orderNoteMapper.select(orderNoteQuery);
        Map<Byte, List<OrderNoteDTO>> noteMap = new LinkedHashMap<>();
        for (OrderNoteDO orderNoteDO : orderNoteDOList) {
            OrderNoteDTO orderNoteDTO = PropertyCopyUtil.transfer(orderNoteDO, OrderNoteDTO.class);
            orderNoteDTO.setOrderNoteId(orderNoteDO.getId());
            orderNoteDTO.setCreateTime(DateUtil.dateToStringWithHms(orderNoteDO.getCreateTime()));
            if (noteMap.containsKey(orderNoteDO.getNoteType())) {
                noteMap.get(orderNoteDO.getNoteType()).add(orderNoteDTO);
            } else {
                List<OrderNoteDTO> orderNoteDTOList = new ArrayList<>();
                orderNoteDTOList.add(orderNoteDTO);
                noteMap.put(orderNoteDO.getNoteType(), orderNoteDTOList);
            }
        }
        List<OrderNoteGroupResponseDTO> orderNoteGroups = new ArrayList<>();
        for (Map.Entry<Byte, List<OrderNoteDTO>> noteEntry : noteMap.entrySet()) {
            OrderNoteGroupResponseDTO noteGroup = new OrderNoteGroupResponseDTO();
            noteGroup.setNoteType(noteEntry.getKey().intValue());
            noteGroup.setOrderNoteList(noteEntry.getValue());
            orderNoteGroups.add(noteGroup);
        }

        ResponseDTO<List<OrderNoteGroupResponseDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderNoteGroups);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderOperationLogDTO>> logInfo(OrderDetailRequestDTO requestDTO) {
        // 1. 校验参数
        ResponseDTO validResult = validateParam(requestDTO);
        if (validResult.getResult().equals(ResultCodeEnum.FAILURE.code)) {
            return validResult;
        }
        // 2. 查询日志
        Example example = new Example(OrderOperationLogDO.class);
        example.setOrderByClause("operate_time asc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", requestDTO.getOrderId());
        List<OrderOperationLogDO> orderOperationLogDOList = operationLogMapper.selectByExample(example);
        // 3. 组装响应对象
        List<OrderOperationLogDTO> orderOperationLogList = new ArrayList<>();
        for (OrderOperationLogDO logDO : orderOperationLogDOList) {
            OrderOperationLogDTO logDTO = PropertyCopyUtil.transfer(logDO, OrderOperationLogDTO.class);
            logDTO.setOperateTime(DateUtil.dateToStringWithHms(logDO.getOperateTime()));
            orderOperationLogList.add(logDTO);
        }
        ResponseDTO<List<OrderOperationLogDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderOperationLogList);
        return responseDTO;
    }

    @Override
    public ResponseDTO unlock(UnlockRequestDTO unlockRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        OrderDO orderDO = new OrderDO();
        orderDO.setId(unlockRequestDTO.getOrderId());
        orderDO.setLockUser(unlockRequestDTO.getLockUser());
        orderDO.setLockName(unlockRequestDTO.getCreator());
        orderMapper.updateByPrimaryKeySelective(orderDO);
        return responseDTO;
    }

    /**
     * 校验参数
     *
     * @param requestDTO
     * @return
     */
    private ResponseDTO validateParam(OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (orderDO == null) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("订单不存在");
            return responseDTO;
        }
        if (!StringUtils.equals(orderDO.getMerchantCode(), requestDTO.getMerchantCode())) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("非法操作，不能查看其它商家订单");
            return responseDTO;
        }
        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
