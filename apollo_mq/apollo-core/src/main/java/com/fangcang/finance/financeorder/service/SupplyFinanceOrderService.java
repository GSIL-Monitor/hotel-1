package com.fangcang.finance.financeorder.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.ConfirmPayRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.QueryBillRequestDTO;
import com.fangcang.finance.financeorder.request.QuerySupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.request.SupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QuerySupplyFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.finance.financeorder.response.SupplyOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.UnpayResponseDTO;

import java.util.List;

/**
 * Created by Vinney on 2018/7/17.
 */
public interface SupplyFinanceOrderService {


    /**
     * 通知财务退款
     * @param supplytFinanceOrderRequestDTO
     * @return
     */
    ResponseDTO<Integer> notifyRefundAmount(SupplyFinanceOrderRequestDTO supplytFinanceOrderRequestDTO);

    /**
     * 通知财务付款
     * @param supplyFinanceOrderRequestDTO
     * @return
     */
    ResponseDTO<Integer> notifyPayableAmount(SupplyFinanceOrderRequestDTO supplyFinanceOrderRequestDTO);


    /**
     *  订单里面查询交易明细，所有的字段都做了转换成了汉字
     * @param orderCode  订单编码
     * @return 交易明细的记录
     */
    ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(String orderCode);

    /**
     * 查询交易明细：工单列表。
     * @param querySupplyFinanceOrderRequestDTO
     * @return
     */
    ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> queryPayItemList(QuerySupplyFinanceOrderRequestDTO querySupplyFinanceOrderRequestDTO);


    /**
     * 查询待收款
     * @param singleBalanceQueryDTO
     * @return
     */
    PaginationSupportDTO<SupplyOrderListResponseDTO> getUnpaid(SingleBalanceQueryDTO singleBalanceQueryDTO);

    /**
     * 导出订单待付款
     * @param singleBalanceQueryDTO
     * @return
     */
    List<UnpayResponseDTO> exportUnpayOrder(SingleBalanceQueryDTO singleBalanceQueryDTO);

    /**
     * 导出账单待付款
     * @param requestDTO
     * @return
     */
    List<UnpayResponseDTO> exportUnpayBill(QueryBillRequestDTO requestDTO);

    /**
     * 查询待收款
     * @param singleBalanceQueryDTO
     * @return
     */
    PaginationSupportDTO<SupplyOrderListResponseDTO> getPaid(SingleBalanceQueryDTO singleBalanceQueryDTO);


    /**
     * 查询未完成
     * @param singleBalanceQueryDTO
     * @return
     */
    PaginationSupportDTO<SupplyOrderListResponseDTO> getUnfinished(SingleBalanceQueryDTO singleBalanceQueryDTO);

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
