package com.fangcang.hotelinfo.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.hotelinfo.dto.BedTypeDTO;
import lombok.Data;

@Data
public class HotelRoomTypeRsponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 382506305603974947L;
	
	
	/**
	 * 酒店房型ID
	 */

	private Long roomTypeId ;
	
	
	/**
	 * 酒店房型名称
	 */
	

	private  String roomTypeName;
	/**
	 * 酒店房型英文名称
	 */
	
	
	private String  engRoomTypeName;
	
	/**
	 * 所属楼层
	 */
	
	
	private  String floor;
	
	/**
	 * 房型面积
	 */
	
	 private String area; 
	 
	 /**
	  * 最大入住人
	  */
	
	 private Integer maxPersion;
	 
	 /**
	  * 房间数量
	  */
	
	 private  String roomNum;
	 
	 /**
	  * 是否有窗户
	  * 
	  */
	
	 private  Integer  isWindow;
	 
	 /**
	  * 有线是否收费
	  * 0无，1免费，2收费
	  */
	
	  private  Integer  isWired;
	  
	  /**
	   *是否有wifi
	   *0无  1有
	   */
	
	  private  Integer  isWifi;
	  
	  /**
	   *是否无烟房
	   *0.不是 1是
	   */
	
	 private  Integer  isSmokeless;
	 
	 /**
	   *床型 
	   *
	   */

	 private List<BedTypeDTO>  bedTypelist;
	 
	 /**
	  * 床型描述
	  */
	
	 private  String bedDescription;
	 
    /**
     * 是否可加床
     * 0.不可加床  1.可加床
     */
	
	 private  Integer isExtraBed;
	 
	 /**
	  * 有效性
	  */
	
	 private  Integer  isActive;
	 
	 /**
	  * 房型描述
	  */
	
	 private   String  roomTypeDescription;
	 
	 /**
	  * 房型主图
	  */
	 private  String  imageUrl;
	 
	 /**
	  * 真实路径
	  */
      private  String  realPath;
	 
	 
	 

}
