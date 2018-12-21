package com.fangcang.product.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_htlpro_restrict")
public class RestrictDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rate_plan_id")
    private Integer ratePlanId;

    @Column(name = "advance_booking_days")
    private Integer advanceBookingDays;

    @Column(name = "advance_booking_hours")
    private Integer advanceBookingHours;

    @Column(name = "occupancy_of_days")
    private Integer occupancyOfDays;

    @Column(name = "number_of_booking")
    private Integer numberOfBooking;

    /**
     * 1-已经预定不可取消；2-提前取消
     */
    @Column(name = "cancel_type")
    private Integer cancelType;

    @Column(name = "cancel_days")
    private Integer cancelDays;

    @Column(name = "cancel_hours")
    private String cancelHours;

    @Column(name = "last_confirm_days")
    private Integer lastConfirmDays;

    @Column(name = "last_confirm_hours")
    private Integer lastConfirmHours;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_date")
    private Date modifyDate;

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
     * @return rate_plan_id
     */
    public Integer getRatePlanId() {
        return ratePlanId;
    }

    /**
     * @param ratePlanId
     */
    public void setRatePlanId(Integer ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    /**
     * @return advance_booking_days
     */
    public Integer getAdvanceBookingDays() {
        return advanceBookingDays;
    }

    /**
     * @param advanceBookingDays
     */
    public void setAdvanceBookingDays(Integer advanceBookingDays) {
        this.advanceBookingDays = advanceBookingDays;
    }

    /**
     * @return advance_booking_hours
     */
    public Integer getAdvanceBookingHours() {
        return advanceBookingHours;
    }

    /**
     * @param advanceBookingHours
     */
    public void setAdvanceBookingHours(Integer advanceBookingHours) {
        this.advanceBookingHours = advanceBookingHours;
    }

    /**
     * @return occupancy_of_days
     */
    public Integer getOccupancyOfDays() {
        return occupancyOfDays;
    }

    /**
     * @param occupancyOfDays
     */
    public void setOccupancyOfDays(Integer occupancyOfDays) {
        this.occupancyOfDays = occupancyOfDays;
    }

    /**
     * @return number_of_booking
     */
    public Integer getNumberOfBooking() {
        return numberOfBooking;
    }

    /**
     * @param numberOfBooking
     */
    public void setNumberOfBooking(Integer numberOfBooking) {
        this.numberOfBooking = numberOfBooking;
    }

    /**
     * 获取1-已经预定不可取消；2-提前取消
     *
     * @return cancel_type - 1-已经预定不可取消；2-提前取消
     */
    public Integer getCancelType() {
        return cancelType;
    }

    /**
     * 设置1-已经预定不可取消；2-提前取消
     *
     * @param cancelType 1-已经预定不可取消；2-提前取消
     */
    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    /**
     * @return cancel_days
     */
    public Integer getCancelDays() {
        return cancelDays;
    }

    /**
     * @param cancelDays
     */
    public void setCancelDays(Integer cancelDays) {
        this.cancelDays = cancelDays;
    }

    /**
     * @return cancel_hours
     */
    public String getCancelHours() {
        return cancelHours;
    }

    /**
     * @param cancelHours
     */
    public void setCancelHours(String cancelHours) {
        this.cancelHours = cancelHours;
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
     * @return modify_date
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getLastConfirmDays() {
        return lastConfirmDays;
    }

    public void setLastConfirmDays(Integer lastConfirmDays) {
        this.lastConfirmDays = lastConfirmDays;
    }

    public Integer getLastConfirmHours() {
        return lastConfirmHours;
    }

    public void setLastConfirmHours(Integer lastConfirmHours) {
        this.lastConfirmHours = lastConfirmHours;
    }
}