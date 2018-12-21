//package com.travel.hotel.order.service.impl;
//
//import com.travel.common.dto.order.request.CreateOrderRequestDTO;
//import com.travel.common.dto.order.response.CreateOrderResponseDTO;
//import com.travel.common.utils.GenericValidate;
//import com.travel.common.validate.CreateB2BOrderValidate;
//import com.travel.common.validate.CreateOrderValidate;
//import com.travel.hotel.order.service.HotelOrderService;
//import com.travel.hotel.order.service.abstracts.AbstractHotelOrderService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
///**
// * B2B订单后台服务类
// * @author  2018/1/10
// */
//@Service
//public class HotelB2BOrderServiceImpl extends AbstractHotelOrderService implements HotelOrderService {
//
//    private static final Logger LOG = LoggerFactory.getLogger(HotelB2BOrderServiceImpl.class);
//
//    @Override
//    protected String requestParameterValidate(CreateOrderRequestDTO requestDTO) {
//        return GenericValidate.validate(requestDTO, new Class[]{CreateOrderValidate.class, CreateB2BOrderValidate.class});
//    }
//
//    @Override
//    protected void checkDuplicateOrder(CreateOrderRequestDTO requestDTO, CreateOrderResponseDTO responseDTO) {
//        return null;
//    }
//}
