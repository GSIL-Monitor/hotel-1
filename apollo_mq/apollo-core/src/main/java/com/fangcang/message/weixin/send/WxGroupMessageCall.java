package com.fangcang.message.weixin.send;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.request.PreviewGroupMessageRequest;
import com.fangcang.message.weixin.send.request.SendGroupMessageRequest;
import com.fangcang.message.weixin.send.response.SendGroupMessageResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 高级群发服务
 */
@Slf4j
public class WxGroupMessageCall extends BaseActiveCall {
	
	/**
     * 发送news消息<br>
     * @param accessToken accessToken【必须】
     * @param isToAll 是否全体【isToAll，groupId，openIds三选一】
     * @param groupId 分组ID【isToAll，groupId，openIds三选一】
     * @param openIds 关注者列表【isToAll，groupId，openIds三选一】
     * @return SendGroupMessageResponse
	 * @throws Exception 
     */
	public static SendGroupMessageResponse sendMpnewsMessage(String accessToken, Boolean isToAll, Integer groupId, List<String> openIds, String mediaId) throws Exception{
		String url = (openIds==null || openIds.size()==0)? WxURLConstant.WXURL_MSG_SEND_ALL: WxURLConstant.WXURL_MSG_SEND;
		SendGroupMessageRequest msg = new SendGroupMessageRequest();
		msg.setReceiver(isToAll, groupId, openIds);
		msg.setMpnewsInfo(mediaId);
		
		//发送请求
		SendGroupMessageResponse servSendGroupMessageReturn = postActiveCall(accessToken, url, null, JSONObject.toJSONString(msg), SendGroupMessageResponse.class);
        return servSendGroupMessageReturn;
	}
	
	/**
     * 发送text消息<br>
     * @param accessToken accessToken【必须】
     * @param isToAll 是否全体【isToAll，groupId，openIds三选一】
     * @param groupId 分组ID【isToAll，groupId，openIds三选一】
     * @param openIds 关注者列表【isToAll，groupId，openIds三选一】
     * @return SendGroupMessageResponse
	 * @throws Exception 
     */
	public static SendGroupMessageResponse sendTextMessage(String accessToken, Boolean isToAll, Integer groupId, List<String> openIds, String content) throws Exception{
		String url = (openIds==null || openIds.size()==0)? WxURLConstant.WXURL_MSG_SEND_ALL: WxURLConstant.WXURL_MSG_SEND;
		SendGroupMessageRequest msg = new SendGroupMessageRequest();
		msg.setReceiver(isToAll, groupId, openIds);
		msg.setTextInfo(content);
		
		//发送请求
		SendGroupMessageResponse servSendGroupMessageReturn = postActiveCall(accessToken, url, null, JSONObject.toJSONString(msg), SendGroupMessageResponse.class);
        return servSendGroupMessageReturn;
	}
	
	/**
     * 预览news消息<br>
     * @param accessToken accessToken【必须】
     * @param touser 接收人openid【接收人openid 和 接收人微信号 二选一】
     * @param toWxName 接收人微信号【接收人openid 和 接收人微信号 二选一】
     * @return SendGroupMessageResponse
	 * @throws Exception 
     */
	public static SendGroupMessageResponse previewMpnewsMessage(String accessToken, String touser, String toWxName, String mediaId) throws Exception{
		PreviewGroupMessageRequest msg = new PreviewGroupMessageRequest();
		msg.setTouser(touser);
		msg.setTowxname(toWxName);
		msg.setMpnewsInfo(mediaId);
		
		//发送请求
		SendGroupMessageResponse servSendGroupMessageReturn = postActiveCall(accessToken, WxURLConstant.WXURL_MSG_PREVIEW, null, JSONObject.toJSONString(msg), SendGroupMessageResponse.class);
        return servSendGroupMessageReturn;
	}
}
