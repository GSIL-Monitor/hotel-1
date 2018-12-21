package com.fangcang.merchant.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.merchant.dto.ResourceDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.request.RoleQueryDTO;
import com.fangcang.merchant.response.AllRoleResponseDTO;
import com.fangcang.merchant.response.MenuResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@RestController
@RequestMapping(value = "/test/role")
public class TestRoleController {

    @RequestMapping(value = "/getAllRoleList",method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<AllRoleResponseDTO> getAllRoleList(){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for(int j = 0;j<3;j++){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(Long.valueOf(50000 + j));
            roleDTO.setRoleName("产品员" +  + j);
            roleDTO.setDescription("负责产品采购，供应商维护，库存维护等");
            roleDTOList.add(roleDTO);
        }
        AllRoleResponseDTO allRoleResponseDTO = new AllRoleResponseDTO();
        allRoleResponseDTO.setRoleList(roleDTOList);
        responseDTO.setModel(allRoleResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/getMenuByRole",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<MenuResponseDTO> getMenuByRole(@RequestBody @Valid RoleQueryDTO roleQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResourceId(10001L);
        resourceDTO.setResourceName("库存管理");
        resourceDTO.setPid(1L);

        ResourceDTO resourceDTO1 = new ResourceDTO();
        resourceDTO1.setResourceId(10001001L);
        resourceDTO1.setResourceName("国内库存");
        resourceDTO1.setPid(10001L);

        ResourceDTO resourceDTO2 = new ResourceDTO();
        resourceDTO2.setResourceId(10001002L);
        resourceDTO2.setResourceName("海外库存");
        resourceDTO2.setPid(10001L);


        ResourceDTO ordersource = new ResourceDTO();
        ordersource.setResourceId(10002L);
        ordersource.setResourceName("订单管理");
        ordersource.setPid(1L);

        ResourceDTO ordersource1 = new ResourceDTO();
        ordersource1.setResourceId(10002001L);
        ordersource1.setResourceName("B2B订单");
        ordersource1.setPid(1L);

        ResourceDTO ordersource2 = new ResourceDTO();
        ordersource2.setResourceId(10002002L);
        ordersource2.setResourceName("OTA订单");
        ordersource2.setPid(1L);

        resourceDTOList.add(resourceDTO);
        resourceDTOList.add(resourceDTO1);
        resourceDTOList.add(resourceDTO2);
        resourceDTOList.add(ordersource);
        resourceDTOList.add(ordersource1);
        resourceDTOList.add(ordersource2);

        MenuResponseDTO menuResponseDTO = new MenuResponseDTO();
        menuResponseDTO.setResourceList(resourceDTOList);
        menuResponseDTO.setRoleId(1L);
        menuResponseDTO.setRoleName("菜单管理");
        menuResponseDTO.setDescribe("管理所有菜单");
        responseDTO.setModel(menuResponseDTO);
        return responseDTO;
    }
}
