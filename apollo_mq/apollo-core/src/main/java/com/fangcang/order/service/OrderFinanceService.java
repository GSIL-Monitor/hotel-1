package com.fangcang.order.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.financeorder.request.AgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.QueryAgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.order.request.OrderFinanceCallbackRequestDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单财务服务
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
public interface OrderFinanceService {
    /**
     * 通知财务收款
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<Integer> notifyReceivableAmount(AgentFinanceOrderRequestDTO requestDTO);

    /**
     * 通知财务退款
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<Integer> notifyRefundAmount(AgentFinanceOrderRequestDTO requestDTO);

    /**
     * 订单里面查询交易明细，所有的字段都做了转换成了汉字
     *
     * @param requestDTO
     * @return 交易明细的记录
     */
    ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(QueryAgentFinanceOrderRequestDTO requestDTO);

    /**
     * 最多能通知收款金额
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<BigDecimal> maxNotifyReceivableAmount(QueryAgentFinanceOrderRequestDTO requestDTO);

    /**
     * 最多能通知退款金额
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<BigDecimal> maxNotifyRefundAmount(QueryAgentFinanceOrderRequestDTO requestDTO);

    /**
     * 单结订单，已结算财务回调接口
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO orderFinanceCallback(OrderFinanceCallbackRequestDTO requestDTO);
}
