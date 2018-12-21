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
public class AddProductRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 5856531841267160757L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 价格计划ID
     */
    @NotNull
    private Integer ratePlanId;
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
