package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_note")
public class OrderNoteDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 1: 分销商备注，2：供应商备注 ，3：内部备注
     */
    @Column(name = "note_type")
    private Byte noteType;

    /**
     * 备注
     */
    private String note;

    /**
     * 备注对象
     */
    @Column(name = "note_object")
    private String noteObject;

    /**
     * 备注人
     */
    private String creator;

    /**
     * 备注时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取1: 分销商备注，2：供应商备注 ，3：内部备注
     *
     * @return note_type - 1: 分销商备注，2：供应商备注 ，3：内部备注
     */
    public Byte getNoteType() {
        return noteType;
    }

    /**
     * 设置1: 分销商备注，2：供应商备注 ，3：内部备注
     *
     * @param noteType 1: 分销商备注，2：供应商备注 ，3：内部备注
     */
    public void setNoteType(Byte noteType) {
        this.noteType = noteType;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取备注对象
     *
     * @return note_object - 备注对象
     */
    public String getNoteObject() {
        return noteObject;
    }

    /**
     * 设置备注对象
     *
     * @param noteObject 备注对象
     */
    public void setNoteObject(String noteObject) {
        this.noteObject = noteObject;
    }

    /**
     * 获取备注人
     *
     * @return creator - 备注人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置备注人
     *
     * @param creator 备注人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取备注时间
     *
     * @return create_time - 备注时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置备注时间
     *
     * @param createTime 备注时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}