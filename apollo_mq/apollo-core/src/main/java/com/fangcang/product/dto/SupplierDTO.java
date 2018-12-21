package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SupplierDTO implements Serializable{

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 共享池集合
     */
    private List<SharedPoolDTO> sharedPoolList;
}
