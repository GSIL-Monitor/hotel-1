package com.fangcang.hotelinfo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class PutTypeDTO implements Serializable {

    private static final long serialVersionUID = -8920406148001699869L;
    /**
     * 摆放类型(1 剧场士 2课桌士 3 U型士 4 回型士 5宴会士 6 董会士)
     */
    private Integer putType;

    /**
     * 适用人数
     */
    private String applicableNumber;
}
