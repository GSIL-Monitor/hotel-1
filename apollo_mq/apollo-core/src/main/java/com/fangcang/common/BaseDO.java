package com.fangcang.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/16.
 */
@Data
public class BaseDO implements Serializable{
    private static final long serialVersionUID = 7718384325192044788L;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
