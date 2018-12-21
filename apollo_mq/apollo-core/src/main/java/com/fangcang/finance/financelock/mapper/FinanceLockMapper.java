package com.fangcang.finance.financelock.mapper;

import com.fangcang.finance.financelock.request.UnlockOrderListRequestDTO;
import com.fangcang.finance.financelock.response.UnlockOrderListResponseDTO;

import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/5
 */
public interface FinanceLockMapper {
    /**
     * 查询订单列表，关联订单财务表
     *
     * @param requestDTO
     * @return
     */
    List<UnlockOrderListResponseDTO> queryUnlockOrderList(UnlockOrderListRequestDTO requestDTO);
}
