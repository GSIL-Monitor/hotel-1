package com.fangcang.order;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.order.dto.OrderDTO;
import com.fangcang.order.dto.OrderRequestDTO;
import com.fangcang.order.request.AddAbatementOrderDTO;
import com.fangcang.order.request.AddAttachRequestDTO;
import com.fangcang.order.request.AddNoteRequestDTO;
import com.fangcang.order.request.ChangeAgentRequestDTO;
import com.fangcang.order.request.ChangeBalanceMethodRequestDTO;
import com.fangcang.order.request.ChangeChannelOrderCodeRequestDTO;
import com.fangcang.order.request.ChangeGuestRequestDTO;
import com.fangcang.order.request.ChangeGuideRequestDTO;
import com.fangcang.order.request.ChangeSpecialReqeustDTO;
import com.fangcang.order.request.CountsRequestDTO;
import com.fangcang.order.request.CreateOrderRequestDTO;
import com.fangcang.order.request.DeleteAttachRequestDTO;
import com.fangcang.order.request.DeleteOrderMessageRequestDTO;
import com.fangcang.order.request.DeleteSupplyOrderCofirmMsgRequestDTO;
import com.fangcang.order.request.HandleOrderReqRequestDTO;
import com.fangcang.order.request.LockOrderRequestDTO;
import com.fangcang.order.request.NotifyAgentRequestDTO;
import com.fangcang.order.request.OrderCancelRequestDTO;
import com.fangcang.order.request.OrderConfirmRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.OrderListRequestDTO;
import com.fangcang.order.request.OtaOrderOperateRequestDTO;
import com.fangcang.order.request.UpdateOrderExceptionAmountDTO;
import com.fangcang.order.response.OrderCountResponseDTO;
import com.fangcang.order.response.OrderStatisticsDTO;
import com.fangcang.order.response.QueryChannelListResponseDTO;
import com.fangcang.order.service.OrderService;
import com.fangcang.order.service.OtaOrderHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    private OrderService orderService;
    @Resource
    private OtaOrderHandleService otaOrderHandleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<OrderDTO>> orderList(@RequestBody @Valid OrderListRequestDTO requestDTO) {
        return getOrderPageResponseDTO(requestDTO);
    }

    @RequestMapping(value = "/otaList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<OrderDTO>> orderOtaList(@RequestBody OrderListRequestDTO requestDTO) {
        return getOrderPageResponseDTO(requestDTO);
    }

    /**
     * 获取订单列表分页对象
     *
     * @param requestDTO
     * @return
     */
    private ResponseDTO<PaginationSupportDTO<OrderDTO>> getOrderPageResponseDTO(OrderListRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            requestDTO.setOperatorUser(getCacheUser().getUsername());
            PaginationSupportDTO<OrderDTO> page = orderService.orderList(requestDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("orderList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/orderStatistics", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO queryOrderStatistics(){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            OrderStatisticsDTO orderStatisticsDTO = orderService.queryOrderStatistics(super.getMerchantCode(),super.getCacheUser().getUsername());
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(orderStatisticsDTO);
        } catch (Exception e) {
            log.error("queryOrderStatistics error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/updateOrderExceptionAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateOrderExceptionAmount(@RequestBody UpdateOrderExceptionAmountDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setOperator(getFullName());
            responseDTO = orderService.updateOrderExceptionAmount(requestDTO);
        } catch (Exception e) {
            log.error("updateOrderExceptionAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO confirmOrder(@RequestBody @Valid OrderConfirmRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.confirmOrder(requestDTO);
        } catch (Exception e) {
            log.error("confirm error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/otaOrderOperate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO otaOrderOperate(@RequestBody @Valid OtaOrderOperateRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = otaOrderHandleService.otaOrderOperate(requestDTO);
        } catch (Exception e) {
            log.error("otaOrderOperate error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cancelOrder(@RequestBody @Valid OrderCancelRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.cancelOrder(requestDTO);
        } catch (Exception e) {
            log.error("cancel error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeAgent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeAgent(@RequestBody @Valid ChangeAgentRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.changeAgent(requestDTO);
        } catch (Exception e) {
            log.error("changeAgent error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeChannelOrderCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeChannelOrderCode(@RequestBody @Valid ChangeChannelOrderCodeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.changeChannelOrderCode(requestDTO);
        } catch (Exception e) {
            log.error("changeChannelOrderCode error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeBalanceMethod", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeBalanceMethod(@RequestBody @Valid ChangeBalanceMethodRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.changeBalanceMethod(requestDTO);
        } catch (Exception e) {
            log.error("changeBalanceMethod error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeGuest(@RequestBody @Valid ChangeGuestRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.changeGuest(requestDTO);
        } catch (Exception e) {
            log.error("changeGuest error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeSpecialRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeSpecialRequest(@RequestBody @Valid ChangeSpecialReqeustDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.changeSpecialRequest(requestDTO);
        } catch (Exception e) {
            log.error("changeSpecialRequest error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeGuide", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeGuide(@RequestBody @Valid ChangeGuideRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.changeGuide(requestDTO);
        } catch (Exception e) {
            log.error("changeGuide error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/preBooking", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO preBooking(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (StringUtils.isEmpty(requestDTO.getMerchantCode())) {
                requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
                requestDTO.setMerchantName(getCacheUser().getMerchantName());
                responseDTO = orderService.preBooking(requestDTO);
            }
        } catch (Exception e) {
            log.error("preBooking error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO create(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            requestDTO.setMerchantName(getCacheUser().getMerchantName());
            requestDTO.setCreator(getCacheUser().getRealName());
            requestDTO.setCreateTime(new Date());
            responseDTO = orderService.create(requestDTO);
        } catch (Exception e) {
            log.error("create error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addManualOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addManualOrder(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            requestDTO.setMerchantName(getCacheUser().getMerchantName());
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.addManualOrder(requestDTO);
        } catch (Exception e) {
            log.error("addManualOrder error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addAbatementOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addAbatementOrder(@RequestBody AddAbatementOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setOperator(getFullName());
            responseDTO = orderService.addAbatementOrder(requestDTO);
        } catch (Exception e) {
            log.error("addAbatementOrder error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/handleOrderRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO handleOrderRequest(@RequestBody @Valid HandleOrderReqRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.handleOrderRequest(requestDTO);
        } catch (Exception e) {
            log.error("handleOrderRequest error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryOrderRequestList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderRequestDTO>> queryOrderRequestList(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = orderService.queryOrderRequestList(requestDTO);
        } catch (Exception e) {
            log.error("queryOrderRequestList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryChannelList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<QueryChannelListResponseDTO>> queryChannelList() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = orderService.queryChannelList();
        } catch (Exception e) {
            log.error("queryChannelList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/notifyAgent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifyAgent(@RequestBody @Valid NotifyAgentRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.notifyAgent(requestDTO);
        } catch (Exception e) {
            log.error("notifyAgent error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/counts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderCountResponseDTO>> counts(@RequestBody @Valid CountsRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            responseDTO = orderService.counts(requestDTO);
        } catch (Exception e) {
            log.error("counts error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/uploadAttach", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO uploadAttach(@RequestParam("file") MultipartFile[] files, AddAttachRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setFiles(files);
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.addAttach(requestDTO);
        } catch (Exception e) {
            log.error("uploadAttach error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/deleteAttach", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteAttach(@RequestBody @Valid DeleteAttachRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = orderService.deleteAttach(requestDTO);
        } catch (Exception e) {
            log.error("deleteAttach error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addNote(@RequestBody @Valid AddNoteRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.dateToStringWithHms(new Date()));
            responseDTO = orderService.addNote(requestDTO);
        } catch (Exception e) {
            log.error("addNote error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/handleOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO handleOrder(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getCacheUser().getUsername());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getCacheUser().getRealName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            requestDTO.setOperator(getFullName());
            responseDTO = orderService.handleOrder(requestDTO);
        } catch (Exception e) {
            log.error("handleOrder error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/lockOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO lockOrder(@RequestBody @Valid LockOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getCacheUser().getUsername());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getCacheUser().getRealName());
            requestDTO.setModifyTime(new Date());
            requestDTO.setOperator(getFullName());
            responseDTO = orderService.lockOrder(requestDTO);
        } catch (Exception e) {
            log.error("lockOrder error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryOrderMessageList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO queryOrderMessageList() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<String> roleCodedList = getCacheUser().getRoleCodedList();
            boolean noPermission = CollectionUtils.isEmpty(roleCodedList) || (!roleCodedList.contains("ROLE_ADMIN") && !roleCodedList.contains("ROLE_ORDER"));
            if (noPermission) {
                responseDTO.setResult(Constant.YES);
                responseDTO.setFailReason("当前用户没权限访问");
                return responseDTO;
            }
            responseDTO = orderService.queryOrderMessageFromRedis(getMerchantCode());
        } catch (Exception e) {
            log.error("queryOrderMessageList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/deleteOrderMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteOrderMessage(@RequestBody @Valid DeleteOrderMessageRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<String> roleCodedList = getCacheUser().getRoleCodedList();
            boolean noPermission = CollectionUtils.isEmpty(roleCodedList) || (!roleCodedList.contains("ROLE_ADMIN") && !roleCodedList.contains("ROLE_ORDER"));
            if (noPermission) {
                responseDTO.setResult(Constant.YES);
                responseDTO.setFailReason("当前用户没权限访问");
                return responseDTO;
            }
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderService.deleteOrderMessageFromRedis(requestDTO);
        } catch (Exception e) {
            log.error("deleteOrderMessage error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }


    @RequestMapping(value = "/querySupplyOrderMessageList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO querySupplyOrderMessageList() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<String> roleCodedList = getCacheUser().getRoleCodedList();
            boolean noPermission = CollectionUtils.isEmpty(roleCodedList) || (!roleCodedList.contains("ROLE_ADMIN") && !roleCodedList.contains("ROLE_ORDER"));
            if (noPermission) {
                responseDTO.setResult(Constant.YES);
                responseDTO.setFailReason("当前用户没权限访问");
                return responseDTO;
            }
            responseDTO = orderService.querySupplyOrderCofirmMsgFromRedis(getMerchantCode());
        } catch (Exception e) {
            log.error("querySupplyOrderMessageList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/deleteSupplyOrderMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteSupplyOrderMessage(@RequestBody @Valid DeleteSupplyOrderCofirmMsgRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<String> roleCodedList = getCacheUser().getRoleCodedList();
            boolean noPermission = CollectionUtils.isEmpty(roleCodedList) || (!roleCodedList.contains("ROLE_ADMIN") && !roleCodedList.contains("ROLE_ORDER"));
            if (noPermission) {
                responseDTO.setResult(Constant.YES);
                responseDTO.setFailReason("当前用户没权限访问");
                return responseDTO;
            }
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderService.deleteSupplyOrderCofirmMsgFromRedis(requestDTO);
        } catch (Exception e) {
            log.error("deleteSupplyOrderMessage error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

}
