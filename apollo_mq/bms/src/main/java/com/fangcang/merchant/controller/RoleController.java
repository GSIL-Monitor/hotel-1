package com.fangcang.merchant.controller;

import com.fangcang.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.common.ResponseDTO;
import com.fangcang.merchant.request.GetRoleListByResourceIdRequestDTO;
import com.fangcang.merchant.request.RoleQueryDTO;
import com.fangcang.merchant.response.AllResourceResponseDTO;
import com.fangcang.merchant.response.AllRoleResponseDTO;
import com.fangcang.merchant.response.GetRoleListByResourceIdResponseDTO;
import com.fangcang.merchant.response.MenuResponseDTO;
import com.fangcang.merchant.service.ResourceService;
import com.fangcang.merchant.service.RoleService;

@RestController
@RequestMapping(("/role"))
public class RoleController extends BaseController{

	@Autowired
    private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	/**
	 * 获取角色信息列表
	 * @return
	 */
    @RequestMapping(value = "/getAllRoleList",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<AllRoleResponseDTO> getAllRoleList(){
        return roleService.queryRoleList();
    }
    
    /**
     * 根据角色获取相应的权限列表
     * @param roleQueryDTO
     * @return
     */
    @RequestMapping(value = "/getMenuByRole",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<MenuResponseDTO> getMenuByRole(@RequestBody RoleQueryDTO roleQueryDTO){
        return roleService.getMenuByRole(roleQueryDTO);
    }
    
    /**
	 * 获取resource信息列表
	 * @return
	 */
    @RequestMapping(value = "/getAllResource",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<AllResourceResponseDTO> getAllResource(){
        return resourceService.queryResourceList();
    }
    
    /**
     * 根据resourceId 查看role列表
     * @param roleQueryDTO
     * @return
     */
    @RequestMapping(value = "/getRoleListByResource",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<GetRoleListByResourceIdResponseDTO> getRoleListByResource(@RequestBody GetRoleListByResourceIdRequestDTO getRoleListByResourceIdRequestDTO){
        return roleService.getRoleListByResource(getRoleListByResourceIdRequestDTO);
    }
}
