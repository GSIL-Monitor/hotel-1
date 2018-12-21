package com.fangcang.order;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.order.dto.OrderDTO;
import com.fangcang.order.request.ChangeAgentRequestDTO;
import com.fangcang.order.request.ChangeGuestRequestDTO;
import com.fangcang.order.request.ChangeSpecialReqeustDTO;
import com.fangcang.order.request.CreateOrderRequestDTO;
import com.fangcang.order.request.HandleOrderReqRequestDTO;
import com.fangcang.order.request.NotifyAgentRequestDTO;
import com.fangcang.order.request.OrderCancelRequestDTO;
import com.fangcang.order.request.OrderConfirmRequestDTO;
import com.fangcang.order.request.OrderListRequestDTO;
import com.fangcang.order.response.OrderCountResponseDTO;
import com.fangcang.order.response.QueryChannelListResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@RequestMapping("/test/order")
public class TestOrderController {

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<OrderDTO>> orderList(@RequestBody OrderListRequestDTO requestDTO) {
        List<OrderDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(1000 + i);
            orderDTO.setOrderCode("H" + orderDTO.getOrderId());
            orderDTO.setCreateTime("2018-05-21");
            orderDTO.setHotelId(2000 + i);
            orderDTO.setHotelName("深圳国际小酒店");
            orderDTO.setRoomTypeNames("标准房，大床房");
            orderDTO.setGuestNames("张三，李四，王五");
            orderDTO.setCheckinDate("2018-5-28");
            orderDTO.setCheckoutDate("2018-5-29");
            orderDTO.setRoomNum(1);
            orderDTO.setOrderSum(new BigDecimal(699));
            orderDTO.setPayStatus(1);
            orderDTO.setOrderStatus(i == 0 ? 1 : i);
            orderDTO.setBelongName("小明");
            orderDTO.setChannelName("B2B");
            orderDTO.setLockUser("18600000000");
            orderDTO.setLockName("小明");
            orderDTO.setIsGroupRoom(1);
            orderDTO.setConfirmNo("123456，78945");
            list.add(orderDTO);
        }

        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(5);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setCurrentPage(requestDTO.getCurrentPage());
        paginationSupportDTO.setPageSize(requestDTO.getPageSize());
        paginationSupportDTO.setItemList(list);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO orderConfirm(@RequestBody @Valid OrderConfirmRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO orderConfirm(@RequestBody @Valid OrderCancelRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/changeAgent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeAgent(@RequestBody @Valid ChangeAgentRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeGuest(@RequestBody @Valid ChangeGuestRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/changeSpecialRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeSpecialReqeust(@RequestBody @Valid ChangeSpecialReqeustDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO create(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/addManualOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addManualOrder(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/handleCancelRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO handleCancelRequest(@RequestBody @Valid HandleOrderReqRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }


    @RequestMapping(value = "/queryChannelList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO supplyPayOrRefund() {
        List<QueryChannelListResponseDTO> channelList = new ArrayList<>();
        ChannelTypeEnum[] values = ChannelTypeEnum.values();
        for (int i = 0; i < values.length; i++) {
            ChannelTypeEnum value = values[i];
            if (value.key.equals("B2B")) {
                continue;
            }
            QueryChannelListResponseDTO channelListResponseDTO = new QueryChannelListResponseDTO();
            channelListResponseDTO.setChannelCode(value.key);
            channelListResponseDTO.setChannelName(value.value);
            channelList.add(channelListResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(channelList);
        return responseDTO;
    }

    @RequestMapping(value = "/notifyAgent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifyAgent(@RequestBody @Valid NotifyAgentRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/counts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO counts() {
        List<OrderCountResponseDTO> list = new ArrayList<>();

        OrderCountResponseDTO count = new OrderCountResponseDTO();
        count.setQuickQueryType(1);
        count.setCounts(19);
        list.add(count);

        OrderCountResponseDTO count2 = new OrderCountResponseDTO();
        count2.setQuickQueryType(2);
        count2.setCounts(99);
        list.add(count2);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(list);
        return responseDTO;
    }

}
