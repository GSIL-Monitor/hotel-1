package com.fangcang.b2b.request;

import com.fangcang.b2b.dto.HotelDeratePolicyRequestDTO;
import com.fangcang.b2b.dto.PreBookPricePlanDTO;
import com.fangcang.b2b.dto.RatePricePlanDTO;
import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class PreBookRequestDTO implements Serializable {
    private static final long serialVersionUID = 7246285806300702078L;

    /**
     * 是否团房订单
     */
    @NotNull(message = "isGroupRoom不能为Null")
    private Integer isGroupRoom;
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
     * 产品明细
     */
    @NotNull(message = "ratePlanList不能为null")
    private List<PreBookPricePlanDTO> ratePlanList;

    /**
     * 附加项
     */
    private List<HotelAdditionalDTO> additionalList;

    /**
     * 减免政策明细
     */
    private List<HotelDeratePolicyRequestDTO> deratePolicyList;

    /**
     * 取消政策
     */
    private String cancelPolicy;
}
