package com.fangcang.merchant.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "t_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    private Long merchantId;

    private String username;

    private String password;

    private String realName;

    private Long department;

    private String description;

    private String phone;

    private Integer isActive;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

    private String  merchantCode;

    private String  merchantName;

    private String secondDomain;

    private String systemName;

    /**
     * 员工对应的权限列表
     */
    private List<RoleDO> roleList;

    /**
     * qq
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 座机
     */
    private String landlineTelephone;
}
