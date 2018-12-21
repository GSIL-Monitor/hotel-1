package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class DeleteProductRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 2691641555788211562L;
    /**
     * 供货产品id
     */
    @NotNull
    private Integer supplyProductId;


}
