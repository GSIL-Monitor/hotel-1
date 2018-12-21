package com.fangcang.message.remote.response.email;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailConfigDTO implements Serializable {

    private Long id;

    /**
     * 发送邮件的服务器的IP
     */
    private String emailServerHost;

    /**
     * 发送邮件的服务器的端口
     */
    private String emailServerPort;

    /**
     * 发件人邮箱
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
