package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class KeyWordDTO implements Serializable {
    private static final long serialVersionUID = -7986816122396005273L;

    private Long historyId;

    /**
     * 关键字
     */
    private String keyWord;
}
