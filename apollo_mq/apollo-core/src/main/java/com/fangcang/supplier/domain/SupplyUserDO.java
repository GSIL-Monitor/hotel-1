package com.fangcang.supplier.domain;

import lombok.Data;

import java.util.Date;
/**
* @Description:    供应商里的账号DO
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/28 11:38
*/
@Data
public class SupplyUserDO {


    private Long supplyUserId;

    private String userName;

    private String password;

    private String realName;

    private String phone;

    private Date createTime;

    private String creator;

    private Date modifyTime;

    private String modifier;

    private Integer isActive;

    private Long supplyId;

    private Integer master;


}