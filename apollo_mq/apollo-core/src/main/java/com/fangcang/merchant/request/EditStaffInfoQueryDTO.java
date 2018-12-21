package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class EditStaffInfoQueryDTO implements Serializable {
    private static final long serialVersionUID = 4654676244593109730L;

    /**
     * 用户ID
     */
    @NotNull(message = "userId不能为Null")
    private Long userId;
}
