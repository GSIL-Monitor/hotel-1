package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vinney on 2018/10/25.
 */
@Data
public class SupplyOtherCostDTO extends BaseDTO {

    private static final long serialVersionUID = -3725491024453521649L;

    private Integer id;

    private Integer supplyId;

    private String costName;

    private Date beginUseDate;

    private Date endUseDate;

    private BigDecimal costPrice;

    private Integer isActive;

}
