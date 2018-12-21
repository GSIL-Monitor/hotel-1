package com.fangcang.product.response;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.DebuctQuotaQueryPricePlanDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/2.
 */
@Data
public class DebuctQuotaQueryResponseDTO extends BaseDTO implements Serializable {

    /**
     * 订单编码
     */
    private String orderCode;

    private List<DebuctQuotaQueryPricePlanDTO> debuctQuotaQueryPricePlanDTOS;
}
