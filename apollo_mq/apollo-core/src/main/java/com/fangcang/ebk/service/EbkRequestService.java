package com.fangcang.ebk.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.ebk.request.BookRequestDTO;
import com.fangcang.ebk.request.CancelRequestDTO;

public interface EbkRequestService {

    /**
     * 发送预订请求
     * @param requestDTO
     * @return
     */
    public ResponseDTO sendBookRequest(BookRequestDTO requestDTO);

    /**
     * 发送修改请求
     * @param requestDTO
     * @return
     */
    public ResponseDTO sendModifyRequest(BookRequestDTO requestDTO);

    /**
     * 发送取消请求
     * @param requestDTO
     * @return
     */
    public ResponseDTO sendCancelRequest(CancelRequestDTO requestDTO);
}
