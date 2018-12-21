package com.fangcang.b2b.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/7/24.
 */
@Data
public class WeChatUserInfoDTO extends BaseDTO implements Serializable{

    /**
     * 微信昵称
     */
    private String wxName;

    /**
     * 微信头像地址
     */
    private String wxHead;
}
