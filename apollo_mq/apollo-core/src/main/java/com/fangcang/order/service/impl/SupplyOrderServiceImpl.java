package com.fangcang.order.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.SupplyWayEnum;
import com.fangcang.common.enums.order.NoteTypeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.PayStatusEnum;
import com.fangcang.common.enums.order.SupplySendTypeEnum;
import com.fangcang.common.enums.order.SupplyStatusEnum;
import com.fangcang.common.enums.order.SupplyTypeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.TemplateFileUtil;
import com.fangcang.ebk.request.BookRequestDTO;
import com.fangcang.ebk.request.BookRequestItemDTO;
import com.fangcang.ebk.request.BookRequestItemPriceDTO;
import com.fangcang.ebk.request.CancelRequestDTO;
import com.fangcang.ebk.service.EbkRequestService;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.merchant.dto.MerchantDTO;
import com.fangcang.merchant.mapper.MerchantMapper;
import com.fangcang.merchant.request.QueryMerchantCompanyDTO;
import com.fangcang.merchant.request.QueryMerchantDTO;
import com.fangcang.merchant.response.MerchantCompanyDTO;
import com.fangcang.merchant.service.MerchantCompanyService;
import com.fangcang.message.remote.EmailRemote;
import com.fangcang.message.remote.WxTemplateMsgRemote;
import com.fangcang.message.remote.request.email.EmailSendRequestDTO;
import com.fangcang.message.remote.request.weixin.templatemsg.OrderProcessTemplateMsgRequestDTO;
import com.fangcang.order.domain.DeratePolicyDO;
import com.fangcang.order.domain.DeratePolicyPriceDO;
import com.fangcang.order.domain.OrderAttachDO;
import com.fangcang.order.domain.OrderCheckDetailDO;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderNoteDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.SupplyAdditionChargeDO;
import com.fangcang.order.domain.SupplyFinanceDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.domain.SupplyProductDO;
import com.fangcang.order.domain.SupplyProductPriceDO;
import com.fangcang.order.domain.SupplyRequestDO;
import com.fangcang.order.dto.DeratePolicyPriceDTO;
import com.fangcang.order.dto.OrderCheckDetailDTO;
import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.dto.SupplyFinanceDTO;
import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyProductDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.dto.SupplyRequestDTO;
import com.fangcang.order.mapper.DeratePolicyMapper;
import com.fangcang.order.mapper.DeratePolicyPriceMapper;
import com.fangcang.order.mapper.OrderAttachMapper;
import com.fangcang.order.mapper.OrderCheckDetailMapper;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.mapper.OrderNoteMapper;
import com.fangcang.order.mapper.SupplyAdditionChargeMapper;
import com.fangcang.order.mapper.SupplyFinanceMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.mapper.SupplyProductMapper;
import com.fangcang.order.mapper.SupplyProductPriceMapper;
import com.fangcang.order.mapper.SupplyRequestMapper;
import com.fangcang.order.request.AddAdditionChargeRequestDTO;
import com.fangcang.order.request.AddDeratePolicyRequestDTO;
import com.fangcang.order.request.AddProductRequestDTO;
import com.fangcang.order.request.ChangeAdditionChargeRequestDTO;
import com.fangcang.order.request.ChangeDeratePolicyRequestDTO;
import com.fangcang.order.request.ChangeProductRequestDTO;
import com.fangcang.order.request.DeleteAdditionChargeRequestDTO;
import com.fangcang.order.request.DeleteDeratePolicyRequestDTO;
import com.fangcang.order.request.DeleteProductRequestDTO;
import com.fangcang.order.request.DeratePolicyRequestDTO;
import com.fangcang.order.request.NotifySupplierRequestDTO;
import com.fangcang.order.request.OrderCreditPayOrRefundRequestDTO;
import com.fangcang.order.request.OrderDebuctOrRetreatQuotaRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.ProductDetailRequestDTO;
import com.fangcang.order.request.QueryProductListRequestDTO;
import com.fangcang.order.request.QueryProductPriceRequestDTO;
import com.fangcang.order.request.SaveSupplyResultRequestDTO;
import com.fangcang.order.request.SendToSupplierRequestDTO;
import com.fangcang.order.request.SupplyCreditPayOrReceiptRequestDTO;
import com.fangcang.order.request.SupplyDetailRequestDTO;
import com.fangcang.order.request.UpdateSupplyOrderExceptionAmountDTO;
import com.fangcang.order.response.DeratePolicyResponseDTO;
import com.fangcang.order.response.OrderRedundancyInfoResponseDTO;
import com.fangcang.order.response.PriceDetailResponseDTO;
import com.fangcang.order.response.ProductDetailResponseDTO;
import com.fangcang.order.response.QueryProductListResponseDTO;
import com.fangcang.order.response.QuerySupplyReqResponseDTO;
import com.fangcang.order.response.SupplyDetailResponseDTO;
import com.fangcang.order.response.SupplyOrderPriceSumResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import com.fangcang.order.response.SupplyProductResponseDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.order.service.OrderDetailService;
import com.fangcang.order.service.SupplyOrderService;
import com.fangcang.product.dto.ProductDailyDTO;
import com.fangcang.product.dto.QuotaStateDTO;
import com.fangcang.product.request.DebuctQuotaQueryDTO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.request.ProductPageQueryDTO;
import com.fangcang.product.response.DebuctQuotaQueryResponseDTO;
import com.fangcang.product.response.PricePlanResponseDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.service.PriceInfoService;
import com.fangcang.product.service.PricePlanService;
import com.fangcang.product.service.QuotaStateService;
import com.fangcang.supplier.domain.SupplyUserBindDO;
import com.fangcang.supplier.request.QuerySupplyUserBindDTO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 供货单服务
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
@Service
@Slf4j
public class SupplyOrderServiceImpl implements SupplyOrderService {


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
    private OrderCheckDetailMapper checkDetailMapper;
    @Resource
    private SupplyRequestMapper supplyRequestMapper;
    @Resource
    private OrderNoteMapper orderNoteMapper;
    @Resource
    private OrderAttachMapper attachMapper;
    @Resource
    private DeratePolicyMapper deratePolicyMapper;
    @Resource
    private DeratePolicyPriceMapper deratePolicyPriceMapper;
    @Resource
    private SupplyFinanceMapper supplyFinanceMapper;


    @Resource
    private PriceInfoService priceInfoService;
    @Resource
    private PricePlanService pricePlanService;
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private OrderCommonService orderCommonService;
    @Resource
    private SupplyService supplyService;
    @Resource
    private HotelInfoService hotelInfoService;
    @Resource
    private QuotaStateService quotaStateService;
    @Resource
    private EbkRequestService ebkRequestService;

    @Autowired
    private EmailRemote emailRemote;

    @Autowired
    private MerchantCompanyService merchantCompanyService;

    @Autowired
    private WxTemplateMsgRemote wxTemplateMsgRemote;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public ResponseDTO updateSupplyOrderExceptionAmount(UpdateSupplyOrderExceptionAmountDTO request) {
        ResponseDTO responseDTO=new ResponseDTO();
        SupplyOrderDO supplyOrderParam=new SupplyOrderDO();
        supplyOrderParam.setId(request.getSupplyOrderId());
        supplyOrderParam.setExceptionAmount(request.getExceptionAmount());
        supplyOrderParam.setModifier(request.getOperator());
        supplyOrderParam.setModifyTime(new Date());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderParam);

