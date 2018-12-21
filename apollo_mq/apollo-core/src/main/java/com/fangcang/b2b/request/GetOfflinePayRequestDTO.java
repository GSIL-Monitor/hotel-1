package com.fangcang.b2b.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class GetOfflinePayRequestDTO implements Serializable {

    private static final long serialVersionUID = 4707902701408853893L;
    /**
     * 商家编码
     */
    private String merchantCode;
}
