package com.fangcang.finance.financeorder.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPayItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     */
    private Integer payItemType;

    /**
     * 关联id
     */
    private Integer relationId;
}
