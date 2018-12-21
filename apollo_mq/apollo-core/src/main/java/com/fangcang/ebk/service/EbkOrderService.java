package com.fangcang.ebk.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.ebk.request.LockRequestDTO;
import com.fangcang.ebk.request.QueryEbkOrderDTO;
import com.fangcang.ebk.request.UpdateConfirmNoDTO;
import com.fangcang.ebk.request.UpdateConfirmResultDTO;
import com.fangcang.ebk.response.EbkNotifyDTO;
import com.fangcang.ebk.response.EbkOrderDTO;
import com.fangcang.ebk.response.EbkOrderSimpleDTO;
import com.fangcang.ebk.response.EbkOrderSnapDTO;
import com.fangcang.ebk.response.EbkOrderStatisticsDTO;

import java.util.List;

public interface EbkOrderService {

    /**
     * 查询订单列表
     * @param requestDTO
     * @return
     */
    public PaginationSupportDTO<EbkOrderSimpleDTO> queryOrderList(QueryEbkOrderDTO requestDTO);

    /**
     * 订单列表统计
     * @param supplyCode
     * @return
     */
    public EbkOrderStatisticsDTO queryOrderStatistics(String supplyCode);

    /**
     * 查询订单详情
     * @param supplyOrderCode
     * @return
     */
    public EbkOrderDTO queryOrderDetail(String supplyOrderCode);

    /**
     * 查询修改请求详情
     * @param requestId
     * @return
     */
    public EbkOrderSnapDTO queryModifyRequestDetail(Long requestId);

    /**
     * 确认请求
     * @param requestDTO
     * @return
     */
    public ResponseDTO confirmRequest(UpdateConfirmResultDTO requestDTO);

    /**
     * 更新确认号
     * @param requestDTO
     * @return
     */
    public ResponseDTO modifyConfirmNo(UpdateConfirmNoDTO requestDTO);

    /**
     * 对订单加锁
     * @param requestDTO
     * @return
     */
    public ResponseDTO lock(LockRequestDTO requestDTO);

    /**
     * 对订单解锁
     * @param requestDTO
     * @return
     */
    public ResponseDTO unlock(LockRequestDTO requestDTO);

    /**
     * 查询消息通知
     * @return
     */
    public List<EbkNotifyDTO> queryNotify(String supplyCode);
}
