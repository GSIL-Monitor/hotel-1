package com.fangcang.hotelinfo.request;

import lombok.Data;

import java.io.PipedReader;
import java.io.Serializable;

import com.fangcang.common.BaseDTO;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class DeleteRoomTypeRequestDTO extends BaseDTO implements Serializable {

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 商家编码
     */
    private String merchantCode;
}
