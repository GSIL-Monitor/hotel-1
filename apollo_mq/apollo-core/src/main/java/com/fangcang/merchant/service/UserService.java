package com.fangcang.merchant.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.response.ImageReponseDTO;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.dto.QueryUserConditionDTO;
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
import org.springframework.web.multipart.MultipartFile;

public interface UserService
{

    public UserDO queryUserById(long userId);

    public UserDO queryUserByDomainAndUsername(String domain,String userName);

    public PaginationSupportDTO<UserDO> queryUserForPage(QueryUserConditionDTO queryUserConditionDTO);

    public boolean addUser(UserDO userDO);

    public boolean updateUser(UserDO userDO);

    public boolean deleteUserById(long userId);
    
    /**
	 * 根据ID获取用户信息
	 * @return
	 */
    public ResponseDTO<GetUserByIdResponseDTO> getUserById(GetUserByIdRequestDTO getUserByIdRequestDTO);
    
    /**
     * 设置用户是否停用
     * 1-启用；0-停用
     * @param userSetActiveRequestDTO
     * @return
     */
    public ResponseDTO setActive(UserSetActiveRequestDTO userSetActiveRequestDTO);
    
    /**
     * 用户修改密码
     * @param userChangePasswordRequestDTO
     * @return
     */
    public ResponseDTO changePassword(UserChangePasswordRequestDTO userChangePasswordRequestDTO);
    
    /**
	 * 获取银行卡列表
	 * @return
	 */
    public ResponseDTO<UserBankCardResponseDTO> queryUserBankCardList(Long merchantId);
    
    /**
     * 添加银行卡信息
     * @param addBankCardRequestDTO
     * @return
     */
    public ResponseDTO<AddBankCardResponseDTO> addUserBankCardInfo(AddBankCardRequestDTO addBankCardRequestDTO);
    
    /**
     * 保存用户
     * @param saveStaffRequestDTO
     * @return
     */
    public ResponseDTO<SaveStaffResponseDTO> saveStaff(SaveStaffRequestDTO saveStaffRequestDTO);
    
    /**
	 * 获取用户列表
	 * @param staffListQueryDTO
	 * @return
	 */
    public ResponseDTO<PaginationSupportDTO<StaffListResponseDTO>> queryStaffList(StaffListQueryDTO staffListQueryDTO);

    /**
     * 上传支付宝二维码和微信收款码
     * @param file
     * @return
     */
    public ResponseDTO<ImageReponseDTO> uploadPayImage(MultipartFile file);
}
