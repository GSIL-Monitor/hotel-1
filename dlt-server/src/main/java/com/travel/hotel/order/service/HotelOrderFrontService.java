package com.travel.hotel.order.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.finance.query.OrderLockQueryDTO;
import com.travel.common.dto.order.OrderDTO;
import com.travel.common.dto.order.OrderDayPriceDTO;
import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.dto.order.OrderRestrictDTO;
import com.travel.common.dto.order.request.OrderConfirmDTO;
import com.travel.common.dto.order.request.QueryOrderRequestDTO;
import com.travel.common.dto.order.request.SearchRoomQueryDTO;
import com.travel.common.dto.order.response.OrderInfoResponseDTO;
import com.travel.hotel.order.entity.HotelOrderCount;
import com.travel.hotel.order.entity.po.OrderQuotaLogPO;
import com.travel.hotel.order.entity.po.UsedQuotaPO;

import java.util.List;

/**
 * 订单前台页面服务类
 * @author  2018/1/10
 */
public interface HotelOrderFrontService {

    ResultDTO<List<OrderDTO>> queryOrderList(QueryOrderRequestDTO queryOrderRequestDTO);

    ResultDTO<OrderDTO> queryOrderDetail(QueryOrderRequestDTO queryOrderRequestDTO);

    ResultDTO<List<OrderProductDTO>> queryOrderProductList(String orderCode);

    ResultDTO<List<OrderDayPriceDTO>> queryOrderDayPriceList(String orderCode, Integer productId);

    ResultDTO<List<OrderRestrictDTO>> queryOrderRestrictList(String orderCode);
    
    /**
     * 订单锁单分页查询
     * @param orderLockQuery
     * @return
     */
    PaginationDTO<OrderInfoResponseDTO> listOrderLockForPage(OrderLockQueryDTO orderLockQuery);
    
    /**
     * 订单信息分页查询
     * @param queryOrderRequest
     * @return
     */
    PaginationDTO<OrderInfoResponseDTO> listOrderInfoForPage(QueryOrderRequestDTO queryOrderRequest);


    /**
     * 设置订单锁单人
     * @param order
     */
    void updateOrderLock(OrderDTO order);
    
    /**
     * 修改订单
     * @param order
     */
    void updateOrder(OrderDTO order);
    
    /**
     * 订单确认客人
     * @param orderConfirm
     */
    void orderConfirm(OrderConfirmDTO orderConfirm);
    
    /**
     * 订单确认发送邮件
     * @param orderConfirm
     */
    void orderConfirmEmailToAgent(OrderConfirmDTO orderConfirm);
    
    /**
     * 发单给供应商
     * @param orderConfirm
     * @param signetPath
     */
    void sendOrderEmailToSupply(OrderConfirmDTO orderConfirm);

    /**
     * 查询占用配额的订单
     * @param orderQuotaLogPO
     * @return
     */
    List<UsedQuotaPO>  queryUsedQuotaOrderList(OrderQuotaLogPO orderQuotaLogPO);
    
    /**
     * 分销商确认订单预览
     * @param orderConfirm
     * @return
     */
    String orderPreview(OrderConfirmDTO orderConfirm);
    
    /**
     * 供应商确认订单预览
     * @param orderConfirm
     * @param signetPath
     * @return
     */
    String supplyOrderPreview(OrderConfirmDTO orderConfirm);
    
    /**
     * 查询酒店当天订单数量
     * @param searchRoomQuery
     * @return
     */
    PaginationDTO<HotelOrderCount> listHotelOrderCountForPage(SearchRoomQueryDTO searchRoomQuery);
}
