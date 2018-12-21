package com.fangcang.product.response;

import com.fangcang.product.dto.SupplierDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class AllSharedPoolResponseDTO implements Serializable{

    private static final long serialVersionUID = 5801787246698156430L;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 供应商列表
     */
    private List<SupplierDTO> supplierList;

}
