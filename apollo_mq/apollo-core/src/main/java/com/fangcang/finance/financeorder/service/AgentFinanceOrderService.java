package com.fangcang.finance.financeorder.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.ConfirmPayRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.QueryAgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.AgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.response.AgentOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryAgentFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;

import java.util.List;

/**
 * Created by Vinney on 2018/7/17.
 */
public interface AgentFinanceOrderService {


    /**
     * 通知财务收款
     * @param agentFinanceOrderRequestDTO
     * @return
     */
    ResponseDTO<Integer> notifyReceivableAmount(AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO);

    /**
     * 通知财务退款
     * @param agentFinanceOrderRequestDTO
     * @return
     */
    ResponseDTO<Integer> notifyRefundAmount(AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO);


    /**
     *  订单里面查询交易明细，所有的字段都做了转换成了汉字
     * @param orderCode  订单编码
     * @return 交易明细的记录
     */
    ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(String orderCode);

    /**
     * 查询交易明细：工单列表。
     * @param queryAgentFinanceOrderRequestDTO
     * @return
     */
    ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> queryPayItemList(QueryAgentFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO);


    /**
     * 查询待收款
     * @param singleBalanceQueryDTO
     * @return
     */
    PaginationSupportDTO<AgentOrderListResponseDTO> getUnreceived(SingleBalanceQueryDTO singleBalanceQueryDTO);

    /**
     * 查询待收款
     * @param singleBalanceQueryDTO
     * @return
     */
    PaginationSupportDTO<AgentOrderListResponseDTO> getReceived(SingleBalanceQueryDTO singleBalanceQueryDTO);


    /**
     * 查询未完成
     * @param singleBalanceQueryDTO
     * @return
     */
    PaginationSupportDTO<AgentOrderListResponseDTO> getUnfinished(SingleBalanceQueryDTO singleBalanceQueryDTO);

    /**
     * 作废工单
     * @param financeOrderRequestDTO
     */
    void cancelFinanceOrder(FinanceOrderRequestDTO financeOrderRequestDTO);

    /**
     * 确认工单
     * @param confirmFinanceOrderRequestDTO
     * @return
     */
    Integer confirmFinanceOrder(ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO);

    /**
     * B2B直接支付后，回写工单金额和状态
     * @param confirmPayRequestDTO
     * @return 工单ID
     */
    ResponseDTO<Integer> confirmPay(ConfirmPayRequestDTO confirmPayRequestDTO);

    /**
     * 单个工单详情
     * @param confirmFinanceOrderRequestDTO
     * @return
     */
    ConfirmFinanceOrderResponseDTO queryFinanceOrderDetail(ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO);

}
