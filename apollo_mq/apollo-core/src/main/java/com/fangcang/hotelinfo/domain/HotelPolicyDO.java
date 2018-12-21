package com.fangcang.hotelinfo.domain;

import java.io.Serializable;

import com.fangcang.common.BaseDO;

import lombok.Data;

@Data
public class HotelPolicyDO extends BaseDO implements Serializable{

	private static final long serialVersionUID = -3603547580519775652L;
	/**
	 * 政策id
	 */
	private Long id;
	/**
	 * 酒店id
	 */
	private Long hotelId;
	/**
	 * 入住时间
	 */
	private String checkInTime;
	/**
	 * 退房时间
	 */
	private String checkOutTime;
	
	/**
     * 宠物政策(1 允许携带宠物，0 不允许携带宠物)
     */
    private Integer pet;
    /**
     * 接受的信用卡,多个逗号分隔(
     * 1:国内发行银联卡   2:威士(VISA)  3:万事达(Master) 4运通(AMEX)
     * 5大来(Diners Club) 6 Euro卡  7 Euro 6000卡  8EC借记卡
     * 9 威士电子借记卡 10Maestro卡  11吉士美卡)
     */
    private String creditCard;
    /**
     * 其他政策
     */
    private String otherPolicy;
}
