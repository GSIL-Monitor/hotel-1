package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class BedTypeDTO implements Serializable{
    private static final long serialVersionUID = -6324700367198695482L;

    /**
     * 床型ID
     */
    private Long bedTypeId;

    /**
     * 床型名称
     */
    private String bedTypeName;
}
