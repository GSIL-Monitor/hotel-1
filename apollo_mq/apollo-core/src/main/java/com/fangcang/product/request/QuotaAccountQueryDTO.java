package com.fangcang.product.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

/**
 * Created by ASUS on 2018/6/21.
 */
@Data
public class QuotaAccountQueryDTO extends BaseQueryConditionDTO {

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;
    /**
     * 配额账号IDS
     */
    private String quotaAccountIds;
}
