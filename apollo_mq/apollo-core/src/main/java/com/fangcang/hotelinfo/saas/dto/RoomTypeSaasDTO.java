package com.fangcang.hotelinfo.saas.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 房型信息
 * @author ivan
 *
 */
@Data
public class RoomTypeSaasDTO implements Serializable{

	private static final long serialVersionUID = -3769673281758397707L;

	private Long roomTypeId;
	
	private Long hotelId;
	
	/**
	 * 房型名称
	 */
	private String roomTypeName;
	
	/**
	 * 房型编码
	 */
	private String roomTypeCode;
	
	/**
	 * 推荐级别
	 */
	private String commendLevel;
	
	/**
	 * 房间面积
	 */
	private String roomArea;
	
	/**
	 * 房型描述
	 */
	private String description;
	
	/**
	 * 房间数量
	 */
	private Integer roomNum;
	
	/**
	 * 床型
	 */
	private String bedType; //再已是临时字段
	private String bedSize ;//再已是临时字段
	
	/**
	 * 床型名称
	 */
	private String bedTypeName;
	
	/**
	 * 是否允许加床
	 */
	private Integer addBed;
	
	/**
	 * 房型所属楼层
	 */
	private String roomFloor;
	
	/**
	 * 宽带
	 */
	private Integer broadNet;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 最大入住人数
	 */
	private Integer maxPerson;
	
	/**
	 * 是否无烟房
	 */
	private Integer allowSmoke;
	

	private String createName;
	
	private Date createTime;
	
	private String modifyName;
	
	private Date modifyTime;


	/**
	 * 申请状态	
	 */
	private Integer applyStatus;
	
	/**
	 * 审核原因
	 */
	private String cause;
	
	/**
	 * 审核操作人
	 */
	private String auditName;
	
	/**
	 * 审核时间
	 */
	private Date auditTime;
	
	/**
	 * 是否有效（1有效，0无效）
	 */
	private String isActive;
	
	/**
	 * 商家编码
	 */
	private String merchantCode;	
	//是否管理员操作
	private boolean isApply;
	//0-无窗，1-有窗，2-部分有窗
	private Integer windowType ;
	
	/**房型和床型关联对象集合**/
	private List<RoomTypeBedTypeRelaDTO> roomTypeBedTypeRelaDTOList ;
	/**房型图片**/
	private List<HotelImgDTO>  roomTypeImgDTOList ;
	/**临时保存操作类型 1修改、2审核,3删除（非物理删除）；修改又分管理员修改和商家修改**/
	private Integer operationType ;
}
