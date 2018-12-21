package com.fangcang.b2b.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class HotelOrderNoteDTO implements Serializable {

    private static final long serialVersionUID = 5432139309682086750L;

    /**
     * 1: 分销商备注，2：供应商备注 ，3：内部备注
     */
    private Integer noteType;

    /**
     * 备注
     */
    private String note;

    /**
     * 备注对象
     */
    private String noteObject;

    /**
     * 备注人
     */
    private String creator;

    /**
     * 备注时间
     */
    private String createTime;

}