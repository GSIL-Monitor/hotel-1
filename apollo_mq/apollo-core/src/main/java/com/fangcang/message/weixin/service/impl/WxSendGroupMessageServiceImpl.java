package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.remote.request.weixin.WxSendGroupMpnewsMessageRequestDTO;
import com.fangcang.message.remote.request.weixin.WxSendGroupTextMessageRequestDTO;
import com.fangcang.message.remote.response.weixin.WxSendGroupMessageResponseDTO;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.enums.WxEnumConstant;
import com.fangcang.message.weixin.enums.WxErrorConstant;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.send.WxGroupMessageCall;
import com.fangcang.message.weixin.send.WxMediaCall;
import com.fangcang.message.weixin.send.request.NewsMediaRequest;
import com.fangcang.message.weixin.send.response.BaseResponse;
import com.fangcang.message.weixin.send.response.MediaUploadResponse;
import com.fangcang.message.weixin.send.response.SendGroupMessageResponse;
import com.fangcang.message.weixin.service.WxSendGroupMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务号群发消息接口
 */
@Slf4j
@Service("wxSendGroupMessageService")
public class WxSendGroupMessageServiceImpl implements WxSendGroupMessageService {

	@Autowired
	private WxConfigMapper wxConfigMapper;

//	@Autowired
//	private ServHistoryMsgDao servHistoryMsgDao;
	
	/**
	 * 发送图文信息
	 */
	@Override
	public WxSendGroupMessageResponseDTO sendMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request) {
		WxSendGroupMessageResponseDTO servSendGroupMessageResponseDTO = null;

		if(request==null){
			throw new ParameterException("code不能为空");
		}

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		String appId=configDOList.get(0).getAppid();
		String appSecret=configDOList.get(0).getAppsecret();

		try{
			//1.先将图文信息上传为临时素材
			NewsMediaRequest servNewsMedia = new NewsMediaRequest();
			List<NewsMediaRequest.Article> articles = new ArrayList<NewsMediaRequest.Article>();
			if(request != null && request.getArticles()!=null && request.getArticles().size()>0){
				for(WxSendGroupMpnewsMessageRequestDTO.Article fromArticle : request.getArticles()){
					NewsMediaRequest.Article toArticle = servNewsMedia.new Article();
					BeanUtils.copyProperties(fromArticle,toArticle);
					articles.add(toArticle);
				}
			}
			
			String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);
			servNewsMedia.setArticles(articles);
			MediaUploadResponse servMediaUploadReturn = WxMediaCall.uploadNews(accessToken, servNewsMedia);
			
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(servMediaUploadReturn, null);
			
			//2.将该图文素材发送出去
			SendGroupMessageResponse servSendGroupMessageReturn = WxGroupMessageCall.sendMpnewsMessage(accessToken, request.getIsToAll(), request.getGroupId(), request.getOpenIds(), servMediaUploadReturn.getMedia_id());
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(servSendGroupMessageReturn, null);
			if(servSendGroupMessageResponseDTO != null && servSendGroupMessageReturn != null){
				servSendGroupMessageResponseDTO.setMsg_id(servSendGroupMessageReturn.getMsg_id());
			}
		}catch(Exception e){
			log.error("发送失败", e);
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(null, e);
		}
		
		/*将图文消息存储到db表*/
		try{
			int msgType= WxEnumConstant.GroupSendMsgTypes.MPNEWS.getTypeValue();//消息类型-图文
			//saveServMsg(request, servSendGroupMessageResponseDTO,appId,msgType);
		}catch(Exception e){
			log.error("图文信息存储到db表失败", e);
			//servSendGroupMessageResponseDTO = convertSendReturnToDTO(null, e);
		}
		return servSendGroupMessageResponseDTO;
	}
	
	/**
	 * 发送文本信息
	 */
	@Override
	public WxSendGroupMessageResponseDTO sendTextMessage(WxSendGroupTextMessageRequestDTO request) {
		WxSendGroupMessageResponseDTO servSendGroupMessageResponseDTO = null;

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		String appId=configDOList.get(0).getAppid();
		String appSecret=configDOList.get(0).getAppsecret();

		try{
			String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);
			SendGroupMessageResponse servSendGroupMessageReturn = WxGroupMessageCall.sendTextMessage(accessToken, request.getIsToAll(), request.getGroupId(), request.getOpenIds(), request.getContent());
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(servSendGroupMessageReturn, null);
			if(servSendGroupMessageResponseDTO != null && servSendGroupMessageReturn != null){
				servSendGroupMessageResponseDTO.setMsg_id(servSendGroupMessageReturn.getMsg_id());
			}
		}catch(Exception e){
			log.error("发送失败", e);
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(null, e);
		}
		
		//将文本消息存储到db表
		try{
			Integer msgType= WxEnumConstant.GroupSendMsgTypes.TEXT.getTypeValue();//消息类型-文本
//			saveServMsg(request, servSendGroupMessageResponseDTO,appId,msgType);
		}catch(Exception e){
			log.error("文本信息存储到db表失败", e);
			//servSendGroupMessageResponseDTO = convertSendReturnToDTO(null, e);
		}
		return servSendGroupMessageResponseDTO;
	}

	/**
	 * 将微信返回结果转换为DTO
	 */
	private WxSendGroupMessageResponseDTO convertSendReturnToDTO(BaseResponse servBaseReturn, Exception e){
		WxSendGroupMessageResponseDTO dto = new WxSendGroupMessageResponseDTO();

		if(e != null){
			ExceptionHandle.throwServiceException(e,e.getMessage());
		}else if(servBaseReturn != null){
			if(servBaseReturn.getErrcode()!=null && servBaseReturn.getErrcode()!= WxErrorConstant.SUCCESS){
				ExceptionHandle.throwServiceException(e,servBaseReturn.getErrmsg());
			}
		}else{
			ExceptionHandle.throwServiceException(e,"微信返回结果转换为DTO异常");
		}
		return dto;
	}
	
	/**
	 * 将消息存储到db表
	 * @param request
	 * @param servSendGroupMessageResponseDTO
	 * @param reciverType
	 * @throws ParseException
	 */
