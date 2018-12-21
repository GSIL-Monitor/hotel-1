package com.fangcang.message.weixin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_weixin_msgtemplate")
public class WxMsgTemplateDO implements Serializable {
    private static final long serialVersionUID = -9158958518006423192L;

    /**
     * 店铺消息模板ID（主键）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 模板编号
     */
    @Column(name="templateCode")
    private String templateCode;

    /**
     * 模板名称
     */
    @Column(name="templatetitle")
    private String templateTitle;

    /**
     * 微信模板ID
     */
    @Column(name="wxTemplateId")
    private String wxTemplateId;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getWxTemplateId() {
        return wxTemplateId;
    }

    public void setWxTemplateId(String wxTemplateId) {
        this.wxTemplateId = wxTemplateId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }
}
