package com.fangcang.hotelinfo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/25.
 */
@Data
public class TransferHotelInfoDataRequestDTO implements Serializable {
    private static final long serialVersionUID = 8581534443815077407L;

    /**
     * 酒店ID
     */
    private Long [] hotels;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 城市编码
     */
    @NotEmpty(message = "countryCode不能为空")
    private String countryCode;
}
