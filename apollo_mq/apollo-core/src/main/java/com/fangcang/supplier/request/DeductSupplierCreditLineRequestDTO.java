package com.fangcang.supplier.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 扣除分销商剩余额度RequestDTO
 */
@Data
public class DeductSupplierCreditLineRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 4076802272928761021L;

    /**
     * 供应商编码
     */
    @NotEmpty(message = "供应商编码不能为null")
    private String supplierCode;

    /**
     * 扣除额度
     */
    private BigDecimal debuctAmount;

    /**
     * 操作类型
     * 1.扣除额度 2.退还额度
     */
    @NotNull(message = "操作类型不能为null")
    private Integer operateType;

    /**
     * 商家编码
     */
    @NotNull
    private String merchantCode;

    /**
     * 订单编码
     */
    @NotNull
    private String orderCode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 操作人
     */
    private String operator;
}
