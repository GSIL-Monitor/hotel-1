package com.fangcang.order.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.order.dto.OrderOperationLogDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.UnlockRequestDTO;
import com.fangcang.order.response.OrderAttachGroupResponseDTO;
import com.fangcang.order.response.OrderCustomerDTO;
import com.fangcang.order.response.OrderDetailResponseDTO;
import com.fangcang.order.response.OrderNoteGroupResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;

import java.util.List;

/**
 * 订单详情服务
 *
 * @author : zhanwang
 * @date : 2018/5/24
 */
public interface OrderDetailService {
    /**
     * 订单信息
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<OrderDetailResponseDTO> orderInfo(OrderDetailRequestDTO requestDTO);

    /**
     * 查询订单的客户信息
     * @param requestDTO
     * @return
     */
    ResponseDTO<OrderCustomerDTO> queryOrderCustomer(OrderDetailRequestDTO requestDTO);

    /**
     * 产品信息
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<SupplyOrderResponseDTO>> productInfo(OrderDetailRequestDTO requestDTO);

    /**
     * 附件信息
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<OrderAttachGroupResponseDTO>> attachInfo(OrderDetailRequestDTO requestDTO);

    /**
     * 备注信息
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<OrderNoteGroupResponseDTO>> noteInfo(OrderDetailRequestDTO requestDTO);

    /**
     * 操作日志
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<OrderOperationLogDTO>> logInfo(OrderDetailRequestDTO requestDTO);

    /**
     * 解锁别人订单，并自己锁定
     * @param unlockRequestDTO
     * @return
     */
    ResponseDTO unlock(UnlockRequestDTO unlockRequestDTO);
}
