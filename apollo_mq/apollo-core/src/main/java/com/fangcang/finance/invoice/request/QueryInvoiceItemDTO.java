package com.fangcang.finance.invoice.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryInvoiceItemDTO extends BaseQueryConditionDTO{

    private Integer invoiceId;
}
