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
public class QueryRechargeListRequestDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = -2469859876005565885L;
    /**
     * 合约id
     */
    @NotNull
    private Integer contractId;
}
