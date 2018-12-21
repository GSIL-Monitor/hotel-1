package com.fangcang.b2b.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 14:41
 * @Description: 获取酒店详情RequestDTO
 */
@Data
public class GetHotelDetailRequestDTO implements Serializable {

    private static final long serialVersionUID = 8801031985050986321L;

    /**
     * 酒店ID
     */
    @NotNull(message = "酒店id不能为null")
    private Long hotelId;
    /**
     * 入住日期
     */
    @NotNull(message = "入住时间不能为null")
    private Date checkInDate;

    /**
     * 离店日期
     */
    @NotNull(message = "离店日期不能为null")
    private Date checkOutDate;

    /**
     * 商家编码(会话里面获取)
     */
    private String merchantCode;

    /**
     * 分销商编码(会话里面获取)
     */
    private String agentCode;

    /**
     * 产品类型(1 散房  2 团房)
     */
    @NotEmpty(message = "产品类型不能为null")
    private String productType;


}
