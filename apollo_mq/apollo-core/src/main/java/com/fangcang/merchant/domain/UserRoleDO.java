package com.fangcang.merchant.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "t_user_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userRoleId;

    private Long userId;

    private Long roleId;
}
