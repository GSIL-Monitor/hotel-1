package com.fangcang.hotelinfo.saas.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 酒店特征对象
 * @author zdy
 *
 */
@Data
public class FeatureDTO implements Serializable {

	private static final long serialVersionUID = 2291549859327909597L;
	/**主健*/
	private Long featureId ;
	/**分组CODE*/
	private Long groupId  ;
	/**特征名字*/
	private String featureName ;
	/**是否收费标志（Y:启用，N不启用）用来控制勾选这项时是否出现是否收费选项*/
	private String feeFlag ;
	/**是否需要录入标志（Y:启用，N不启用）用来控制勾选这项时是否出现出现录入框*/
	private String textFlag ;
	/**图标存放路径*/
	private String iconPath ;
	/**是否有效,用与前台是否展示*/
	private String isActive ;
	/**展示时排序*/
	private Integer sort ;

}
