package com.fangcang.finance.financelock.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.QueryLockLogRequestDTO;
import com.fangcang.finance.financelock.request.UnlockOrderListRequestDTO;
import com.fangcang.finance.financelock.response.FinanceLockLogResponseDTO;
import com.fangcang.finance.financelock.response.UnlockOrderListResponseDTO;

import java.util.List;

/**
 * 财务锁服务
 *
 * @author : zhanwang
 * @date : 2018/7/4
 */
public interface FinanceLockService {
    /**
     * 解锁订单查询
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<UnlockOrderListResponseDTO> unlockOrderList(UnlockOrderListRequestDTO requestDTO);

    /**
     * 加锁/解锁订单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO lockOrder(FinanceLockOrderRequestDTO requestDTO);

    /**
     * 查询加锁日志
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<FinanceLockLogResponseDTO>> queryLockLog(QueryLockLogRequestDTO requestDTO);
}
