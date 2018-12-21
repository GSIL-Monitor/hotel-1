package com.fangcang.product.response;

import com.fangcang.product.dto.PricePlanDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class PricePlanListResponseDTO implements Serializable {
    private static final long serialVersionUID = -2271757544339493454L;

    /**
     * 价格计划列表
     */
    private List<PricePlanDTO> pricePlanList;
}
