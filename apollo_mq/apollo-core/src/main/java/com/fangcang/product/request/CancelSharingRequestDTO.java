package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class CancelSharingRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -5255865711846851836L;

    @NotNull(message = "quotaAccountId不能为Null")
    private Long quotaAccountId;

    @NotNull(message = "pricePlanId不能为Null")
    private Long pricePlanId;

    /**
     * 商家编码
     */
    private String merchantCode;
}
