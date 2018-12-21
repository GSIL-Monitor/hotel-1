package com.fangcang.b2b.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class BookOrderResponseDTO implements Serializable {
    private static final long serialVersionUID = -1593211044030405967L;

    /**
     * 订单ID
     */
    private Long orderId;
}
