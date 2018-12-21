package com.fangcang.product.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class ProductRoomTypeListQueryDTO extends BaseQueryConditionDTO implements Serializable{
    private static final long serialVersionUID = -4471659586094024116L;

    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为Null")
    private Long hotelId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

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
     * 商家编码
     */
    private String merchantCode;
}
