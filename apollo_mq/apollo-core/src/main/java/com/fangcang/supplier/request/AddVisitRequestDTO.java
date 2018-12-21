package com.fangcang.supplier.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 17:40
 * @Description: 拜访记录请求
 */
@Data
public class AddVisitRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 624132069216780624L;
    /**
     * 供应商ID
     */
    @NotNull(message = "供应商id不能为空")
    private Long supplyId;

    /**
     * 商家编码
     */
    private String supplyCode;

    /**
     * 拜访人
     */
    private String visitor;

    /**
     * 拜访时间
     */
    private String visitTime;

    /**
     * 拜访内容
     */
    private String content;
}
