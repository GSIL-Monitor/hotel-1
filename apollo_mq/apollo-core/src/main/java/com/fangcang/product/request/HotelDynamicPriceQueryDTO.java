package com.fangcang.product.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class HotelDynamicPriceQueryDTO extends BaseQueryConditionDTO implements Serializable{
    private static final long serialVersionUID = 6789045419004530819L;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 开始日期
     */
    @NotNull(message = "startDate不能为Null")
    private Date startDate;

    /**
     * 结束日期
     */
    @NotNull(message = "endDate不能为Null")
    private Date endDate;

    /**
     * 酒店星级
     */
    private List<Integer> hotelStarList;
}
