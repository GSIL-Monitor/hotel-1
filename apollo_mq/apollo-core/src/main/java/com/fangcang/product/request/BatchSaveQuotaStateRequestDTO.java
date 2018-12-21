package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.BatchQuotaStateDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class BatchSaveQuotaStateRequestDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = -7963822650166269652L;
    /**
     * 批量保存配额房态
     */
    @NotNull(message = "quotaStateList不能为Null")
    private List<BatchQuotaStateDTO> quotaStateList;

    /**
     * 商家编码
     */
    private String merchantCode;
}
