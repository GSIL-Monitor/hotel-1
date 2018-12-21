package com.fangcang.agent.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class UserAccountLogDTO extends BaseDTO implements Serializable {
    /**
     * 操作内容
     */
    private String content;
}
