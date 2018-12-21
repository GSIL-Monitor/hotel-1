package com.travel.hotel.dlt.service.impl;

import com.travel.channel.dto.request.HotelOrderOperateRequestDTO;
import com.travel.channel.dto.response.HotelOrderOperateResponseDTO;
import com.travel.common.enums.OrderStateEnum;
import com.travel.common.exception.ServiceException;
import com.travel.hotel.dlt.dao.DltOrderDetailPOMapper;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPO;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPOExample;
import com.travel.hotel.dlt.request.dto.OperaterDltOrderRequest;
import com.travel.hotel.dlt.response.base.ResultStatus;
import com.travel.hotel.dlt.response.dto.OperaterDltOrderResponse;
import com.travel.hotel.dlt.service.DltHotelOrderOperateService;
import com.travel.hotel.dlt.utils.DltInterfaceInvoker;
import com.travel.hotel.order.dao.OrderPOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 *   2018/3/12.
 */
@Service("dltHotelOrderOperateService")
public class DltHotelOrderOperateServiceImpl implements DltHotelOrderOperateService {

    private static final Logger LOG = LoggerFactory.getLogger(DltHotelOrderOperateServiceImpl.class);

    @Autowired
    private DltOrderDetailPOMapper dltOrderDetailPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Override
    public HotelOrderOperateResponseDTO operateOrder(HotelOrderOperateRequestDTO requestDTO) {

        HotelOrderOperateResponseDTO hotelOrderOperateResponseDTO = new HotelOrderOperateResponseDTO();
        hotelOrderOperateResponseDTO.setIsSuccess(0);

        DltOrderDetailPOExample example = new DltOrderDetailPOExample();
        example.createCriteria().andOrderIdEqualTo(requestDTO.getCustomerOrderCode());
        List<DltOrderDetailPO> dltOrderDetailPOList = dltOrderDetailPOMapper.selectByExample(example);
        DltOrderDetailPO dltOrderDetailPO = null;
        if(null!=dltOrderDetailPOList && dltOrderDetailPOList.size() > 0) {
            for(DltOrderDetailPO dltOrderDetail : dltOrderDetailPOList) {
                if(null!=dltOrderDetail.getZhOrderCode()&&dltOrderDetail.getZhOrderCode().equals(requestDTO.getOrderCode())) {
                    dltOrderDetailPO = dltOrderDetail;
                }
            }
        }

        if (null == requestDTO || null == requestDTO.getOrderCode()
                || null == requestDTO.getChannelCode() || null == requestDTO.getOperateType()
                || null == requestDTO.getMerchantCode() || null == dltOrderDetailPO) {
            hotelOrderOperateResponseDTO.setFailureReason("操作代理通订单参数为空，请检查");
            LOG.error("操作代理通订单参数为空, requestDTO=" + requestDTO);
            return hotelOrderOperateResponseDTO;
        }

        if (null == requestDTO.getOrderCode() || null == requestDTO.getCustomerOrderCode()
                || null == requestDTO.getOrderState()) {
            hotelOrderOperateResponseDTO.setFailureReason("订单传入参数有误！订单ID，渠道订单编码，订单状态为必传！");
            LOG.error("订单传入参数有误！订单ID，渠道订单编码，订单状态为必传！, requestDTO=" + requestDTO);
            return hotelOrderOperateResponseDTO;
        }

        hotelOrderOperateResponseDTO.setOrderCode(requestDTO.getOrderCode());
        hotelOrderOperateResponseDTO.setOrderState(requestDTO.getOrderState());
        requestDTO.setOrderState(requestDTO.getOrderState());
        requestDTO.setCustomerOrderCode(dltOrderDetailPO.getDltOrderId());

        OperaterDltOrderRequest operaterDltOrderRequest = this.buildOperateDltOrderRequest(requestDTO);

        String operateId = requestDTO.getOperateType();
        if (requestDTO.getChannelCode().toLowerCase().startsWith("ctrip")) {

            if (!"ebk".equals(dltOrderDetailPO.getChannel().toLowerCase())
                    && !"tc".equals(dltOrderDetailPO.getChannel().toLowerCase())
                    && !"b2b".equals(dltOrderDetailPO.getChannel().toLowerCase())
                    && !"b2boffline".equals(dltOrderDetailPO.getChannel().toLowerCase())) {
                hotelOrderOperateResponseDTO.setFailureReason("本订单来自携程" + dltOrderDetailPO.getChannel()
                        + "渠道，只有来自EBK、B2B、B2BOffline、ChannelA的订单才可以操作");
                return hotelOrderOperateResponseDTO;
            }

            List<String> operateIdList = Arrays.asList("0", "1", "2", "11", "12");
            if (null == operateId || !operateIdList.contains(operateId)) {
                hotelOrderOperateResponseDTO.setFailureReason("操作代理通携程订单，操作类型【" + requestDTO.getOperateType() + "】非法");
                return hotelOrderOperateResponseDTO;
            }

            switch (operateId) {
                case "0" : return this.ctripAccept(operaterDltOrderRequest, requestDTO);
                case "1" : return this.ctripRefuse(operaterDltOrderRequest, requestDTO);
                case "2" : return this.ctripChangeConfirmNo(operaterDltOrderRequest, requestDTO);
                case "11" : return this.ctripAcceptCancel(operaterDltOrderRequest, requestDTO);
                case "12" : return this.ctripRefuseCancel(operaterDltOrderRequest, requestDTO);
            }

        }

        if (requestDTO.getChannelCode().toLowerCase().startsWith("qunar")) {
            List<String> operateIdList = Arrays.asList("14", "15", "16", "17", "18");
            if (null == operateId || !operateIdList.contains(operateId)) {
                hotelOrderOperateResponseDTO.setFailureReason("操作代理通去哪儿订单，操作类型【" + requestDTO.getOperateType() + "】非法");
                return hotelOrderOperateResponseDTO;
            }

            switch (operateId) {
                case "14" : return this.qunarHaveRoomAndAccept(operaterDltOrderRequest, requestDTO);
                case "15" : return this.qunarNoRoom(operaterDltOrderRequest, requestDTO);
                case "16" : return this.qunarApplyUnsubscribe(operaterDltOrderRequest, requestDTO);
                case "17" : return this.qunarAcceptUnsubscribe(operaterDltOrderRequest, requestDTO);
                case "18" : return this.qunarRefuseUnsubscribe(operaterDltOrderRequest, requestDTO);
            }
        }

        hotelOrderOperateResponseDTO.setFailureReason("操作代理通携程订单，操作类型【" + requestDTO.getOperateType() + "】非法");
        return hotelOrderOperateResponseDTO;
    }

