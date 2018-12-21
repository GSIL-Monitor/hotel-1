package com.fangcang.finance.invoice.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.invoice.domain.SupplyInvoiceItemDO;
import com.fangcang.finance.invoice.request.AddInvoiceItemDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceAmountDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceSupplyOrderDTO;

import java.util.List;

public interface SupplyInvoiceItemMapper extends MyMapper<SupplyInvoiceItemDO> {

    /**
     * 批量保存订单到发票
     * @param request
     * @return
     */
    public int saveBatchOrderToInvoice(AddInvoiceItemDTO request);

    /**
     * 批量保存账单到发票
     * @param request
     * @return
     */
    public int saveBatchBillToInvoice(AddInvoiceItemDTO request);

    /**
     * 更新订单已开发票金额
     * @param request
     * @return
     */
    public int updateOrderInvoiceAmount(UpdateInvoiceAmountDTO request);

    /**
     * 更新账单已开发票金额
     * @param request
     * @return
     */
    public int updateBillInvoiceAmount(UpdateInvoiceAmountDTO request);

    /**
     * 更新账单对应的订单已开发票金额
     * @param request
     * @return
     */
    public int updateBillOrderInvoiceAmount(UpdateInvoiceAmountDTO request);

    /**
     * 删除发票明细
     * @param request
     * @return
     */
    public int deleteSupplyInvoiceItem(DeleteInvoiceItemDTO request);

    /**
     * 查询发票明细
     * @param request
     * @return
     */
    public List<InvoiceItemDTO> querySupplyInvoiceItem(QueryInvoiceItemDTO request);

    /**
     * 查询未开发票的订单
     * @param request
     * @return
     */
    public List<UnInvoiceSupplyOrderDTO> queryUnInvoiceSupplyOrder(QueryUnInvoiceOrderDTO request);

    /**
     * 查询未开发票的账单
     * @param request
     * @return
     */
    public List<UnInvoiceBillDTO> queryUnInvoiceSupplyBill(QueryUnInvoiceBillDTO request);
}