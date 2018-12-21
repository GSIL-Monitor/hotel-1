package com.fangcang.product.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/6/19.
 */
@Data
public class DynamicQuotationQueryDTO implements Serializable {
    private static final long serialVersionUID = 5435609139410788218L;

    /**
     * 酒店IDS
     */
    @NotNull(message = "hotelIds不能为Null")
    private Long [] hotelIds;

    /**
     * 开始时间
     */
    @NotNull(message = "startDate不能为Null")
    private Date startDate;

    /**
     * 结束时间
     */
    @NotNull(message = "endDate不能为Null")
    private Date endDate;

    /**
     * 商家编码
     */
    private String merchantCode;
}
