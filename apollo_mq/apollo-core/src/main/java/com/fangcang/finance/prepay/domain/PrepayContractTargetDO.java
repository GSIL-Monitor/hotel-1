package com.fangcang.finance.prepay.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_prepay_contract_target")
public class PrepayContractTargetDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 合约id
     */
    @Column(name = "contract_id")
    private Integer contractId;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 目标间夜
     */
    @Column(name = "room_night")
    private Integer roomNight;

    /**
     * 已完成间夜
     */
    @Column(name = "done_room_night")
    private Integer doneRoomNight;

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
     * 获取合约id
     *
     * @return contract_id - 合约id
     */
    public Integer getContractId() {
        return contractId;
    }

    /**
     * 设置合约id
     *
     * @param contractId 合约id
     */
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    /**
     * 获取年
     *
     * @return year - 年
     */
    public String getYear() {
        return year;
    }

    /**
     * 设置年
     *
     * @param year 年
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 获取月
     *
     * @return month - 月
     */
    public String getMonth() {
        return month;
    }

    /**
     * 设置月
     *
     * @param month 月
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * 获取目标间夜
     *
     * @return room_night - 目标间夜
     */
    public Integer getRoomNight() {
        return roomNight;
    }

    /**
     * 设置目标间夜
     *
     * @param roomNight 目标间夜
     */
    public void setRoomNight(Integer roomNight) {
        this.roomNight = roomNight;
    }

    /**
     * 获取已完成间夜
     *
     * @return done_room_night - 已完成间夜
     */
    public Integer getDoneRoomNight() {
        return doneRoomNight;
    }

    /**
     * 设置已完成间夜
     *
     * @param doneRoomNight 已完成间夜
     */
    public void setDoneRoomNight(Integer doneRoomNight) {
        this.doneRoomNight = doneRoomNight;
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