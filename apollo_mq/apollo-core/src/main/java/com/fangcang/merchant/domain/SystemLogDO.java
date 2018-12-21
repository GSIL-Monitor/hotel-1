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

@Table(name = "t_system_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemLogDO implements Serializable {
    private static final long serialVersionUID = 7718384325192044788L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String className;
    private String method;
    private String request;
    private String response;
    private Date start;
    private Date end;
    private Long cost;
    private Integer status;
    private String creator;
    private Date createTime;
}
