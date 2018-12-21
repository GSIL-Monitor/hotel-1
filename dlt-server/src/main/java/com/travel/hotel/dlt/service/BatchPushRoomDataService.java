package com.travel.hotel.dlt.service;

import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.common.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 *   2018/4/9.
 */
public interface BatchPushRoomDataService {

    void pushRoomDataToDltByPricePlan(Long pricePlanId, Date checkInDate, Date checkOutDate) throws ServiceException;

    void pushRoomDataToDltByPricePlanList(List<Long> pricePlanIdList, Date checkInDate, Date checkOutDate) throws ServiceException;

    void pushRoomDataToDltByMapRoomList(List<DltMapRoomPO> dltMapRoomPOList, Date checkInDate, Date checkOutDate,String merchantCode) throws ServiceException;

    void setRoomSaleRule(List<DltMapRoomPO> dltMapRoomPOList,String merchantCode) throws ServiceException;
}