    /**
     * 接受，confirmType字段必传，如果confirmType字段必传为2，则确认号必传
     * 操作完，订单状态变为已确认
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO ctripAccept(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {

        if (null == operaterDltOrderRequest.getConfirmType() || (1 != operaterDltOrderRequest.getConfirmType() && 2 != operaterDltOrderRequest.getConfirmType())) {
            LOG.error("确认方式有误，必须选择按入住人姓名或按确认号入住，ConfirmType=" + operaterDltOrderRequest.getConfirmType());
            throw new ServiceException("确认方式有误，必须选择按入住人姓名或按确认号入住");
        }

        if (2 == operaterDltOrderRequest.getConfirmType() && StringUtils.isEmpty(operaterDltOrderRequest.getBookingNo())) {
            LOG.error("确认方式为按确认号入住时，酒店确认号必须填");
            throw new ServiceException("确认方式为按确认号入住时，酒店确认号必须填");
        }

        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.CONFIRMED.code);
    }

    /**
     * 拒绝订单，拒绝类型默认写4-其他原因，订单状态变为已拒绝
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO ctripRefuse(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        if (null == operaterDltOrderRequest.getRefuseType()) {
            operaterDltOrderRequest.setRefuseType(4);//其他原因
        }
        //申请修改的如果已经确认或者已经取消，就直接调一次接口拒绝掉，不影响内部订单状态，否则就直接修改订单信息，确认的时候提示订单状态处于修改申请中，请再次确认订单信息
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.REFUSED.code);
    }

    /**
     * 修改确认号，订单状态不变
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO ctripChangeConfirmNo(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {

        if (null == operaterDltOrderRequest.getBookingNo()) {
            LOG.error("更改确认号时未传入确认号，BookingNo=" + operaterDltOrderRequest.getBookingNo());
            throw new ServiceException("更改确认号时未传入确认号");
        }
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, hotelOrderOperateRequestDTO.getOrderState());
    }

    /**
     * 接受取消，验证当前状态是否为申请取消中，订单状态变为已取消
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO ctripAcceptCancel(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        if (null == hotelOrderOperateRequestDTO || null == hotelOrderOperateRequestDTO.getOrderState()) {
            LOG.error("接受取消操作失败， hotelOrderOperateRequestDTO = " + hotelOrderOperateRequestDTO);
            throw new ServiceException("接受取消操作失败，参数错误");
        }
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.CANCELED.code);
    }

    /**
     * 拒绝取消，订单状态不变
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO ctripRefuseCancel(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        if (null == hotelOrderOperateRequestDTO || null == hotelOrderOperateRequestDTO.getOrderState()) {
            LOG.error("拒绝取消操作失败，hotelOrderOperateRequestDTO = " + hotelOrderOperateRequestDTO);
            throw new ServiceException("拒绝取消操作失败，参数错误");
        }
        if (null == operaterDltOrderRequest.getRefuseType()) {
            operaterDltOrderRequest.setRefuseType(4);//其他原因
        }
        //申请取消的如果已经确认或者已经取消，就直接调一次接口拒绝掉，不影响内部订单状态，否则就改为申请取消中，拒绝后变为处理中
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.PROCESSING.code);
    }

    /**
     * 有房并接受
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO qunarHaveRoomAndAccept(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {

        if (null == operaterDltOrderRequest.getConfirmType() || (1 != operaterDltOrderRequest.getConfirmType() && 2 != operaterDltOrderRequest.getConfirmType())) {
            LOG.error("确认方式有误，必须选择按入住人姓名或按确认号入住，ConfirmType=" + operaterDltOrderRequest.getConfirmType());
            throw new ServiceException("确认方式有误，必须选择按入住人姓名或按确认号入住");
        }

        if (2 == operaterDltOrderRequest.getConfirmType() && StringUtils.isEmpty(operaterDltOrderRequest.getBookingNo())) {
            LOG.error("确认方式为按确认号入住时，酒店确认号必须填");
            throw new ServiceException("确认方式为按确认号入住时，酒店确认号必须填");
        }

        // 去哪儿确认，先操作有房，再操作接受
        operaterDltOrderRequest.setOperaterType(14);
        HotelOrderOperateResponseDTO dto1 = this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, hotelOrderOperateRequestDTO.getOrderState());
        if (1 != dto1.getIsSuccess()) {
            return dto1;
        }
        operaterDltOrderRequest.setOperaterType(0);
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.CONFIRMED.code);
    }

    /**
     *
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO qunarNoRoom(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        if (null == operaterDltOrderRequest.getRefuseType()) {
            operaterDltOrderRequest.setRefuseType(1);//满房
        }
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.REFUSED.code);
    }

    /**
     * 接受退订
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO qunarApplyUnsubscribe(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        throw new ServiceException("暂不支持该操作");
//        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, hotelOrderOperateRequestDTO.getOrderState());
    }

    /**
     *
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO qunarAcceptUnsubscribe(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        if (null == operaterDltOrderRequest.getRefundAmount() || operaterDltOrderRequest.getRefundAmount().compareTo(BigDecimal.ZERO) < 0) {
            operaterDltOrderRequest.setRefundAmount(BigDecimal.ZERO);
        }
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.CANCELED.code);
    }

    /**
     *
     * @param operaterDltOrderRequest OperaterDltOrderRequest
     * @param hotelOrderOperateRequestDTO HotelOrderOperateRequestDTO
     * @return
     */
    private HotelOrderOperateResponseDTO qunarRefuseUnsubscribe(OperaterDltOrderRequest operaterDltOrderRequest, HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO) {
        if (null == hotelOrderOperateRequestDTO || null == hotelOrderOperateRequestDTO.getOrderState()) {
            LOG.error("拒绝取消操作失败，hotelOrderOperateRequestDTO = " + hotelOrderOperateRequestDTO);
            throw new ServiceException("拒绝取消操作失败，参数错误");
        }
        if (null == operaterDltOrderRequest.getRefuseType()) {
            operaterDltOrderRequest.setRefuseType(4);//其他原因
        }
        //申请取消的如果已经确认或者已经取消，就直接调一次接口拒绝掉，不影响内部订单状态，否则就改为申请取消中，拒绝后变为处理中
        return this.request(operaterDltOrderRequest, hotelOrderOperateRequestDTO, OrderStateEnum.PROCESSING.code);
    }

