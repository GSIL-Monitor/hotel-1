package com.fangcang.merchant.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.merchant.domain.ResourceDO;
import com.fangcang.merchant.domain.RoleDO;
import com.fangcang.merchant.dto.ResourceDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.mapper.RoleMapper;
import com.fangcang.merchant.request.GetUserByIdRequestDTO;
import com.fangcang.merchant.request.GetRoleListByResourceIdRequestDTO;
import com.fangcang.merchant.request.RoleQueryDTO;
import com.fangcang.merchant.response.AllRoleResponseDTO;
import com.fangcang.merchant.response.GetRoleListByResourceIdResponseDTO;
import com.fangcang.merchant.response.MenuResponseDTO;
import com.fangcang.merchant.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     *获取角色列表
     */
	@Override
	public ResponseDTO<AllRoleResponseDTO> queryRoleList() {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			AllRoleResponseDTO allRoleResponseDTO = new AllRoleResponseDTO();
            List<RoleDO> roleList = roleMapper.queryRoleList();
            if(!CollectionUtils.isEmpty(roleList)){
            	List<RoleDTO> roleDTOList = new ArrayList<>();
            	roleDTOList = PropertyCopyUtil.transferArray(roleList,RoleDTO.class);
            	allRoleResponseDTO.setRoleList(roleDTOList);
            	responseDTO.setModel(allRoleResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryRoleList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}

	/**
	 * 根据角色ID获取菜单权限
	 */
	@Override
	public ResponseDTO<MenuResponseDTO> getMenuByRole(RoleQueryDTO roleQueryDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			MenuResponseDTO menuResponseDTO = new MenuResponseDTO();
			List<ResourceDO> resourceList = roleMapper.queryResourceByRoleId(roleQueryDTO);
			if(!CollectionUtils.isEmpty(resourceList)){
				List<ResourceDTO> resourceDTOList = new ArrayList<>();
				resourceDTOList = PropertyCopyUtil.transferArray(resourceList, ResourceDTO.class);
				menuResponseDTO.setResourceList(resourceDTOList);
				menuResponseDTO.setRoleId(roleQueryDTO.getRoleId());
				RoleDO roleDO  = new RoleDO();
				roleDO = roleMapper.getRoleInfoByRoleId(roleQueryDTO);
				if(roleDO != null){
					menuResponseDTO.setRoleName(roleDO.getRoleName());
					menuResponseDTO.setDescribe(roleDO.getDescription());
				}
				responseDTO.setModel(menuResponseDTO);
			}
		}catch (Exception e) {
            log.error("getMenuByRole",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
		return responseDTO;
	}
	
	/**
     *根据resourceID获取对应的角色列表
     */
	@Override
	public ResponseDTO<GetRoleListByResourceIdResponseDTO> getRoleListByResource(GetRoleListByResourceIdRequestDTO getRoleListByResourceIdRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			GetRoleListByResourceIdResponseDTO getRoleListByResourceIdResponseDTO = new GetRoleListByResourceIdResponseDTO();
			List<RoleDO> roleList = roleMapper.queryRoleListByResourceId(getRoleListByResourceIdRequestDTO);
			if(!CollectionUtils.isEmpty(roleList)){
				List<RoleDTO> roleDTOList = new ArrayList<>();
				roleDTOList = PropertyCopyUtil.transferArray(roleList,RoleDTO.class);
				getRoleListByResourceIdResponseDTO.setRoleList(roleDTOList);
				responseDTO.setModel(getRoleListByResourceIdResponseDTO);
			}
        } catch (Exception e) {
            log.error("queryRoleList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}

	@Override
	public List<ResourceDTO> queryMenuByRoleId(List<Long> roleId) {
		List<ResourceDO> resourceDOList = roleMapper.queryMenuByRoleId(roleId);
		return PropertyCopyUtil.transferArray(resourceDOList,ResourceDTO.class);
	}

	@Override
	public List<String> getResourceUrl(List<ResourceDTO> resourceDTOList) {
		if(CollectionUtils.isEmpty(resourceDTOList)){
			return null;
		}
		List<String> urlList = new ArrayList<>();
		for (ResourceDTO dto : resourceDTOList){
			urlList.add(dto.getUrlPattern());
		}
		return urlList;
	}

	@Override
	public Map<String, List<ResourceDTO>> queryMenuMap(List<ResourceDTO> resourceDTOList) {
		Map<String, List<ResourceDTO>> resultMap = new HashMap<>();
		//去除有角色有相同菜单导致的重复问题。
		Map<String, List<String>> tempMap = new HashMap<>();
		for (ResourceDTO resourceDTO : resourceDTOList){
			if (isTopMenu(resourceDTO)){
				if (!resultMap.containsKey(resourceDTO.getResourceCode())){
					List<ResourceDTO> resourceDTOs = new ArrayList<>();
					resultMap.put(resourceDTO.getResourceCode(),resourceDTOs);

					List<String> tempList = new ArrayList<>();
					tempMap.put(resourceDTO.getResourceCode(),tempList);

				}
			}
			else {
				if (resultMap.containsKey(resourceDTO.getPCode())){

					if (!tempMap.get(resourceDTO.getPCode()).contains(resourceDTO.getResourceCode())){
						resultMap.get(resourceDTO.getPCode()).add(resourceDTO);
					}

					tempMap.get(resourceDTO.getPCode()).add(resourceDTO.getResourceCode());

				}
				else {
					List<ResourceDTO> resourceDTOs = new ArrayList<>();
					resourceDTOs.add(resourceDTO);
					resultMap.put(resourceDTO.getPCode(),resourceDTOs);


					List<String> tempList = new ArrayList<>();
					tempList.add(resourceDTO.getResourceCode());
					tempMap.put(resourceDTO.getPCode(),tempList);

				}
			}
		}
		return resultMap;
	}

	/**
	 * 是否是一级菜单
	 * @return
     */
	private boolean isTopMenu(ResourceDTO resourceDTO){
		return resourceDTO.getLevel().intValue() == 1;
	}

	@Override
	public List<RoleDTO> getRoleListByUserId(Long userId) {
		GetUserByIdRequestDTO getUserByIdRequestDTO = new GetUserByIdRequestDTO();
		getUserByIdRequestDTO.setUserId(userId);
		List<RoleDO> roleDOList = roleMapper.getRoleListByUserId(getUserByIdRequestDTO);
		List<RoleDTO> roleDTOList = PropertyCopyUtil.transferArray(roleDOList,RoleDTO.class);
		return roleDTOList;
	}

}
