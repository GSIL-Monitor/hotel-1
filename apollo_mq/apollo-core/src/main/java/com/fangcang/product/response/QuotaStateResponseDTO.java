package com.fangcang.product.response;

import com.fangcang.product.dto.QuotaStateDailyDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class QuotaStateResponseDTO implements Serializable{

    private static final long serialVersionUID = 8214777033025111437L;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;

    /**
     * 配额房态每日集合
     */
    private List<QuotaStateDailyDTO> quotaStateDailyList;
}
