package com.travel.hotel.dlt.enums;

import org.springframework.util.StringUtils;


public enum ErrorCodeEnum {
	/** 100, 取消失败/异常,4 */
	RETURN_100(100, "取消失败/异常",2,"INVALID_ORDERCODE"),
	/** 101, "酒店满房/房量不足",4 */
	RETURN_101(101, "酒店满房/房量不足",4,"DISSATISFY_QUOTA,INVALID_SUPPLY_CONTRACT,INVALID_CONTRACT,INVALID_ONSALE"),
	/** 102, "备注不接受",4 */
	RETURN_102(102, "备注不接受",4,""),
	/** 103, "价格错误",4  */
	RETURN_103(103, "价格错误",4,"PRICE_NEED_QUERY,INVALID_PRICE"),
	/** 104, "日期错误/日期超过最大入住天数",4 */
	RETURN_104(104, "日期错误/日期超过最大入住天数",4,"INVALID_CHECKINDATE,INVALID_CHECKOUTDATE"),
	/** 105, "已入住不允许取消或修改",4 */
	RETURN_105(105, "已入住不允许取消或修改",4,""),
	/** 106, "已过最晚取消修改时间",2 */
	RETURN_106(106, "已过最晚取消修改时间",2,""),
	/** 107, "修改/取消原订单不存在",2 */
	RETURN_107(107, "修改/取消原订单不存在",2,""),
	/**  108, "程序错误",2 */
	RETURN_108(108, "程序错误",2,""),
	/**  109, "网络问题(程序错误)",2  */
	RETURN_109(109, "网络问题(程序错误)",2,""),
	/** 110, "担保错误",4 */
	RETURN_110(110, "担保错误",4,""),
	/** 111, "酒店已停止合作",2 */
	RETURN_111(111, "酒店已停止合作",2,""),
	/** 112  直连匹配错误(程序错误) 2*/
	RETURN_112(112, "直连匹配错误(程序错误)",2,""),
	/** 113  重复预定 4*/
	RETURN_113(113, "重复预定",4,""),
	/** 114  需要全额担保 4*/
	RETURN_114(114, "需要全额担保",4,""),
	/** 115  人数超限 4*/
	RETURN_115(115, "人数超限",4,""),
	/** 116  客人当天累计已超间，请提供担保重新预订 4*/
	RETURN_116(116, "客人当天累计已超间，请提供担保重新预订",4,""),
	/** 117  直连匹配错误(程序错误) 2*/
	RETURN_117(117, "服务器运行错误(程序错误)",2,""),
	/** 118  用户认证或授权错误(程序错误) 2*/
	RETURN_118(118, "用户认证或授权错误(程序错误)",2,""),
	/** 119  参数错误(程序错误) 2*/
	RETURN_119(119, "参数错误(程序错误)",2,"INVALID_INPUTPARAM"),
	/** 120  预订信息不正确 4*/
	RETURN_120(120, "预订信息不正确",4,"INVALID_GUEST,INVALID_BREAKFAST_NUM,INVALID_BREAKFAST_TYPE,INVALID_ROOMTYPE,INVALID_HOTEL,INVALID_COMMODITY,INVALID_INPUTPARAM,DISSATISFY_OCCUPANCYCLAUSE,DISSATISFY_BOOKINGCLAUSE,INVALID_INPUTPARAM"),
	/** 122  订单不允许修改 2*/
	RETURN_121(121, "订单不允许修改",4,""),
	/** 122  订单不允许取消 4*/
	RETURN_122(122, "订单不允许取消",4,""),
	/** 123  需要一律担保 4*/
	RETURN_123(123, "需要一律担保",4,""),
    RETURN_1001(1001, "用户认证或授权错误(程序错误)",4,""),
    RETURN_1002(1002, "每分钟超过调用限制",4,""),
    RETURN_1003(1003, "消息序列化失败",4,""),
    RETURN_1011(1011, "用户与集团编号不匹配",4,""),
    RETURN_1012(1012, "用户身份验证失败",4,""),
    RETURN_1013(1013, "用户已被禁用",4,""),
    RETURN_1014(1014, "用户名长度不符，用户名长度为6-20",4,""),
    RETURN_1016(1016, "密码长度不符，密码长度为8-20",4,""),
    RETURN_1017(1017, "该用户已被锁定",4,""),
    RETURN_1101(1101, "消息序号乱序",4,""),
    RETURN_1102(1102, "消息序号重复",4,""),
    RETURN_1103(1103, "消息序号缺失",4,""),
    RETURN_1104(1104, "无效的消息序号",4,""),
    RETURN_1105(1105, "酒店与集团不匹配",4,""),
    RETURN_1106(1106, "无效的酒店编号",4,""),
    RETURN_1107(1107, "无效的房型编号",4,""),
    RETURN_1108(1108, "房型与酒店不匹配",4,""),
    RETURN_1109(1109, "同一房型时间重叠",4,""),
    RETURN_1110(1110, "该酒店保留房功能已关闭",4,""),
    RETURN_1111(1111, "推送的房量不能为负数",4,""),
    RETURN_1112(1112, "请求的保留房更新方式必须为同一类型",4,""),
    RETURN_1113(1113, "请求的保留房更新方式与该集团的设置不相符",4,""),
    RETURN_1114(1114, "日期错误，时间格式不正确",4,""),
    RETURN_1115(1115, "日期错误，时间范围超限，一个请求只允许更新120天内的房量",4,""),
    RETURN_1116(1116, "日期错误，起始时间大于结束时间",4,""),
    RETURN_1117(1117, "日期错误，起始时间或结束时间小于当前时间",4,""),
    RETURN_1118(1118, "编辑者名称拼音超过长度，长度需小于50",4,""),
    RETURN_1119(1119, "更新描述超过长度，长度需小于200",4,""),
    RETURN_1120(1120, "一个请求只允许发送少于100个房型",4,""),
    RETURN_1121(1121, "推送的房量超出上限",4,""),
    RETURN_1122(1122, "连续发送请求次数超过限制",4,""),
    RETURN_1123(1123, "离再次请求的时间还有*秒钟，请继续等待",4,""),
    RETURN_1124(1124, "当前正在处理的任务数量太多，无法处理新的任务",4,""),
    RETURN_1125(1125, "推送的房量不能超过32767",4,""),
    RETURN_1126(1126, "一个请求只允许发送10个酒店的房型",4,""),
	/** 9999  其他 2*/
	RETURN_9999(9999, "其他 ",2,"");
	
	public int key;
	public String value;
	public int orderState;
	public String error;
	
	ErrorCodeEnum(int key, String value, int orderState, String error){
		this.key = key;
		this.value = value;
		this.orderState = orderState;
		this.error = error;
	}
	
	public static String getValueByKey(int key){
		for(ErrorCodeEnum code : ErrorCodeEnum.values()){
			if(code.key == key) {
				return code.value;
			}
		}
		return "";
	}
	
	public static ErrorCodeEnum getErrorCodeEnumByError(String error)
	{
		if(StringUtils.isEmpty(error))
		{
			return null;
		}
		for(ErrorCodeEnum code : ErrorCodeEnum.values()){
			if(code.error.contains(error)) {
				return code;
			}
		}
		return RETURN_9999;
	}
	
}