    private OperaterDltOrderRequest buildOperateDltOrderRequest(HotelOrderOperateRequestDTO requestDTO) {
        OperaterDltOrderRequest request = new OperaterDltOrderRequest();

        request.setDltOrderId(requestDTO.getCustomerOrderCode());
        request.setChannelCode(requestDTO.getChannelCode());
        String operateType = requestDTO.getOperateType();
        if (StringUtils.isEmpty(operateType)) {
            throw new ServiceException("订单操作类型无效");
        }
        request.setOperaterType(Integer.valueOf(operateType));
        request.setConfirmType(requestDTO.getConfirmType());
        request.setRefuseType(requestDTO.getRefuseType());
        request.setRefuseRemark(requestDTO.getRefuseReason());
        request.setRefundAmount(requestDTO.getRefundAmount());
        request.setBookingNo(requestDTO.getConfirmNo());
        request.setRemark(requestDTO.getRemark());
        return request;
    }

    private HotelOrderOperateResponseDTO request(OperaterDltOrderRequest operaterDltOrderRequest,HotelOrderOperateRequestDTO hotelOrderOperateRequestDTO, String orderState) {

        HotelOrderOperateResponseDTO response = new HotelOrderOperateResponseDTO();
        response.setIsSuccess(0);
        response.setOrderCode(hotelOrderOperateRequestDTO.getOrderCode());
        response.setOrderState(hotelOrderOperateRequestDTO.getOrderState());

        try {
            OperaterDltOrderResponse operaterDltOrderResponse = DltInterfaceInvoker.invoke(operaterDltOrderRequest,hotelOrderOperateRequestDTO.getMerchantCode());
            if (null == operaterDltOrderResponse || null == operaterDltOrderResponse.getResultStatus()) {
                response.setFailureReason("调用代理通接口无返回, 请稍后重试");
            } else {
                ResultStatus rs = operaterDltOrderResponse.getResultStatus();
                // 代理通0为成功，我方0为失败，1为成功
                response.setIsSuccess(0 == rs.getResultCode() ? 1 : 0);
                response.setFailureReason(rs.getResultMsg());
                // 如果操作成功，则更新订单状态，否则返回原状态
                response.setOrderState(0 == rs.getResultCode() ? orderState : hotelOrderOperateRequestDTO.getOrderState());
            }
        } catch (Exception e) {
            LOG.error("调用代理通接口失败, 请稍后重试", e);
            response.setFailureReason("调用代理通接口失败, 请稍后重试");
        }
        return response;
    }

}
