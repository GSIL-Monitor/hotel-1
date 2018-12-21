package com.fangcang.hotelinfo.saas.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MapPositionDTO implements Serializable {
	
	private static final long serialVersionUID = 5694813019786189491L;
	
	/**位置ID**/
	private Long positionId ;
	/**GPS经度**/
	private Double lngGps  ;
	/**GPS维度**/
	private Double latGps  ;
	/**百度地图经度**/
	private Double lngBaidu  ;
	/**百度地图维度**/
	private Double latBaidu  ;
	/**谷歌地图经度**/
	private Double  lngGoogle;
	/**谷歌地图维度**/
	private Double  latGoogle;
	/**1：酒店 2：景点 3：交通场所（车站，地铁站，飞机场，火车站） 4：医院 5：大学 6:地铁公交站**/
	private Integer  positionType;
	/**位置名称	VARCHAR2(256)**/
	private String  positonName;
	
}
