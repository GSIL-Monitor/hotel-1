package com.travel.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 基础查询类
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午11:48:05
 */
@Data
public class GenericQueryDTO implements Serializable {

	private static final long serialVersionUID = 5470961679994601274L;

	protected Integer pageSize = 10;

	protected Integer currentPage = 1;
}
