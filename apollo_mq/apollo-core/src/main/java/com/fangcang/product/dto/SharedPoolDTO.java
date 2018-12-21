package com.fangcang.product.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SharedPoolDTO extends BaseDTO implements Serializable{

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 是否共享(1 共享  ,0 不共享)
     */
    private Integer isShare;

    private List<SharedPoolPricePlanDTO> sharedPoolPricePlanList;
}
