package com.fangcang.supplier.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QuerySupplyCashItemDTO extends BaseQueryConditionDTO{

    private Integer supplyId;

    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 操作类型:1转入,2转出,3结算
     */
    private Integer accountItemType;
}
