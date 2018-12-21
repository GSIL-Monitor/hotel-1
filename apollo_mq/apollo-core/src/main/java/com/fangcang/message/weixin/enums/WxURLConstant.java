package com.fangcang.message.weixin.enums;

/**
 * 微信URL配置常量类
 */
public class WxURLConstant {
	//获取accessToken服务
	public static final String WXURL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	//获取JSApiTicket服务
	public static final String WXURL_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	//群发消息预览接口地址
	public static final String WXURL_MSG_PREVIEW = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
	//根据分组进行群发消息地址
	public static final String WXURL_MSG_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
	//根据OpenID列表群发消息地址
	public static final String WXURL_MSG_SEND = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
	
	//新增临时素材(除图文外)地址
	public static final String WXURL_ADD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";
	//新增临时图文素材地址
	public static final String WXURL_ADD_MEDIA_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
	//图文素材内图片地址
	public static final String WXURL_MSG_IMAGE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
	
	//新增永久素材(除图文外)地址
	public static final String WXURL_ADD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material";
	
	//获取模板ID地址
	public static final String WXURL_GET_TEMPLATEID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template";
	
	//发送模板消息地址
	public static final String WXURL_TEMPLATE_MSG_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send";
	
	//获取用户基本信息
	public static final String WXURL_GET_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	//通过code换取网页授权access_token地址
	public static final String WXURL_AUTH2_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	// 长链接转短链接地址
	public static final String WXURL_SHORTURL = "https://api.weixin.qq.com/cgi-bin/shorturl";
	
	//微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
}