        SupplyOrderDO supplyOrderDO=supplyOrderMapper.selectByPrimaryKey(request.getSupplyOrderId());

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("修改供货单（"+supplyOrderDO.getSupplyOrderCode()+"）其他应收："+request.getExceptionAmount());
        logDO.setOperator(request.getOperator());
        logDO.setOperateTime(new Date());
        logDO.setContent(content.toString());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO addProduct(AddProductRequestDTO requestDTO) {
        log.info("addProduct param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 组装数据
        SupplyOrderResponseDTO supplyOrderData = assemblySupplyOrderData(requestDTO);
        // 2. 如果供应商尚未创建供货单，插入供货单表
        // 添加产品，如果产品所属的供应商对应的供货单已存在并且是非不确认的， 可以直接加入， 否则创建一个新的供货单
        Example example = new Example(SupplyOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", requestDTO.getOrderId());
        criteria.andEqualTo("supplyCode", supplyOrderData.getSupplyCode());
        criteria.andNotEqualTo("supplyStatus", SupplyStatusEnum.UN_CONFIRM.key);
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.selectByExample(example);
        boolean isSupplyOrderAlready;
        SupplyOrderDO supplyOrderDO = null;
        int supplyOrderId;
        if (CollectionUtils.isEmpty(supplyOrderDOList)) {
            isSupplyOrderAlready = false;
            SupplyOrderDO supplyOrderInsert = PropertyCopyUtil.transfer(supplyOrderData, SupplyOrderDO.class);
            supplyOrderMapper.insertSelective(supplyOrderInsert);
            supplyOrderId = supplyOrderInsert.getId();
        } else {
            isSupplyOrderAlready = true;
            supplyOrderDO = supplyOrderDOList.get(0);
            supplyOrderId = supplyOrderDO.getId();
        }
        // 3. 如果供货单不存在，插入供货单财务信息
        if (!isSupplyOrderAlready) {
            SupplyOrderDO supplyOrderNew = supplyOrderMapper.selectByPrimaryKey(supplyOrderId);
            supplyOrderDO = supplyOrderNew;
            SupplyFinanceDO supplyFinanceDO = PropertyCopyUtil.transfer(supplyOrderData.getSupplyFinanceDTO(), SupplyFinanceDO.class);
            supplyFinanceDO.setSupplyOrderId(supplyOrderId);
            supplyFinanceDO.setSupplyOrderCode(supplyOrderNew.getSupplyOrderCode());
            supplyFinanceMapper.insert(supplyFinanceDO);
        }
        // 4. 插入供货产品表
        SupplyProductResponseDTO productResponseDTO = supplyOrderData.getSupplyProductList().get(0);
        SupplyProductDO supplyProductDO = PropertyCopyUtil.transfer(productResponseDTO, SupplyProductDO.class);
        supplyProductDO.setSupplyOrderId(supplyOrderId);
        supplyProductMapper.insertSelective(supplyProductDO);
        int supplyProductId = supplyProductDO.getId();
        // 5. 插入供货价格表
        List<SupplyProductPriceDO> productPriceDOList = PropertyCopyUtil.transferArray(productResponseDTO.getProductPriceDTOList(), SupplyProductPriceDO.class);
        for (SupplyProductPriceDO productPriceDO : productPriceDOList) {
            productPriceDO.setSupplyProductId(supplyProductId);
        }
        productPriceMapper.insertList(productPriceDOList);
        // 6. 更新对账明细表
        List<OrderCheckDetailDO> checkDetailDOList = PropertyCopyUtil.transferArray(productResponseDTO.getCheckDetailDTOList(), OrderCheckDetailDO.class);
        for (OrderCheckDetailDO orderCheckDetailDO : checkDetailDOList) {
            orderCheckDetailDO.setOderId(requestDTO.getOrderId());
            orderCheckDetailDO.setSupplyOrderId(supplyOrderId);
            orderCheckDetailDO.setSupplyProductId(supplyProductId);
        }
        checkDetailMapper.insertList(checkDetailDOList);
        // 7. 如果供货单已存在，更新供货单表总金额
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (isSupplyOrderAlready) {
            SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
            supplyOrderUpdate.setId(supplyOrderId);
            supplyOrderDO.setSupplySum(supplyOrderDO.getSupplySum().add(supplyProductDO.getBasePriceSum()));
            supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum());
            supplyOrderUpdate.setPayableAmount(supplyOrderDO.getSupplySum());
            supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().add(supplyProductDO.getSalePriceSum()));
            supplyOrderUpdate.setModifier(requestDTO.getModifier());
            supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
            supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
            // 8. 供货单金额发生变化，处理供货单财务信息
            handleSupplyFinanceInfo(requestDTO.getModifier(), orderDO.getMerchantCode(), supplyOrderDO, supplyOrderUpdate);
        }
        // 9. 更新订单表总金额、房型名称、供货单编码、供货单状态
        orderDO.setOrderSum(orderDO.getOrderSum().add(supplyProductDO.getSalePriceSum()));
        SupplyOrderDO supplyOrderListQuery = new SupplyOrderDO();
        supplyOrderListQuery.setOrderId(requestDTO.getOrderId());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderListQuery);
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(requestDTO.getOrderId());
        orderUpdate.setOrderSum(orderDO.getOrderSum());
        orderUpdate.setReceivableAmount(orderDO.getOrderSum());
        orderUpdate.setProfit(orderCommonService.calcProfit(requestDTO.getOrderId(), orderUpdate.getReceivableAmount(), supplyOrderDOS));
        OrderRedundancyInfoResponseDTO orderRedundancyInfo = orderCommonService.getOrderRedundancyInfo(requestDTO.getOrderId(), supplyOrderDOS);
        orderUpdate.setRoomTypeNames(StringUtil.listToSQLString(orderRedundancyInfo.getRoomtypeNameSet()));
        orderUpdate.setSupplyOrderCodes(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyOrderCodeSet()));
        orderUpdate.setSupplyStatus(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyStatusSet()));
        orderUpdate.setSupplyCodes(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyCodeSet()));
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 10. 订单金额发生变化，处理订单财务信息
        handleOrderFinanceInfo(requestDTO.getModifier(), orderDO, orderUpdate);
        // 11. 如果是OTA订单需要扣配额
        boolean isOtaOrder = !ChannelTypeEnum.B2B.key.equals(orderDO.getChannelCode()) && orderDO.getIsGroupRoom().intValue() == 0 && orderDO.getIsManualOrder().intValue() == 0;
        if (isOtaOrder) {
            List<String> newDateList = new ArrayList<>();
            for (SupplyProductPriceDTO productPriceDTO : requestDTO.getSupplyProductPriceList()) {
                newDateList.add(productPriceDTO.getSaleDate());
            }
            // 扣配额
            OrderDebuctOrRetreatQuotaRequestDTO debuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
            debuctRequestDTO.setDebuctQuotaType(1);
            debuctRequestDTO.setOrderCode(orderDO.getOrderCode());
            debuctRequestDTO.setDateList(newDateList);
            debuctRequestDTO.setQuotaAccountId(productResponseDTO.getQuotaAccountId());
            debuctRequestDTO.setRatePlanId(requestDTO.getRatePlanId());
            debuctRequestDTO.setRoomNum(requestDTO.getRoomNum());
            debuctRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
            debuctRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
            debuctRequestDTO.setStartDate(DateUtil.stringToDate(requestDTO.getCheckinDate()));
            debuctRequestDTO.setEndDate(DateUtil.stringToDate(requestDTO.getCheckoutDate()));
            debuctRequestDTO.setMerchantCode(orderDO.getMerchantCode());
            debuctRequestDTO.setOrderId(orderDO.getId());
            orderCommonService.debuctOrRetreatQuota(debuctRequestDTO);
        }
        // 12. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent("添加客房（" + supplyProductDO.getRoomTypeName() + "|" + supplyProductDO.getRateplanName() + "）成功");
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }


    /**
     * 组装供货单数据
     *
     * @param requestDTO
     * @return
     */
    private SupplyOrderResponseDTO assemblySupplyOrderData(AddProductRequestDTO requestDTO) {
        // 1. 组装供货单
        SupplyOrderResponseDTO supplyOrderDTO = new SupplyOrderResponseDTO();
        supplyOrderDTO.setOrderId(requestDTO.getOrderId());
        supplyOrderDTO.setSupplyStatus(SupplyStatusEnum.UN_SEND.key);
        supplyOrderDTO.setPayStatus(PayStatusEnum.UN_PAID.key);
        supplyOrderDTO.setSupplyWay(SupplyWayEnum.SupplyWayEnum.key);
        supplyOrderDTO.setCreator(requestDTO.getCreator());
        supplyOrderDTO.setCreateTime(DateUtil.dateToString(requestDTO.getCreateTime()));
        // 2. 组装供货单财务
        SupplyFinanceDTO supplyFinanceDTO = new SupplyFinanceDTO();
        supplyFinanceDTO.setSettlementAmount(BigDecimal.ZERO);
        supplyFinanceDTO.setReceiptAmount(BigDecimal.ZERO);
        supplyFinanceDTO.setSettlementStatus(Byte.valueOf("0"));
        supplyFinanceDTO.setAccountStatus(Byte.valueOf("0"));
        supplyFinanceDTO.setCreator(requestDTO.getCreator());
        supplyFinanceDTO.setCreateTime(requestDTO.getCreateTime());
        supplyOrderDTO.setSupplyFinanceDTO(supplyFinanceDTO);
        // 3. 组装供货产品
        List<SupplyProductResponseDTO> supplyProductDTOList = new ArrayList<>();
        SupplyProductResponseDTO productDTO = PropertyCopyUtil.transfer(requestDTO, SupplyProductResponseDTO.class);
        // 查询产品信息
        PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
        pricePlanQueryDTO.setPricePlanId(requestDTO.getRatePlanId().longValue());
        ResponseDTO<PricePlanResponseDTO> pricePlanResponseDTO = pricePlanService.queryPricePlanInfo(pricePlanQueryDTO);
        PricePlanResponseDTO pricePlanResponse = pricePlanResponseDTO.getModel();
        productDTO.setRoomTypeId(pricePlanResponse.getRoomTypeId().intValue());
        productDTO.setRoomTypeName(pricePlanResponse.getRoomTypeName());
        productDTO.setRateplanName(pricePlanResponse.getPricePlanName());
        productDTO.setBedtype(pricePlanResponse.getBedType());
        productDTO.setBreakfastType(pricePlanResponse.getBreakFastType());
        productDTO.setQuotaType(pricePlanResponse.getQuotaType());
        productDTO.setQuotaAccountId(pricePlanResponse.getQuotaAccountId().intValue());
        productDTO.setCheckinDate(requestDTO.getCheckinDate());
        productDTO.setCheckoutDate(requestDTO.getCheckoutDate());
        productDTO.setRoomNum(requestDTO.getRoomNum());
        productDTO.setCreator(requestDTO.getCreator());
        productDTO.setCreateTime(requestDTO.getCreateTime());
        // 调用供应商模块，查询供应商信息
        SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyCode(pricePlanResponse.getSupplyCode());
        ResponseDTO<SingleSupplyInfoResponseDTO> supplyResponse = supplyService.getSupplyById(singleUserRequestDTO);
        SingleSupplyInfoResponseDTO supplyInfo = supplyResponse.getModel();
        supplyOrderDTO.setBaseCurrency(supplyInfo.getBaseCurrency());
        supplyOrderDTO.setSupplyCode(pricePlanResponse.getSupplyCode());
        supplyOrderDTO.setSupplyName(supplyInfo.getSupplyName());
        supplyOrderDTO.setBalanceMethod(supplyInfo.getBillingMethod());
        if (BalanceMethodEnum.SINGLE.key != supplyInfo.getBillingMethod()) {
            supplyOrderDTO.setPayStatus(PayStatusEnum.UN_CREDIT.key);
        }
        // 4. 组装供货产品价格
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        // 查产品底价
        Map<String, SupplyProductPriceResponseDTO> productPriceMap = orderCommonService.getProductDailyPriceMap(orderDO.getChannelCode(),
                requestDTO.getRatePlanId(), requestDTO.getCheckinDate(), requestDTO.getCheckoutDate(), requestDTO.getRoomNum(), orderDO.getIsGroupRoom().intValue());
        List<SupplyProductPriceDTO> dayList = requestDTO.getSupplyProductPriceList();
        BigDecimal basePriceSum = BigDecimal.ZERO;
        BigDecimal salePriceSum = BigDecimal.ZERO;
        for (SupplyProductPriceDTO productPriceDTO : dayList) {
            SupplyProductPriceResponseDTO productPriceResponseDTO = productPriceMap.get(productPriceDTO.getSaleDate());
            if (productPriceResponseDTO != null && productPriceResponseDTO.getBasePrice() != null) {
                basePriceSum = basePriceSum.add(productPriceResponseDTO.getBasePrice().multiply(new BigDecimal(requestDTO.getRoomNum())));
            }
            salePriceSum = salePriceSum.add(productPriceDTO.getSalePrice().multiply(new BigDecimal(requestDTO.getRoomNum())));
        }
        productDTO.setBasePriceSum(basePriceSum);
        productDTO.setSalePriceSum(salePriceSum);
        productDTO.setProductPriceDTOList(dayList);
        // 5. 组装对账明细：根据每日价格， 推演对账明细
        List<OrderCheckDetailDTO> checkDetailDTOList = orderCommonService.assemblyCheckDetail(requestDTO.getRoomNum(), dayList);
        productDTO.setCheckDetailDTOList(checkDetailDTOList);
        supplyProductDTOList.add(productDTO);
        // 6. 更新供货单冗余字段
        supplyOrderDTO.setSupplySum(basePriceSum);
        supplyOrderDTO.setPayableAmount(basePriceSum);
        supplyOrderDTO.setSalePriceSum(salePriceSum);
        supplyOrderDTO.setHotelId(pricePlanResponse.getHotelId().intValue());
        supplyOrderDTO.setHotelName(pricePlanResponse.getHotelName());
        supplyOrderDTO.setSupplyProductList(supplyProductDTOList);
        supplyOrderDTO.setRate(BigDecimal.ONE);
        // 冗余供货单下第一个产品信息
        SupplyProductResponseDTO firstProduct = supplyProductDTOList.get(0);
        supplyOrderDTO.setCheckinDate(firstProduct.getCheckinDate());
        supplyOrderDTO.setCheckoutDate(firstProduct.getCheckoutDate());
        supplyOrderDTO.setRoomNum(firstProduct.getRoomNum());
        supplyOrderDTO.setRoomTypeName(firstProduct.getRoomTypeName());
        supplyOrderDTO.setRateplanName(firstProduct.getRateplanName());
        return supplyOrderDTO;
    }

    @Override
    @Transactional
    public ResponseDTO changeProduct(ChangeProductRequestDTO requestDTO) {
        log.info("changeProduct param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 组装数据
        BigDecimal salePriceSum = BigDecimal.ZERO;
        BigDecimal basePriceSum = BigDecimal.ZERO;
        for (SupplyProductPriceDTO productPriceDTO : requestDTO.getSupplyProductPriceList()) {
            salePriceSum = salePriceSum.add(productPriceDTO.getSalePrice().multiply(new BigDecimal(requestDTO.getRoomNum())));
            basePriceSum = basePriceSum.add(productPriceDTO.getBasePrice().multiply(new BigDecimal(requestDTO.getRoomNum())));
        }
        // 2. 更新供货产品表
        SupplyProductDO oldSupplyProductDO = supplyProductMapper.selectByPrimaryKey(requestDTO.getSupplyProductId());
        SupplyProductPriceDO productPriceQuery = new SupplyProductPriceDO();
        productPriceQuery.setSupplyProductId(requestDTO.getSupplyProductId());
        List<SupplyProductPriceDO> oldProductPriceDOList = productPriceMapper.select(productPriceQuery);
        SupplyProductDO supplyProductUpdate = new SupplyProductDO();
        supplyProductUpdate.setId(requestDTO.getSupplyProductId());
        supplyProductUpdate.setCheckinDate(DateUtil.stringToDate(requestDTO.getCheckinDate()));
        supplyProductUpdate.setCheckoutDate(DateUtil.stringToDate(requestDTO.getCheckoutDate()));
        supplyProductUpdate.setRoomNum(requestDTO.getRoomNum());
        supplyProductUpdate.setBasePriceSum(basePriceSum);
        supplyProductUpdate.setSalePriceSum(salePriceSum);
        supplyProductUpdate.setModifier(requestDTO.getModifier());
        supplyProductUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyProductMapper.updateByPrimaryKeySelective(supplyProductUpdate);
        // 3. 更新供货产品价格表
        SupplyProductPriceDO productPriceDelete = new SupplyProductPriceDO();
        productPriceDelete.setSupplyProductId(requestDTO.getSupplyProductId());
        productPriceMapper.delete(productPriceDelete);
        List<SupplyProductPriceDO> supplyProductPriceDOList = PropertyCopyUtil.transferArray(requestDTO.getSupplyProductPriceList(), SupplyProductPriceDO.class);
        for (SupplyProductPriceDO productPriceDO : supplyProductPriceDOList) {
            productPriceDO.setId(null);
            productPriceDO.setSupplyProductId(requestDTO.getSupplyProductId());
        }
        productPriceMapper.insertList(supplyProductPriceDOList);
        // 4. 组装对账明细：根据每日价格， 推演对账明细<先删除旧明细>
        OrderCheckDetailDO checkDetailDelete = new OrderCheckDetailDO();
        checkDetailDelete.setSupplyProductId(requestDTO.getSupplyProductId());
        checkDetailMapper.delete(checkDetailDelete);
        List<OrderCheckDetailDTO> checkDetailDTOList = orderCommonService.assemblyCheckDetail(requestDTO.getRoomNum(), requestDTO.getSupplyProductPriceList());
        List<OrderCheckDetailDO> orderCheckDetailDOList = PropertyCopyUtil.transferArray(checkDetailDTOList, OrderCheckDetailDO.class);
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(oldSupplyProductDO.getSupplyOrderId());
        for (OrderCheckDetailDO checkDetailDO : orderCheckDetailDOList) {
            checkDetailDO.setOderId(supplyOrderDO.getOrderId());
            checkDetailDO.setSupplyOrderId(supplyOrderDO.getId());
            checkDetailDO.setSupplyProductId(requestDTO.getSupplyProductId());
        }
        checkDetailMapper.insertList(orderCheckDetailDOList);
        // 5. 更新供货单表总金额
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(oldSupplyProductDO.getSupplyOrderId());
        // 供货单总额 = 供货单原总额 - 修改产品前原总额 + 修改产品后总额
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().subtract(oldSupplyProductDO.getBasePriceSum()).add(basePriceSum));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().subtract(oldSupplyProductDO.getSalePriceSum()).add(salePriceSum));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 6. 供货单金额发生变化，处理供货单财务信息
        handleSupplyFinanceInfo(requestDTO.getCreator(), orderDO.getMerchantCode(), supplyOrderDO, supplyOrderUpdate);
        // 7. 更新订单表总金额
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(supplyOrderDO.getOrderId());
        // 订单总额 = 订单原总额 - 修改前供货单总额 + 修改后供货单总额
        orderUpdate.setOrderSum(orderDO.getOrderSum().subtract(supplyOrderDO.getSalePriceSum()).add(supplyOrderUpdate.getSalePriceSum()));
        orderUpdate.setReceivableAmount(orderUpdate.getOrderSum());
        orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderUpdate.getReceivableAmount(), null));
        // 更新订单表入离日期、间数为第一个供货单的第一个产品
        SupplyProductDO supplyProductQuery = new SupplyProductDO();
        supplyProductQuery.setSupplyOrderId(supplyOrderDO.getId());
        SupplyProductDO supplyProductDO = supplyProductMapper.select(supplyProductQuery).get(0);
        if (supplyProductDO.getId().equals(requestDTO.getSupplyProductId())) {
            orderUpdate.setCheckinDate(supplyProductDO.getCheckinDate());
            orderUpdate.setCheckoutDate(supplyProductDO.getCheckoutDate());
            orderUpdate.setRoomNum(supplyProductDO.getRoomNum());
        }
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 8. 订单金额发生变化，处理订单财务信息
        handleOrderFinanceInfo(requestDTO.getCreator(), orderDO, orderUpdate);
        // 9. 散房扣退配额<团房订单无需理配额>
        if (Constant.NO.equals(orderDO.getIsGroupRoom().intValue())) {
            List<String> newDateList = new ArrayList<>();
            BigDecimal newSalePriceSum = BigDecimal.ZERO;
            for (SupplyProductPriceDTO productPriceDTO : requestDTO.getSupplyProductPriceList()) {
                newDateList.add(productPriceDTO.getSaleDate());
                newSalePriceSum = newSalePriceSum.add(productPriceDTO.getSalePrice().multiply(new BigDecimal(requestDTO.getRoomNum())));
            }
            // 如果产品总价没变、入离日期、间数没变，无需重新扣配额
            if (oldSupplyProductDO.getSalePriceSum().compareTo(newSalePriceSum) == 0
                    && oldSupplyProductDO.getRoomNum().equals(requestDTO.getRoomNum())
                    && DateUtil.dateToString(oldSupplyProductDO.getCheckinDate()).equals(requestDTO.getCheckinDate())
                    && DateUtil.dateToString(oldSupplyProductDO.getCheckoutDate()).equals(requestDTO.getCheckoutDate())) {

            } else {
                // 退原来配额
                List<String> oldDateList = new ArrayList<>();
                for (SupplyProductPriceDO productPriceDO : oldProductPriceDOList) {
                    oldDateList.add(DateUtil.dateToString(productPriceDO.getSaleDate()));
                }
                OrderDebuctOrRetreatQuotaRequestDTO oldDebuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
                oldDebuctRequestDTO.setDebuctQuotaType(2);
                oldDebuctRequestDTO.setOrderCode(orderDO.getOrderCode());
                oldDebuctRequestDTO.setDateList(oldDateList);
                oldDebuctRequestDTO.setQuotaAccountId(oldSupplyProductDO.getQuotaAccountId());
                oldDebuctRequestDTO.setRatePlanId(oldSupplyProductDO.getRateplanId());
                oldDebuctRequestDTO.setRoomNum(oldSupplyProductDO.getRoomNum());
                oldDebuctRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
                oldDebuctRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
                oldDebuctRequestDTO.setStartDate(oldSupplyProductDO.getCheckinDate());
                oldDebuctRequestDTO.setEndDate(oldSupplyProductDO.getCheckoutDate());
                oldDebuctRequestDTO.setMerchantCode(orderDO.getMerchantCode());
                oldDebuctRequestDTO.setOrderId(orderDO.getId());
                Future<ResponseDTO> future = orderCommonService.debuctOrRetreatQuota(oldDebuctRequestDTO);
                // 阻塞线程，直到获取结果
                ResponseDTO asyncResponseDTO = null;
                try {
                    asyncResponseDTO = future.get();
                } catch (InterruptedException e) {
                    log.error("获取扣配额结果异常", e);
                } catch (ExecutionException e) {
                    log.error("获取扣配额结果异常", e);
                }
                if (asyncResponseDTO.getResult().equals(ResultCodeEnum.SUCCESS.code)) {
                    // 扣配额
                    OrderDebuctOrRetreatQuotaRequestDTO debuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
                    debuctRequestDTO.setDebuctQuotaType(1);
                    debuctRequestDTO.setOrderCode(orderDO.getOrderCode());
                    debuctRequestDTO.setDateList(newDateList);
                    debuctRequestDTO.setQuotaAccountId(oldSupplyProductDO.getQuotaAccountId());
                    debuctRequestDTO.setRatePlanId(oldSupplyProductDO.getRateplanId());
                    debuctRequestDTO.setRoomNum(requestDTO.getRoomNum());
                    debuctRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
                    debuctRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
                    debuctRequestDTO.setStartDate(DateUtil.stringToDate(requestDTO.getCheckinDate()));
                    debuctRequestDTO.setEndDate(DateUtil.stringToDate(requestDTO.getCheckoutDate()));
                    debuctRequestDTO.setMerchantCode(orderDO.getMerchantCode());
                    debuctRequestDTO.setOrderId(orderDO.getId());
                    orderCommonService.debuctOrRetreatQuota(debuctRequestDTO);
                }
            }
        }
        // 10. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent("产品（" + oldSupplyProductDO.getRateplanName() + "），修改了入离日期：" + requestDTO.getCheckinDate() + "~"
                + requestDTO.getCheckoutDate() + " 原值：" + DateUtil.dateToString(oldSupplyProductDO.getCheckinDate()) + "~"
                + DateUtil.dateToString(oldSupplyProductDO.getCheckoutDate()) + "。修改了间数：" + requestDTO.getRoomNum() + "，原值" + oldSupplyProductDO.getRoomNum());
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO deleteProduct(DeleteProductRequestDTO requestDTO) {
        log.info("deleteProduct param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 检查订单产品数量， 数量小于3不能删除
        SupplyProductDO supplyProductDO = supplyProductMapper.selectByPrimaryKey(requestDTO.getSupplyProductId());
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(supplyProductDO.getSupplyOrderId());
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        SupplyProductPriceDO productPriceQuery = new SupplyProductPriceDO();
        productPriceQuery.setSupplyProductId(requestDTO.getSupplyProductId());
        List<SupplyProductPriceDO> oldProductPriceDOList = productPriceMapper.select(productPriceQuery);
        int productNum = 0;
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setOrderId(orderDO.getId());
        List<SupplyOrderDO> oldSupplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        for (SupplyOrderDO oldSupplyOrderDO : oldSupplyOrderDOS) {
            SupplyProductDO supplyProductQuery = new SupplyProductDO();
            supplyProductQuery.setSupplyOrderId(oldSupplyOrderDO.getId());
            List<SupplyProductDO> supplyProductDOS = supplyProductMapper.select(supplyProductQuery);
            productNum = productNum + supplyProductDOS.size();
        }
        // OTA散房订单不用校验数量
        boolean isOtaOrder = !ChannelTypeEnum.B2B.key.equals(orderDO.getChannelCode()) && orderDO.getIsGroupRoom().intValue() == 0 && orderDO.getIsManualOrder().intValue() == 0;
        if (productNum < 2 && !isOtaOrder) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("团房订单产品数量不能少于1");
            return responseDTO;
        }
        // 2. 删除供货价格表
        SupplyProductPriceDO productPriceDelete = new SupplyProductPriceDO();
        productPriceDelete.setSupplyProductId(requestDTO.getSupplyProductId());
        productPriceMapper.delete(productPriceDelete);
        // 3. 删除供货产品表
        SupplyProductDO supplyProductDelete = new SupplyProductDO();
        supplyProductDelete.setId(requestDTO.getSupplyProductId());
        supplyProductMapper.delete(supplyProductDelete);
        // 4. 如果供货单下面没产品， 删除供货单
        SupplyProductDO supplyProductQuery = new SupplyProductDO();
        supplyProductQuery.setSupplyOrderId(supplyOrderDO.getId());
        List<SupplyProductDO> supplyProductDOList = supplyProductMapper.select(supplyProductQuery);
        if (CollectionUtils.isEmpty(supplyProductDOList)) {
            supplyOrderMapper.deleteByPrimaryKey(supplyOrderDO.getId());
        } else {
            // 5. 如果供货单下面有产品， 更新供货单表总金额、冗余字段
            SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
            supplyOrderUpdate.setId(supplyOrderDO.getId());
            BigDecimal supplySum = supplyOrderDO.getPayableAmount().subtract(supplyProductDO.getBasePriceSum());
            supplyOrderUpdate.setSupplySum(supplySum);
            supplyOrderUpdate.setPayableAmount(supplySum);
            supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().subtract(supplyProductDO.getSalePriceSum()));
            supplyOrderUpdate.setModifier(requestDTO.getModifier());
            supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
            // 更新供货单冗余的产品信息
            SupplyProductDO firstSupplyProductQuery = new SupplyProductDO();
            firstSupplyProductQuery.setSupplyOrderId(supplyOrderDO.getId());
            SupplyProductDO supplyFirstProduct = supplyProductMapper.select(firstSupplyProductQuery).get(0);
            supplyOrderUpdate.setCheckinDate(supplyFirstProduct.getCheckinDate());
            supplyOrderUpdate.setCheckoutDate(supplyFirstProduct.getCheckoutDate());
            supplyOrderUpdate.setRoomNum(supplyFirstProduct.getRoomNum());
            supplyOrderUpdate.setRoomTypeName(supplyFirstProduct.getRoomTypeName());
            supplyOrderUpdate.setRateplanName(supplyFirstProduct.getRateplanName());
            supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
            // 6. 供货单金额发生变化，处理供货单财务信息
            handleSupplyFinanceInfo(requestDTO.getModifier(), orderDO.getMerchantCode(), supplyOrderDO, supplyOrderUpdate);
        }
        // 7. 更新订单表总金额、房型名称、供货单编码、供货单状态
        SupplyOrderDO supplyOrderListQuery = new SupplyOrderDO();
        supplyOrderListQuery.setOrderId(supplyOrderDO.getOrderId());
        List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderListQuery);
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(supplyOrderDO.getOrderId());
        BigDecimal orderSum = orderDO.getOrderSum().subtract(supplyProductDO.getSalePriceSum());
        orderUpdate.setOrderSum(orderSum);
        orderUpdate.setReceivableAmount(orderSum);
        orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderUpdate.getReceivableAmount(), supplyOrderDOS));
        OrderRedundancyInfoResponseDTO orderRedundancyInfo = orderCommonService.getOrderRedundancyInfo(supplyOrderDO.getOrderId(), supplyOrderDOS);
        orderUpdate.setRoomTypeNames(StringUtil.listToSQLString(orderRedundancyInfo.getRoomtypeNameSet()));
        orderUpdate.setSupplyOrderCodes(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyOrderCodeSet()));
        orderUpdate.setSupplyStatus(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyStatusSet()));
        orderUpdate.setSupplyCodes(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyCodeSet()));
        // 更新订单表产品冗余字段：为第一个供货单的第一个产品的入离日期、间数、产品名称
        SupplyProductDO firstProductQuery = new SupplyProductDO();
        firstProductQuery.setSupplyOrderId(supplyOrderDOS.get(0).getId());
        SupplyProductDO firstProduct = supplyProductMapper.select(firstProductQuery).get(0);
        orderUpdate.setCheckinDate(firstProduct.getCheckinDate());
        orderUpdate.setCheckoutDate(firstProduct.getCheckoutDate());
        orderUpdate.setRoomNum(firstProduct.getRoomNum());
        orderUpdate.setRateplanName(firstProduct.getRateplanName());
        orderUpdate.setBreakfastType(firstProduct.getBreakfastType());
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 8. 订单金额发生变化，处理订单财务信息
        handleOrderFinanceInfo(requestDTO.getModifier(), orderDO, orderUpdate);
        // 9. 删除对账明细表
        OrderCheckDetailDO orderCheckDetailDelete = new OrderCheckDetailDO();
        orderCheckDetailDelete.setSupplyProductId(requestDTO.getSupplyProductId());
        checkDetailMapper.delete(orderCheckDetailDelete);
        // 10. 如果是OTA订单需要退配额
        if (isOtaOrder) {
            // 退原来配额
            List<String> oldDateList = new ArrayList<>();
            for (SupplyProductPriceDO productPriceDO : oldProductPriceDOList) {
                oldDateList.add(DateUtil.dateToString(productPriceDO.getSaleDate()));
            }
            OrderDebuctOrRetreatQuotaRequestDTO oldDebuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
            oldDebuctRequestDTO.setDebuctQuotaType(2);
            oldDebuctRequestDTO.setOrderCode(orderDO.getOrderCode());
            oldDebuctRequestDTO.setDateList(oldDateList);
            oldDebuctRequestDTO.setQuotaAccountId(supplyProductDO.getQuotaAccountId());
            oldDebuctRequestDTO.setRatePlanId(supplyProductDO.getRateplanId());
            oldDebuctRequestDTO.setRoomNum(supplyProductDO.getRoomNum());
            oldDebuctRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
            oldDebuctRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
            oldDebuctRequestDTO.setStartDate(supplyProductDO.getCheckinDate());
            oldDebuctRequestDTO.setEndDate(supplyProductDO.getCheckoutDate());
            oldDebuctRequestDTO.setMerchantCode(orderDO.getMerchantCode());
            oldDebuctRequestDTO.setOrderId(orderDO.getId());
            orderCommonService.debuctOrRetreatQuota(oldDebuctRequestDTO);
        }
        // 11. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent("删除客房（" + supplyProductDO.getRoomTypeName() + "|" + supplyProductDO.getRateplanName() + "）成功");
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<PriceDetailResponseDTO> priceDetail(ProductDetailRequestDTO requestDTO) {
        log.info("priceDetail param: {}", requestDTO);
        ResponseDTO<PriceDetailResponseDTO> responseDTO = new ResponseDTO<>();
        PriceDetailResponseDTO priceDetailResponseDTO = new PriceDetailResponseDTO();
        // 1. 根据供货产品ID查价格明细
        SupplyProductPriceDO productPriceQuery = new SupplyProductPriceDO();
        productPriceQuery.setSupplyProductId(requestDTO.getSupplyProductId());
        List<SupplyProductPriceDO> productPriceDOList = productPriceMapper.select(productPriceQuery);
        List<SupplyProductPriceResponseDTO> productPriceResponseDTOList = new ArrayList<>();
        for (SupplyProductPriceDO productPriceDO : productPriceDOList) {
            SupplyProductPriceResponseDTO productPriceResponseDTO = PropertyCopyUtil.transfer(productPriceDO, SupplyProductPriceResponseDTO.class);
            productPriceResponseDTO.setSaleDate(DateUtil.dateToString(productPriceDO.getSaleDate()));
            productPriceResponseDTOList.add(productPriceResponseDTO);
        }


        // 2. 散房订单，查询每日剩余配额、实扣配额
        SupplyProductDO supplyProductDO = supplyProductMapper.selectByPrimaryKey(requestDTO.getSupplyProductId());
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(supplyProductDO.getSupplyOrderId());
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        if (Constant.NO.equals(orderDO.getIsGroupRoom().intValue())) {
            // 查产品每日剩余配额
            Map<String, Integer> quotaMap = new HashMap<>(16);
            if (supplyProductDO.getRateplanId() != null) {
                ResponseDTO<ProductDailyInfoResponseDTO> dailyInfoResponse = queryProductDailyInfo(supplyProductDO.getRateplanId(),
                        supplyProductDO.getCheckinDate(), supplyProductDO.getCheckoutDate());
                if (dailyInfoResponse.getResult().equals(Constant.YES)) {
                    List<ProductDailyDTO> productDailyList = dailyInfoResponse.getModel().getProductDailyList();
                    for (ProductDailyDTO productDailyDTO : productDailyList) {
                        quotaMap.put(DateUtil.dateToString(productDailyDTO.getSaleDate()), productDailyDTO.getQuotaNum());
                    }
                }
            }
            // 调用产品模块，散房订单查询订单实扣配额
            Map<String, Integer> realQuotaMap = new HashMap<>(16);
            if (supplyProductDO.getRateplanId() != null) {
                DebuctQuotaQueryDTO debuctQuotaQueryDTO = new DebuctQuotaQueryDTO();
                debuctQuotaQueryDTO.setOrderCode(orderDO.getOrderCode());
                debuctQuotaQueryDTO.setPricePlanId(supplyProductDO.getRateplanId().longValue());
                ResponseDTO<DebuctQuotaQueryResponseDTO> debuctQuotaResponseDTO = quotaStateService.queryOrderDebuctQuotaInfo(debuctQuotaQueryDTO);
                DebuctQuotaQueryResponseDTO debuctQuotaDTO = debuctQuotaResponseDTO.getModel();
                if (debuctQuotaDTO != null && !CollectionUtils.isEmpty(debuctQuotaDTO.getDebuctQuotaQueryPricePlanDTOS())) {
                    List<QuotaStateDTO> quotaStateList = debuctQuotaDTO.getDebuctQuotaQueryPricePlanDTOS().get(0).getQuotaStateList();
                    for (QuotaStateDTO quotaStateDTO : quotaStateList) {
                        realQuotaMap.put(DateUtil.dateToString(quotaStateDTO.getSaleDate()), quotaStateDTO.getQuotaNum());
                    }
                }
            }

            for (SupplyProductPriceResponseDTO productPriceDTO : productPriceResponseDTOList) {
                productPriceDTO.setQuotaNum(quotaMap.get(productPriceDTO.getSaleDate()));
                productPriceDTO.setRealQuota(realQuotaMap.get(productPriceDTO.getSaleDate()));
            }
        }
        priceDetailResponseDTO.setDayList(productPriceResponseDTOList);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(priceDetailResponseDTO);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO addAdditionCharge(AddAdditionChargeRequestDTO requestDTO) {
        log.info("addAdditionCharge param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 添加附加项，  默认加入到第一个为非不确认的供货单下面
        Example example = new Example(SupplyOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", requestDTO.getOrderId());
        criteria.andNotEqualTo("supplyStatus", SupplyStatusEnum.UN_CONFIRM.key);
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(supplyOrderDOList)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("没有有效的供货单，不能添加附加项");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOList.get(0);
        List<SupplyAdditionChargeDO> additionChargeList = new ArrayList<>();
        BigDecimal basePriceSum = BigDecimal.ZERO;
        BigDecimal salePriceSum = BigDecimal.ZERO;
        StringBuffer additionNames = new StringBuffer();
        for (int i = 0; i < requestDTO.getAdditionChargeList().size(); i++) {
            SupplyAdditionChargeDTO additionChargeDTO = requestDTO.getAdditionChargeList().get(i);
            additionChargeDTO.setAdditionType(1);
            if (additionChargeDTO.getBasePriceSum() == null) {
                additionChargeDTO.setBasePriceSum(additionChargeDTO.getSalePriceSum());
            }
            if (additionChargeDTO.getBasePrice() == null) {
                additionChargeDTO.setBasePrice(additionChargeDTO.getSalePrice());
            }
            if (i > 0) {
                additionNames.append(",");
            }
            additionNames.append("名称：" + additionChargeDTO.getName() + "，售价：" + additionChargeDTO.getSalePrice()
                    + "，底价：" + additionChargeDTO.getBasePrice() + "，份数：" + additionChargeDTO.getNum() + "，天数：" + additionChargeDTO.getDays() + "；");
            basePriceSum = basePriceSum.add(additionChargeDTO.getBasePriceSum());
            salePriceSum = salePriceSum.add(additionChargeDTO.getSalePriceSum());
            SupplyAdditionChargeDO additionChargeInsert = PropertyCopyUtil.transfer(additionChargeDTO, SupplyAdditionChargeDO.class);
            additionChargeInsert.setOrderId(supplyOrderDO.getOrderId());
            additionChargeInsert.setSupplyOrderId(supplyOrderDO.getId());
            additionChargeInsert.setModifier(requestDTO.getModifier());
            additionChargeInsert.setModifyTime(requestDTO.getModifyTime());
            additionChargeList.add(additionChargeInsert);
        }
        additionChargeMapper.insertList(additionChargeList);
        // 2. 更新供货单表总金额
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(supplyOrderDO.getId());
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().add(basePriceSum));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().add(salePriceSum));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 3. 更新订单表总金额
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(orderDO.getId());
        orderDO.setOrderSum(orderDO.getOrderSum().add(salePriceSum));
        orderUpdate.setOrderSum(orderDO.getOrderSum());
        orderUpdate.setReceivableAmount(orderDO.getOrderSum());
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent("添加附加项（" + additionNames + "）成功");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO changeAdditionCharge(ChangeAdditionChargeRequestDTO requestDTO) {
        log.info("changeAdditionCharge param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新供货附加项表
        SupplyAdditionChargeDO additionChargeDO = additionChargeMapper.selectByPrimaryKey(requestDTO.getSupplyAdditionChargeId());
        if (additionChargeDO.getAdditionType() == 3) {
            requestDTO.setBasePriceSum(requestDTO.getBasePriceSum().negate());
            requestDTO.setSalePriceSum(requestDTO.getSalePriceSum().negate());
            requestDTO.setBasePrice(requestDTO.getBasePrice().negate());
            requestDTO.setSalePrice(requestDTO.getSalePrice().negate());
        }
        SupplyAdditionChargeDO additionChargeUpdate = PropertyCopyUtil.transfer(requestDTO, SupplyAdditionChargeDO.class);
        additionChargeUpdate.setId(requestDTO.getSupplyAdditionChargeId());
        additionChargeMapper.updateByPrimaryKeySelective(additionChargeUpdate);
        // 2. 更新供货单表总金额
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(additionChargeDO.getSupplyOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(additionChargeDO.getSupplyOrderId());
        // 供货单总额 = 供货单原总额 - 修改附加项前原总额 + 修改附加项后总额
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().subtract(additionChargeDO.getBasePriceSum()).add(requestDTO.getBasePriceSum()));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().subtract(additionChargeDO.getSalePriceSum()).add(requestDTO.getSalePriceSum()));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 3. 更新订单表总金额
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(supplyOrderDO.getOrderId());
        // 订单总额 = 订单原总额 - 修改前供货单总额 + 修改后供货单总额
        orderUpdate.setOrderSum(orderDO.getOrderSum().subtract(supplyOrderDO.getSalePriceSum()).add(supplyOrderUpdate.getSalePriceSum()));
        orderUpdate.setReceivableAmount(orderUpdate.getOrderSum());
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        String content = "名称：" + requestDTO.getName() + "，售价：" + requestDTO.getSalePrice()
                + "，底价：" + requestDTO.getBasePrice() + "，份数：" + requestDTO.getNum() + "，天数：" + requestDTO.getDays() + "；";
        logDO.setContent("修改" + (additionChargeDO.getAdditionType() == 1 ? "附加项" : "减免政策") + "（" + content + "）成功");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO deleteAdditionCharge(DeleteAdditionChargeRequestDTO requestDTO) {
        log.info("deleteAdditionCharge param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 删除供货附加项表
        SupplyAdditionChargeDO additionChargeDO = additionChargeMapper.selectByPrimaryKey(requestDTO.getSupplyAdditionChargeId());
        additionChargeMapper.deleteByPrimaryKey(requestDTO.getSupplyAdditionChargeId());
        // 2. 更新供货单表总金额
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(additionChargeDO.getSupplyOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(additionChargeDO.getSupplyOrderId());
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().subtract(additionChargeDO.getBasePriceSum()));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().subtract(additionChargeDO.getSalePriceSum()));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 3. 更新订单表总金额
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(orderDO.getId());
        BigDecimal orderSum = orderDO.getOrderSum().subtract(additionChargeDO.getSalePriceSum());
        orderUpdate.setOrderSum(orderSum);
        orderUpdate.setReceivableAmount(orderSum);
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent("删除附加项（" + additionChargeDO.getName() + "）成功");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO sendToSupplier(SendToSupplierRequestDTO requestDTO) {
        log.info("sendToSupplier param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 0. 校验：预订单只能发送一次， 供货单已确认不能重发预订单
        SupplyRequestDO supplyRequestQuery = new SupplyRequestDO();
        supplyRequestQuery.setSupplyOrderId(requestDTO.getSupplyOrderId());
        supplyRequestQuery.setSupplyType(Byte.valueOf(1 + ""));
        List<SupplyRequestDO> bookRequestDOList = supplyRequestMapper.select(supplyRequestQuery);
        if (requestDTO.getSupplyType().equals(SupplyTypeEnum.BOOK.key)) {
            if (!CollectionUtils.isEmpty(bookRequestDOList)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("预订单只能发送一次");
                return responseDTO;
            }
        }
        // 已经有尚未处理的取消（修改）申请时，提示：有尚未处理的取消（修改）申请，无法发起新申请
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(requestDTO.getSupplyOrderId());
        Example example = new Example(SupplyRequestDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("supplyOrderId", requestDTO.getSupplyOrderId());
        criteria.andEqualTo("sendType",1);
        criteria.andEqualTo("thisConfirmType", 0);
        List<Integer> supplyTypeList = new ArrayList<>();
        supplyTypeList.add(3);
        supplyTypeList.add(4);
        criteria.andIn("supplyType", supplyTypeList);
        List<SupplyRequestDO> supplyRequestDOList = supplyRequestMapper.selectByExample(example);
        boolean haveUndoneRequest = (requestDTO.getSupplyType() == 3 || requestDTO.getSupplyType() == 4) && !CollectionUtils.isEmpty(supplyRequestDOList);
        if (haveUndoneRequest) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("有尚未处理的取消（修改）申请，无法发起新申请");
            return responseDTO;
        }
        if (requestDTO.getSupplyType() == 3 && supplyOrderDO.getSupplyStatus().intValue() != SupplyStatusEnum.CONFIRMED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单不是已确认，不能发修改单");
            return responseDTO;
        }
        if (CollectionUtils.isEmpty(bookRequestDOList) && requestDTO.getSupplyType() != 1) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("没发过预订单，不能直接发" + SupplyTypeEnum.getValueByKey(requestDTO.getSupplyType()));
            return responseDTO;
        }
        if (supplyOrderDO.getSupplyStatus().intValue() == SupplyStatusEnum.CONFIRMED.key && requestDTO.getSupplyType() == 2) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单已确认，不能重发预订单");
            return responseDTO;
        }
        if (supplyOrderDO.getSupplyStatus().intValue() == SupplyStatusEnum.UN_CONFIRM.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单已取消，不能再发" + SupplyTypeEnum.getValueByKey(requestDTO.getSupplyType()));
            return responseDTO;
        }


        // 1. 插入发单申请表
        SupplyRequestDO supplyRequestInsert = PropertyCopyUtil.transfer(requestDTO, SupplyRequestDO.class);
        supplyRequestInsert.setSendType(Byte.valueOf(supplyRequestInsert.getSendType() + ""));
        supplyRequestInsert.setSendStatus(Constant.NO.byteValue());
        supplyRequestInsert.setThisConfirmType(Byte.valueOf(0 + ""));
        supplyRequestMapper.insertSelective(supplyRequestInsert);
        Integer supplyRequestId = supplyRequestInsert.getId();

        //2. 发单
        if (requestDTO.getSendType()== SupplySendTypeEnum.EBK.key){
            // 调用EBK发单接口
            ResponseDTO sendEbkResponseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);
            try {
                // 发供货单类型：1：预定单，2:重发预订单， 3：修改单，4：取消单
                if (requestDTO.getSupplyType() == 1 || requestDTO.getSupplyType() == 2) {
                    // 组装发送EBK数据
                    BookRequestDTO bookRequestDTO = assemblySendEbkRequestData(requestDTO, supplyOrderDO, supplyRequestId);
                    sendEbkResponseDTO = ebkRequestService.sendBookRequest(bookRequestDTO);
                } else if (requestDTO.getSupplyType() == 3) {
                    // 修改单发EBK
                    BookRequestDTO bookRequestDTO = assemblySendEbkRequestData(requestDTO, supplyOrderDO, supplyRequestId);
                    sendEbkResponseDTO = ebkRequestService.sendModifyRequest(bookRequestDTO);
                } else if (requestDTO.getSupplyType() == 4) {
                    CancelRequestDTO cancelRequestDTO = new CancelRequestDTO();
                    cancelRequestDTO.setMerchantRemark(requestDTO.getNote());
                    cancelRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
                    cancelRequestDTO.setSupplyRequestId(supplyRequestId.longValue());
                    cancelRequestDTO.setOperator(requestDTO.getModifier());
                    sendEbkResponseDTO = ebkRequestService.sendCancelRequest(cancelRequestDTO);
                }
            } catch (Exception e) {
                log.error("sendBookRequest|sendModifyRequest|sendCancelRequest exception", e);
                sendEbkResponseDTO.setResult(Constant.NO);
                sendEbkResponseDTO.setFailReason(e.getMessage());
            }
            if (sendEbkResponseDTO.getResult().equals(ResultCodeEnum.FAILURE.code)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason(sendEbkResponseDTO.getFailReason());
                return responseDTO;
            }
            //发送微信消息
            sendTemplateMessageToSupply(requestDTO);
        }else if (requestDTO.getSendType()== SupplySendTypeEnum.EMAIL.key){
            //调发邮件接口
            ResponseDTO sendEmailResponseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);
            try {
                Map<String, Object> data=assemblyTemplateData(requestDTO);
                String content=TemplateFileUtil.getTemplateContent(TemplateFileUtil.FileType.email,data);
                EmailSendRequestDTO emailSendRequestDTO=new EmailSendRequestDTO();
                BeanUtils.copyProperties(requestDTO,emailSendRequestDTO);
                emailSendRequestDTO.setSender(requestDTO.getUserName());
                emailSendRequestDTO.setContent(content);
                sendEmailResponseDTO=emailRemote.sendEmail(emailSendRequestDTO);
            }catch (Exception e){
                log.error("sendEmail exception", e);
                sendEmailResponseDTO.setResult(Constant.NO);
                sendEmailResponseDTO.setFailReason(e.getMessage());
            }
            if (sendEmailResponseDTO.getResult().equals(ResultCodeEnum.FAILURE.code)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason(sendEmailResponseDTO.getFailReason());
                return responseDTO;
            }
        }

        // 3. 更新供货请求为：已发送
        SupplyRequestDO supplyRequestUpdate = new SupplyRequestDO();
        supplyRequestUpdate.setId(supplyRequestId);
        supplyRequestUpdate.setSendStatus(Byte.valueOf(Constant.YES + ""));
        supplyRequestMapper.updateByPrimaryKeySelective(supplyRequestUpdate);

        // 4. 更新供货单状态为：已发单（发取消单申请后，供货单状态不能变化）
        if (requestDTO.getSupplyType() == 1 || requestDTO.getSupplyType() == 2) {
            SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
            supplyOrderUpdate.setId(requestDTO.getSupplyOrderId());
            supplyOrderUpdate.setSupplyStatus(Byte.valueOf(SupplyStatusEnum.SENT.key + ""));
            supplyOrderUpdate.setModifier(requestDTO.getModifier());
            supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
            supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);

            // 5. 更新订单冗余字段： 供货单状态
            SupplyOrderDO supplyOrderListQuery = new SupplyOrderDO();
            supplyOrderListQuery.setOrderId(supplyOrderDO.getOrderId());
            List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.select(supplyOrderListQuery);
            Set<String> supplyStatusSet = new HashSet<>();
            for (SupplyOrderDO supplyOrder : supplyOrderDOList) {
                supplyStatusSet.add(supplyOrder.getSupplyStatus() + "");
            }

            OrderDO orderUpdate = new OrderDO();
            orderUpdate.setId(supplyOrderDO.getOrderId());
            orderUpdate.setSupplyStatus(StringUtil.listToSQLString(supplyStatusSet));
            orderMapper.updateByPrimaryKeySelective(orderUpdate);
        }

        // 5. 添加商家给供应商备注
        if (!StringUtils.isEmpty(requestDTO.getNote())) {
            OrderNoteDO noteInsert = new OrderNoteDO();
            noteInsert.setOrderId(supplyOrderDO.getOrderId());
            noteInsert.setCreator(requestDTO.getCreator());
            noteInsert.setCreateTime(requestDTO.getCreateTime());
            noteInsert.setNote(requestDTO.getNote());
            noteInsert.setNoteObject(supplyOrderDO.getSupplyName());
            noteInsert.setNoteType(Byte.valueOf(NoteTypeEnum.SUPPLY_NOTE.key + ""));
            orderNoteMapper.insert(noteInsert);
        }
        // 6. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        logDO.setContent("发单给供应商（供货单：" + supplyOrderDO.getSupplyOrderCode() + "，发送方式：EBK，发单类型：" + SupplyTypeEnum.getValueByKey(requestDTO.getSupplyType()) + "）");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    /**
     * 组装发送EBK数据
     *
     * @param requestDTO
     * @param supplyOrderDO
     * @param supplyRequestId
     * @return
     */
    private BookRequestDTO assemblySendEbkRequestData(SendToSupplierRequestDTO requestDTO, SupplyOrderDO supplyOrderDO, Integer supplyRequestId) {
        // 1. 查订单
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        // 2. 查供货单第一个产品
        SupplyProductDO supplyProductQuery = new SupplyProductDO();
        supplyProductQuery.setSupplyOrderId(requestDTO.getSupplyOrderId());
        List<SupplyProductDO> supplyProductDOList = supplyProductMapper.select(supplyProductQuery);
        SupplyProductDO supplyProductDO = supplyProductDOList.get(0);
        // 3. 查入住名单
        OrderAttachDO attachQuery = new OrderAttachDO();
        attachQuery.setOrderId(orderDO.getId());
        attachQuery.setType(Byte.valueOf(9 + ""));
        List<OrderAttachDO> orderAttachDOList = attachMapper.select(attachQuery);
        String guestAttachUrl = null;
        if (!CollectionUtils.isEmpty(orderAttachDOList)) {
            guestAttachUrl = orderAttachDOList.get(0).getUrl();
        }
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setSupplyRequestId(supplyRequestId.longValue());
        bookRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
        bookRequestDTO.setIsGroupon(orderDO.getIsGroupRoom().intValue());
        bookRequestDTO.setHotelId(supplyOrderDO.getHotelId().longValue());
        bookRequestDTO.setHotelName(supplyOrderDO.getHotelName());
        if (supplyProductDO.getRoomTypeId() != null) {
            bookRequestDTO.setRoomtypeId(supplyProductDO.getRoomTypeId().longValue());
        }
        bookRequestDTO.setRoomtypeName(supplyProductDO.getRoomTypeName());
        if (supplyProductDO.getRateplanId() != null) {
            bookRequestDTO.setRateplanId(supplyProductDO.getRateplanId().longValue());
        }
        bookRequestDTO.setRateplanName(supplyProductDO.getRateplanName());
        bookRequestDTO.setCheckInDate(supplyProductDO.getCheckinDate());
        bookRequestDTO.setCheckOutDate(supplyProductDO.getCheckoutDate());
        bookRequestDTO.setRoomNum(supplyProductDO.getRoomNum());
        bookRequestDTO.setGuest(orderDO.getGuestNames());
        bookRequestDTO.setGuestAttachUrl(guestAttachUrl);
        bookRequestDTO.setOrderSum(supplyOrderDO.getPayableAmount());
        bookRequestDTO.setCurrency(supplyOrderDO.getBaseCurrency());
        bookRequestDTO.setSpecialRequirement(orderDO.getSpecialRequest());
        bookRequestDTO.setSupplyCode(supplyOrderDO.getSupplyCode());
        bookRequestDTO.setSupplyName(supplyOrderDO.getSupplyName());
        bookRequestDTO.setMerchantCode(orderDO.getMerchantCode());
        bookRequestDTO.setMerchantName(orderDO.getMerchantName());
        bookRequestDTO.setMerchantRemark(requestDTO.getNote());
        bookRequestDTO.setBalanceMethod(supplyOrderDO.getBalanceMethod().intValue());
        bookRequestDTO.setGuide(orderDO.getGuide());
        bookRequestDTO.setGuidePhone(orderDO.getGuidePhone());
        bookRequestDTO.setOperator(requestDTO.getCreator());

        // 4. 查产品对账明细
        List<BookRequestItemDTO> itemDTOList = new ArrayList<>();
        for (SupplyProductDO productDO : supplyProductDOList) {
            OrderCheckDetailDO checkDetailQuery = new OrderCheckDetailDO();
            checkDetailQuery.setSupplyProductId(productDO.getId());
            List<OrderCheckDetailDO> checkDetailDOList = checkDetailMapper.select(checkDetailQuery);
            for (OrderCheckDetailDO checkDetailDO : checkDetailDOList) {
                BookRequestItemDTO itemDTO = new BookRequestItemDTO();
                //客房1，附加项2，减免政策3
                itemDTO.setItemType(1);
                itemDTO.setItemName("客房");
                if (productDO.getRoomTypeId() != null) {
                    itemDTO.setRoomtypeId(productDO.getRoomTypeId().longValue());
                }
                itemDTO.setRoomtypeName(productDO.getRoomTypeName());
                if (productDO.getRateplanId() != null) {
                    itemDTO.setRateplanId(productDO.getRateplanId().longValue());
                }
                itemDTO.setRateplanName(productDO.getRateplanName());
                itemDTO.setBedType(productDO.getBedtype());
                itemDTO.setBreakfastType(productDO.getBreakfastType().intValue());
                itemDTO.setCheckInDate(checkDetailDO.getCheckinDate());
                itemDTO.setCheckOutDate(checkDetailDO.getCheckoutDate());
                itemDTO.setRoomNum(new BigDecimal(checkDetailDO.getRoomNum()));
                itemDTO.setSalePrice(checkDetailDO.getBasePrice());
                itemDTOList.add(itemDTO);
            }
        }
        // 5. 查附加项
        SupplyAdditionChargeDO additionChargeQuery = new SupplyAdditionChargeDO();
        additionChargeQuery.setSupplyOrderId(supplyOrderDO.getId());
        additionChargeQuery.setAdditionType(Byte.valueOf("1"));
        List<SupplyAdditionChargeDO> additionChargeDOList = additionChargeMapper.select(additionChargeQuery);
        for (SupplyAdditionChargeDO additionChargeDO : additionChargeDOList) {
            BookRequestItemDTO itemDTO = new BookRequestItemDTO();
            //客房1，附加项2，减免政策3
            itemDTO.setItemType(2);
            itemDTO.setItemName(additionChargeDO.getName());
            itemDTO.setNightNum(additionChargeDO.getDays());
            itemDTO.setRoomNum(new BigDecimal(additionChargeDO.getNum()));
            itemDTO.setSalePrice(additionChargeDO.getBasePrice());
            itemDTOList.add(itemDTO);
        }
        bookRequestDTO.setItemDTOList(itemDTOList);

        // 6. 查减免政策
        List<DeratePolicyResponseDTO> deratePolicyList = orderCommonService.queryDeratePolicyList(supplyOrderDO.getId());
        if (!CollectionUtils.isEmpty(deratePolicyList)) {
            for (DeratePolicyResponseDTO deratePolicyDTO : deratePolicyList) {
                BookRequestItemDTO itemDTO = new BookRequestItemDTO();
                //客房1，附加项2，减免政策3
                itemDTO.setItemType(3);
                itemDTO.setItemName(deratePolicyDTO.getName());
                itemDTO.setRoomNum(deratePolicyDTO.getRoomNumSum());
                itemDTO.setSalePrice(deratePolicyDTO.getBasePriceSum());
                if (!CollectionUtils.isEmpty(deratePolicyDTO.getDayList())) {
                    List<BookRequestItemPriceDTO> itemPriceDTOList = new ArrayList<>();
                    for (DeratePolicyPriceDTO deratePolicyPriceDTO : deratePolicyDTO.getDayList()) {
                        BookRequestItemPriceDTO priceDTO = new BookRequestItemPriceDTO();
                        priceDTO.setSaleDate(DateUtil.stringToDate(deratePolicyPriceDTO.getSaleDate()));
                        priceDTO.setSalePrice(deratePolicyPriceDTO.getBasePrice());
                        priceDTO.setRoomNum(deratePolicyPriceDTO.getRoomNum());
                        itemPriceDTOList.add(priceDTO);
                    }
                    itemDTO.setItemPriceDTOList(itemPriceDTOList);
                }
                itemDTOList.add(itemDTO);
            }
        }
        return bookRequestDTO;
    }

    /**
     * 组装邮件模板数据
     */
    private Map<String, Object> assemblyTemplateData(SendToSupplierRequestDTO requestDTO){
        Map<String, Object> data=new HashMap();

        //查询要求供货单数据
        SupplyDetailRequestDTO supplyDetailRequestDTO=new SupplyDetailRequestDTO();
        supplyDetailRequestDTO.setSupplyOrderId(requestDTO.getSupplyOrderId());
        ResponseDTO<SupplyDetailResponseDTO> res=this.supplyDetail(supplyDetailRequestDTO);
        SupplyDetailResponseDTO supplyDetail=res.getModel();

        //查询商家文件
        QueryMerchantCompanyDTO queryMerchantCompanyDTO=new QueryMerchantCompanyDTO();
        queryMerchantCompanyDTO.setMerchantCompanyId(requestDTO.getMerchantCompanyId());
        List<MerchantCompanyDTO> merchantCompanyDTOList=merchantCompanyService.queryMerchantCompany(queryMerchantCompanyDTO);
        if (merchantCompanyDTOList!=null && merchantCompanyDTOList.size()==0){
            throw new ServiceException("商家文件为空");
        }

        data.put("supplyType",SupplyTypeEnum.getValueByKey(requestDTO.getSupplyType()));
        data.put("sendTime",DateUtil.dateToStringWithHms(new Date()));
        data.put("supplyOrderCode",supplyDetail.getSupplyOrderCode());
        data.put("supplyName",supplyDetail.getSupplyName());
        data.put("company",merchantCompanyDTOList.get(0).getCompany());
        data.put("seal",merchantCompanyDTOList.get(0).getSeal());
        data.put("hotelName",supplyDetail.getHotelName());
        data.put("hotelPhone",supplyDetail.getHotelPhone()==null?"":supplyDetail.getHotelPhone());
        data.put("hotelAddress",supplyDetail.getHotelAddress()==null?"":supplyDetail.getHotelAddress());
        data.put("guest",supplyDetail.getGuestNames());
        data.put("specialRequest",supplyDetail.getSpecialRequest()==null?"":supplyDetail.getSpecialRequest());
        data.put("note",requestDTO.getNote()==null?"":requestDTO.getNote());
        data.put("currency",supplyDetail.getBaseCurrency());
        data.put("orderSum",supplyDetail.getSupplySum());
        data.put("supplyProductList",supplyDetail.getSupplyProductList()==null?new ArrayList<>():supplyDetail.getSupplyProductList());
        data.put("derateList",supplyDetail.getDerateList()==null?new ArrayList<>():supplyDetail.getDerateList());
        data.put("supplyAdditionChargeList",supplyDetail.getSupplyAdditionChargeList()==null?new ArrayList<>():supplyDetail.getSupplyAdditionChargeList());
        return data;
    }

    @Override
    @Transactional
    public ResponseDTO saveSupplyResult(SaveSupplyResultRequestDTO requestDTO) {
        log.info("saveSupplyResult param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 供货单状态变化，重新计算供货单金额
        SupplyOrderPriceSumResponseDTO priceSumResponseDTO = orderCommonService.calcSupplyOrderPriceSum(requestDTO.getSupplyOrderId(), requestDTO.getSupplyStatus());
        BigDecimal supplyBasePriceSum = priceSumResponseDTO.getSupplyBasePriceSum();
        BigDecimal supplySalePriceSum = priceSumResponseDTO.getSupplySalePriceSum();

        // 2. 更新供货单状态、供货单金额、供货单应付
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(requestDTO.getSupplyOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(requestDTO.getSupplyOrderId());
        supplyOrderUpdate.setConfirmName(requestDTO.getConfirmName());
        supplyOrderUpdate.setConfirmNo(requestDTO.getConfirmNo());
        supplyOrderUpdate.setSupplyStatus(Byte.valueOf(requestDTO.getSupplyStatus() + ""));
        // 供货单金额已变化
        if (supplyBasePriceSum != null) {
            supplyOrderUpdate.setSupplySum(supplyBasePriceSum);
            supplyOrderUpdate.setPayableAmount(supplyBasePriceSum);
            supplyOrderUpdate.setSalePriceSum(supplySalePriceSum);
        }
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 3. 更新单结供货单财务信息：支付状态、结算状态
        if (supplyBasePriceSum != null) {
            if (supplyOrderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key) {
                orderCommonService.updateSupplyFinanceInfo(supplyOrderDO.getId(), supplyOrderUpdate.getPayableAmount(), null, null);
            }
        }
        // 4. 非单结供货单，挂账或退挂账
        if (supplyOrderDO.getBalanceMethod().intValue() != BalanceMethodEnum.SINGLE.key) {
            SupplyCreditPayOrReceiptRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(supplyOrderDO, SupplyCreditPayOrReceiptRequestDTO.class);
            creditRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
            creditRequestDTO.setPayableAmount(supplyBasePriceSum != null ? supplyOrderUpdate.getPayableAmount() : supplyOrderDO.getPayableAmount());
            creditRequestDTO.setPayAmount(supplyOrderDO.getPayableAmount());
            creditRequestDTO.setOperateType(requestDTO.getSupplyStatus() == 3 ? 1 : 2);
            creditRequestDTO.setOperator(requestDTO.getCreator());
            creditRequestDTO.setMerchantCode(requestDTO.getMerchantCode());
            creditRequestDTO.setSupplyStatus(requestDTO.getSupplyStatus());
            orderCommonService.supplyCreditPayOrRefund(creditRequestDTO);
        }
        // 5. 更新订单表冗余字段：订单确认号、供货单状态、订单总金额
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setOrderId(supplyOrderDO.getOrderId());
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.select(supplyOrderQuery);
        Set<String> supplyStatusSet = new HashSet<>();
        Set<String> supplyConfirmNoSet = new HashSet<>();
        BigDecimal orderSum = BigDecimal.ZERO;
        Integer firstActiveSupplyOrder = null;
        for (SupplyOrderDO supplyOrder : supplyOrderDOList) {
            if (!StringUtils.isEmpty(supplyOrder.getConfirmNo())) {
                supplyConfirmNoSet.add(supplyOrder.getConfirmNo());
            }
            supplyStatusSet.add(supplyOrder.getSupplyStatus() + "");
            orderSum = orderSum.add(supplyOrder.getSalePriceSum());
            if (firstActiveSupplyOrder == null && supplyOrder.getSupplyStatus().intValue() != SupplyStatusEnum.UN_CONFIRM.key) {
                firstActiveSupplyOrder = supplyOrder.getId();
            }
        }
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(supplyOrderDO.getOrderId());
        orderUpdate.setSupplyConfirmNo(StringUtil.listToSQLString(supplyConfirmNoSet));
        orderUpdate.setSupplyStatus(StringUtil.listToSQLString(supplyStatusSet));
        // 供货单金额已变化
        if (supplyBasePriceSum != null) {
            orderUpdate.setOrderSum(orderSum);
            orderUpdate.setReceivableAmount(orderSum);
            orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderUpdate.getReceivableAmount(), supplyOrderDOList));
        }
        // 更新订单表产品冗余字段：为第一个供货单的第一个产品的入离日期、间数、产品名称
        if (firstActiveSupplyOrder != null) {
            SupplyProductDO firstProductQuery = new SupplyProductDO();
            firstProductQuery.setSupplyOrderId(firstActiveSupplyOrder);
            SupplyProductDO firstProduct = supplyProductMapper.select(firstProductQuery).get(0);
            orderUpdate.setCheckinDate(firstProduct.getCheckinDate());
            orderUpdate.setCheckoutDate(firstProduct.getCheckoutDate());
            orderUpdate.setRoomNum(firstProduct.getRoomNum());
            orderUpdate.setRateplanName(firstProduct.getRateplanName());
            orderUpdate.setBreakfastType(firstProduct.getBreakfastType());
        }
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 6. 订单金额发生变化，处理订单财务信息
        if (supplyBasePriceSum != null) {
            handleOrderFinanceInfo(requestDTO.getCreator(), orderDO, orderUpdate);
        }
        // 7. 散房订单， 调用产品模块， 退扣配额
        orderCommonService.debuctOrRetreatSupplyOrderQuota(requestDTO.getSupplyStatus(), supplyOrderDO, orderDO, requestDTO.getCreator());

        // 8. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("更新供货单状态，供货单：" + supplyOrderDO.getSupplyOrderCode() + "，"
                + "由" + SupplyStatusEnum.getValueByKey(supplyOrderDO.getSupplyStatus()) + "变更为"
                + SupplyStatusEnum.getValueByKey(requestDTO.getSupplyStatus()));
        if (StringUtils.isNotEmpty(requestDTO.getConfirmNo())) {
            content.append("，确认号：" + requestDTO.getConfirmNo());
        }
        if (StringUtils.isNotEmpty(requestDTO.getConfirmName())) {
            content.append("，确认人：" + requestDTO.getConfirmName());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }


    @Override
    public ResponseDTO<QuerySupplyReqResponseDTO> querySupplyRequest(SupplyDetailRequestDTO requestDTO) {
        log.info("querySupplyRequest param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 根据供货单ID查发单申请
        Example example = new Example(SupplyRequestDO.class);
        example.setOrderByClause("id desc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("supplyOrderId", requestDTO.getSupplyOrderId());
        List<SupplyRequestDO> supplyRequestDOList = supplyRequestMapper.selectByExample(example);

        // 2. 组装响应对象
        List<SupplyRequestDTO> supplyRequestDTOList = new ArrayList<>();
        for (SupplyRequestDO supplyRequestDO : supplyRequestDOList) {
            SupplyRequestDTO supplyRequestDTO = PropertyCopyUtil.transfer(supplyRequestDO, SupplyRequestDTO.class);
            supplyRequestDTO.setSupplyRequestId(supplyRequestDO.getId());
            supplyRequestDTO.setCreateTime(DateUtil.dateToStringWithHms(supplyRequestDO.getCreateTime()));
            if (supplyRequestDO.getModifyTime() != null) {
                supplyRequestDTO.setModifyTime(DateUtil.dateToStringWithHms(supplyRequestDO.getModifyTime()));
            }
            supplyRequestDTOList.add(supplyRequestDTO);
        }
        QuerySupplyReqResponseDTO supplyReqResponseDTO = new QuerySupplyReqResponseDTO();
        supplyReqResponseDTO.setSupplyRequestList(supplyRequestDTOList);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(supplyReqResponseDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO notifySupplier(NotifySupplierRequestDTO requestDTO) {
        /**
         * 一期不做， 二期通过微信模版消息提醒
         */
        log.info("notifySupplier param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 发消息

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public PaginationSupportDTO<QueryProductListResponseDTO> queryProductList(QueryProductListRequestDTO requestDTO) {
        log.info("queryProductList param: {}", requestDTO);
        // 1. 调用产品模块， 分页查询产品列表
        ProductPageQueryDTO productPageQueryDTO = PropertyCopyUtil.transfer(requestDTO, ProductPageQueryDTO.class);
        productPageQueryDTO.setStartDate(DateUtil.stringToDate(requestDTO.getCheckinDate()));
        productPageQueryDTO.setEndDate(DateUtil.stringToDate(requestDTO.getCheckoutDate()));
        ResponseDTO<PaginationSupportDTO<ProductDailyInfoResponseDTO>> pageResponse = pricePlanService.queryProductInfoByPage(productPageQueryDTO);
        PaginationSupportDTO<ProductDailyInfoResponseDTO> page = pageResponse.getModel();
        PaginationSupportDTO<QueryProductListResponseDTO> pageResponseDTO = new PaginationSupportDTO<>();
        if (page == null) {
            return pageResponseDTO;
        }
        BeanUtils.copyProperties(page, pageResponseDTO);

        List<QueryProductListResponseDTO> productDTOList = new ArrayList<>();
        for (ProductDailyInfoResponseDTO productInfoResponseDTO : page.getItemList()) {
            QueryProductListResponseDTO productDTO = PropertyCopyUtil.transfer(productInfoResponseDTO, QueryProductListResponseDTO.class);
            productDTO.setRatePlanId(productInfoResponseDTO.getPricePlanId().intValue());
            productDTO.setRatePlanName(productInfoResponseDTO.getPricePlanName());
            productDTO.setBedtype(productInfoResponseDTO.getBedType());
            productDTO.setBreakfastType(productInfoResponseDTO.getBreakFastType());
            productDTO.setSupplyName(productInfoResponseDTO.getSupplyName());
            List<SupplyProductPriceDTO> dayList = new ArrayList<>();
            for (ProductDailyDTO productDailyDTO : productInfoResponseDTO.getProductDailyList()) {
                SupplyProductPriceDTO productPriceDTO = new SupplyProductPriceDTO();
                SupplyProductPriceDTO priceDTO = orderCommonService.getSalePriceByChannelCode(requestDTO.getChannelCode(), productDailyDTO, requestDTO.getRoomNum(), 1);
                productPriceDTO.setBasePrice(priceDTO.getBasePrice());
                productPriceDTO.setSalePrice(priceDTO.getSalePrice());
                productPriceDTO.setSaleDate(DateUtil.dateToString(productDailyDTO.getSaleDate()));
                dayList.add(productPriceDTO);
            }
            productDTO.setDayList(dayList);
            productDTOList.add(productDTO);
        }
        pageResponseDTO.setItemList(productDTOList);
        return pageResponseDTO;
    }

    @Override
    public ResponseDTO<SupplyProductDTO> productDetail(ProductDetailRequestDTO requestDTO) {
        log.info("productDetail param: {}", requestDTO);
        ResponseDTO<SupplyProductDTO> responseDTO = new ResponseDTO();
        // 1. 根据供货产品ID查供货产品详情
        SupplyProductDO supplyProductDO = supplyProductMapper.selectByPrimaryKey(requestDTO.getSupplyProductId());
        SupplyProductDTO supplyProductDTO = PropertyCopyUtil.transfer(supplyProductDO, SupplyProductDTO.class);
        supplyProductDTO.setSupplyProductId(requestDTO.getSupplyProductId());
        supplyProductDTO.setCheckinDate(DateUtil.dateToString(supplyProductDO.getCheckinDate()));
        supplyProductDTO.setCheckoutDate(DateUtil.dateToString(supplyProductDO.getCheckoutDate()));
        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(supplyProductDTO);
        return responseDTO;
    }

    /**
     * 调用产品模块，查产品最新价格
     *
     * @param rateplanId
     * @param checkinDate
     * @param checkoutDate
     * @return
     */
    private ResponseDTO<ProductDailyInfoResponseDTO> queryProductDailyInfo(Integer rateplanId, Date checkinDate, Date checkoutDate) {
        ResponseDTO<ProductDailyInfoResponseDTO> responseDTO = new ResponseDTO();
        ProductDailyInfoQueryDTO productDailyInfoQuery = new ProductDailyInfoQueryDTO();
        productDailyInfoQuery.setPricePlanId(rateplanId.longValue());
        productDailyInfoQuery.setStartDate(checkinDate);
        productDailyInfoQuery.setEndDate(checkoutDate);
        log.info("priceInfoService.queryProductDailyInfo param: {}", productDailyInfoQuery);
        ResponseDTO<List<ProductDailyInfoResponseDTO>> productInfoResponse = priceInfoService.queryProductDailyInfo(productDailyInfoQuery);
        log.info("priceInfoService.queryProductDailyInfo response: {}", productInfoResponse);
        if (ResultCodeEnum.FAILURE.code == productInfoResponse.getResult()) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(productInfoResponse.getFailCode());
            responseDTO.setFailReason(productInfoResponse.getFailReason());
            return responseDTO;
        }
        if (CollectionUtils.isEmpty(productInfoResponse.getModel())) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("产品没找到价格");
            return responseDTO;
        }
        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(productInfoResponse.getModel().get(0));
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<SupplyProductPriceResponseDTO>> queryProductPrice(QueryProductPriceRequestDTO requestDTO) {
        log.info("queryProductPrice param: {}", requestDTO);
        requestDTO.setIsGroupRoom(requestDTO.getIsGroupRoom() == null ? 0 : requestDTO.getIsGroupRoom());
        ResponseDTO<List<SupplyProductPriceResponseDTO>> responseDTO = new ResponseDTO();
        // 1. 调用产品模块，查产品最新价格
        ResponseDTO<ProductDailyInfoResponseDTO> dailyInfoResponse = queryProductDailyInfo(requestDTO.getRatePlanId(),
                DateUtil.stringToDate(requestDTO.getCheckinDate()), DateUtil.stringToDate(requestDTO.getCheckoutDate()));
        if (dailyInfoResponse.getResult().equals(Constant.NO)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason(dailyInfoResponse.getFailReason());
            return responseDTO;
        }
        List<ProductDailyDTO> productDailyList = dailyInfoResponse.getModel().getProductDailyList();
        List<SupplyProductPriceResponseDTO> productPriceList = new ArrayList<>();
        for (ProductDailyDTO productDailyDTO : productDailyList) {
            String saleDate = DateUtil.dateToString(productDailyDTO.getSaleDate());
            if (StringUtils.equals(requestDTO.getCheckoutDate(), saleDate)) {
                continue;
            }
            SupplyProductPriceResponseDTO productPrice = new SupplyProductPriceResponseDTO();
            productPrice.setSaleDate(saleDate);
            productPrice.setRealQuota(requestDTO.getRoomNum());
            productPrice.setQuotaNum(productDailyDTO.getQuotaNum());
            SupplyProductPriceDTO priceDTO = orderCommonService.getSalePriceByChannelCode(requestDTO.getChannelCode(), productDailyDTO, requestDTO.getRoomNum(), requestDTO.getIsGroupRoom());
            productPrice.setBasePrice(priceDTO.getBasePrice());
            productPrice.setSalePrice(priceDTO.getSalePrice());
            productPriceList.add(productPrice);
        }

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(productPriceList);
        return responseDTO;
    }


    @Override
    public ResponseDTO<SupplyDetailResponseDTO> supplyDetail(SupplyDetailRequestDTO requestDTO) {
        log.info("supplyDetail param: {}", requestDTO);
        ResponseDTO<SupplyDetailResponseDTO> responseDTO = new ResponseDTO();
        // 1. 查供货单信息
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(requestDTO.getSupplyOrderId());
        SupplyDetailResponseDTO supplyDetailDTO = PropertyCopyUtil.transfer(supplyOrderDO, SupplyDetailResponseDTO.class);
        supplyDetailDTO.setSupplyOrderId(supplyOrderDO.getId());
        supplyDetailDTO.setBaseCurrency(supplyOrderDO.getBaseCurrency());
        supplyDetailDTO.setSupplySum(supplyOrderDO.getSupplySum());

        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.CANCELED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("无法查看发单详情，订单已取消！");
            return responseDTO;
        }
        // 调用酒店基础信息模块，查酒店地址和电话
        HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO = new HotelBaseInfoRequestDTO();
        hotelBaseInfoRequestDTO.setHotelId(supplyOrderDO.getHotelId().longValue());
        ResponseDTO<HotelBaseInfoRsponseDTO> hotelBaseInfoResponseDTO = hotelInfoService.queryHotelInfoByHotelId(hotelBaseInfoRequestDTO);
        HotelBaseInfoRsponseDTO hotelBaseInfo = hotelBaseInfoResponseDTO.getModel();
        supplyDetailDTO.setHotelName(orderDO.getHotelName());
        supplyDetailDTO.setHotelAddress(hotelBaseInfo.getHotelAddress());
        supplyDetailDTO.setHotelPhone(hotelBaseInfo.getPhone());
        supplyDetailDTO.setSpecialRequest(orderDO.getSpecialRequest());
        supplyDetailDTO.setGuestNames(orderDO.getGuestNames());
        // 2. 查产品
        List<ProductDetailResponseDTO> supplyProductList = new ArrayList<>();
        SupplyProductDO supplyProductQuery = new SupplyProductDO();
        supplyProductQuery.setSupplyOrderId(requestDTO.getSupplyOrderId());
        List<SupplyProductDO> supplyProductDOList = supplyProductMapper.select(supplyProductQuery);
        for (SupplyProductDO supplyProductDO : supplyProductDOList) {
            ProductDetailResponseDTO productDetail = new ProductDetailResponseDTO();
            productDetail.setRoomTypeName(supplyProductDO.getRoomTypeName());
            productDetail.setRateplanName(supplyProductDO.getRateplanName());
            // 3. 查产品对账明细
            OrderCheckDetailDO orderCheckDetailQuery = new OrderCheckDetailDO();
            orderCheckDetailQuery.setSupplyProductId(supplyProductDO.getId());
            List<OrderCheckDetailDO> checkDetailDOList = checkDetailMapper.select(orderCheckDetailQuery);
            List<OrderCheckDetailDTO> dateSegmentList = new ArrayList<>();
            for (OrderCheckDetailDO checkDetailDO : checkDetailDOList) {
                OrderCheckDetailDTO checkDetailDTO = PropertyCopyUtil.transfer(checkDetailDO, OrderCheckDetailDTO.class);
                checkDetailDTO.setCheckinDate(DateUtil.dateToString(checkDetailDO.getCheckinDate()));
                checkDetailDTO.setCheckoutDate(DateUtil.dateToString(checkDetailDO.getCheckoutDate()));
                Long days = DateUtil.getDay(checkDetailDO.getCheckinDate(), checkDetailDO.getCheckoutDate());
                checkDetailDTO.setDays(days.intValue());
                dateSegmentList.add(checkDetailDTO);

            }
            productDetail.setDateSegmentList(dateSegmentList);
            supplyProductList.add(productDetail);
        }
        supplyDetailDTO.setSupplyProductList(supplyProductList);
        // 4. 查附加项
        SupplyAdditionChargeDO additionChargeQuery = new SupplyAdditionChargeDO();
        additionChargeQuery.setSupplyOrderId(requestDTO.getSupplyOrderId());
        additionChargeQuery.setAdditionType(Byte.valueOf("1"));
        List<SupplyAdditionChargeDO> additionChargeDOList = additionChargeMapper.select(additionChargeQuery);
        List<SupplyAdditionChargeDTO> additionChargeDTOList = new ArrayList<>();
        for (SupplyAdditionChargeDO supplyAdditionChargeDO : additionChargeDOList) {
            SupplyAdditionChargeDTO additionChargeDTO = PropertyCopyUtil.transfer(supplyAdditionChargeDO, SupplyAdditionChargeDTO.class);
            additionChargeDTO.setSupplyAdditionChargeId(supplyAdditionChargeDO.getId());
            additionChargeDTOList.add(additionChargeDTO);
        }
        supplyDetailDTO.setSupplyAdditionChargeList(additionChargeDTOList);
        // 5. 查减免政策
        List<DeratePolicyResponseDTO> deratePolicyResponseDTOList = orderCommonService.queryDeratePolicyList(supplyOrderDO.getId());
        supplyDetailDTO.setDerateList(deratePolicyResponseDTOList);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(supplyDetailDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<SupplyOrderDTO>> querySupplyList(OrderDetailRequestDTO requestDTO) {
        log.info("querySupplyList param: {}", requestDTO);
        ResponseDTO<List<SupplyOrderDTO>> responseDTO = new ResponseDTO();
        // 1. 根据订单id查供货单列表
        SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
        supplyOrderQuery.setOrderId(requestDTO.getOrderId());
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.select(supplyOrderQuery);
        List<SupplyOrderDTO> supplyOrderDTOList = PropertyCopyUtil.transferArray(supplyOrderDOList, SupplyOrderDTO.class);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(supplyOrderDTOList);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO addDeratePolicy(AddDeratePolicyRequestDTO requestDTO) {
        log.info("addDeratePolicy param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 添加减免政策，  默认加入到第一个为非不确认的供货单下面
        Example example = new Example(SupplyOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", requestDTO.getOrderId());
        criteria.andNotEqualTo("supplyStatus", SupplyStatusEnum.UN_CONFIRM.key);
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(supplyOrderDOList)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("没有有效的供货单，不能添加减免政策");
            return responseDTO;
        }
        SupplyOrderDO supplyOrderDO = supplyOrderDOList.get(0);
        BigDecimal supplyBasePriceSum = BigDecimal.ZERO;
        BigDecimal supplySalePriceSum = BigDecimal.ZERO;
        StringBuilder additionNames = new StringBuilder();
        additionNames.append("添加减免政策，");
        for (int i = 0; i < requestDTO.getDeratePolicyList().size(); i++) {
            BigDecimal basePriceSum = BigDecimal.ZERO;
            BigDecimal salePriceSum = BigDecimal.ZERO;
            BigDecimal roomNumSum = BigDecimal.ZERO;
            DeratePolicyRequestDTO deratePolicyRequestDTO = requestDTO.getDeratePolicyList().get(i);
            if (i > 0) {
                additionNames.append(",");
            }
            additionNames.append("名称：" + deratePolicyRequestDTO.getName());
            for (DeratePolicyPriceDTO deratePolicyPriceDTO : deratePolicyRequestDTO.getDayList()) {
                deratePolicyPriceDTO.setSalePrice(deratePolicyPriceDTO.getSalePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getSalePrice());
                deratePolicyPriceDTO.setBasePrice(deratePolicyPriceDTO.getBasePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getBasePrice());
                deratePolicyPriceDTO.setRoomNum(deratePolicyPriceDTO.getRoomNum() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getRoomNum());
                deratePolicyPriceDTO.setSalePrice(deratePolicyPriceDTO.getSalePrice().negate());
                deratePolicyPriceDTO.setBasePrice(deratePolicyPriceDTO.getBasePrice().negate());
                additionNames.append("，日期：" + deratePolicyPriceDTO.getSaleDate() + "，售价：" + deratePolicyPriceDTO.getSalePrice()
                        + "，底价：" + deratePolicyPriceDTO.getBasePrice() + "，减免间数：" + deratePolicyPriceDTO.getRoomNum() + "；");

                basePriceSum = basePriceSum.add(deratePolicyPriceDTO.getBasePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                salePriceSum = salePriceSum.add(deratePolicyPriceDTO.getSalePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                roomNumSum = roomNumSum.add(deratePolicyPriceDTO.getRoomNum());
                supplyBasePriceSum = supplyBasePriceSum.add(deratePolicyPriceDTO.getBasePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                supplySalePriceSum = supplySalePriceSum.add(deratePolicyPriceDTO.getSalePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
            }
            // 1.1插入减免政策表
            DeratePolicyDO deratePolicyInsert = new DeratePolicyDO();
            deratePolicyInsert.setOrderId(requestDTO.getOrderId());
            deratePolicyInsert.setSupplyOrderId(supplyOrderDO.getId());
            deratePolicyInsert.setName(deratePolicyRequestDTO.getName());
            deratePolicyInsert.setSalePriceSum(salePriceSum);
            deratePolicyInsert.setBasePriceSum(basePriceSum);
            deratePolicyInsert.setRoomNumSum(roomNumSum);
            deratePolicyInsert.setCreator(requestDTO.getCreator());
            deratePolicyInsert.setCreateTime(requestDTO.getCreateTime());
            deratePolicyMapper.insert(deratePolicyInsert);
            int deratePolicyId = deratePolicyInsert.getId();

            // 1.2插入减免政策价格明细表
            List<DeratePolicyPriceDO> deratePolicyPriceDOList = PropertyCopyUtil.transferArray(deratePolicyRequestDTO.getDayList(), DeratePolicyPriceDO.class);
            for (DeratePolicyPriceDO deratePolicyPriceDO : deratePolicyPriceDOList) {
                deratePolicyPriceDO.setDeratePolicyId(deratePolicyId);
                deratePolicyPriceDO.setCreator(requestDTO.getCreator());
                deratePolicyPriceDO.setCreateTime(requestDTO.getCreateTime());
            }
            deratePolicyPriceMapper.insertList(deratePolicyPriceDOList);
        }
        // 2. 更新供货单表总金额
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(supplyOrderDO.getId());
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().add(supplyBasePriceSum));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().add(supplySalePriceSum));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 3. 供货单金额发生变化，处理供货单财务信息
        handleSupplyFinanceInfo(requestDTO.getCreator(), orderDO.getMerchantCode(), supplyOrderDO, supplyOrderUpdate);
        // 4. 更新订单表总金额
        orderDO.setOrderSum(orderDO.getOrderSum().add(supplySalePriceSum));
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(orderDO.getId());
        orderUpdate.setOrderSum(orderDO.getOrderSum());
        orderUpdate.setReceivableAmount(orderDO.getOrderSum());
        orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderUpdate.getReceivableAmount(), null));
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 5. 订单金额发生变化，处理订单财务信息
        handleOrderFinanceInfo(requestDTO.getCreator(), orderDO, orderUpdate);
        // 6. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent(additionNames.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO changeDeratePolicy(ChangeDeratePolicyRequestDTO requestDTO) {
        log.info("changeDeratePolicy param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新减免政策价格明细表(先删后加)
        DeratePolicyDO deratePolicyDO = deratePolicyMapper.selectByPrimaryKey(requestDTO.getDeratePolicyId());
        DeratePolicyPriceDO deratePolicyPriceDelete = new DeratePolicyPriceDO();
        deratePolicyPriceDelete.setDeratePolicyId(requestDTO.getDeratePolicyId());
        deratePolicyPriceMapper.delete(deratePolicyPriceDelete);

        List<DeratePolicyPriceDO> deratePolicyPriceDOList = PropertyCopyUtil.transferArray(requestDTO.getDayList(), DeratePolicyPriceDO.class);
        BigDecimal basePriceSum = BigDecimal.ZERO;
        BigDecimal salePriceSum = BigDecimal.ZERO;
        BigDecimal roomNumSum = BigDecimal.ZERO;
        StringBuilder additionNames = new StringBuilder();
        additionNames.append("修改减免政策，名称：" + deratePolicyDO.getName());
        for (DeratePolicyPriceDO deratePolicyPriceDO : deratePolicyPriceDOList) {
            deratePolicyPriceDO.setSalePrice(deratePolicyPriceDO.getSalePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDO.getSalePrice());
            deratePolicyPriceDO.setBasePrice(deratePolicyPriceDO.getBasePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDO.getBasePrice());
            deratePolicyPriceDO.setRoomNum(deratePolicyPriceDO.getRoomNum() == null ? BigDecimal.ZERO : deratePolicyPriceDO.getRoomNum());
            deratePolicyPriceDO.setBasePrice(deratePolicyPriceDO.getBasePrice().negate());
            deratePolicyPriceDO.setSalePrice(deratePolicyPriceDO.getSalePrice().negate());

            additionNames.append("，日期：" + DateUtil.dateToString(deratePolicyPriceDO.getSaleDate()) + "，售价：" + deratePolicyPriceDO.getSalePrice()
                    + "，底价：" + deratePolicyPriceDO.getBasePrice() + "，减免间数：" + deratePolicyPriceDO.getRoomNum() + "；");
            deratePolicyPriceDO.setDeratePolicyId(requestDTO.getDeratePolicyId());
            deratePolicyPriceDO.setCreator(requestDTO.getModifier());
            deratePolicyPriceDO.setCreateTime(requestDTO.getModifyTime());
            basePriceSum = basePriceSum.add(deratePolicyPriceDO.getBasePrice().multiply(deratePolicyPriceDO.getRoomNum()));
            salePriceSum = salePriceSum.add(deratePolicyPriceDO.getSalePrice().multiply(deratePolicyPriceDO.getRoomNum()));
            roomNumSum = roomNumSum.add(deratePolicyPriceDO.getRoomNum());
        }
        deratePolicyPriceMapper.insertList(deratePolicyPriceDOList);
        // 2. 更新减免政策总金额
        DeratePolicyDO deratePolicyUpdate = new DeratePolicyDO();
        deratePolicyUpdate.setId(requestDTO.getDeratePolicyId());
        deratePolicyUpdate.setBasePriceSum(basePriceSum);
        deratePolicyUpdate.setSalePriceSum(salePriceSum);
        deratePolicyUpdate.setRoomNumSum(roomNumSum);
        deratePolicyMapper.updateByPrimaryKeySelective(deratePolicyUpdate);
        // 3. 更新供货单表总金额
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(deratePolicyDO.getSupplyOrderId());
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(deratePolicyDO.getSupplyOrderId());
        // 供货单总额 = 供货单原总额 - 修改附加项前原总额 + 修改附加项后总额
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().subtract(deratePolicyDO.getBasePriceSum()).add(basePriceSum));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().subtract(deratePolicyDO.getSalePriceSum()).add(salePriceSum));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 4. 供货单金额发生变化，处理供货单财务信息
        handleSupplyFinanceInfo(requestDTO.getCreator(), orderDO.getMerchantCode(), supplyOrderDO, supplyOrderUpdate);
        // 5. 更新订单表总金额
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(supplyOrderDO.getOrderId());
        // 订单总额 = 订单原总额 - 修改前供货单总额 + 修改后供货单总额
        orderUpdate.setOrderSum(orderDO.getOrderSum().subtract(supplyOrderDO.getSalePriceSum()).add(supplyOrderUpdate.getSalePriceSum()));
        orderUpdate.setReceivableAmount(orderUpdate.getOrderSum());
        orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderUpdate.getReceivableAmount(), null));
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 6. 订单金额发生变化，处理订单财务信息
        handleOrderFinanceInfo(requestDTO.getCreator(), orderDO, orderUpdate);
        // 7. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent(additionNames.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO deleteDeratePolicy(DeleteDeratePolicyRequestDTO requestDTO) {
        log.info("deleteDeratePolicy param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        DeratePolicyDO deratePolicyDO = deratePolicyMapper.selectByPrimaryKey(requestDTO.getDeratePolicyId());
        // 1. 删除减免政策价格明细
        DeratePolicyPriceDO deratePolicyPriceDelete = new DeratePolicyPriceDO();
        deratePolicyPriceDelete.setDeratePolicyId(requestDTO.getDeratePolicyId());
        deratePolicyPriceMapper.delete(deratePolicyPriceDelete);
        // 2. 删除减免政策明细
        deratePolicyMapper.deleteByPrimaryKey(requestDTO.getDeratePolicyId());
        // 3. 更新供货单表总金额
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(deratePolicyDO.getSupplyOrderId());
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
        supplyOrderUpdate.setId(deratePolicyDO.getSupplyOrderId());
        supplyOrderUpdate.setSupplySum(supplyOrderDO.getSupplySum().subtract(deratePolicyDO.getBasePriceSum()));
        supplyOrderUpdate.setPayableAmount(supplyOrderUpdate.getSupplySum());
        supplyOrderUpdate.setSalePriceSum(supplyOrderDO.getSalePriceSum().subtract(deratePolicyDO.getSalePriceSum()));
        supplyOrderUpdate.setModifier(requestDTO.getModifier());
        supplyOrderUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        // 4. 供货单金额发生变化，处理供货单财务信息
        handleSupplyFinanceInfo(requestDTO.getCreator(), orderDO.getMerchantCode(), supplyOrderDO, supplyOrderUpdate);
        // 5. 更新订单表总金额
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(orderDO.getId());
        BigDecimal orderSum = orderDO.getOrderSum().subtract(deratePolicyDO.getSalePriceSum());
        orderUpdate.setOrderSum(orderSum);
        orderUpdate.setReceivableAmount(orderSum);
        orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderUpdate.getReceivableAmount(), null));
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 6. 订单金额发生变化，处理订单财务信息
        handleOrderFinanceInfo(requestDTO.getCreator(), orderDO, orderUpdate);
        // 7. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderDO.getId());
        logDO.setContent("删除减免政策（" + deratePolicyDO.getName() + "）成功");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    /**
     * 订单金额发生变化，处理订单财务信息
     *
     * @param operator
     * @param orderDO
     * @param orderUpdate
     */
    private void handleOrderFinanceInfo(String operator, OrderDO orderDO, OrderDO orderUpdate) {
        if (orderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key) {
            // 1. 更新单结订单财务信息：支付状态、结算状态
            orderCommonService.updateOrderFinanceInfo(orderDO.getId(), orderUpdate.getReceivableAmount(), null, null);
        } else {
            // 2. 非单结、已确认订单自动挂账
            if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.TRADED.key) {
                OrderCreditPayOrRefundRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(orderDO, OrderCreditPayOrRefundRequestDTO.class);
                creditRequestDTO.setReceivableAmount(orderUpdate.getReceivableAmount());
                creditRequestDTO.setPayAmount(orderDO.getReceivableAmount());
                creditRequestDTO.setOperateType(3);
                creditRequestDTO.setOperator(operator);
                creditRequestDTO.setOrderId(orderDO.getId());
                orderCommonService.orderCreditPayOrRefund(creditRequestDTO);
            }
        }
    }

    /**
     * 供货单金额发生变化，处理供货单财务信息
     *
     * @param operator
     * @param merchantCode
     * @param supplyOrderDO
     * @param supplyOrderUpdate
     */
    private void handleSupplyFinanceInfo(String operator, String merchantCode, SupplyOrderDO supplyOrderDO, SupplyOrderDO supplyOrderUpdate) {
        if (supplyOrderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key) {
            // 1. 更新单结供货单财务信息：支付状态、结算状态
            orderCommonService.updateSupplyFinanceInfo(supplyOrderDO.getId(), supplyOrderUpdate.getPayableAmount(), null, null);
        } else {
            // 2. 非单结、已确认供货单，挂账或退挂账
            if (supplyOrderDO.getSupplyStatus().intValue() == SupplyStatusEnum.CONFIRMED.key) {
                SupplyCreditPayOrReceiptRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(supplyOrderDO, SupplyCreditPayOrReceiptRequestDTO.class);
                creditRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
                creditRequestDTO.setPayableAmount(supplyOrderUpdate.getPayableAmount());
                creditRequestDTO.setPayAmount(supplyOrderDO.getPayableAmount());
                creditRequestDTO.setOperateType(3);
                creditRequestDTO.setOperator(operator);
                creditRequestDTO.setMerchantCode(merchantCode);
                orderCommonService.supplyCreditPayOrRefund(creditRequestDTO);
            }
        }
    }

    @Async
    private void sendTemplateMessageToSupply(SendToSupplierRequestDTO requestDTO){
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(requestDTO.getSupplyOrderId());
        OrderDO orderDO=orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        QueryMerchantDTO queryMerchantDTO=new QueryMerchantDTO();
        queryMerchantDTO.setMerchantCode(orderDO.getMerchantCode());
        MerchantDTO merchantDTO=merchantMapper.queryMerchant(queryMerchantDTO);
        String url="http://"+merchantDTO.getEbkDomain()+"/ebk-mobile/#/orderDetail?supplyOrderCode="+supplyOrderDO.getSupplyOrderCode();

        QuerySupplyUserBindDTO querySupplyUserBindDTO=new QuerySupplyUserBindDTO();
        querySupplyUserBindDTO.setMerchantCode(orderDO.getMerchantCode());
        querySupplyUserBindDTO.setSupplyCode(supplyOrderDO.getSupplyCode());
        List<SupplyUserBindDO> supplyUserBindDOList=supplyService.querySupplyUserBind(querySupplyUserBindDTO);
        for (SupplyUserBindDO supplyUserBindDO:supplyUserBindDOList){
            OrderProcessTemplateMsgRequestDTO templateMsgInfo=new OrderProcessTemplateMsgRequestDTO();
            templateMsgInfo.setClientAppName("bms");
            templateMsgInfo.setMerchantCode(orderDO.getMerchantCode());
            templateMsgInfo.setToUser(supplyUserBindDO.getOpenId());
            templateMsgInfo.setUrl(url);//订单详情页面请求路径
            templateMsgInfo.setOrderCode(supplyOrderDO.getSupplyOrderCode());
            templateMsgInfo.setGuest(orderDO.getGuestNames());

            Object[] keyWord2Array = new Object[3];
            keyWord2Array[0] = DateUtil.dateToString(supplyOrderDO.getCheckinDate());
            keyWord2Array[1] = supplyOrderDO.getRoomNum();
            keyWord2Array[2] = DateUtil.getDay(supplyOrderDO.getCheckinDate(),supplyOrderDO.getCheckoutDate());
            String checkInDateStr = String.format("%s %s间%s晚", keyWord2Array);
            templateMsgInfo.setCheckInDate(checkInDateStr);

            Object[] firstArray = new Object[5];
            firstArray[0] = merchantDTO.getMerchantName();
            firstArray[1] = supplyOrderDO.getHotelName();
            firstArray[2] = supplyOrderDO.getRoomTypeName();
            firstArray[3] = DateUtil.getDay(supplyOrderDO.getCheckinDate(),supplyOrderDO.getCheckoutDate());
            switch (requestDTO.getSupplyType()){
                case 1:firstArray[4]="预订单";break;
                case 2:firstArray[4]="预订单";break;
                case 3:firstArray[4]="修改单";break;
                case 4:firstArray[4]="取消单";
            }
            String title = String.format("商家(%s)提交了一张%s-%s-连住%s晚的%s",firstArray);
            templateMsgInfo.setTitle(title);
            templateMsgInfo.setRemark("请及时处理订单，谢谢！");
            try{
                wxTemplateMsgRemote.sendTemplateMessage(templateMsgInfo);
            }catch(Exception e){
                log.error("发送模板消息失败",e);
            }
        }
    }

}
