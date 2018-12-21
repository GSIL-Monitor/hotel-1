package com.travel.common.dto.order.request;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 查房查询参数
 * @author : Zhiping Sun
 * @date : 2018年4月16日下午11:28:30
 */
@Data
public class SearchRoomQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = -5093490684207640766L;
	
	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;

}
