package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeProductRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -4649423550171567104L;

    /**
     * 供货产品ID
     */
    @NotNull
    private Integer supplyProductId;

    /**
     * 间数
     */
    @NotNull
    private Integer roomNum;
    /**
     * 入住日期
     */
    @NotBlank
    private String checkinDate;
    /**
     * 离店日期
     */
    @NotBlank
    private String checkoutDate;

    /**
     * 供货产品价格明细
     */
    @NotEmpty
    private List<SupplyProductPriceDTO> supplyProductPriceList;


}
