package com.fangcang.hotelinfo.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

/**
 * Created by ASUS on 2018/6/26.
 */
@Data
public class TransferHotelInfoResult extends BaseDTO {

    /**
     * 结果 1 成功 0失败
     */
    private Integer result;

    /**
     * 原来酒店ID
     */
    private Long oldHotelId;

    /**
     * 新的酒店ID
     */
    private Long newHotelId;
}
