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
public class ChangeGuideRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 8932103745132559878L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;

    /**
     * 团号，团房订单才有值
     */
    private String groupNo;

    /**
     * 向导
     */
    private String guide;

    /**
     * 向导电话
     */
    private String guidePhone;


}
