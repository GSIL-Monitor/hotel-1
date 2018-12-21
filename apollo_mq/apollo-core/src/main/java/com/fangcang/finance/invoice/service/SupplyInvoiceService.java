package com.fangcang.finance.invoice.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.invoice.request.AddInvoiceItemDTO;
import com.fangcang.finance.invoice.request.CreateInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceSummaryDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.InvoiceSummaryDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceSupplyOrderDTO;

import javax.validation.Valid;
import java.io.InputStream;

public interface SupplyInvoiceService {

    /**
     * 创建发票
     */
    public ResponseDTO createSupplyInvoice(@Valid CreateInvoiceDTO request);

    /**
     * 更新发票
     */
    public ResponseDTO updateSupplyInvoice(UpdateInvoiceDTO request);

    /**
     * 删除发票
     */
    public ResponseDTO deleteSupplyInvoice(DeleteInvoiceDTO request);

    /**
     * 添加发票明细
     */
    public ResponseDTO addSupplyInvoiceItem(AddInvoiceItemDTO request);

    /**
     * 删除发票明细
     */
    public ResponseDTO deleteSupplyInvoiceItem(DeleteInvoiceItemDTO request);

    /**
     * 查询发票
     */
    public PaginationSupportDTO<InvoiceDTO> querySupplyInvoice(QueryInvoiceDTO request);

    /**
     * 查询发票明细
     */
    public PaginationSupportDTO<InvoiceItemDTO> querySupplyInvoiceItem(QueryInvoiceItemDTO request);

    /**
     * 查询未开发票的订单
     */
    public PaginationSupportDTO<UnInvoiceSupplyOrderDTO> queryUnInvoiceSupplyOrder(QueryUnInvoiceOrderDTO request);

    /**
     * 查询未开发票的账单
     */
    public PaginationSupportDTO<UnInvoiceBillDTO> queryUnInvoiceSupplyBill(QueryUnInvoiceBillDTO request);

    /**
     * 查询发票汇总
     */
    public PaginationSupportDTO<InvoiceSummaryDTO> querySupplyInvoiceSummary(QueryInvoiceSummaryDTO request);

    /**
     * 读取上传的excel文件并自动添加到发票明细
     */
    public ResponseDTO uploadSupplyInvoiceItem(InputStream in,Integer invoiceId,String operator);
}