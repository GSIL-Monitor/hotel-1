package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SharedPoolRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 469604646714817781L;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 配额账户名称
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
}
