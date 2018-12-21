package com.fangcang.supplier.response;

import com.fangcang.supplier.dto.SupplyInfoDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:35
 * @Description: 供应商响应列表
 */
@Data
public class SupplyListResponseDTO implements Serializable {

    private static final long serialVersionUID = 3367240569891033558L;

    private SupplyInfoDTO supplyInfoDTO;
}
