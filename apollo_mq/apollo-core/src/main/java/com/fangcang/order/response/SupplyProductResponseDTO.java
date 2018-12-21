package com.fangcang.order.response;

import com.fangcang.order.dto.OrderCheckDetailDTO;
import com.fangcang.order.dto.SupplyProductDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 供货单产品对象
 */
@Data
public class SupplyProductResponseDTO extends SupplyProductDTO implements Serializable {

    private static final long serialVersionUID = 975033672504857620L;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 产品价格明细
     */
    private List<SupplyProductPriceDTO> productPriceDTOList;

    /**
     * 产品对账明细
     */
    private List<OrderCheckDetailDTO> checkDetailDTOList;


}