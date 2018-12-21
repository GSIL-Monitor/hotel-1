package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.SaleStateDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class SaleStateRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -2013967431109232176L;

    /**
     * 批量上下架
     */
    @NotNull(message = "saleStateList不能为Null")
    private List<SaleStateDTO> saleStateList;

    /**
     * 商家编码
     */
    private String merchantCode;
}
