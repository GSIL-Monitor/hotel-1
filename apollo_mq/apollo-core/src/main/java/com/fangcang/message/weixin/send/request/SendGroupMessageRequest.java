package com.fangcang.message.weixin.send.request;

import com.fangcang.message.weixin.enums.WxEnumConstant;

import java.util.List;

/**
 * 群发消息对象
 */
public class SendGroupMessageRequest {
	/**
	 * 设置接收者(全体发送或分组发送此属性不为null)
	 */
	private SendFilter filter;
	/**
	 * 根据OpenID列表
	 */
	private List<String> touser;
	/**
	 * 消息类型
	 */
	private String msgtype;
	/**
	 * 文本消息内容
	 */
	private TextInfo text;
	/**
	 * 图文消息内容
	 */
	private MediaInfo mpnews;
	/**
	 * 语音消息内容
	 */
	private MediaInfo voice;
	/**
	 * 图片消息内容
	 */
	private MediaInfo image;
	/**
	 * 视频消息内容
	 */
	private MediaInfo video;
	/**
	 * 音乐消息内容
	 */
	private MediaInfo music;
	
	/**
	 * 设置接收者，优先级(openIds > groupId > isToAll)
	 * 当openIds与groupId都没有传递时，默认为全体发送
	 * @param isToAll
	 * @param groupId
	 * @param openIds
	 */
	public void setReceiver(Boolean isToAll, Integer groupId, List<String> openIds){
		if(openIds != null && openIds.size() > 0){
			this.filter = null;
			this.touser = openIds;
		}else{
			this.filter = new SendFilter();
			if(groupId != null){
				this.filter.setGroup_id(groupId);
				this.filter.setIs_to_all(false);
			}else{
				this.filter.setIs_to_all(true);
			}
		}
	}
	
	/**
	 * 设置文本消息内容
	 * @param content
	 */
	public void setTextInfo(String content){
		this.msgtype = WxEnumConstant.GroupSendMsgTypes.TEXT.getType();
		this.text = new TextInfo();
		this.text.setContent(content);
		
		this.mpnews = null;
		this.voice = null;
		this.image = null;
		this.video = null;
		this.music = null;
	}
	/**
	 * 设置图文消息内容
	 * @param mediaId
	 */
	public void setMpnewsInfo(String mediaId){
		this.msgtype = WxEnumConstant.GroupSendMsgTypes.MPNEWS.getType();
		this.mpnews = new MediaInfo();
		this.mpnews.setMedia_id(mediaId);
		
		this.text = null;
		this.voice = null;
		this.image = null;
		this.video = null;
		this.music = null;
	}
	/**
	 * 设置语音消息内容
	 * @param mediaId
	 */
	public void setVoiceInfo(String mediaId){
		this.msgtype = WxEnumConstant.GroupSendMsgTypes.VOICE.getType();
		this.voice = new MediaInfo();
		this.voice.setMedia_id(mediaId);
		
		this.text = null;
		this.mpnews = null;
		this.image = null;
		this.video = null;
		this.music = null;
	}
	/**
	 * 设置图片消息内容
	 * @param mediaId
	 */
	public void setImageInfo(String mediaId){
		this.msgtype = WxEnumConstant.GroupSendMsgTypes.IMAGE.getType();
		this.image = new MediaInfo();
		this.image.setMedia_id(mediaId);
		
		this.text = null;
		this.mpnews = null;
		this.voice = null;
		this.video = null;
		this.music = null;
	}
	/**
	 * 设置视频消息内容
	 * @param mediaId
	 */
	public void setVideoInfo(String mediaId, String title, String description){
		this.msgtype = WxEnumConstant.GroupSendMsgTypes.VIDEO.getType();
		this.video = new MediaInfo();
		this.video.setMedia_id(mediaId);
		this.video.setTitle(title);
		this.video.setDescription(description);
		
		this.text = null;
		this.mpnews = null;
		this.voice = null;
		this.image = null;
		this.music = null;
	}
	
	
	/**
	 * 接收者(全体发送或分组发送)
	 */
	private class SendFilter{
		/**
		 * 用于设定是否向全部用户发送，值为true或false
		 * 选择true该消息群发给所有用户
		 * 选择false可根据group_id发送给指定群组的用户
		 */
		private boolean is_to_all;
		/**
		 * 群发到的分组的group_id，参加用户管理中用户分组接口
		 * 若is_to_all值为true，可不填写group_id
		 */
		private Integer group_id;
		
		public boolean isIs_to_all() {
			return is_to_all;
		}
		public void setIs_to_all(boolean is_to_all) {
			this.is_to_all = is_to_all;
		}
		public Integer getGroup_id() {
			return group_id;
		}
		public void setGroup_id(Integer group_id) {
			this.group_id = group_id;
		}
	}
	
	/**
	 * 文本消息体
	 */
	private class TextInfo{
		/**
		 * 文本消息内容
		 */
		private String content;

		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
	
	/**
	 * 媒体消息体
	 */
	private class MediaInfo{
		/**
		 * 媒体media_id
		 */
		private String media_id;
		/**
		 * 消息的标题
		 */
		private String title;
		/**
		 * 消息的描述
		 */
		private String description;

		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	
	
	public SendFilter getFilter() {
		return filter;
	}
	public List<String> getTouser() {
		return touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public TextInfo getText() {
		return text;
	}
	public MediaInfo getMpnews() {
		return mpnews;
	}
	public MediaInfo getVoice() {
		return voice;
	}
	public MediaInfo getImage() {
		return image;
	}
	public MediaInfo getVideo() {
		return video;
	}
	public MediaInfo getMusic() {
		return music;
	}
	
}
