package com.fangcang.product.response;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.SharedPoolPricePlanDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SharedPoolResponseDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = -4328457534662757101L;
    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;

    private List<SharedPoolPricePlanDTO> sharedPoolPricePlanList;
}
