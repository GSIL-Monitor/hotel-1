package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/30 16:00
 * @Description: 供应商用户日志DTO
 */
@Data
public class SupplyUserLogDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5535082548646555883L;

    /**
     * 操作内容
     */
    private String Content;
}
