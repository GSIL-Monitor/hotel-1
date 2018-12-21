package com.fangcang.order.response;

import com.fangcang.order.dto.OrderCheckDetailDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 发单-产品详情对象
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ProductDetailResponseDTO implements Serializable {
    private static final long serialVersionUID = -7471371366383656437L;

    /**
     * 房型名称
     */
    private String roomTypeName;
    /**
     * 价格计划名称
     */
    private String rateplanName;
    /**
     * 日期段列表
     */
    private List<OrderCheckDetailDTO> dateSegmentList;

}
