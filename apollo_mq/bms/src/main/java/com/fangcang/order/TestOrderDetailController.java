package com.fangcang.order;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.QuotaTypeEnum;
import com.fangcang.common.enums.order.AttachTypeEnum;
import com.fangcang.common.enums.order.NoteTypeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.PayMethodEnum;
import com.fangcang.order.dto.OrderAttachDTO;
import com.fangcang.order.dto.OrderNoteDTO;
import com.fangcang.order.dto.OrderOperationLogDTO;
import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.response.OrderAttachGroupResponseDTO;
import com.fangcang.order.response.OrderDetailResponseDTO;
import com.fangcang.order.response.OrderNoteGroupResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.response.SupplyProductResponseDTO;
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
@RequestMapping("/test/order/detail")
public class TestOrderDetailController extends BaseController {

    @RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<OrderDetailResponseDTO> orderInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        //订单
        OrderDetailResponseDTO orderDetail = new OrderDetailResponseDTO();
        orderDetail.setOrderId(requestDTO.getOrderId());
        orderDetail.setOrderCode("H" + orderDetail.getOrderId());
        orderDetail.setCreator("张三");
        orderDetail.setCreateTime("2018-05-21");
        orderDetail.setHotelId(2000);
        orderDetail.setHotelName("深圳国际小酒店");
        orderDetail.setRoomTypeNames("标准房，大床房");
        orderDetail.setGuestNames("张三，李四，王五");
        orderDetail.setCheckinDate("2018-5-28");
        orderDetail.setCheckoutDate("2018-5-29");
        orderDetail.setRoomNum(1);
        orderDetail.setOrderSum(new BigDecimal(699));
        orderDetail.setPayStatus(1);
        orderDetail.setOrderStatus(OrderStatusEnum.NEWORDER.key);
        orderDetail.setBelongName("小明");
        orderDetail.setChannelName("B2B");
        orderDetail.setChannelCode("B2B");
        orderDetail.setLockUser("18600000000");
        orderDetail.setLockName("小明");
        orderDetail.setIsGroupRoom(0);
        orderDetail.setConfirmNo("123456，78945");
        orderDetail.setPayMethod(PayMethodEnum.PRE_PAY.key);
        orderDetail.setSaleCurrency("CNY");
        orderDetail.setIsManualOrder(0);
        orderDetail.setBalanceMethod(BalanceMethodEnum.MONTH.key);
        orderDetail.setReceivableAmount(new BigDecimal(699));
        orderDetail.setAgentCode("F000000");
        orderDetail.setAgentName("月结分销商");
        orderDetail.setSpecialRequest("无烟处理");
        orderDetail.setContractName("张三");
        orderDetail.setContractPhone("18600000000");
        orderDetail.setCustomerOrderCode("123123123");
        orderDetail.setMerchantBm("小王");
        orderDetail.setMerchantPm("小明");

