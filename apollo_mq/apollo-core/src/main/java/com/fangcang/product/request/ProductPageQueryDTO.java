package com.fangcang.product.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by ASUS on 2018/6/4.
 */
@Data
public class ProductPageQueryDTO extends BaseQueryConditionDTO  implements Serializable {

    private static final long serialVersionUID = 7891896887052188412L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 商家编码
     */
    private String merchantCode;
}
