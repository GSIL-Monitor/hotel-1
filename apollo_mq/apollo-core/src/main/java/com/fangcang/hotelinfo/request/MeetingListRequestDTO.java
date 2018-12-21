package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class MeetingListRequestDTO extends BaseDTO implements Serializable {

    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为Null")
    private Long hotelId;
}
