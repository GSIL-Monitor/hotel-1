package com.fangcang.merchant.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/6/4.
 */
@Data
public class RoleResourceDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8735794060791102711L;

    private String roleCode;
    private String urlPattern;

}