        ResponseDTO<OrderDetailResponseDTO> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderDetail);
        return responseDTO;
    }

    @RequestMapping(value = "/productInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<SupplyOrderResponseDTO>> productInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        //供货单
        List<SupplyOrderResponseDTO> supplyOrderList = new ArrayList<>();
        SupplyOrderResponseDTO supplyOrder = new SupplyOrderResponseDTO();
        supplyOrder.setSupplyOrderId(1000);
        supplyOrder.setSupplyCode("S" + 1000);
        supplyOrder.setSupplySum(new BigDecimal(500));
        supplyOrder.setBaseCurrency("CNY");
        supplyOrder.setSupplyCode("S000000");
        supplyOrder.setSupplyName("月结供应商");
        supplyOrder.setSupplyStatus(1);
        supplyOrder.setPayStatus(1);
        supplyOrder.setConfirmNo("123456");
        supplyOrder.setBalanceMethod(1);
        //供货产品
        List<SupplyProductResponseDTO> supplyProductList = new ArrayList<>();
        SupplyProductResponseDTO supplyProductDTO = new SupplyProductResponseDTO();
        supplyProductDTO.setSupplyProductId(1000);
        supplyProductDTO.setRoomTypeId(1000);
        supplyProductDTO.setRoomTypeName("大床房");
        supplyProductDTO.setRateplanId(1000);
        supplyProductDTO.setRateplanName("优惠早餐");
        supplyProductDTO.setQuotaType(QuotaTypeEnum.QUOTA_ROOM.key);
        supplyProductDTO.setBedtype("1");
        supplyProductDTO.setBreakfastType(1);
        supplyProductDTO.setCheckinDate("2018-5-28");
        supplyProductDTO.setCheckoutDate("2018-5-29");
        supplyProductDTO.setRoomNum(1);
        supplyProductDTO.setSalePriceSum(new BigDecimal(699));
        supplyProductDTO.setBasePriceSum(new BigDecimal(500));
        supplyProductList.add(supplyProductDTO);
        supplyOrder.setSupplyProductList(supplyProductList);
        //供货附加费
        List<SupplyAdditionChargeDTO> supplyAdditionChargeList = new ArrayList<>();
        SupplyAdditionChargeDTO additionChargeDTO = new SupplyAdditionChargeDTO();
        additionChargeDTO.setSupplyAdditionChargeId(1000);
        additionChargeDTO.setName("加床");
        additionChargeDTO.setBasePriceSum(new BigDecimal(100));
        additionChargeDTO.setSalePriceSum(new BigDecimal(100));
        additionChargeDTO.setNum(1);
        supplyAdditionChargeList.add(additionChargeDTO);
        supplyOrder.setSupplyAdditionChargeList(supplyAdditionChargeList);
        supplyOrderList.add(supplyOrder);

        ResponseDTO<List<SupplyOrderResponseDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(supplyOrderList);
        return responseDTO;
    }

    @RequestMapping(value = "/attachInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderAttachGroupResponseDTO>> attachInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        //附件
        List<OrderAttachGroupResponseDTO> orderAttachGroupList = new ArrayList<>();

        OrderAttachGroupResponseDTO orderAttachGroup = new OrderAttachGroupResponseDTO();
        List<OrderAttachDTO> attachList = new ArrayList<>();
        OrderAttachDTO attachDTO = new OrderAttachDTO();
        attachDTO.setName("入住人名单");
        attachDTO.setUrl("http://xxxxxx");
        attachDTO.setOrderAttachId(1000);
        attachList.add(attachDTO);
        orderAttachGroup.setType(AttachTypeEnum.CHECKIN_LIST.key);
        orderAttachGroup.setOrderAttachList(attachList);
        orderAttachGroupList.add(orderAttachGroup);

        ResponseDTO<List<OrderAttachGroupResponseDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderAttachGroupList);
        return responseDTO;
    }

    @RequestMapping(value = "/noteInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderNoteGroupResponseDTO>> noteInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        //备注
        List<OrderNoteGroupResponseDTO> orderNoteGroups = new ArrayList<>();
        OrderNoteGroupResponseDTO noteGroup = new OrderNoteGroupResponseDTO();
        noteGroup.setNoteType(NoteTypeEnum.AGENT_NOTE.key);

        List<OrderNoteDTO> noteList = new ArrayList<>();
        OrderNoteDTO note = new OrderNoteDTO();
        note.setNote("此单已确认，保证入住全段，不可更改或取消，NO SHOW房费照付，谢谢。");
        note.setCreator("李雷");
        note.setCreateTime("2018-05-21 15:00:00");
        note.setNote("月结分销商");
        noteList.add(note);
        noteGroup.setOrderNoteList(noteList);
        orderNoteGroups.add(noteGroup);

        ResponseDTO<List<OrderNoteGroupResponseDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderNoteGroups);
        return responseDTO;
    }

    @RequestMapping(value = "/logInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderOperationLogDTO>> logInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {

        //操作日志
        List<OrderOperationLogDTO> orderOperationLogList = new ArrayList<>();
        OrderOperationLogDTO logDTO = new OrderOperationLogDTO();
        logDTO.setContent("修改入住人：由张三改为李四");
        logDTO.setOperator("小明");
        logDTO.setOperateTime("2018-05-21 18:30:00");
        logDTO.setOperateObject("H1000000");
        orderOperationLogList.add(logDTO);

        ResponseDTO<List<OrderOperationLogDTO>> responseDTO = new ResponseDTO<>(1);
        responseDTO.setModel(orderOperationLogList);
        return responseDTO;

    }

}
