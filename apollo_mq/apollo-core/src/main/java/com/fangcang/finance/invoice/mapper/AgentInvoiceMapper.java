package com.fangcang.finance.invoice.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.invoice.domain.AgentInvoiceDO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;

import java.util.List;

public interface AgentInvoiceMapper extends MyMapper<AgentInvoiceDO> {

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
    public List<InvoiceDTO> queryAgentInvoice(QueryInvoiceDTO request);
}