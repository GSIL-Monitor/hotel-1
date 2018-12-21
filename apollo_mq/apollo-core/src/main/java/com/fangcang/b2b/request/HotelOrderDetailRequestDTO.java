package com.fangcang.b2b.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class HotelOrderDetailRequestDTO implements Serializable {
    private static final long serialVersionUID = -8359607295826871147L;

    @NotNull(message = "orderId不能为Null")
    private Long orderId;
}
