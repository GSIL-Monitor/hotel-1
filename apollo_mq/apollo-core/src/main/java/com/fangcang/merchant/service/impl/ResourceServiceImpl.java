package com.fangcang.merchant.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.merchant.domain.ResourceDO;
import com.fangcang.merchant.domain.RoleResourceDO;
import com.fangcang.merchant.dto.ResourceDTO;
import com.fangcang.merchant.dto.RoleResourceDTO;
import com.fangcang.merchant.mapper.ResourceMapper;
import com.fangcang.merchant.response.AllResourceResponseDTO;
import com.fangcang.merchant.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     *获取resource列表
     */
	@Override
	public ResponseDTO<AllResourceResponseDTO> queryResourceList() {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			AllResourceResponseDTO allResourceResponseDTO = new AllResourceResponseDTO();
            List<ResourceDO> resourceList = resourceMapper.queryResourceList();
            
            if(!CollectionUtils.isEmpty(resourceList)){
            	List<ResourceDTO> resourceDTOList = new ArrayList<>();
            	resourceDTOList = PropertyCopyUtil.transferArray(resourceList,ResourceDTO.class);
            	allResourceResponseDTO.setResourceList(resourceDTOList);
            	responseDTO.setModel(allResourceResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryResourceList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}

    @Override
    public List<String> getNeedCheckResourceUrl() {
        ResponseDTO<AllResourceResponseDTO> allResourceResponseDTO = this.queryResourceList();
        List<ResourceDTO> resourceDTOList = allResourceResponseDTO.getModel().getResourceList();

        List<String> resourceURLList = new ArrayList<>();

        if (CollectionUtils.isEmpty(resourceDTOList)){
            return resourceURLList;
        }

        for (ResourceDTO dto : resourceDTOList){
            resourceURLList.add(dto.getUrlPattern());
        }

        return resourceURLList;
    }

    @Override
    public List<RoleResourceDTO> queryRoleResourceList() {
        List<RoleResourceDO> resourceDOList = resourceMapper.queryRoleResourceList();
        List<RoleResourceDTO> dtoList = PropertyCopyUtil.transferArray(resourceDOList,RoleResourceDTO.class);
        return dtoList;
    }
}
