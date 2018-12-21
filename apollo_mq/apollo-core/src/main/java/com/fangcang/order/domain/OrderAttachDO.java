package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_attach")
public class OrderAttachDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     */
    private Byte type;

    /**
     * 附件名称
     */
    private String name;

    /**
     * url地址
     */
    private String url;

    /**
     * 实际地址(带后缀的文件名称)
     */
    private String realpath;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     *
     * @return type - 附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     *
     * @param type 附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取附件名称
     *
     * @return name - 附件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置附件名称
     *
     * @param name 附件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取url地址
     *
     * @return url - url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url地址
     *
     * @param url url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取实际地址(带后缀的文件名称)
     *
     * @return realpath - 实际地址(带后缀的文件名称)
     */
    public String getRealpath() {
        return realpath;
    }

    /**
     * 设置实际地址(带后缀的文件名称)
     *
     * @param realpath 实际地址(带后缀的文件名称)
     */
    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}