package com.fangcang.message.weixin.enums;

/**
 * 一般enum类常量类
 */
public class WxEnumConstant {
	
	/**
	 * 群体发送消息类型
	 */
	public enum GroupSendMsgTypes {
		TEXT(0), //文本
		MPNEWS(1), //图文
		IMAGE(2), //图片
		VOICE(3), //语音
		VIDEO(4), //视频
		MUSIC(5); //音乐
		
		private int typeValue;
		
		private GroupSendMsgTypes(int typeValue){
			this.typeValue = typeValue;
		}
		
		public String getType() {
			return this.name().toLowerCase();
		}
		
		public int getTypeValue() {
			return this.typeValue;
		}
		
		public static boolean contains(String msgType) {
			for (GroupSendMsgTypes msg : GroupSendMsgTypes.values()) {
                if (msg.getType().equalsIgnoreCase(msgType)) {
                    return true;
                }
            }
			return false;
		}
		
		public static boolean contains(int msgTypeValue) {
			for (GroupSendMsgTypes msg : GroupSendMsgTypes.values()) {
                if (msg.getTypeValue() == msgTypeValue) {
                    return true;
                }
            }
			return false;
		}
	}
	
	/**
	 * 预览群体发送消息类型
	 */
	public enum GroupPreviewMsgTypes {
		TEXT(0), //文本
		MPNEWS(1), //图文
		IMAGE(2), //图片
		VOICE(3), //语音
		MPVIDEO(4), //视频
		MUSIC(5); //音乐
		
		private int typeValue;
		
		private GroupPreviewMsgTypes(int typeValue){
			this.typeValue = typeValue;
		}
		
		public String getType() {
			return this.name().toLowerCase();
		}
		
		public int getTypeValue() {
			return this.typeValue;
		}
		
		public static boolean contains(String msgType) {
			for (GroupPreviewMsgTypes msg : GroupPreviewMsgTypes.values()) {
                if (msg.getType().equalsIgnoreCase(msgType)) {
                    return true;
                }
            }
			return false;
		}
		
		public static boolean contains(int msgTypeValue) {
			for (GroupPreviewMsgTypes msg : GroupPreviewMsgTypes.values()) {
                if (msg.getTypeValue() == msgTypeValue) {
                    return true;
                }
            }
			return false;
		}
	}
	
	/**
	 * 上传临时素材文件类型
	 */
	public enum MediaTypes {
		IMAGE(0), //图片
		VOICE(1), //语音
		VIDEO(2), //视频
		THUMB(3), //缩略图
		NEWS(4); //图文
		
		private int typeValue;
		
		private MediaTypes(int typeValue){
			this.typeValue = typeValue;
		}
		
		public String getType() {
			return this.name().toLowerCase();
		}
		
		public int getTypeValue() {
			return this.typeValue;
		}
		
		public static boolean contains(String mediaType) {
			for (MediaTypes media : MediaTypes.values()) {
                if (media.getType().equalsIgnoreCase(mediaType)) {
                    return true;
                }
            }
			return false;
		}
		
		public static boolean contains(int mediaTypeValue) {
			for (MediaTypes media : MediaTypes.values()) {
                if (media.getTypeValue() == mediaTypeValue) {
                    return true;
                }
            }
			return false;
		}
	}
	
	/**
	 * 上传永久素材文件类型
	 */
	public enum MaterialTypes {
		IMAGE(0), //图片
		VOICE(1), //语音
		VIDEO(2), //视频
		THUMB(3); //缩略图
		
		private int typeValue;
		
		private MaterialTypes(int typeValue){
			this.typeValue = typeValue;
		}
		
		public String getType() {
			return this.name().toLowerCase();
		}
		
		public int getTypeValue() {
			return this.typeValue;
		}
		
		public static boolean contains(String mediaType) {
			for (MaterialTypes media : MaterialTypes.values()) {
                if (media.getType().equalsIgnoreCase(mediaType)) {
                    return true;
                }
            }
			return false;
		}
		
		public static boolean contains(int mediaTypeValue) {
			for (MaterialTypes media : MaterialTypes.values()) {
                if (media.getTypeValue() == mediaTypeValue) {
                    return true;
                }
            }
			return false;
		}
	}
	
	/**
	 * 是否
	 */
	public enum YesOrNo {
		NO(0), //否
		YES(1); //是
		
		private int cValue;
		
		private YesOrNo(int cValue){
			this.cValue = cValue;
		}
		
		public static boolean contains(int cValue) {
			for (YesOrNo g : YesOrNo.values()) {
                if (g.getcValue() == cValue) {
                    return true;
                }
            }
			return false;
		}

		public int getcValue() {
			return cValue;
		}

		public void setcValue(int cValue) {
			this.cValue = cValue;
		}
	}
	
}
