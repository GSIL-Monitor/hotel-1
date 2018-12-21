package com.fangcang.b2b.service.impl;

import com.fangcang.agent.domain.AgentDO;
import com.fangcang.agent.mapper.AgentMapper;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.b2b.domain.B2BHistoryDO;
import com.fangcang.b2b.dto.KeyWordDTO;
import com.fangcang.b2b.mapper.B2BHistoryMapper;
import com.fangcang.b2b.request.SaveSearchHistoryDTO;
import com.fangcang.b2b.request.SearchHistoryQueryDTO;
import com.fangcang.b2b.response.ContactUsResponseDTO;
import com.fangcang.b2b.response.SearchHistoryResponseDTO;
import com.fangcang.b2b.service.B2BHistoryService;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.merchant.mapper.UserMapper;
import com.fangcang.merchant.request.StaffListQueryDTO;
import com.fangcang.supplier.dto.MerchantOPDTO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/3 10:34
 * @Description:
 */
@Service
@Slf4j
public class B2BHistoryServiceImpl implements B2BHistoryService {

    @Autowired
    private B2BHistoryMapper b2BHistoryMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseDTO saveSearchHistory(SaveSearchHistoryDTO saveSearchHistoryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            B2BHistoryDO b2BHistoryDO = null;
            b2BHistoryDO = PropertyCopyUtil.transfer(saveSearchHistoryDTO, B2BHistoryDO.class);
            //前端传来的loginName和后台DO中的username是对应的
            b2BHistoryDO.setUsername(saveSearchHistoryDTO.getLoginName());
            b2BHistoryMapper.saveHistory(b2BHistoryDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("saveSearchHistory", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<SearchHistoryResponseDTO> querySearchHistory(SearchHistoryQueryDTO searchHistoryQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        SearchHistoryResponseDTO searchHistoryResponseDTO = new SearchHistoryResponseDTO();
        try {
            B2BHistoryDO b2BHistoryDO = null;
            List<KeyWordDTO> resultKeyWords = null;
            b2BHistoryDO = PropertyCopyUtil.transfer(searchHistoryQueryDTO, B2BHistoryDO.class);
            b2BHistoryDO.setUsername(searchHistoryQueryDTO.getLoginName());
            int pageNum = searchHistoryQueryDTO.getCurrentPage();//当前页
            int pageSize = searchHistoryQueryDTO.getPageSize();//每页大小
            PageHelper.startPage(pageNum, pageSize);
            resultKeyWords = b2BHistoryMapper.queryHistory(b2BHistoryDO);
            searchHistoryResponseDTO.setKeyWords(resultKeyWords);
            responseDTO.setModel(searchHistoryResponseDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("querySearchHistory", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<ContactUsResponseDTO> contactUs(Long merchantId, String agentCode) {
        ResponseDTO responseDTO = new ResponseDTO();
        ContactUsResponseDTO contactUsResponseDTO = new ContactUsResponseDTO();
        try {
            //获取产品经理信息
            UserDTO bmUser = b2BHistoryMapper.getMerchantBMInfo(merchantId, agentCode);
            SingleAgentRequestDTO singleAgentRequestDTO = new SingleAgentRequestDTO();
            singleAgentRequestDTO.setAgentCode(agentCode);
            //订单处理员
            AgentDO agentDO = agentMapper.selectSingleAgentInfo(singleAgentRequestDTO);
            if(StringUtil.isValidString(agentDO.getMerchantOPStr())){
                String [] merchantOPIds = agentDO.getMerchantOPStr().split(",");
                List<Long> merchantOPIdList = new ArrayList<>();
                for (String s : merchantOPIds) {
                    merchantOPIdList.add(Long.valueOf(s));
                }
                StaffListQueryDTO staffListQueryDTO = new StaffListQueryDTO();
                staffListQueryDTO.setUserIds(merchantOPIdList);
                List<UserDO> userDOS = userMapper.getUserListByUserId(staffListQueryDTO);
                List<MerchantOPDTO> merchantOPs = new ArrayList<>();
                for (UserDO userDO : userDOS) {
                    MerchantOPDTO merchantOPDTO = new MerchantOPDTO();
                    merchantOPDTO.setMerchantOP(userDO.getUserId());
                    merchantOPDTO.setMerchantOPName(userDO.getRealName());
                    merchantOPDTO.setMerchantOPPhone(userDO.getLandlineTelephone());//B2B展示座机。
                    merchantOPs.add(merchantOPDTO);
                }
                contactUsResponseDTO.setMerchantOPList(merchantOPs);
            }

            if (null != bmUser) {
                contactUsResponseDTO.setMerchantBMName(bmUser.getRealName());
                contactUsResponseDTO.setMerchantBMPhone(bmUser.getLandlineTelephone());
            }
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(contactUsResponseDTO);
        } catch (Exception e) {
            log.error("contactUs", e);
            throw new ServiceException("contactUs has occured error");
        }
        return responseDTO;
    }

    @Transactional
    public ResponseDTO deleteSearchHistory(SearchHistoryQueryDTO searchHistoryQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            B2BHistoryDO b2BHistoryDO = new B2BHistoryDO();
            b2BHistoryDO.setUsername(searchHistoryQueryDTO.getLoginName());
            b2BHistoryDO.setAgentUserId(searchHistoryQueryDTO.getAgentUserId());
            b2BHistoryMapper.deleteSearchHistory(b2BHistoryDO);
        } catch (Exception e) {
            log.error("deleteSearchHistory", e);
            throw new ServiceException("deleteSearchHistory has error",e);
        }
        return responseDTO;
    }
}
