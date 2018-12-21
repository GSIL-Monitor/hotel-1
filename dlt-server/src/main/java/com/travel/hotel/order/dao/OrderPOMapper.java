package com.travel.hotel.order.dao;

import com.travel.common.dto.finance.query.OrderLockQueryDTO;
import com.travel.common.dto.order.request.QueryOrderRequestDTO;
import com.travel.common.dto.order.request.SearchRoomQueryDTO;
import com.travel.hotel.order.entity.HotelOrderCount;
import com.travel.hotel.order.entity.OrderInfo;
import com.travel.hotel.order.entity.po.OrderPO;
import com.travel.hotel.order.entity.po.OrderPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderPOMapper {
    int countByExample(OrderPOExample example);

    int deleteByExample(OrderPOExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    List<OrderPO> selectByExample(OrderPOExample example);

    OrderPO selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") OrderPO record, @Param("example") OrderPOExample example);

    int updateByExample(@Param("record") OrderPO record, @Param("example") OrderPOExample example);

    OrderPO selectByOrderCode(String orderCode);

    OrderPO selectOrderDetailByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);
    
    /**
     * 查询订单锁单集合
     * @param orderLockQuery
     * @return
     */
    List<OrderInfo> queryOrderLockList(OrderLockQueryDTO orderLockQuery);
    
    /**
     * 查询订单集合
     * @param queryOrderRequest
     * @return
     */
    List<OrderInfo> queryOrderInfoList(QueryOrderRequestDTO queryOrderRequest);

    /**
     * 追加订单备注
     * @param order
     */
    void appendRemark(OrderPO order);
    
    /**
     * 订单批量锁单
     * @param orderList
     */
    void batchLockOrder(List<OrderPO> orderList);
    
    /**
     * 查询酒店当天订单量
     * @param searchRoomQuery
     * @return
     */
    List<HotelOrderCount> queryOrderCountByHotel(SearchRoomQueryDTO searchRoomQuery);
}