//	private void saveServMsg(WxBaseSendGroupMessageRequestDTO request,
//			WxSendGroupMessageResponseDTO servSendGroupMessageResponseDTO,String appId, Integer msgType)
//			throws ParseException {
//		ServHistoryMsg servMsg=new ServHistoryMsg();
//		servMsg.setMerchantCode(request.getMerchantCode());
//		servMsg.setAppId(appId);
//		servMsg.setCreator(request.getCreator());
//		//时间
//		servMsg.setCreateTime(new Date());
//		servMsg.setMsgType(msgType);//消息类型
//		Integer reciverType=0;//接收人类型
//		//接收人
//		List<String> openIds=request.getOpenIds();//openid列表
//		if(null==openIds || openIds.size()==0){//不是openid列表
//			Integer groupId=request.getGroupId();//分组id
//			if(null!=groupId){//是分组ID
//				servMsg.setReciver(groupId+"");
//				reciverType=ServReciverType.SERV_GROUP_SENDMSG_GROUP.getKey();
//			}else{//发送对象是全体
//				reciverType=ServReciverType.SERV_GROUP_SENDMSG_ALL.getKey();
//			}
//		}else{
//			String openIdsStr= JSONArray.toJSONString(openIds);
//			servMsg.setReciver(openIdsStr);
//			reciverType=ServReciverType.SERV_GROUP_SENDMSG_OpenID.getKey();
//		}
//		servMsg.setReciverType(reciverType);
//		servMsg.setErrorCode(servSendGroupMessageResponseDTO.getErrorCode());
//		servMsg.setErrorMsg(servSendGroupMessageResponseDTO.getErrorMsg());
//		servMsg.setMsgId(servSendGroupMessageResponseDTO.getMsg_id());
//		if(request instanceof WxSendGroupMpnewsMessageRequestDTO){
//
//		}else{
//			servMsg.setMsgContent(JSON.toJSONString(request));
//		}
//		servHistoryMsgDao.saveHistoryMsg(servMsg);
//	}

	/**
	 * 预览图文信息
	 */
	public WxSendGroupMessageResponseDTO previewMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request) {
		WxSendGroupMessageResponseDTO servSendGroupMessageResponseDTO = null;

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		String appId=configDOList.get(0).getAppid();
		String appSecret=configDOList.get(0).getAppsecret();

		try{
			//1.先将图文信息上传为临时素材
			NewsMediaRequest servNewsMedia = new NewsMediaRequest();
			List<NewsMediaRequest.Article> articles = new ArrayList<NewsMediaRequest.Article>();
			if(request != null && request.getArticles()!=null && request.getArticles().size()>0){
				for(WxSendGroupMpnewsMessageRequestDTO.Article fromArticle : request.getArticles()){
					NewsMediaRequest.Article toArticle = servNewsMedia.new Article();
					BeanUtils.copyProperties(fromArticle,toArticle);
					articles.add(toArticle);
				}
			}
			servNewsMedia.setArticles(articles);
			
			String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);
			MediaUploadResponse servMediaUploadReturn = WxMediaCall.uploadNews(accessToken, servNewsMedia);
			
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(servMediaUploadReturn, null);
			
			//2.将该图文素材发送出去
			SendGroupMessageResponse servSendGroupMessageReturn = WxGroupMessageCall.previewMpnewsMessage(accessToken, request.getPreviewToUser(), request.getPreviewToWxName(), servMediaUploadReturn.getMedia_id());
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(servSendGroupMessageReturn, null);
		}catch(Exception e){
			log.error("发送失败", e);
			servSendGroupMessageResponseDTO = convertSendReturnToDTO(null, e);
		}
		
		return servSendGroupMessageResponseDTO;
	}
}
