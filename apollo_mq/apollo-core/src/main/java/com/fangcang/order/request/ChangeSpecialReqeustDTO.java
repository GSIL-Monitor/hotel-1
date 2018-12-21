package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeSpecialReqeustDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 4706219594100087495L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 客人特殊要求，最大100个字符
     */
    @NotBlank
    private String specialRequest;


}
