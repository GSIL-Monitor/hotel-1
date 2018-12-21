package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/7/5.
 */
@Data
public class HotelDeratePolicyRequestDTO implements Serializable {
    private static final long serialVersionUID = -1294207021616233470L;

    /**
     * 减免政策名称
     */
    private String name;

    /**
     * 减免政策明细
     */
    private List<HotelDeratePolicyDailyDTO> dayList;
}
