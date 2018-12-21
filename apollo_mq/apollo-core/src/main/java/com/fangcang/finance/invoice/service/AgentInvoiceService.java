package com.fangcang.finance.invoice.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.invoice.request.AddInvoiceItemDTO;
import com.fangcang.finance.invoice.request.CreateInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceOrderDTO;

public interface AgentInvoiceService {

    /**
     * 创建发票
     */
    public ResponseDTO createAgentInvoice(CreateInvoiceDTO request);

    /**
     * 更新发票
     */
    public ResponseDTO updateAgentInvoice(UpdateInvoiceDTO request);

    /**
     * 删除发票
     */
    public ResponseDTO deleteAgentInvoice(DeleteInvoiceDTO request);

    /**
     * 添加发票明细
     */
    public ResponseDTO addAgentInvoiceItem(AddInvoiceItemDTO request);

    /**
     * 删除发票明细
     */
    public ResponseDTO deleteAgentInvoiceItem(DeleteInvoiceItemDTO request);

    /**
     * 查询发票
     */
    public PaginationSupportDTO<InvoiceDTO> queryAgentInvoice(QueryInvoiceDTO request);

    /**
     * 查询发票明细
     */
    public PaginationSupportDTO<InvoiceItemDTO> queryAgentInvoiceItem(QueryInvoiceItemDTO request);

    /**
     * 查询未开发票的订单
     */
    public PaginationSupportDTO<UnInvoiceOrderDTO> queryUnInvoiceOrder(QueryUnInvoiceOrderDTO request);

    /**
     * 查询未开发票的账单
     */
    public PaginationSupportDTO<UnInvoiceBillDTO> queryUnInvoiceAgentBill(QueryUnInvoiceBillDTO request);
}