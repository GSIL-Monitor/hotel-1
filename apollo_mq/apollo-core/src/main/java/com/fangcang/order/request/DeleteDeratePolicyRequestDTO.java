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
public class DeleteDeratePolicyRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 129279559762222342L;
    /**
     * 减免政策id
     */
    @NotNull
    private Integer deratePolicyId;


}
