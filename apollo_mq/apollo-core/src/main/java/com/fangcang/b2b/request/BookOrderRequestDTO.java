package com.fangcang.b2b.request;

import com.fangcang.b2b.dto.AdditionChargeDTO;
import com.fangcang.b2b.dto.HotelDeratePolicyRequestDTO;
import com.fangcang.b2b.dto.PreBookPricePlanDTO;
import com.fangcang.b2b.dto.RatePricePlanDTO;
import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class BookOrderRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 6818175437602058171L;

    /**
     * 订单总额
     */
    @NotNull(message = "orderSum不能为空")
    private BigDecimal orderSum;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 特殊要求
     */
    private String specialRequest;

    /**
     * 是否团房订单
     */
    @NotNull(message = "isGroupRoom不能为Null")
    private Integer isGroupRoom;

    /**
     *入住人名称
     */
    @NotNull(message = "guestList不能为null")
    private List<String> guestList;

    /**
     * 产品明细
     */
    @NotNull(message = "ratePlanList不能为null")
    private List<PreBookPricePlanDTO> ratePlanList;

    /**
     * 附加项
     */
    private List<AdditionChargeDTO> additionChargeList;

    /**
     * 团号
     */
    private String groupNo;

    /**
     * 联系人名称
     */
    @NotEmpty(message = "contractName不能为空")
    private String contractName;

    /**
     * 联系人电话
     */
    @NotEmpty(message = "contractPhone不能为空")
    private String contractPhone;

    /**
     * 减免政策
     */
    private List<HotelDeratePolicyRequestDTO> deratePolicyList;
}
