package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.PricePlanDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class AddProductToSharedPoolRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 5915041146232156455L;

    /**
     * 配额账号ID
     */
    @NotNull(message = "quotaAccountId不能为Null")
    private Long quotaAccountId;

    @NotNull(message = "pricePlanList不能为Null")
    private List<PricePlanDTO> pricePlanList;

    /**
     * 商家编码
     */
    private String merchantCode;
}
