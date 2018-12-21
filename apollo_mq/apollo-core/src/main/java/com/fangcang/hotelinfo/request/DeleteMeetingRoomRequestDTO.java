package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class DeleteMeetingRoomRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -5917575636428198885L;
    /**
     * 会议室ID
     */
    @NotNull(message = "meetingId不能为Null")
    private Long meetingId;
}
