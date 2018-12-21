package com.fangcang.finance.prepay.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/7/6
 */
@Data
public class QueryPrepayItemListRequestDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 4493174234275838091L;

    /**
     * 合约id
     */
    @NotNull
    private Integer contractId;
    /**
     * 结算开始日期
     */
    private String settlementStartDate;
    /**
     * 结算结束日期
     */
    private String settlementEndDate;
    /**
     * 账单名称或编码
     */
    private String billNameOrCode;

}
