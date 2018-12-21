package com.fangcang.common;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页对象
 */
@AllArgsConstructor
public class PaginationSupportDTO<T> implements Serializable{

	private static final long serialVersionUID = 6233240765810813271L;
	
	private List<T> itemList;
	
	/**
	 * 总记录数
	 */
	private long totalCount;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 当前页 默认1
	 */
	private int currentPage = 1;
	
	/**
	 * 页面记录数
	 */
	private int pageSize = 5;
	
	public PaginationSupportDTO(){
		this(15);
	}
	
	public PaginationSupportDTO(int pageSize){
		this.pageSize = pageSize;
	}

	public List<T> getItemList() {
		if(null == this.itemList) this.itemList = Collections.EMPTY_LIST;
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		
		if((this.totalCount % this.pageSize) == 0){
			this.totalPage = (new Long(this.totalCount/this.pageSize)).intValue();
			
		}else{
			this.totalPage = (new Long((this.totalCount/this.pageSize) + 1)).intValue();
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(0 == currentPage){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize == 0){
			this.pageSize = 15;
		}else{
			this.pageSize = pageSize;
		}
	}
}
