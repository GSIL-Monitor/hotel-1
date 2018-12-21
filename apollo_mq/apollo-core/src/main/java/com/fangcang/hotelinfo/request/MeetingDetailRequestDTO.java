package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class MeetingDetailRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 8700847072282773411L;

    @NotNull(message = "meetingId不能为Null")
    private Long meetingId;
}
