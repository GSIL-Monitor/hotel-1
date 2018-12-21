package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class GetRoleListByResourceIdRequestDTO implements Serializable{
    private static final long serialVersionUID = -9179160103816200551L;

    @NotNull(message = "resourceId不能为Null")
    private Long resourceId;
}
