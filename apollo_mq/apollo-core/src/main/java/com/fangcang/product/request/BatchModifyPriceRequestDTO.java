package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.PriceInfoOperateDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class BatchModifyPriceRequestDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = 1400734355346175370L;

    /**
     * 调价基准
     */
    @NotNull(message = "priceStandard不能为Null")
    private Integer priceStandard;

    /**
     * 调整价格
     */
    @NotNull(message = "modifyPriceStr不能为Null")
    private Integer [] modifyPriceStr;

    @NotNull(message = "priceInfoOperateList不能为Null")
    private List<PriceInfoOperateDTO> priceInfoOperateList;

    /**
     * 商家编码
     */
    private String merchantCode;
}
