package com.fangcang.message.weixin.send;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.request.SendTemplateMessageRequest;
import com.fangcang.message.weixin.send.response.TemplateIdResponse;
import com.fangcang.message.weixin.send.response.TemplateMessageResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息服务
 */
@Slf4j
public class WxTemplateMessageCall extends BaseActiveCall {
	
	/**
	 * 获取模板ID
	 *
	 * @param accessToken	微信access token
	 * @param templateCode	模板编号
	 * @return	模板ID
	 * @throws Exception
	 */
	public static TemplateIdResponse getTemplateId(String accessToken, String templateCode) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("template_id_short", templateCode);
		
		//发送请求
		TemplateIdResponse servTemplateIdReturn = postActiveCall(accessToken, WxURLConstant.WXURL_GET_TEMPLATEID, null, JSONObject.toJSONString(map), TemplateIdResponse.class);
        return servTemplateIdReturn;
	}
	
	public static TemplateMessageResponse sendTemplateMessage(String accessToken, String toUser, String templateId, String url, String topColor, JSONObject data) throws Exception {
		SendTemplateMessageRequest msg = new SendTemplateMessageRequest();
		msg.setTouser(toUser);
		msg.setTemplate_id(templateId);
		msg.setUrl(url);
		msg.setTopcolor(topColor);
		msg.setData(data);
		
		//发送请求
		TemplateMessageResponse servTemplateMessageReturn = postActiveCall(accessToken, WxURLConstant.WXURL_TEMPLATE_MSG_SEND, null, JSONObject.toJSONString(msg), TemplateMessageResponse.class);
		return servTemplateMessageReturn;
	}
}
