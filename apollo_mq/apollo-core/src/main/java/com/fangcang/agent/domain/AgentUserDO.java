package com.fangcang.agent.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
* @Description:    分销商的账号DO
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/28 11:38
*/
@Data
@Table(name="t_agent_user")
@EqualsAndHashCode(callSuper = false)
public class AgentUserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_user_id")
    private Long agentUserId;

    private String userName;

    private String password;

    private String realName;

    private String phone;

    private Date createTime;

    private String creator;

    private Date modifyTime;

    private String modifier;

    private Integer isActive;

    private Long agentId;

    private Integer master;




}