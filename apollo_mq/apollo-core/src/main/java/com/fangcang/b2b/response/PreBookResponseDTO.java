package com.fangcang.b2b.response;

import com.fangcang.b2b.dto.AdditionChargeDTO;
import com.fangcang.b2b.dto.HotelOrderDerateDTO;
import com.fangcang.b2b.dto.PreBookPricePlanDTO;
import com.fangcang.b2b.dto.RatePricePlanDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class PreBookResponseDTO implements Serializable {
    private static final long serialVersionUID = 9092213371693268070L;


    /**
     * 产品明细
     */
    private List<PreBookPricePlanDTO> ratePlanList;

    /**
     * 附加项
     */
    private List<AdditionChargeDTO> additionChargeList;

    /**
     * 联系人
     */
    private String contractName;

    /**
     *联系电话
     */
    private String contractPhone;

    /**
     * 订单总额
     */
    private BigDecimal orderSum;

    /**
     * 免房政策信息
     */
    private List<HotelOrderDerateDTO> derateList;

    /**
     * 取消政策
     */
    private String cancelPolicy;
}
