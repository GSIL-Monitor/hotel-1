package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.hotelinfo.dto.BedTypeDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class RoomTypeRequestDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 7266938527530471419L;

	/**
	 * 酒店id
	 */
	@NotNull(message = "hotelId不能为Null")
	private Long hotelId;
    /**
     * 房型名称
     */
    @NotEmpty(message = "roomTypeName不能为空")
	private String roomTypeName;
	/**
	 * 房型英文名
	 */
	private String engRoomTypeName;
	/**
	 * 所属楼层
	 */
	private String floor;
	/**
	 * 房间面积
	 */
	private String area;
	/**
	 * 最大入住人
	 */
	private String maxPerson;
	/**
	 * 房间数量
	 */
	private String roomNum;
	/**
	 * 是否有窗（0：没有 1：有 2：部分有窗）
	 */
	private Integer isWindow;
	/**
	 * 有线是否收费：0无，1免费，2收费
	 */
	private Integer isWired;
	/**
	 * wifi是否收费：0无 1有
	 */
	private Integer isWifi;
	/**
	 * 是否无烟房 0.不是 1是
	 */
	private Integer isSmokeless;
	/**
	 * 房型主图
	 */
	private String imageUrl;
	/**
	 * 真实路径
	 */
	private String realPath;
	/**
	 * 是否可加床 0.不可加床 1.可加床
	 */
	private String isExtraBed;
	/**
	 * 修改传，新增不需要
	 */
	private Long roomTypeId;
	/**
	 * 操作类型 1新增 2修改
	 */
	private String operationType;
	/**
	 * 房型描述
	 */
	private String roomTypeDescribe;
	/**
	 * 床型集合
	 */
	private List<BedTypeDTO> bedTypeList;
	
	/**
     * 排序字段
     */
    private  Integer sort;
    
    /**
     * 图片id
     */
    private Long  imageId;
}
