package com.fangcang.finance.invoice.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.invoice.domain.SupplyInvoiceDO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceSummaryDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceSummaryDTO;

import java.util.List;

public interface SupplyInvoiceMapper extends MyMapper<SupplyInvoiceDO> {

    /**
     * 更新发票金额
     * @param invoiceId
     * @return
     */
    public int updateInvoiceAmount(Integer invoiceId);

    /**
     * 查询发票
     * @param request
     * @return
     */
    public List<InvoiceDTO> querySupplyInvoice(QueryInvoiceDTO request);

    /**
     * 查询发票汇总
     */
    public List<InvoiceSummaryDTO> querySupplyInvoiceSummary(QueryInvoiceSummaryDTO request);
}