package com.fangcang.message.weixin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="t_weixin_config")
public class WxConfigDO implements Serializable {
    private static final long serialVersionUID = -9158958518006423192L;

    @Id
    @Column(name="appid")
    private String appid;

    @Column(name="appsecret")
    private String appsecret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
