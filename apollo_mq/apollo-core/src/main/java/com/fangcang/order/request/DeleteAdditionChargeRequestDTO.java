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
public class DeleteAdditionChargeRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -8845187213405416344L;
    /**
     * 供应附加项ID
     */
    @NotNull
    private Integer supplyAdditionChargeId;


}
