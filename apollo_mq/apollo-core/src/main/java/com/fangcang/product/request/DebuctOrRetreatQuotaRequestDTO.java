package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.QuotaStateDailyDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class DebuctOrRetreatQuotaRequestDTO extends BaseDTO implements Serializable{

    /**
     * 供应订单ID
     */
    @NotNull(message = "supplyOrderId不能为Null")
    private Long supplyOrderId;

    /**
     * 供货单编码
     */
    @NotNull(message = "supplyOrderCode不能为Null")
    private String supplyOrderCode;

    /**
     * 价格计划ID
     */
    @NotNull(message = "pricePlanId不能为Null")
    private Long pricePlanId;

    /**
     * 配额类型(1 扣配额,2 退配额)
     */
    @NotNull(message = "quotaType不能为Null")
    private Integer quotaType;

    /**
     * 配额账号ID
     */
    @NotNull(message = "quotaAccountId不能为Null")
    private Long quotaAccountId;

    /**
     * 订单号
     */
    @NotNull(message = "orderCode不能为Null")
    private String orderCode;

    @NotNull(message = "startDate不能为Null")
    private Date startDate;

    @NotNull(message = "endDate不能为Null")
    private Date endDate;

    @NotNull(message = "quotaStateDailyList不能为Null")
    private List<QuotaStateDailyDTO> quotaStateDailyList;

    /**
     * 商家编码
     */
    private String merchantCode;

}
