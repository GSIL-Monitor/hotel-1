package com.fangcang.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class PropertyCopyUtil {
	
	public static <T,S> S transfer(T t,Class<S> s){

		return (S) JSONObject.parseObject(JSONObject.toJSONString(t),s);
	}

	public static <T> List<T>  transferArray(Object object,Class<T> t){
		return JSON.parseArray(JSON.toJSONString(object),t);
	}

}
