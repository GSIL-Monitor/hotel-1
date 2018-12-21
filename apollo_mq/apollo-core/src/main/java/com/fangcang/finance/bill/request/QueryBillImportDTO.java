package com.fangcang.finance.bill.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryBillImportDTO extends BaseQueryConditionDTO {

    /**
     * 导入批次
     */
    private Integer importNo;

    /**
     * 客户单号
     */
    private String customerOrderCode;

    /**
     * 1已匹配、2未匹配、-1全部
     */
    private Integer matchStatus;
}
