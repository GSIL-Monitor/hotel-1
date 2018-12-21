package com.fangcang.merchant.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.merchant.dto.ResourceDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.request.GetRoleListByResourceIdRequestDTO;
import com.fangcang.merchant.request.RoleQueryDTO;
import com.fangcang.merchant.response.AllRoleResponseDTO;
import com.fangcang.merchant.response.GetRoleListByResourceIdResponseDTO;
import com.fangcang.merchant.response.MenuResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wen.zhong@fangcang.com
 * @Date: 2018/5/30
 * @Description: 角色Service接口
 */
public interface RoleService {

	/**
	 * 获取角色列表
	 * @return
	 */
    public ResponseDTO<AllRoleResponseDTO> queryRoleList();
    
    /**
	 * 根据角色ID获取菜单列表
	 * @return
	 */
    public ResponseDTO<MenuResponseDTO> getMenuByRole(RoleQueryDTO roleQueryDTO);


	/**
	 * 根据用户ID获取用户权限
	 * @param userId
	 * @return
	 */
	public List<RoleDTO> getRoleListByUserId(Long userId);

    
    /**
	 * 根据resourceID获取对应的角色列表
	 * @return
	 */
    public ResponseDTO<GetRoleListByResourceIdResponseDTO> getRoleListByResource(GetRoleListByResourceIdRequestDTO getRoleListByResourceIdRequestDTO);

	/**
	 * 根据角色ID查询菜单列表
	 * @param roleId
	 * @return
     */
	public List<ResourceDTO> queryMenuByRoleId(List<Long> roleId);


	/**
	 * URL列表
	 * @param resourceDTOList
	 * @return
	 */
	public List<String> getResourceUrl(List<ResourceDTO> resourceDTOList);

	/**
	 *
	 * @param resourceDTOList
	 * @return key：一级菜单CODE, value:子菜单集合
     */
	public Map<String,List<ResourceDTO>> queryMenuMap(List<ResourceDTO> resourceDTOList);

}
