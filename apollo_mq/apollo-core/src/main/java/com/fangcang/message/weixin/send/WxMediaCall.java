package com.fangcang.message.weixin.send;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.weixin.enums.WxEnumConstant;
import com.fangcang.message.weixin.enums.WxErrorConstant;
import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.request.NewsMediaRequest;
import com.fangcang.message.weixin.send.response.MediaUploadResponse;
import com.fangcang.message.weixin.send.response.MsgImageUploadResponse;
import com.fangcang.message.weixin.util.ErrorUtils;
import com.fangcang.message.weixin.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 临时素材管理
 */
@Slf4j
public class WxMediaCall extends BaseActiveCall {
	
	/**
	 * 上传媒体素材(默认实现)
	 */
	public static MediaUploadResponse uploadMedia(String accessToken, String mediaType, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		urlParams.put("type", mediaType==null?null:mediaType.toLowerCase());
		return uploadMedia(accessToken, urlParams, fileNetUrl);
	}
	
	/**
	 * 上传图片素材
	 */
	public static MediaUploadResponse uploadImage(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		urlParams.put("type", WxEnumConstant.MediaTypes.IMAGE.toString().toLowerCase());
		return uploadMedia(accessToken, urlParams, fileNetUrl);
	}
	
	/**
	 * 上传语音素材
	 */
	public static MediaUploadResponse uploadVoice(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		urlParams.put("type", WxEnumConstant.MediaTypes.VOICE.toString().toLowerCase());
		return uploadMedia(accessToken, urlParams, fileNetUrl);
	}
	
	/**
	 * 上传视频素材
	 */
	public static MediaUploadResponse uploadVideo(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		urlParams.put("type", WxEnumConstant.MediaTypes.VIDEO.toString().toLowerCase());
		return uploadMedia(accessToken, urlParams, fileNetUrl);
	}
	
	/**
	 * 上传缩略图素材
	 */
	public static MediaUploadResponse uploadThumb(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		urlParams.put("type", WxEnumConstant.MediaTypes.THUMB.toString().toLowerCase());
		return uploadMedia(accessToken, urlParams, fileNetUrl);
	}
	
	
	/**
     * 上传素材文件
     * @param accessToken
     * @param urlParams 请求URL后带的参数【非必须】
     * @param fileNetUrl
     * @return MediaUploadResponse
	 * @throws Exception 
     */
	private static MediaUploadResponse uploadMedia(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		try{
			urlParams.put("access_token", accessToken);
			
			String requestUrl = HttpUtils.getUrlWithParams(WxURLConstant.WXURL_ADD_MEDIA,urlParams);
			log.info("请求微信地址[UPLOAD_MEDIA]："+requestUrl);
			
			String fileName = null;
			int start = fileNetUrl.lastIndexOf("/");
			fileName = fileNetUrl.substring(start+1);
			
			BufferedInputStream fileDataInputStream = HttpUtils.getNetFileInputStream(fileNetUrl);
			String receiveJsonStr = HttpUtils.upload(requestUrl, null, fileName, fileDataInputStream);
			log.info("微信返回结果[UPLOAD_MEDIA]："+receiveJsonStr);
			
			MediaUploadResponse servMediaUploadReturn = JSONObject.parseObject(receiveJsonStr, MediaUploadResponse.class);
	        
	        return servMediaUploadReturn;
		}catch(Throwable t){
			log.error(t.getMessage(), t);
			return ErrorUtils.getWeixinErrorReturn(WxErrorConstant.SYSTEM_BUSY, t.getMessage(), new MediaUploadResponse());
		}
	}
	
	/**
     * 上传媒体图片（图文消息内图片用此接口）
     * @param accessToken
     * @param fileNetUrl
     * @return MsgImageUploadResponse
	 * @throws Exception 
     */
	public static MsgImageUploadResponse uploadMsgImage(String accessToken, String fileNetUrl) throws Exception{
		try{
			Map<String, String> urlParams = new HashMap<String, String>();
			urlParams.put("access_token", accessToken);
			
			String requestUrl = HttpUtils.getUrlWithParams(WxURLConstant.WXURL_MSG_IMAGE,urlParams);
			log.info("请求微信地址[UPLOAD_MSG_IMAGE]："+requestUrl);
			
			String fileName = null;
			int start = fileNetUrl.lastIndexOf("/");
			fileName = fileNetUrl.substring(start+1);
			
			BufferedInputStream fileDataInputStream = HttpUtils.getNetFileInputStream(fileNetUrl);
			String receiveJsonStr = HttpUtils.upload(requestUrl, null, fileName, fileDataInputStream);
			log.info("微信返回结果[UPLOAD_MSG_IMAGE]："+receiveJsonStr);
			
			MsgImageUploadResponse servMsgImageUploadReturn = JSONObject.parseObject(receiveJsonStr, MsgImageUploadResponse.class);
	        
	        return servMsgImageUploadReturn;
		}catch(Throwable t){
			log.error(t.getMessage(), t);
			return ErrorUtils.getWeixinErrorReturn(WxErrorConstant.SYSTEM_BUSY, t.getMessage(), new MsgImageUploadResponse());
		}
	}
	
	/**
     * 上传媒体图片（图文消息内图片用此接口）
     * @param accessToken
     * @param inputStream
     * @param fileName
     * @return MsgImageUploadResponse
	 * @throws Exception 
     */
	public static MsgImageUploadResponse uploadMsgImage(String accessToken, InputStream inputStream, String fileName) throws Exception{
		try{
			Map<String, String> urlParams = new HashMap<String, String>();
			urlParams.put("access_token", accessToken);
			
			String requestUrl = HttpUtils.getUrlWithParams(WxURLConstant.WXURL_MSG_IMAGE,urlParams);
			log.info("请求微信地址[UPLOAD_MSG_IMAGE]："+requestUrl);
			
			BufferedInputStream fileDataInputStream = new BufferedInputStream(inputStream);
			String receiveJsonStr = HttpUtils.upload(requestUrl, null, fileName, fileDataInputStream);
			log.info("微信返回结果[UPLOAD_MSG_IMAGE]："+receiveJsonStr);
			
			MsgImageUploadResponse servMsgImageUploadReturn = JSONObject.parseObject(receiveJsonStr, MsgImageUploadResponse.class);
	        
	        return servMsgImageUploadReturn;
		}catch(Throwable t){
			log.error(t.getMessage(), t);
			return ErrorUtils.getWeixinErrorReturn(WxErrorConstant.SYSTEM_BUSY, t.getMessage(), new MsgImageUploadResponse());
		}
	}
	
	/**
     * 上传news图文素材<br>
     * @param accessToken accessToken【必须】
     * @param sendMessage 要发送的消息对象【必须】
     * @return SendMessageReturn
	 * @throws Exception 
     */
	public static MediaUploadResponse uploadNews(String accessToken, NewsMediaRequest servNewsMedia) throws Exception{
		//发送请求
		MediaUploadResponse servMediaUploadReturn = postActiveCall(accessToken, WxURLConstant.WXURL_ADD_MEDIA_NEWS, null, JSONObject.toJSONString(servNewsMedia), MediaUploadResponse.class);
        return servMediaUploadReturn;
	}
	
	
}
