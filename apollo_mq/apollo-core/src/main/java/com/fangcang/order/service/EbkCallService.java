package com.fangcang.order.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.order.request.EbkCallbackRequestDTO;

/**
 * EBK回调service
 *
 * @author : zhanwang
 * @date : 2018/6/8
 */
public interface EbkCallService {
    /**
     * EBK回调接口
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO ebkCallback(EbkCallbackRequestDTO requestDTO);
}

