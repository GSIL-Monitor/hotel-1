package com.fangcang.message.remote.request.weixin;

import java.io.Serializable;
import java.util.List;

public class WxBaseSendGroupMessageRequestDTO extends WxBaseRequestDTO implements Serializable {
	
	private static final long serialVersionUID = -1014564495589567462L;
	
	/**
	 * 是否发送全体(isToAll,groupId,openIds 三选一)
	 */
	private Boolean isToAll;
	/**
	 * 发送的分组ID(isToAll,groupId,openIds 三选一)
	 */
	private Integer groupId;
	/**
	 * 发送的openid列表(isToAll,groupId,openIds 三选一)
	 */
	private List<String> openIds;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 预览接收人openid（预览模式才会用到）
	 */
	private String previewToUser;
	/**
	 * 预览接收人微信号（预览模式才会用到）
	 */
	private String previewToWxName;
	
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Boolean getIsToAll() {
		return isToAll;
	}
	public void setIsToAll(Boolean isToAll) {
		this.isToAll = isToAll;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public List<String> getOpenIds() {
		return openIds;
	}
	public void setOpenIds(List<String> openIds) {
		this.openIds = openIds;
	}
	public String getPreviewToUser() {
		return previewToUser;
	}
	public void setPreviewToUser(String previewToUser) {
		this.previewToUser = previewToUser;
	}
	public String getPreviewToWxName() {
		return previewToWxName;
	}
	public void setPreviewToWxName(String previewToWxName) {
		this.previewToWxName = previewToWxName;
	}

}
