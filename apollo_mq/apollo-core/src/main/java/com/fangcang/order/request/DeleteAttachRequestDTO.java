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
public class DeleteAttachRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 2522388905920156253L;
    /**
     * 附件ID
     */
    @NotNull
    private Integer orderAttachId;


}
