package com.fangcang.supplier.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vinney on 2018/10/24.
 */
@Data
public class AddSupplyCostRequestDTO extends BaseDTO {

    private static final long serialVersionUID = 8094731420779541060L;

    private Integer id;

    private Integer supplyId;

    private String costName;

    private Date beginUseDate;

    private Date endUseDate;

    private BigDecimal costPrice;

    private Integer isActive;

}
