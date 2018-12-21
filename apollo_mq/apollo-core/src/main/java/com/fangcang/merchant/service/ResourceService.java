package com.fangcang.merchant.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.merchant.dto.RoleResourceDTO;
import com.fangcang.merchant.response.AllResourceResponseDTO;

import java.util.List;

/**
 * @Auther: wen.zhong@fangcang.com
 * @Date: 2018/5/30
 * @Description: 资源resource Service接口
 */
public interface ResourceService {

	/**
	 * 获取所有资源resource列表
	 * @return
	 */
    public ResponseDTO<AllResourceResponseDTO> queryResourceList();

	/**
	 * 查询需要校验的资源的URL
	 * @return
     */
	public List<String> getNeedCheckResourceUrl();

	/**
	 * 查询资源，以及访问资源需要的角色
	 * @return
     */
	List<RoleResourceDTO> queryRoleResourceList();
}
