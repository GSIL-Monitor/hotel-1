package com.fangcang.order.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.order.request.OtaOrderOperateRequestDTO;

/**
 * OTA订单处理服务
 *
 * @author : zhanwang
 * @date : 2018/8/2
 */
public interface OtaOrderHandleService {

    /**
     * 操作OTA订单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO otaOrderOperate(OtaOrderOperateRequestDTO requestDTO);
}
