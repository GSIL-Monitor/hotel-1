package com.fangcang.message.email.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="t_msg_email_config")
@Data
public class EmailConfigDO implements Serializable {
    private static final long serialVersionUID = 7264436401398464187L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="emailserverhost")
    private String emailServerHost;

    /**
     * 发送邮件的服务器的端口
     */
    @Column(name="emailserverport")
    private String emailServerPort;

    @Column(name="username")
    private String userName;

    private String password;
}
