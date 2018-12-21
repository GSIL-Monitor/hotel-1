package com.fangcang.b2b.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class HotelChangeGuideRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 2689896735055313891L;

    /**
     * 订单ID
     */
    @NotNull(message = "orderId不能为Null")
    private Long orderId;

    /**
     * 团号
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
