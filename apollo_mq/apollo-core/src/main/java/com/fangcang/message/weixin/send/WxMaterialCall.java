package com.fangcang.message.weixin.send;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.weixin.enums.WxEnumConstant;
import com.fangcang.message.weixin.enums.WxErrorConstant;
import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.response.MaterialUploadResponse;
import com.fangcang.message.weixin.util.ErrorUtils;
import com.fangcang.message.weixin.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 永久素材管理
 */
@Slf4j
public class WxMaterialCall {
	
	
	/**
	 * 上传图片素材
	 */
	public static MaterialUploadResponse uploadImageMaterial(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		urlParams.put("type", WxEnumConstant.MaterialTypes.IMAGE.toString().toLowerCase());
		return uploadMaterial(accessToken, urlParams, fileNetUrl);
	}
	
	
	/**
     * 上传素材文件
     * @param accessToken
     * @param urlParams 请求URL后带的参数【非必须】
     * @param fileNetUrl
     * @return MaterialUploadResponse
	 * @throws Exception 
     */
	private static MaterialUploadResponse uploadMaterial(String accessToken, Map<String, String> urlParams, String fileNetUrl) throws Exception{
		try{
			urlParams.put("access_token", accessToken);
			
			String requestUrl = HttpUtils.getUrlWithParams(WxURLConstant.WXURL_ADD_MATERIAL,urlParams);
			log.info("请求微信地址[UPLOAD_MEDIA]："+requestUrl);
			
			String fileName = null;
			int start = fileNetUrl.lastIndexOf("/");
			fileName = fileNetUrl.substring(start+1);
			
			BufferedInputStream fileDataInputStream = HttpUtils.getNetFileInputStream(fileNetUrl);
			String receiveJsonStr = HttpUtils.upload(requestUrl, null, fileName, fileDataInputStream);
			log.info("微信返回结果[UPLOAD_MEDIA]："+receiveJsonStr);
			
			MaterialUploadResponse servMaterialUploadReturn = JSONObject.parseObject(receiveJsonStr, MaterialUploadResponse.class);
	        
	        return servMaterialUploadReturn;
		}catch(Throwable t){
			log.error(t.getMessage(), t);
			return ErrorUtils.getWeixinErrorReturn(WxErrorConstant.SYSTEM_BUSY, t.getMessage(), new MaterialUploadResponse());
		}
	}
}
