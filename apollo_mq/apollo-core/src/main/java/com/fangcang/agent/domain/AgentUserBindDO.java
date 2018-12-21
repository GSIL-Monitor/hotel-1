package com.fangcang.agent.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/6 09:42
 * @Description: b2b微信绑定用户信息
 */
@Table(name = "t_agent_user_bind")
@Data
public class AgentUserBindDO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应的商家编码
     */
    @Column(name = "merchant_code")
    private String merchantCode;

    /**
     * 对应的分销商编码
     */
    @Column(name = "agent_code")
    private String agentCode;

    /**
     * 对应的分销商用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 微信openID
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;
}
