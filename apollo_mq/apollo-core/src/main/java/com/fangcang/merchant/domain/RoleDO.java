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

@Table(name="t_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long roleId;

    /*
     * 角色编码
     */
    @Column(name = "role_code")
    private String roleCode;
    
    /*
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;
    
    /*
     * 描述
     */
    @Column(name = "description")
    private String description;
    
    /*
     * 是否有效
     */
    @Column(name = "is_active")
    private Integer isActive;

    /*
     * 创建者
     */
    @Column(name = "creator")
    private String creator;
    
    /*
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    
    /*
     * 修改者
     */
    @Column(name = "modifier")
    private String modifier;
    
    /*
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;
}
