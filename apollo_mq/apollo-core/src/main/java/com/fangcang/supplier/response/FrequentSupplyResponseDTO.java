package com.fangcang.supplier.response;

import com.fangcang.supplier.dto.FrequentSupplyDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 20:10
 * @Description:
 */
@Data
public class FrequentSupplyResponseDTO  implements Serializable {

    private static final long serialVersionUID = 6600136578364644669L;

    /**
     * 常用供应商列表
     */
    List<FrequentSupplyDTO> supplierList;
}
