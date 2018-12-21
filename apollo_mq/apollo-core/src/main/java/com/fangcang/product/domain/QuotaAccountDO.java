package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

/**
 * Created by ASUS on 2018/5/22.
 */
@Data
public class QuotaAccountDO extends BaseDO{

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
}
