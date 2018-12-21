package com.fangcang.finance.bill.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.bill.domain.AgentBillOrderDO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.InsertBillOrderDTO;
import com.fangcang.finance.bill.request.InsertBillOrderItemDTO;
import com.fangcang.finance.bill.request.QueryBillCurrencyDTO;
import com.fangcang.finance.bill.request.QueryBillOrderDTO;
import com.fangcang.finance.bill.request.UpdateBillOrderFinanceDTO;
import com.fangcang.finance.bill.response.BillOrderDTO;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;

import java.util.List;

public interface AgentBillOrderMapper extends MyMapper<AgentBillOrderDO> {

    /**
     * 批量保存账单明细
     */
    public int saveBatchBillOrder(InsertBillOrderDTO request);

    /**
     * 批量保存账单明细的产品
     */
    public int saveBatchBillOrderProduct(InsertBillOrderItemDTO request);

    /**
     * 批量保存账单明细的附加项
     */
    public int saveBatchBillOrderAddition(InsertBillOrderItemDTO request);

    /**
     * 批量保存账单明细的减免政策
     */
    public int saveBatchBillOrderDeratePolicy(InsertBillOrderItemDTO request);

    /**
     * 批量保存账单明细中已取消的订单
     */
    public int saveBatchBillCanceledOrder(InsertBillOrderItemDTO request);

    /**
     * 更新订单明细数量
     */
    public int updateBillOrderItemCount(InsertBillOrderItemDTO request);

    /**
     * 保存账单应收金额
     */
    public int saveBillCurrency(BillIdDTO request);

    /**
     * 查询账单最新的应收金额
     */
    public List<MultipleCurrencyAmountDTO> queryBillNewCurrency(BillIdDTO request);

    /**
     * 更新账单明细的结算状态
     * @param request
     * @return
     */
    public int updateBillOrderFinance(UpdateBillOrderFinanceDTO request);

    /**
     * 更新账单明细内容
     */
    public int updateAgentBillOrder(BillIdDTO request);

    /**
     * 查询账单明细
     */
    public List<BillOrderDTO> queryAgentBillOrder(QueryBillOrderDTO requestDTO);

    /**
     * 查询账单金额
     */
    public List<MultipleCurrencyAmountDTO>  queryAgentBillCurrency(QueryBillCurrencyDTO requestDTO);
}