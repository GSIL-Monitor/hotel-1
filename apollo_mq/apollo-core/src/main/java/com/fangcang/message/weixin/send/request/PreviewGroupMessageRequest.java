package com.fangcang.message.weixin.send.request;

import com.fangcang.message.weixin.enums.WxEnumConstant;

/**
 * 预览群发消息对象
 */
public class PreviewGroupMessageRequest {
	/**
	 * 接收消息用户对应该公众号的openid
	 */
	private String touser;
	/**
	 * 接收消息用户微信号
	 */
	private String towxname;
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
	private MediaInfo mpvideo;
	/**
	 * 音乐消息内容
	 */
	private MediaInfo music;
	
	/**
	 * 设置文本消息内容
	 * @param content
	 */
	public void setTextInfo(String content){
		this.msgtype = WxEnumConstant.GroupPreviewMsgTypes.TEXT.getType();
		this.text = new TextInfo();
		this.text.setContent(content);
		
		this.mpnews = null;
		this.voice = null;
		this.image = null;
		this.mpvideo = null;
		this.music = null;
	}
	/**
	 * 设置图文消息内容
	 * @param mediaId
	 */
	public void setMpnewsInfo(String mediaId){
		this.msgtype = WxEnumConstant.GroupPreviewMsgTypes.MPNEWS.getType();
		this.mpnews = new MediaInfo();
		this.mpnews.setMedia_id(mediaId);
		
		this.text = null;
		this.voice = null;
		this.image = null;
		this.mpvideo = null;
		this.music = null;
	}
	/**
	 * 设置语音消息内容
	 * @param mediaId
	 */
	public void setVoiceInfo(String mediaId){
		this.msgtype = WxEnumConstant.GroupPreviewMsgTypes.VOICE.getType();
		this.voice = new MediaInfo();
		this.voice.setMedia_id(mediaId);
		
		this.text = null;
		this.mpnews = null;
		this.image = null;
		this.mpvideo = null;
		this.music = null;
	}
	/**
	 * 设置图片消息内容
	 * @param mediaId
	 */
	public void setImageInfo(String mediaId){
		this.msgtype = WxEnumConstant.GroupPreviewMsgTypes.IMAGE.getType();
		this.image = new MediaInfo();
		this.image.setMedia_id(mediaId);
		
		this.text = null;
		this.mpnews = null;
		this.voice = null;
		this.mpvideo = null;
		this.music = null;
	}
	/**
	 * 设置视频消息内容
	 * @param mediaId
	 */
	public void setVideoInfo(String mediaId, String title, String description){
		this.msgtype = WxEnumConstant.GroupPreviewMsgTypes.MPVIDEO.getType();
		this.mpvideo = new MediaInfo();
		this.mpvideo.setMedia_id(mediaId);
		
		this.text = null;
		this.mpnews = null;
		this.voice = null;
		this.image = null;
		this.music = null;
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


		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
	}
	
	
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTowxname() {
		return towxname;
	}
	public void setTowxname(String towxname) {
		this.towxname = towxname;
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
	public MediaInfo getMpvideo() {
		return mpvideo;
	}
	public MediaInfo getMusic() {
		return music;
	}
	
}
