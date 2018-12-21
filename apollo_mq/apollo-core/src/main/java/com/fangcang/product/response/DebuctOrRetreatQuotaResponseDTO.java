package com.fangcang.product.response;

import com.fangcang.product.dto.QuotaStateDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class DebuctOrRetreatQuotaResponseDTO implements Serializable{
    private static final long serialVersionUID = -6775700025348748315L;

    private List<QuotaStateDTO> quotaStateList;
}
