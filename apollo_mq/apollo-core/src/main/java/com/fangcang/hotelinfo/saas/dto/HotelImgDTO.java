package com.fangcang.hotelinfo.saas.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HotelImgDTO  implements  Serializable  {
	private static final long serialVersionUID = -2380242560016316247L;
	/**
	 * 图片ID
	 */
	protected Long  imgId;
	
	/**
	 * 酒店ID
	 */
	private Long hotelId;
	
	/**房型ID，当IMGTYPE=1时，不能为空;当IMGTYPE=6、7、8、9时为关联的数据ID也不能为空**/
	private Long roomTypeId ;
	/**状态 1：有效 0：无效**/
	private Integer isActive ;
	/**
	 * 图片地址
	 */
	private String imgurl;
	/**不同规格的图片*/
	private String imgurl1;//720*480
	private String imgurl2;//480*320
	private String imgurl3;//270*180
	private String imgurl4;//180*120
	private String imgurl5;//76*76
	
	/**图片类型 1房型；2外观图；3大堂图；4设施；5：其它；6宴会厅;7会议厅;8门票;9其它服务*/
	private String imgType;
	
	/**
	 * 是否酒店主图 1表示是
	 */
	private String isMainImg;
	/**上传商家CODE**/
	protected String  merchantCode;
	/**
	 * 操作类型
	 */
	private String operateType;
	
	/**
	 * 酒店标题
	 */
	private String title;  
	/**
	 * 创建者
	 */
	private String createName ;
	
	
	/** 申请状态	 */
	private Integer applyStatus;
	
	/** 审核原因 */
	private String cause;
	
	/** 审核操作人*/
	private String auditName;
	
	/** 审核时间 */
	private Date auditTime;
	
	private int imgSize;
	
	private String ftpUrl;//ftp访问路径
}
