package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_guest")
public class GuestDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 入住人名称
     */
    @Column(name = "guest_name")
    private String guestName;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 手机
     */
    private String phone;

    /**
     * 证件类型
     */
    @Column(name = "id_type")
    private Byte idType;

    /**
     * 证件号码
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 名
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 姓
     */
    @Column(name = "last_name")
    private String lastName;

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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取入住人名称
     *
     * @return guest_name - 入住人名称
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * 设置入住人名称
     *
     * @param guestName 入住人名称
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取证件类型
     *
     * @return id_type - 证件类型
     */
    public Byte getIdType() {
        return idType;
    }

    /**
     * 设置证件类型
     *
     * @param idType 证件类型
     */
    public void setIdType(Byte idType) {
        this.idType = idType;
    }

    /**
     * 获取证件号码
     *
     * @return id_number - 证件号码
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置证件号码
     *
     * @param idNumber 证件号码
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Byte getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * 获取名
     *
     * @return first_name - 名
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置名
     *
     * @param firstName 名
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 获取姓
     *
     * @return last_name - 姓
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置姓
     *
     * @param lastName 姓
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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