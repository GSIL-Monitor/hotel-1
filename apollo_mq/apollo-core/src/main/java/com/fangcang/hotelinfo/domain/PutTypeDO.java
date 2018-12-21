package com.fangcang.hotelinfo.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class PutTypeDO extends BaseDO{

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 会议室ID
     */
    private Long meetingId;


    /**
     * 摆放类型(1 剧场士 2课桌士 3 U型士 4 回型士 5宴会士 6 董会士)
     */
    private Integer putType;

    /**
     * 适用人数
     */
    private String applicableNumber;
}
