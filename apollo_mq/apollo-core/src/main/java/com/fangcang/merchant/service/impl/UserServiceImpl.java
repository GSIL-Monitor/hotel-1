package com.fangcang.merchant.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.FileUploadUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.UploadFileConfig;
import com.fangcang.hotelinfo.response.ImageReponseDTO;
import com.fangcang.merchant.domain.RoleDO;
import com.fangcang.merchant.domain.UserBankCardDO;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.domain.UserRoleDO;
import com.fangcang.merchant.dto.BankCardDTO;
import com.fangcang.merchant.dto.QueryUserConditionDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.mapper.RoleMapper;
import com.fangcang.merchant.mapper.UserBankCardMapper;
import com.fangcang.merchant.mapper.UserMapper;
import com.fangcang.merchant.mapper.UserRoleMapper;
import com.fangcang.merchant.request.AddBankCardRequestDTO;
import com.fangcang.merchant.request.GetUserByIdRequestDTO;
import com.fangcang.merchant.request.SaveStaffRequestDTO;
import com.fangcang.merchant.request.StaffListQueryDTO;
import com.fangcang.merchant.request.UserChangePasswordRequestDTO;
import com.fangcang.merchant.request.UserSetActiveRequestDTO;
import com.fangcang.merchant.response.AddBankCardResponseDTO;
import com.fangcang.merchant.response.GetUserByIdResponseDTO;
import com.fangcang.merchant.response.SaveStaffResponseDTO;
import com.fangcang.merchant.response.StaffListResponseDTO;
import com.fangcang.merchant.response.UserBankCardResponseDTO;
import com.fangcang.merchant.service.RoleService;
import com.fangcang.merchant.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private UserBankCardMapper userBankCardMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UploadFileConfig uploadFileConfig;

    @Override
    public UserDO queryUserById(long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserDO queryUserByDomainAndUsername(String domain,String userName) {
        QueryUserConditionDTO queryUserConditionDTO = new QueryUserConditionDTO();
        queryUserConditionDTO.setUserName(userName);
        queryUserConditionDTO.setIsActive(1);
        queryUserConditionDTO.setDomain(domain);
        List<UserDO> userDOList = userMapper.queryUserForPage(queryUserConditionDTO);
        if (null == userDOList || userDOList.size() != 1){
            return null;
        }
        return userDOList.get(0);
    }

    @Override
    public PaginationSupportDTO<UserDO> queryUserForPage(QueryUserConditionDTO queryUserConditionDTO) {
        PageHelper.startPage(queryUserConditionDTO.getCurrentPage(), queryUserConditionDTO.getPageSize());
        List<UserDO> list =userMapper.queryUserForPage(queryUserConditionDTO);
        PageInfo<UserDO> page = new PageInfo<UserDO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage((int)page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public boolean addUser(UserDO userDO) {
        userMapper.insert(userDO);
        return true;
    }

    @Override
    public boolean updateUser(UserDO userDO) {
        userMapper.updateByPrimaryKeySelective(userDO);
        return true;
    }

    @Override
    public boolean deleteUserById(long userId) {
        userMapper.deleteByPrimaryKey(userId);
        return true;
    }

    /**
	 * 根据ID获取用户信息
	 */
	@Override
	public ResponseDTO<GetUserByIdResponseDTO> getUserById(GetUserByIdRequestDTO getUserByIdRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			GetUserByIdResponseDTO getUserByIdResponseDTO = new GetUserByIdResponseDTO();
			UserDO userDO = new UserDO();
			userDO = userMapper.queryUserInfoById(getUserByIdRequestDTO);
			if(userDO != null){
				getUserByIdResponseDTO.setUserId(userDO.getUserId());
				getUserByIdResponseDTO.setUserName(userDO.getUsername());
				getUserByIdResponseDTO.setPassword(userDO.getPassword());
				getUserByIdResponseDTO.setRealName(userDO.getRealName());
				getUserByIdResponseDTO.setQq(userDO.getQq());
				getUserByIdResponseDTO.setEmail(userDO.getEmail());
				getUserByIdResponseDTO.setLandlineTelephone(userDO.getLandlineTelephone());
				List<RoleDO> roleDOList = roleMapper.getRoleListByUserId(getUserByIdRequestDTO);
				if(!CollectionUtils.isEmpty(roleDOList)){
					List<RoleDTO> roleDTOList = new ArrayList<>();
					roleDTOList = PropertyCopyUtil.transferArray(roleDOList, RoleDTO.class);
					getUserByIdResponseDTO.setRoleList(roleDTOList);
				}
				responseDTO.setModel(getUserByIdResponseDTO);
			}
		}catch (Exception e) {
            log.error("getUserById",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
		return responseDTO;
	}

    /**
     * 将用户设置为是否停用
     * 0：停用 1：启用
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO setActive(UserSetActiveRequestDTO userSetActiveRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDO userDO = PropertyCopyUtil.transfer(userSetActiveRequestDTO,UserDO.class);
            userDO.setModifier(userSetActiveRequestDTO.getModifier());
            userDO.setModifyTime(userSetActiveRequestDTO.getModifyTime());
            
            userMapper.setActive(userDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("User setActive has error", e);
            throw new ServiceException("User setActive has error", e);
        }
        return responseDTO;
    }
    
    /**
     * 用户修改密码
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO changePassword(UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDO userDO = new UserDO();
            userDO.setPassword(com.fangcang.common.util.MD5Util.encode(userChangePasswordRequestDTO.getConfirmPassword()));
            userDO.setModifier(userChangePasswordRequestDTO.getModifier());
            userDO.setModifyTime(userChangePasswordRequestDTO.getModifyTime());
            userDO.setUserId(userChangePasswordRequestDTO.getUserId());
            
            userMapper.changePassword(userDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("User changePassword has error", e);
            throw new ServiceException("User changePassword has error", e);
        }
        return responseDTO;
    }
    
    /**
     *获取商家用户银行卡列表
     */
	@Override
	public ResponseDTO<UserBankCardResponseDTO> queryUserBankCardList(Long merchantId) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		UserBankCardResponseDTO userBankCardResponseDTO = new UserBankCardResponseDTO();
		responseDTO.setModel(userBankCardResponseDTO);
		try {

            List<UserBankCardDO> userBankCardDOList = userBankCardMapper.queryUserBankCardList(merchantId);
            if(!CollectionUtils.isEmpty(userBankCardDOList)){
            	List<BankCardDTO> bankCardDTOList = new ArrayList<>();
            	bankCardDTOList = PropertyCopyUtil.transferArray(userBankCardDOList,BankCardDTO.class);
            	userBankCardResponseDTO.setBankCardList(bankCardDTOList);
            }
        } catch (Exception e) {
            log.error("queryUserBankCardList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}
	
	/**
     *给用户添加银行卡信息
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<AddBankCardResponseDTO> addUserBankCardInfo(AddBankCardRequestDTO addBankCardRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			UserBankCardDO userBankCardDO = null;
			userBankCardDO = PropertyCopyUtil.transfer(addBankCardRequestDTO,UserBankCardDO.class);
			AddBankCardResponseDTO addBankCardResponseDTO = new AddBankCardResponseDTO();
			//新增
			if(addBankCardRequestDTO.getBankCardId() == null || "".equals(addBankCardRequestDTO.getBankCardId())){
				userBankCardMapper.insertUserBankCard(userBankCardDO);
				//返回信息写入ResponseDTO
				addBankCardResponseDTO.setBankCardId(userBankCardDO.getBankCardId());
				addBankCardResponseDTO.setCreator(userBankCardDO.getCreator());
				addBankCardResponseDTO.setCreateTime(userBankCardDO.getCreateTime());
			//修改
			}else{
				userBankCardMapper.updateByPrimaryKey(userBankCardDO);
				//返回信息写入ResponseDTO
				addBankCardResponseDTO.setBankCardId(addBankCardRequestDTO.getBankCardId());
				addBankCardResponseDTO.setCreator(userBankCardDO.getCreator());
				addBankCardResponseDTO.setCreateTime(userBankCardDO.getCreateTime());
			}
			
			responseDTO.setModel(addBankCardResponseDTO);
		} catch (Exception e) {
            log.error("addUserBankCardInfo",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}

	/**
	 * 保存用户
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<SaveStaffResponseDTO> saveStaff(SaveStaffRequestDTO saveStaffRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			//新增
			if(saveStaffRequestDTO.getUserId() == null || "".equals(saveStaffRequestDTO.getUserId())){
				//判断username是否重复
				if(null != saveStaffRequestDTO.getUserName()){
					UserDO userCount = new UserDO();
					userCount.setUsername(saveStaffRequestDTO.getUserName());
					userCount.setMerchantId(saveStaffRequestDTO.getMerchantId());
					int userCt = userMapper.selectCount(userCount);
					//新增时不能有username和存量的用户使用同样的username
					if(userCt > 0){
						responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			            responseDTO.setFailCode(ErrorCodeEnum.USER_NAME_IDENTICAL.errorCode);
			            responseDTO.setFailReason(ErrorCodeEnum.USER_NAME_IDENTICAL.errorDesc);
			            return responseDTO;
					}
				}
				//保存用户信息
				UserDO userDO = new UserDO();
				userDO.setUsername(saveStaffRequestDTO.getUserName());
				userDO.setPassword(com.fangcang.common.util.MD5Util.encode(saveStaffRequestDTO.getPassword()));
				userDO.setRealName(saveStaffRequestDTO.getRealName());
				userDO.setCreateTime(saveStaffRequestDTO.getCreateTime());
				userDO.setCreator(saveStaffRequestDTO.getCreator());
				userDO.setIsActive(Constant.YES);
				userDO.setMerchantId(saveStaffRequestDTO.getMerchantId());
				if(null != saveStaffRequestDTO.getPhone()){
					userDO.setPhone(saveStaffRequestDTO.getPhone());
				}else{
					userDO.setPhone(saveStaffRequestDTO.getUserName());
				}
				userDO.setQq(saveStaffRequestDTO.getQq());
				userDO.setEmail(saveStaffRequestDTO.getEmail());
				userDO.setLandlineTelephone(saveStaffRequestDTO.getLandlineTelephone());
				userMapper.saveStaff(userDO);
				//保存用户角色信息
				if(!CollectionUtils.isEmpty(saveStaffRequestDTO.getRoleList())){
					List<UserRoleDO> roleDOList = new ArrayList<UserRoleDO>();
					for(RoleDTO roleDTO : saveStaffRequestDTO.getRoleList()){
						UserRoleDO userRoleDO = new UserRoleDO();
						userRoleDO.setUserId(userDO.getUserId());
						userRoleDO.setRoleId(roleDTO.getRoleId());
						roleDOList.add(userRoleDO);
					}
					userRoleMapper.batchSaveUserRole(roleDOList);
				}
				SaveStaffResponseDTO saveStaffResponseDTO = new SaveStaffResponseDTO();
				saveStaffResponseDTO.setUserId(userDO.getUserId());
				responseDTO.setModel(saveStaffResponseDTO);
			//编辑(修改)
			}else{
				//判断username是否重复
				if(null != saveStaffRequestDTO.getUserName()){
					UserDO userCount = new UserDO();
					userCount.setUserId(saveStaffRequestDTO.getUserId());
					userCount.setUsername(saveStaffRequestDTO.getUserName());
					userCount.setMerchantId(saveStaffRequestDTO.getMerchantId());
					UserDO userCt = userMapper.getUserInfo(userCount);
					if(null != userCt){
						responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			            responseDTO.setFailCode(ErrorCodeEnum.USER_NAME_IDENTICAL.errorCode);
			            responseDTO.setFailReason(ErrorCodeEnum.USER_NAME_IDENTICAL.errorDesc);
			            return responseDTO;
					}
				}
				//保存用户信息
				UserDO userDO = new UserDO();
				userDO.setUsername(saveStaffRequestDTO.getUserName());
				if (StringUtil.isValidString(saveStaffRequestDTO.getPassword())
						&& "666666".equals(saveStaffRequestDTO.getPassword())){
					//重置密码
					userDO.setPassword(com.fangcang.common.util.MD5Util.encode(saveStaffRequestDTO.getPassword()));
				}
				userDO.setRealName(saveStaffRequestDTO.getRealName());
				userDO.setModifyTime(saveStaffRequestDTO.getModifyTime());
				userDO.setModifier(saveStaffRequestDTO.getModifier());
				userDO.setMerchantId(saveStaffRequestDTO.getMerchantId());
				userDO.setUserId(saveStaffRequestDTO.getUserId());
				if(null != saveStaffRequestDTO.getPhone()){
					userDO.setPhone(saveStaffRequestDTO.getPhone());
				}else{
					userDO.setPhone(saveStaffRequestDTO.getUserName());
				}
				userDO.setQq(saveStaffRequestDTO.getQq());
				userDO.setEmail(saveStaffRequestDTO.getEmail());
				userDO.setLandlineTelephone(saveStaffRequestDTO.getLandlineTelephone());
				userMapper.updateByPrimaryKeySelective(userDO);
				//保存用户角色信息
				//避免编辑修改用户时，又新增了角色列表，所以此处先删除用户对应的角色信息
				UserRoleDO userRoleForDelete = new UserRoleDO();
				userRoleForDelete.setUserId(userDO.getUserId());
				userRoleMapper.delete(userRoleForDelete);
				if(!CollectionUtils.isEmpty(saveStaffRequestDTO.getRoleList())){
					List<UserRoleDO> roleDOList = new ArrayList<UserRoleDO>();
					for(RoleDTO roleDTO : saveStaffRequestDTO.getRoleList()){
						UserRoleDO userRoleDO = new UserRoleDO();
						userRoleDO.setUserId(userDO.getUserId());
						userRoleDO.setRoleId(roleDTO.getRoleId());
						roleDOList.add(userRoleDO);
					}
					userRoleMapper.batchSaveUserRole(roleDOList);
				}
			}
		} catch (Exception e) {
            log.error("saveStaff",e);
            throw new ServiceException("saveStaff has error.",e);
        }
		return responseDTO;
	}
	
	/**
     * 获取员工列表
     */
    @Override
	public ResponseDTO<PaginationSupportDTO<StaffListResponseDTO>> queryStaffList(StaffListQueryDTO staffListQueryDTO) {
    	ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    	try {
    		PageHelper.startPage(staffListQueryDTO.getCurrentPage(),staffListQueryDTO.getPageSize());
    		List<UserDO> userDOList = userMapper.getUserListByUserId(staffListQueryDTO);
    		PageInfo<UserDO> pageInfo = new PageInfo<UserDO>(userDOList);
    		PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
    		paginationSupportDTO.setTotalCount(pageInfo.getTotal());
    		paginationSupportDTO.setTotalPage(pageInfo.getPages());
    		paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
    		paginationSupportDTO.setPageSize(pageInfo.getPageSize());
    		List<UserDO> itemList = pageInfo.getList();
    		if(!CollectionUtils.isEmpty(itemList)){
    			List<StaffListResponseDTO> staffListResponseDTOList = new ArrayList<>();
    			StaffListResponseDTO staffListResponseDTO = null;
    			for (UserDO userDO : itemList) {
    				staffListResponseDTO = PropertyCopyUtil.transfer(userDO,StaffListResponseDTO.class);
    				if(staffListQueryDTO.getUserId().equals(userDO.getUserId())){
    					staffListResponseDTO.setIsCurrentAccount(Constant.YES);
    				}else{
    					staffListResponseDTO.setIsCurrentAccount(Constant.NO);
    				}
    				staffListResponseDTOList.add(staffListResponseDTO);
                }
    			paginationSupportDTO.setItemList(staffListResponseDTOList);
    		}
    		responseDTO.setModel(paginationSupportDTO);
    	}catch (Exception e) {
            log.error("queryStaffList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}

	public ResponseDTO<ImageReponseDTO> uploadPayImage(MultipartFile file){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			// 获取上传图片的原始名
			String oriName = file.getOriginalFilename();
			// 获取图片扩展名
			String extType = oriName.substring(oriName.lastIndexOf(".") + 1);
			boolean allow = false;
			String[] imagesType = Constant.IMAGE_ALLOW_TYPES;
			for (String imageType : imagesType) {
				if (imageType.equalsIgnoreCase(extType)) {
					allow = true;
					break;
				}
			}
			if (!allow) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorDesc);
				return responseDTO;
			}

			FileUploadUtil fileUploadUtil = new FileUploadUtil();
			ResponseDTO<UploadResponseDTO> result = fileUploadUtil.addFile(file, "payImage", null,uploadFileConfig);
			if(result.getResult() == ResultCodeEnum.SUCCESS.code && null != result.getModel()){
				ImageReponseDTO imageReponseDTO = new ImageReponseDTO();
				imageReponseDTO.setImageUrl(result.getModel().getFileUrl());
				imageReponseDTO.setRealPath(result.getModel().getFilePath());
				responseDTO.setModel(imageReponseDTO);
				responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
			}else{
				log.error("Upload pay image has error," + result.getFailReason());
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
			}
		} catch (Exception e) {
			log.error("uploadPayImage has error", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
}
