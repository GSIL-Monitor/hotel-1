package com.travel.hotel.dlt.servlet;

import com.alibaba.fastjson.JSON;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.enums.OrderStateEnum;
import com.travel.hotel.BaseTest;
import com.travel.channel.dto.request.HotelOrderOperateRequestDTO;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 *   2018/5/16.
 */
public class DltOrderOperateServletTest extends BaseTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void orderOperate() {
        HotelOrderOperateRequestDTO dto = new HotelOrderOperateRequestDTO();
        dto.setChannelCode(ChannelEnum.CTRIP.key);
        dto.setOrderCode("ZH1805160000");
        dto.setCustomerOrderCode("129147TC");
        dto.setOrderState(OrderStateEnum.PROCESSING.code);
        dto.setOperateType("ctrip_refuse");
        dto.setConfirmType(1);
        dto.setConfirmNo(null);
        dto.setRefuseType(1);
        dto.setRefuseReason("FULL_ROOM");
        dto.setRefundAmount(new BigDecimal("1000.0"));
        dto.setSignature("E2394NDF923RWENWEUR238DFNWEU");
        String json = JSON.toJSONString(dto, true);
        System.out.println(json);
    }

}