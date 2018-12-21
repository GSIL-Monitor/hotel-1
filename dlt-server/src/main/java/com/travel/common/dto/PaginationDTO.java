package com.travel.common.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Description 分页对象
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日下午12:36:03
 */
@Data
public class PaginationDTO<T> extends SerializeDTO {

	private static final long serialVersionUID = -3269275935544151803L;
	
	/**
	 * 每页显示数量
	 */
	private Integer pageSize;
	
	/**
	 * 当前页数
	 */
	private Integer currentPage;
	
	/**
	 * 总数量
	 */
	private Integer totalCount;
	
	/**
	 * 总页数
	 */
	private Integer totalPages;
	
	/**
	 * 结果集
	 */
	private List<T> recordList = Collections.emptyList();

}
