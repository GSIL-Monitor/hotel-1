package com.fangcang.merchant.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.response.ImageReponseDTO;
import com.fangcang.merchant.dto.UserDTO;
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
import com.fangcang.merchant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping(("/user"))
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 查询单个员工信息
     * @param getUserByIdRequestDTO
     * @return
     */
    @RequestMapping(value = "/getUserById",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<GetUserByIdResponseDTO> getUserById(@RequestBody GetUserByIdRequestDTO getUserByIdRequestDTO){
        return userService.getUserById(getUserByIdRequestDTO);
    }
    
    /**
     * 设置商家是否停用
     * 1-启用；0-停用
     *
     * @param userSetActiveRequestDTO
     * @return
     */
    @RequestMapping(value = "/setActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setActive(@RequestBody UserSetActiveRequestDTO userSetActiveRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
        	userSetActiveRequestDTO.setModifier(this.getCacheUser().getRealName());
        	userSetActiveRequestDTO.setModifyTime(new Date());
            responseDTO = userService.setActive(userSetActiveRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
    
    /**
     * 用户修改密码
     *
     * @param userChangePasswordRequestDTO
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO changePassword(@RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        //判断确认密码是否等于新密码 
        if(!userChangePasswordRequestDTO.getPassword().equals(userChangePasswordRequestDTO.getConfirmPassword())){
        	responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.PASSWORD_NOT_EQUAL_CONFIRMPASSWD.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.PASSWORD_NOT_EQUAL_CONFIRMPASSWD.errorDesc);
        }else{
        	try {
            	userChangePasswordRequestDTO.setModifier(this.getCacheUser().getRealName());
            	userChangePasswordRequestDTO.setModifyTime(new Date());
                responseDTO = userService.changePassword(userChangePasswordRequestDTO);
            } catch (Exception e) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
            }
        }
        return responseDTO;
    }
    
    /**
     * 获取商家员工的银行卡列表
     * @return
     */
    @RequestMapping(value = "/bankCardList",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<UserBankCardResponseDTO> getBankCardList(){
    	//获取当前用户的id
        UserDTO userDTO = super.getCacheUser();
        return userService.queryUserBankCardList(userDTO.getMerchantId());
    }
    
    /**
     * 用户添加银行卡
     *
     * @param addBankCardRequestDTO
     * @return
     */
    @RequestMapping(value = "/addBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddBankCardResponseDTO> addBankCard(@RequestBody AddBankCardRequestDTO addBankCardRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
        	addBankCardRequestDTO.setCreator(super.getFullName());
        	addBankCardRequestDTO.setCreateTime(new Date());
            responseDTO = userService.addUserBankCardInfo(addBankCardRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }
    
    /**
     * 用户保存
     * @param saveStaffRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveStaff", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SaveStaffResponseDTO> saveStaff(@RequestBody SaveStaffRequestDTO saveStaffRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        //判断realName 和roleList不得为空
        if(null == saveStaffRequestDTO.getRealName()){
        	responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.REAL_NAME_ISNULL.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.REAL_NAME_ISNULL.errorDesc);
            return responseDTO;
        }else if(CollectionUtils.isEmpty(saveStaffRequestDTO.getRoleList())){
        	responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.ROLE_LIST_ISNULL.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.ROLE_LIST_ISNULL.errorDesc);
            return responseDTO;
        }
        try {
        	//如果是新增，则设置创建者和创建时间
        	if(saveStaffRequestDTO.getUserId() == null || "".equals(saveStaffRequestDTO.getUserId())){
        		saveStaffRequestDTO.setCreateTime(new Date());
        		if(saveStaffRequestDTO.getCreator() == null || "".equals(saveStaffRequestDTO.getCreator())){
        			saveStaffRequestDTO.setCreator(this.getCacheUser().getRealName());
        		}
        		saveStaffRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
        		
        	//否则，则设置修改者和修改时间
        	}else{
        		saveStaffRequestDTO.setModifyTime(new Date());
        		if(saveStaffRequestDTO.getCreator() == null || "".equals(saveStaffRequestDTO.getCreator())){
        			saveStaffRequestDTO.setModifier(this.getCacheUser().getRealName());
        		}else{
        			saveStaffRequestDTO.setModifier(saveStaffRequestDTO.getCreator());;
        		}
        		saveStaffRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
        		
        	}
            responseDTO = userService.saveStaff(saveStaffRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }
    
    /**
	 * 获取用户列表
	 * @param staffListQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/queryStaffList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<StaffListResponseDTO>> queryStaffList(@RequestBody @Valid StaffListQueryDTO staffListQueryDTO){
		 ResponseDTO responseDTO = new ResponseDTO();
		 try {
			 staffListQueryDTO.setUserId(this.getCacheUser().getUserId());
			 staffListQueryDTO.setMerchantId(this.getCacheUser().getMerchantId());
			 responseDTO=userService.queryStaffList(staffListQueryDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
        return responseDTO;
    }


    @RequestMapping(value = "/queryStaffListMenu",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<StaffListResponseDTO>> queryStaffListMenu(@RequestBody @Valid StaffListQueryDTO staffListQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            staffListQueryDTO.setUserId(this.getCacheUser().getUserId());
            staffListQueryDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO=userService.queryStaffList(staffListQueryDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/uploadPayImage")
    public ResponseDTO<ImageReponseDTO> uploadPayImage(@RequestParam(required = false) MultipartFile file){
        return userService.uploadPayImage(file);
    }
}
