package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/30 15:52
 * @Description: 新增供应商银行卡ResponseDTO
 */
@Data
public class AddSupplyBankCardResponseDTO  implements Serializable {

    private static final long serialVersionUID = 3076269175752687274L;

    /**
     * 新增银行卡id
     */
    private Long bankCardId;
}
