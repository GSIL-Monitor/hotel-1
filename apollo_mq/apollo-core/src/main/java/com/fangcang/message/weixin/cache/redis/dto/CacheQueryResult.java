package com.fangcang.message.weixin.cache.redis.dto;

import java.util.List;

public class CacheQueryResult<T> {
	
	private Boolean result = false;
	
	private T t;
	
	private List<T> list;
	
	private List<List<T>> lists;

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setT(T t) {
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}

	public void setLists(List<List<T>> lists) {
		this.lists = lists;
	}

	public List<List<T>> getLists() {
		return lists;
	}

}
