package com.fangcang.hotelinfo.saas.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HotelFeatureDTO extends FeatureDTO implements Serializable {
	
	private static final long serialVersionUID = -4605958781010131095L;
	/**主健**/
	private Long hotelFeatureId;
	/**酒店ID**/
	private Long hotelId;
	/**值，手工录入的**/
	private String textValue;
	/**是否收费 Y：是 N：否 默认为N**/
	private String isFee;
	//是否选中,前端页面是否选用用,不是数据库字段,-1不选中,大于0选中
	private Integer isSelect ;
}
