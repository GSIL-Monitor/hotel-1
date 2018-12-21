package com.fangcang.merchant.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class SaveStaffResponseDTO implements Serializable {

    private static final long serialVersionUID = -3592377512205763503L;
    /**
     * 用户ID
     */
    private Long userId;
}
