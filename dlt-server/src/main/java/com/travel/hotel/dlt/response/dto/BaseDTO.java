package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/17.
 */
@Data
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = -7898391882110177329L;
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

    /**
     * 操作人全名
     */
    private String operator;
}
