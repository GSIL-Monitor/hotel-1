package com.fangcang.order.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "o_order")
public class OrderDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单编码
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     */
    @Column(name = "pay_method")
    private Byte payMethod;

    /**
     * 分销商币种
     */
    @Column(name = "sale_currency")
    private String saleCurrency;

    /**
     * 渠道编码
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 订单总价
     */
    @Column(name = "order_sum")
    private BigDecimal orderSum;

    /**
     * 是否手工单
     */
    @Column(name = "is_manual_order")
    private Byte isManualOrder;

    /**
     * 是否即时确认单，团房该字段为空
     */
    @Column(name = "is_immediately_confirm")
    private Byte isImmediatelyConfirm;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    @Column(name = "balance_method")
    private Byte balanceMethod;

    /**
     * 订单确认号（冗余字段）
     */
    @Column(name = "confirm_no")
    private String confirmNo;

    /**
     * 应收金额
     */
    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

    /**
     * 分销商编码
     */
    @Column(name = "agent_code")
    private String agentCode;

    /**
     * 分销商名称
     */
    @Column(name = "agent_name")
    private String agentName;

    /**
     * 商家编码
     */
    @Column(name = "merchant_code")
    private String merchantCode;

    /**
     * 商家名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 客人特殊要求
     */
    @Column(name = "special_request")
    private String specialRequest;

    /**
     * 联系人名称
     */
    @Column(name = "contract_name")
    private String contractName;

    /**
     * 联系人电话
     */
    @Column(name = "contract_phone")
    private String contractPhone;

    /**
     * 订单归属人帐号
     */
    @Column(name = "belong_user")
    private String belongUser;

    /**
     * 订单归属人名称
     */
    @Column(name = "belong_name")
    private String belongName;

    /**
     * 城市编码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 酒店id
     */
    @Column(name = "hotel_id")
    private Integer hotelId;

    /**
     * 酒店名称
     */
    @Column(name = "hotel_name")
    private String hotelName;

    /**
     * 房型名称，多个房型以逗号隔开（冗余字段）
     */
    @Column(name = "room_type_names")
    private String roomTypeNames;

    /**
     * 第一个产品的入住日期
     */
    @Column(name = "checkin_date")
    private Date checkinDate;

    /**
     * 第一个产品的离店日期
     */
    @Column(name = "checkout_date")
    private Date checkoutDate;

    /**
     * 间数， 团房取第一个产品的售晚
     */
    @Column(name = "room_num")
    private Integer roomNum;

    /**
     * 锁单人帐号
     */
    @Column(name = "lock_user")
    private String lockUser;

    /**
     * 锁单人名称
     */
    @Column(name = "lock_name")
    private String lockName;

    /**
     * 锁单时间
     */
    @Column(name = "lock_time")
    private Date lockTime;

    /**
     * 退改费
     */
    @Column(name = "change_fee")
    private BigDecimal changeFee;

    /**
     * 取消原因

     */
    @Column(name = "cancel_reason")
    private String cancelReason;

    /**
     * 确认订单内容
     */
    @Column(name = "confirm_content")
    private String confirmContent;

    /**
     * 客户订单号
     */
    @Column(name = "customer_order_code")
    private String customerOrderCode;

    /**
     * 渠道订单状态
     */
    @Column(name = "channel_order_status")
    private String channelOrderStatus;

    /**
     * 是否团房订单
     */
    @Column(name = "is_group_room")
    private Byte isGroupRoom;

    /**
     * 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    @Column(name = "guest_names")
    private String guestNames;

    /**
     * 供货单号（冗余字段）
     */
    @Column(name = "supply_order_codes")
    private String supplyOrderCodes;

    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    @Column(name = "breakfast_type")
    private Byte breakfastType;

    /**
     * 团号，团房订单才有值
     */
    @Column(name = "group_no")
    private String groupNo;

    /**
     * 业务经理
     */
    @Column(name = "merchant_bm")
    private String merchantBm;

    /**
     * 产品经理
     */
    @Column(name = "merchant_pm")
    private String merchantPm;

    /**
     * 运营员
     */
    @Column(name = "merchant_op")
    private String merchantOp;

    /**
     * 供货单状态(冗余字段，多个供货单状态已逗号隔开)，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    @Column(name = "supply_status")
    private String supplyStatus;

    /**
     * 向导
     */
    private String guide;

    /**
     * 向导电话
     */
    @Column(name = "guide_phone")
    private String guidePhone;

    /**
     * 价格计划名称，团房取第一个产品名称
     */
    @Column(name = "rateplan_name")
    private String rateplanName;

    /**
     * 取消政策
     */
    @Column(name = "cancel_policy")
    private String cancelPolicy;

    /**
     * 供货单确认号
     */
    @Column(name = "supply_confirm_no")
    private String supplyConfirmNo;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 子渠道编码
     */
    @Column(name = "channel_sub_code")
    private String channelSubCode;

    /**
     * 供应商编码（冗余字段）
     */
    @Column(name = "supply_codes")
    private String supplyCodes;

    /**
     * 分销商币种转商家本币汇率
     */
    private BigDecimal rate;

    /**
     * 总利润
     */
    private BigDecimal profit;
	
	/**
     * 是否保留房
     * 1-保留房
     * 0-非保留房
     */
    @Column(name = "is_hold_room")
    private String isHoldRoom;

    /**
     * 是否核销单
     */
    @Column(name = "is_abatement_order")
    private Integer isAbatementOrder;

    /**
     * 关联订单号
     */
    @Column(name = "relation_order_code")
    private String relationOrderCode;

    /**
     * 异常金额
     */
    @Column(name = "exception_amount")
    private BigDecimal exceptionAmount;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单编码
     *
     * @return order_code - 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编码
     *
     * @param orderCode 订单编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取订单状态，1：新建，2：处理中，3：已确认，4：已取消
     *
     * @return order_status - 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态，1：新建，2：处理中，3：已确认，4：已取消
     *
     * @param orderStatus 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     *
     * @return pay_status - 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     *
     * @param payStatus 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     *
     * @return pay_method - 支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     */
    public Byte getPayMethod() {
        return payMethod;
    }

    /**
     * 设置支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     *
     * @param payMethod 支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     */
    public void setPayMethod(Byte payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取分销商币种
     *
     * @return sale_currency - 分销商币种
     */
    public String getSaleCurrency() {
        return saleCurrency;
    }

    /**
     * 设置分销商币种
     *
     * @param saleCurrency 分销商币种
     */
    public void setSaleCurrency(String saleCurrency) {
        this.saleCurrency = saleCurrency;
    }

    /**
     * 获取渠道编码
     *
     * @return channel_code - 渠道编码
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道编码
     *
     * @param channelCode 渠道编码
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取订单总价
     *
     * @return order_sum - 订单总价
     */
    public BigDecimal getOrderSum() {
        return orderSum;
    }

    /**
     * 设置订单总价
     *
     * @param orderSum 订单总价
     */
    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    /**
     * 获取是否手工单
     *
     * @return is_manual_order - 是否手工单
     */
    public Byte getIsManualOrder() {
        return isManualOrder;
    }

    /**
     * 设置是否手工单
     *
     * @param isManualOrder 是否手工单
     */
    public void setIsManualOrder(Byte isManualOrder) {
        this.isManualOrder = isManualOrder;
    }

    /**
     * 获取是否即时确认单，团房该字段为空
     *
     * @return is_immediately_confirm - 是否即时确认单，团房该字段为空
     */
    public Byte getIsImmediatelyConfirm() {
        return isImmediatelyConfirm;
    }

    /**
     * 设置是否即时确认单，团房该字段为空
     *
     * @param isImmediatelyConfirm 是否即时确认单，团房该字段为空
     */
    public void setIsImmediatelyConfirm(Byte isImmediatelyConfirm) {
        this.isImmediatelyConfirm = isImmediatelyConfirm;
    }

    /**
     * 获取结算方式：1月结 2半月结 3周结 4单结 5日结
     *
     * @return balance_method - 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    public Byte getBalanceMethod() {
        return balanceMethod;
    }

    /**
     * 设置结算方式：1月结 2半月结 3周结 4单结 5日结
     *
     * @param balanceMethod 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    public void setBalanceMethod(Byte balanceMethod) {
        this.balanceMethod = balanceMethod;
    }

    /**
     * 获取订单确认号（冗余字段）
     *
     * @return confirm_no - 订单确认号（冗余字段）
     */
    public String getConfirmNo() {
        return confirmNo;
    }

    /**
     * 设置订单确认号（冗余字段）
     *
     * @param confirmNo 订单确认号（冗余字段）
     */
    public void setConfirmNo(String confirmNo) {
        this.confirmNo = confirmNo;
    }

    /**
     * 获取应收金额
     *
     * @return receivable_amount - 应收金额
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 设置应收金额
     *
     * @param receivableAmount 应收金额
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * 获取分销商编码
     *
     * @return agent_code - 分销商编码
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * 设置分销商编码
     *
     * @param agentCode 分销商编码
     */
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    /**
     * 获取分销商名称
     *
     * @return agent_name - 分销商名称
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * 设置分销商名称
     *
     * @param agentName 分销商名称
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * 获取商家编码
     *
     * @return merchant_code - 商家编码
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 设置商家编码
     *
     * @param merchantCode 商家编码
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     * 获取商家名称
     *
     * @return merchant_name - 商家名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商家名称
     *
     * @param merchantName 商家名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取客人特殊要求
     *
     * @return special_request - 客人特殊要求
     */
    public String getSpecialRequest() {
        return specialRequest;
    }

    /**
     * 设置客人特殊要求
     *
     * @param specialRequest 客人特殊要求
     */
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    /**
     * 获取联系人名称
     *
     * @return contract_name - 联系人名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置联系人名称
     *
     * @param contractName 联系人名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * 获取联系人电话
     *
     * @return contract_phone - 联系人电话
     */
    public String getContractPhone() {
        return contractPhone;
    }

    /**
     * 设置联系人电话
     *
     * @param contractPhone 联系人电话
     */
    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    /**
     * 获取订单归属人帐号
     *
     * @return belong_user - 订单归属人帐号
     */
    public String getBelongUser() {
        return belongUser;
    }

    /**
     * 设置订单归属人帐号
     *
     * @param belongUser 订单归属人帐号
     */
    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    /**
     * 获取订单归属人名称
     *
     * @return belong_name - 订单归属人名称
     */
    public String getBelongName() {
        return belongName;
    }

    /**
     * 设置订单归属人名称
     *
     * @param belongName 订单归属人名称
     */
    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    /**
     * 获取城市编码
     *
     * @return city_code - 城市编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置城市编码
     *
     * @param cityCode 城市编码
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取城市名称
     *
     * @return city_name - 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名称
     *
     * @param cityName 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取酒店id
     *
     * @return hotel_id - 酒店id
     */
    public Integer getHotelId() {
        return hotelId;
    }

    /**
     * 设置酒店id
     *
     * @param hotelId 酒店id
     */
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * 获取酒店名称
     *
     * @return hotel_name - 酒店名称
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * 设置酒店名称
     *
     * @param hotelName 酒店名称
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * 获取房型名称，多个房型以逗号隔开（冗余字段）
     *
     * @return room_type_names - 房型名称，多个房型以逗号隔开（冗余字段）
     */
    public String getRoomTypeNames() {
        return roomTypeNames;
    }

    /**
     * 设置房型名称，多个房型以逗号隔开（冗余字段）
     *
     * @param roomTypeNames 房型名称，多个房型以逗号隔开（冗余字段）
     */
    public void setRoomTypeNames(String roomTypeNames) {
        this.roomTypeNames = roomTypeNames;
    }

    /**
     * 获取第一个产品的入住日期
     *
     * @return checkin_date - 第一个产品的入住日期
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * 设置第一个产品的入住日期
     *
     * @param checkinDate 第一个产品的入住日期
     */
    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * 获取第一个产品的离店日期
     *
     * @return checkout_date - 第一个产品的离店日期
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * 设置第一个产品的离店日期
     *
     * @param checkoutDate 第一个产品的离店日期
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * 获取间数， 团房取第一个产品的售晚
     *
     * @return room_num - 间数， 团房取第一个产品的售晚
     */
    public Integer getRoomNum() {
        return roomNum;
    }

    /**
     * 设置间数， 团房取第一个产品的售晚
     *
     * @param roomNum 间数， 团房取第一个产品的售晚
     */
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * 获取锁单人帐号
     *
     * @return lock_user - 锁单人帐号
     */
    public String getLockUser() {
        return lockUser;
    }

    /**
     * 设置锁单人帐号
     *
     * @param lockUser 锁单人帐号
     */
    public void setLockUser(String lockUser) {
        this.lockUser = lockUser;
    }

    /**
     * 获取锁单人名称
     *
     * @return lock_name - 锁单人名称
     */
    public String getLockName() {
        return lockName;
    }

    /**
     * 设置锁单人名称
     *
     * @param lockName 锁单人名称
     */
    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    /**
     * 获取锁单时间
     *
     * @return lock_time - 锁单时间
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * 设置锁单时间
     *
     * @param lockTime 锁单时间
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 获取退改费
     *
     * @return change_fee - 退改费
     */
    public BigDecimal getChangeFee() {
        return changeFee;
    }

    /**
     * 设置退改费
     *
     * @param changeFee 退改费
     */
    public void setChangeFee(BigDecimal changeFee) {
        this.changeFee = changeFee;
    }

    /**
     * 获取取消原因

     *
     * @return cancel_reason - 取消原因

     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * 设置取消原因

     *
     * @param cancelReason 取消原因

     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    /**
     * 获取确认订单内容
     *
     * @return confirm_content - 确认订单内容
     */
    public String getConfirmContent() {
        return confirmContent;
    }

    /**
     * 设置确认订单内容
     *
     * @param confirmContent 确认订单内容
     */
    public void setConfirmContent(String confirmContent) {
        this.confirmContent = confirmContent;
    }

    /**
     * 获取客户订单号
     *
     * @return customer_order_code - 客户订单号
     */
    public String getCustomerOrderCode() {
        return customerOrderCode;
    }

    /**
     * 设置客户订单号
     *
     * @param customerOrderCode 客户订单号
     */
    public void setCustomerOrderCode(String customerOrderCode) {
        this.customerOrderCode = customerOrderCode;
    }

    /**
     * 获取渠道订单状态
     *
     * @return channel_order_status - 渠道订单状态
     */
    public String getChannelOrderStatus() {
        return channelOrderStatus;
    }

    /**
     * 设置渠道订单状态
     *
     * @param channelOrderStatus 渠道订单状态
     */
    public void setChannelOrderStatus(String channelOrderStatus) {
        this.channelOrderStatus = channelOrderStatus;
    }

    /**
     * 获取是否团房订单
     *
     * @return is_group_room - 是否团房订单
     */
    public Byte getIsGroupRoom() {
        return isGroupRoom;
    }

    /**
     * 设置是否团房订单
     *
     * @param isGroupRoom 是否团房订单
     */
    public void setIsGroupRoom(Byte isGroupRoom) {
        this.isGroupRoom = isGroupRoom;
    }

    /**
     * 获取所有入住人名单，多个以逗号隔开（冗余字段）
     *
     * @return guest_names - 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    public String getGuestNames() {
        return guestNames;
    }

    /**
     * 设置所有入住人名单，多个以逗号隔开（冗余字段）
     *
     * @param guestNames 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    public void setGuestNames(String guestNames) {
        this.guestNames = guestNames;
    }

    /**
     * 获取供货单号（冗余字段）
     *
     * @return supply_order_codes - 供货单号（冗余字段）
     */
    public String getSupplyOrderCodes() {
        return supplyOrderCodes;
    }

    /**
     * 设置供货单号（冗余字段）
     *
     * @param supplyOrderCodes 供货单号（冗余字段）
     */
    public void setSupplyOrderCodes(String supplyOrderCodes) {
        this.supplyOrderCodes = supplyOrderCodes;
    }

    /**
     * 获取早餐类型，1，无早  2：单早，3：双早，4：人头早
     *
     * @return breakfast_type - 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    public Byte getBreakfastType() {
        return breakfastType;
    }

    /**
     * 设置早餐类型，1，无早  2：单早，3：双早，4：人头早
     *
     * @param breakfastType 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    public void setBreakfastType(Byte breakfastType) {
        this.breakfastType = breakfastType;
    }

    /**
     * 获取团号，团房订单才有值
     *
     * @return group_no - 团号，团房订单才有值
     */
    public String getGroupNo() {
        return groupNo;
    }

    /**
     * 设置团号，团房订单才有值
     *
     * @param groupNo 团号，团房订单才有值
     */
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * 获取业务经理
     *
     * @return merchant_bm - 业务经理
     */
    public String getMerchantBm() {
        return merchantBm;
    }

    /**
     * 设置业务经理
     *
     * @param merchantBm 业务经理
     */
    public void setMerchantBm(String merchantBm) {
        this.merchantBm = merchantBm;
    }

    /**
     * 获取产品经理
     *
     * @return merchant_pm - 产品经理
     */
    public String getMerchantPm() {
        return merchantPm;
    }

    /**
     * 设置产品经理
     *
     * @param merchantPm 产品经理
     */
    public void setMerchantPm(String merchantPm) {
        this.merchantPm = merchantPm;
    }

    /**
     * 获取供货单状态(冗余字段，多个供货单状态已逗号隔开)，1：未发单，2：已发单待确认，3：已确认，4：不确认
     *
     * @return supply_status - 供货单状态(冗余字段，多个供货单状态已逗号隔开)，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    public String getSupplyStatus() {
        return supplyStatus;
    }

    /**
     * 设置供货单状态(冗余字段，多个供货单状态已逗号隔开)，1：未发单，2：已发单待确认，3：已确认，4：不确认
     *
     * @param supplyStatus 供货单状态(冗余字段，多个供货单状态已逗号隔开)，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    public void setSupplyStatus(String supplyStatus) {
        this.supplyStatus = supplyStatus;
    }

    /**
     * 获取向导
     *
     * @return guide - 向导
     */
    public String getGuide() {
        return guide;
    }

    /**
     * 设置向导
     *
     * @param guide 向导
     */
    public void setGuide(String guide) {
        this.guide = guide;
    }

    /**
     * 获取向导电话
     *
     * @return guide_phone - 向导电话
     */
    public String getGuidePhone() {
        return guidePhone;
    }

    /**
     * 设置向导电话
     *
     * @param guidePhone 向导电话
     */
    public void setGuidePhone(String guidePhone) {
        this.guidePhone = guidePhone;
    }

    /**
     * 获取价格计划名称，团房取第一个产品名称
     *
     * @return rateplan_name - 价格计划名称，团房取第一个产品名称
     */
    public String getRateplanName() {
        return rateplanName;
    }

    /**
     * 设置价格计划名称，团房取第一个产品名称
     *
     * @param rateplanName 价格计划名称，团房取第一个产品名称
     */
    public void setRateplanName(String rateplanName) {
        this.rateplanName = rateplanName;
    }

    /**
     * 获取取消政策
     *
     * @return cancel_policy - 取消政策
     */
    public String getCancelPolicy() {
        return cancelPolicy;
    }

    /**
     * 设置取消政策
     *
     * @param cancelPolicy 取消政策
     */
    public void setCancelPolicy(String cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    /**
     * 获取供货单确认号
     *
     * @return supply_confirm_no - 供货单确认号
     */
    public String getSupplyConfirmNo() {
        return supplyConfirmNo;
    }

    /**
     * 设置供货单确认号
     *
     * @param supplyConfirmNo 供货单确认号
     */
    public void setSupplyConfirmNo(String supplyConfirmNo) {
        this.supplyConfirmNo = supplyConfirmNo;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取子渠道编码
     *
     * @return channel_sub_code - 子渠道编码
     */
    public String getChannelSubCode() {
        return channelSubCode;
    }

    /**
     * 设置子渠道编码
     *
     * @param channelSubCode 子渠道编码
     */
    public void setChannelSubCode(String channelSubCode) {
        this.channelSubCode = channelSubCode;
    }

    /**
     * 获取供应商编码（冗余字段）
     *
     * @return supply_codes - 供应商编码（冗余字段）
     */
    public String getSupplyCodes() {
        return supplyCodes;
    }

    /**
     * 设置供应商编码（冗余字段）
     *
     * @param supplyCodes 供应商编码（冗余字段）
     */
    public void setSupplyCodes(String supplyCodes) {
        this.supplyCodes = supplyCodes;
    }

    /**
     * 获取分销商币种转商家本币汇率
     *
     * @return rate - 分销商币种转商家本币汇率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置分销商币种转商家本币汇率
     *
     * @param rate 分销商币种转商家本币汇率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取总利润
     *
     * @return profit - 总利润
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 设置总利润
     *
     * @param profit 总利润
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getIsHoldRoom() {
        return isHoldRoom;
    }

    public void setIsHoldRoom(String isHoldRoom) {
        this.isHoldRoom = isHoldRoom;
    }

    public Integer getIsAbatementOrder() {
        return isAbatementOrder;
    }

    public void setIsAbatementOrder(Integer isAbatementOrder) {
        this.isAbatementOrder = isAbatementOrder;
    }

    public String getRelationOrderCode() {
        return relationOrderCode;
    }

    public void setRelationOrderCode(String relationOrderCode) {
        this.relationOrderCode = relationOrderCode;
    }

    public BigDecimal getExceptionAmount() {
        return exceptionAmount;
    }

    public void setExceptionAmount(BigDecimal exceptionAmount) {
        this.exceptionAmount = exceptionAmount;
    }

    public String getMerchantOp() {
        return merchantOp;
    }

    public void setMerchantOp(String merchantOp) {
        this.merchantOp = merchantOp;
    }
}