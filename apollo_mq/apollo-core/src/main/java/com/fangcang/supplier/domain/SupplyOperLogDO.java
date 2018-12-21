package com.fangcang.supplier.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name="t_supply_oper_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplyOperLogDO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long supplyOperLogId;

    /*
     * 供应商ID
     */
    @Column(name = "oper_supply_id")
    private Long operSupplyId;
    
    /*
     * 供应商用户ID
     */
    @Column(name = "oper_supply_user_id")
    private Long operSupplyUserId;
    
    /*
     * 日志描述
     */
    @Column(name = "content")
    private String content;
    
    /*
     * 操作者
     */
    @Column(name = "creator")
    private String creator;
    
    /*
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
