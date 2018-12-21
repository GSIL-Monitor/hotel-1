package com.fangcang.base.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AreaDataDTO implements Serializable{
	private static final long serialVersionUID = 1937184294486554289L;

	private long id;

	/**
	 * 数据类型 1 country/2 province/3 city/4 district/5 business
	 */
	private int type;

	/**
	 * 数据名称
	 */
	private String dataName;

	/**
	 * 数据编码
	 */
	private String dataCode;

	/**
	 * 数据全拼
	 */
	private String pinYin;

	/**
	 * 数据简拼
	 */
	private String acronymPinYin;

	/**
	 * 父节点ID
	 */
	private long parentId;
}
