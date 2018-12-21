package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 产品价格明细
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class PriceDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = 92160947701030318L;

    /**
     * 产品每日价格详情
     */
    private List<SupplyProductPriceResponseDTO> dayList;


}
