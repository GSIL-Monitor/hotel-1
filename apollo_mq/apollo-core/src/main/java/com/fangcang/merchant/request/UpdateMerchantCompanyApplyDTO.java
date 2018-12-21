package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class UpdateMerchantCompanyApplyDTO implements Serializable {
    private static final long serialVersionUID = -9179160103816200551L;

    @NotNull
    private Integer companyId;

    /**
     * 适用供应商：0所有、1指定
     */
    private Integer applySupply=0;

    private List<Integer> supplyIdList;

    private String operator;
}
