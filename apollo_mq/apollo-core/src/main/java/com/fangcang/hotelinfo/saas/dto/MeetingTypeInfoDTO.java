package com.fangcang.hotelinfo.saas.dto;

import java.io.Serializable;
import java.util.List;

import com.fangcang.common.BaseDTO;
import lombok.Data;

@Data
public class MeetingTypeInfoDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -3769673281758397707L;
	
	private Long hotelId;

	/**
	 * 会议室ID
	 */
	private  Long  meetingTypeId;

	/**
	 * 会议室名称
	 */
	private String meetingTypeName;

	/**
	 * 会议室面积长
	 */
	private Double meetingL;

	/**
	 * 会议室面积宽
	 */
	private Double meetingW;

	/**
	 * 会议室面积高
	 */
	private Double meetingH;

	/**
	 * 最小容纳人数
	 */
	private Integer minPeoples;

	/**
	 * 最大容纳人数
	 */
	private Integer maxPeoples;

	/**
	 * 楼层
	 */
	private Integer layer;

	/**
	 * 面积
	 */
	private String area;

	/**
	 * 有无立柱 1 有 0 无
	 */
	private Integer frameIn;

	/**
	 * 上午 0600-1100
	 */
	private  String morningSection;

	/**
	 * 下午
	 */
	private String afternoonSection;

	/**
	 * 晚上
	 */
	private String nightSection;

	/**
	 * 全天
	 */
	private String allDaySection;
	
/*	private List<PutTypeDTO> putTypeList;
*/
	/**
	 * 摆放类型  [{"putTypeKey":"1","putTypeValue":"剧场式","peopleNum":"1000"},{"putTypeKey":"2","putTypeValue":"课桌式","peopleNum":"50"}]
	 */
	private String putTypes;

	/**
	 * 备注
	 */
	private String notes;

	/**
	 * 是否有效
	 */
	private Integer isActive;
	
	private Integer operationType;

	/**
	 * 图片集合
	 */
	private List<HotelImgDTO>  meetingImgDTOList ;
}
