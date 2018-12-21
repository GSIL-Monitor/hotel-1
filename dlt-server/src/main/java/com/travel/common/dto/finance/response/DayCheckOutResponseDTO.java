package com.travel.common.dto.finance.response;

import java.util.List;

import com.travel.finance.entity.DayCheckOut;

import lombok.Data;

/**
 * @Description : 每日小结信息
 * @author : Zhiping Sun
 * @date : 2018年3月19日上午9:34:02
 */
@Data
public class DayCheckOutResponseDTO extends DayCheckOut {
	
	private static final long serialVersionUID = 9049712497164909605L;
	
	/**
	 * 入住日期
	 */
	private String checkInDate;
	
	/**
	 * 离店日期
	 */
	private String checkOutDate;
	
	/**
	 * 当前日期可出账明细
	 */
	private List<OrgCheckOutDetailResponseDTO> orgCheckOutDetailList;

}
