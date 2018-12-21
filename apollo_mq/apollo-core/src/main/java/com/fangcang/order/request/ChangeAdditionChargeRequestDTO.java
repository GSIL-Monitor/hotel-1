package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeAdditionChargeRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 421632552874206579L;

    /**
     * 供货附加项ID
     */
    @NotNull
    private Integer supplyAdditionChargeId;

    /**
     * 附加项名称
     */
    @NotBlank
    private String name;

    /**
     * 附加项总底价
     */
    @NotNull
    private BigDecimal basePriceSum;

    /**
     * 附加项总售价
     */
    @NotNull
    private BigDecimal salePriceSum;

    /**
     * 购买数量
     */
    @NotNull
    private Integer num;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 附加项底价
     */
    private BigDecimal basePrice;

    /**
     * 附加项售价
     */
    private BigDecimal salePrice;
}
