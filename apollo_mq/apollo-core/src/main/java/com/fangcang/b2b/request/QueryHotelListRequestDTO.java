package com.fangcang.b2b.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 11:32
 * @Description: 酒店列表RequestDTO
 */
@Data
public class QueryHotelListRequestDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = -7029957554382338887L;

    /**
     * 城市编码
     */
    @NotEmpty(message = "城市不能为null")
    private String cityCode;

    /**
     * 入住日
     */
    @NotNull(message = "入住时间不能为null")
    private Date checkInDate;

    /**
     * 离店日期
     */
    @NotNull(message = "离店日期不能为null")
    private Date checkOutDate;

    /**
     * 酒店id
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 商业区编码
     */
    private String businessCode;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 酒店星级
     */
    private List<Integer> hotelStars;

    /**
     * 价格范围:最低价
     */
    private BigDecimal minPrice;

    /**
     * 价格范围:最高价
     */
    private BigDecimal maxPrice;

    /**
     * 商家编码
     */
    private String merchantCode;
}
