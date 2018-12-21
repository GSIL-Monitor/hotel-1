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

@Table(name="t_resource")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceDO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long resourceId;

    /*
     * 菜单编码
     */
    @Column(name = "resource_code")
    private String resourceCode;
    
    /*
     * 菜单名称
     */
    @Column(name = "resource_name")
    private String resourceName;
    
    /*
     * 菜单类型
     */
    @Column(name = "resource_type")
    private String resourceType;
    
    /*
     * 菜单映射地址
     */
    @Column(name = "url_pattern")
    private String urlPattern;

    /*
     * 菜单描述
     */
    @Column(name = "description")
    private String description;
    
    /*
     * 父菜单
     */
    @Column(name = "pid")
    private Long pid;
    
    /*
     * 级别
     */
    @Column(name = "level")
    private Integer level;
    
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
    @Column(name = "create_date")
    private Date createTime;
    
    /*
     * 修改者
     */
    @Column(name = "modifier")
    private String modifier;
    
    /*
     * 修改时间
     */
    @Column(name = "modify_date")
    private Date modifyTime;

    //父节点编码
    private String pCode;
    //父节点名称
    private String pName;
    //父节点等级
    private Integer pLevel;

    //父节点类型
    private String pType;

    //父节点URL
    private String pUrl;

}
