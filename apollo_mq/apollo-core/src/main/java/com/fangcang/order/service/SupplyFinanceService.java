package com.fangcang.order.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.financeorder.request.QuerySupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.order.request.SupplyFinanceCallbackRequestDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 供货单财务服务
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
public interface SupplyFinanceService {
    /**
     * 通知财务付款
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<Integer> notifyPayableAmount(SupplyFinanceOrderRequestDTO requestDTO);

    /**
     * 通知财务退款
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<Integer> notifyReceiptAmount(SupplyFinanceOrderRequestDTO requestDTO);

    /**
     * 订单里面查询交易明细，所有的字段都做了转换成了汉字
     *
     * @param requestDTO 订单编码
     * @return 交易明细的记录
     */
    ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(QuerySupplyFinanceOrderRequestDTO requestDTO);

    /**
     * 最多能通知付款金额
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<BigDecimal> maxNotifyPayableAmount(QuerySupplyFinanceOrderRequestDTO requestDTO);

    /**
     * 最多能通知收款金额
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<BigDecimal> maxNotifyReceiptAmount(QuerySupplyFinanceOrderRequestDTO requestDTO);


    /**
     * 单结供货单，已结算财务回调接口
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO supplyFinanceCallback(SupplyFinanceCallbackRequestDTO requestDTO);
}
