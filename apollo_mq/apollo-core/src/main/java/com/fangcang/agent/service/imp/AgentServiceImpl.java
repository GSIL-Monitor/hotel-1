package com.fangcang.agent.service.imp;

import com.fangcang.agent.domain.AgentAmountLogDO;
import com.fangcang.agent.domain.AgentBankCardDO;
import com.fangcang.agent.domain.AgentContractFileDO;
import com.fangcang.agent.domain.AgentCreditItemDO;
import com.fangcang.agent.domain.AgentDO;
import com.fangcang.agent.domain.AgentOperLogDO;
import com.fangcang.agent.domain.AgentUserBindDO;
import com.fangcang.agent.domain.AgentUserDO;
import com.fangcang.agent.domain.AgentVisitDO;
import com.fangcang.agent.dto.AgentAmountLogDTO;
import com.fangcang.agent.dto.AgentBankCardDTO;
import com.fangcang.agent.dto.AgentOperLogDTO;
import com.fangcang.agent.dto.AgentVisitInfoDTO;
import com.fangcang.agent.dto.FrequentAgentDTO;
import com.fangcang.agent.dto.QueryAgentUserBindDTO;
import com.fangcang.agent.dto.UserInfoDTO;
import com.fangcang.agent.mapper.AgentAmountLogMapper;
import com.fangcang.agent.mapper.AgentBankCardMapper;
import com.fangcang.agent.mapper.AgentContractMapper;
import com.fangcang.agent.mapper.AgentCreditItemMapper;
import com.fangcang.agent.mapper.AgentMapper;
import com.fangcang.agent.mapper.AgentOperLogMapper;
import com.fangcang.agent.mapper.AgentUserBindMapper;
import com.fangcang.agent.mapper.AgentUserMapper;
import com.fangcang.agent.mapper.AgentVisitMapper;
import com.fangcang.agent.request.AddAgentContractFileRequestDTO;
import com.fangcang.agent.request.AddAgentRequestDTO;
import com.fangcang.agent.request.AddBankCardRequestDTO;
import com.fangcang.agent.request.AgentAddVisitRequestDTO;
import com.fangcang.agent.request.AgentListQueryRequestDTO;
import com.fangcang.agent.request.AgentUserRequestDTO;
import com.fangcang.agent.request.CommonAgentListRequestDTO;
import com.fangcang.agent.request.DeductAgentCreditLineRequestDTO;
import com.fangcang.agent.request.DeleteAgentContractFileRequestDTO;
import com.fangcang.agent.request.GetAgentAmountLogRequestDTO;
import com.fangcang.agent.request.GetAgentOperLogRequestDTO;
import com.fangcang.agent.request.GetCreditLinesListRequestDTO;
import com.fangcang.agent.request.GetVisitListQueryDTO;
import com.fangcang.agent.request.ModifyAmountRequestDTO;
import com.fangcang.agent.request.QueryOrderDeductCreditDTO;
import com.fangcang.agent.request.SetAgentUserActiveRequestDTO;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.agent.response.AddAgentContractFileResponseDTO;
import com.fangcang.agent.response.AddAgentResponseDTO;
import com.fangcang.agent.response.AddBankCardResponseDTO;
import com.fangcang.agent.response.AgentAddVisitResponseDTO;
import com.fangcang.agent.response.AgentListResponseDTO;
import com.fangcang.agent.response.AgentUserResponseDTO;
import com.fangcang.agent.response.GetAgentAmountLogResponseDTO;
import com.fangcang.agent.response.GetAgentOperLogResponseDTO;
import com.fangcang.agent.response.GetCreditLinesListResponseDTO;
import com.fangcang.agent.response.OrderDeductCreditDTO;
import com.fangcang.agent.response.SingleAgentResponseDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.MD5Util;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.UploadFileConfig;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.mapper.UserMapper;
import com.fangcang.merchant.request.StaffListQueryDTO;
import com.fangcang.supplier.dto.MerchantOPDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/28 16:06
 * @Description: 分销商接口实现类
 */
@Service
@Slf4j
public class AgentServiceImpl implements AgentService {


    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Autowired
    private AgentBankCardMapper agentBankCardMapper;

    @Autowired
    private AgentVisitMapper agentVisitMapper;

    @Autowired
    private AgentContractMapper agentContractMapper;

    @Autowired
    private AgentOperLogMapper agentOperLogMapper;

    @Autowired
    private UploadFileConfig uploadFileConfig;

    @Autowired
    private AgentAmountLogMapper agentAmountLogMapper;

    @Autowired
    private AgentUserBindMapper agentUserBindMapper;

    @Autowired
    private AgentCreditItemMapper agentCreditItemMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AddAgentResponseDTO> saveAgent(AddAgentRequestDTO addAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        /**
         * 1.保存分销商信息返回自增主键agentID(如果新增)
         * 2.保存分销商账号信息
         * 3.保存分销商银行卡信息
         **/
        AgentDO agentDO = PropertyCopyUtil.transfer(addAgentRequestDTO, AgentDO.class);
        if(null != agentDO.getMerchantOPs()){
            StringBuilder merchantOPStr = new StringBuilder();
            for (String merchantOp : agentDO.getMerchantOPs()) {
                merchantOPStr.append(merchantOp).append(",");
            }
            if(merchantOPStr.length()>0){
                agentDO.setMerchantOPStr(merchantOPStr.toString().substring(0,merchantOPStr.length() -1));
            }else{
                agentDO.setMerchantOPStr("");
            }
        }

        List<AgentUserDO> agentUserDOS = new ArrayList<>();
        List<AgentBankCardDO> agentBankCardDOS = new ArrayList<>();

        //获取Request里账号列表
        List<UserInfoDTO> userInfoDTOS = addAgentRequestDTO.getUserList();
        //获取Request里银行卡信息列表
        List<AgentBankCardDTO> bankCardDTOS = addAgentRequestDTO.getBankCardList();

        //如果agentId为空,走新增流程
        if (null == addAgentRequestDTO.getAgentId()) {
            //1.保存分销商信息
            agentMapper.insertAgent(agentDO);
            //获取分销商ID
            Long agentID = agentDO.getAgentId();

            //2.保存分销商账号信息
            if (userInfoDTOS!=null && !CollectionUtils.isEmpty(userInfoDTOS)) {
                /** 做账号查重判断 */
                //用户账号集合
                List<String> usernameList = new ArrayList<>();
                for (int i = 0; i < userInfoDTOS.size(); i++) {
                    usernameList.add(userInfoDTOS.get(i).getUserName());
                }
                //userNum:根据username和商家id查到的用户数量
                Long userNum = agentUserMapper.selectUserByUsernameListAndMerchantId(usernameList, addAgentRequestDTO.getMerchantId());
                if (null != userNum && 0 < userNum) {
                    throw new ServiceException(ErrorCodeEnum.USER_NAME_IDENTICAL);
                }

                for (int i = 0; i < userInfoDTOS.size(); i++) {
                    AgentUserDO agentUserDO = new AgentUserDO();
                    //新增时设置第一个账号为master
                    if (i == 0) {
                        agentUserDO = PropertyCopyUtil.transfer(userInfoDTOS.get(i), AgentUserDO.class);
                        agentUserDO.setMaster(1);
                    } else {
                        agentUserDO = PropertyCopyUtil.transfer(userInfoDTOS.get(i), AgentUserDO.class);
                        agentUserDO.setMaster(0);
                    }
                    /**如果前端没传phone字段,则把username赋值给phone*/
                    if (null == agentUserDO.getPhone() || "".equals(agentUserDO.getPhone())) {
                        agentUserDO.setPhone(agentUserDO.getUserName());
                    }
                    agentUserDO.setIsActive(1);//默认设置为启用
                    agentUserDO.setAgentId(agentID);//set分销商ID
                    agentUserDO.setCreator(agentDO.getCreator());//set创建者;创建时间由NOW()函数定义
                    //对密码加密
                    agentUserDO.setPassword(MD5Util.encode(agentUserDO.getPassword()));
                    agentUserDOS.add(agentUserDO);
                }
                agentUserMapper.insertUsers(agentUserDOS);
            }

            //3.保存分销商银行卡信息
            if (bankCardDTOS!=null && !CollectionUtils.isEmpty(bankCardDTOS)) {
                for (int i = 0; i < bankCardDTOS.size(); i++) {
                    AgentBankCardDO agentBankCardDO = new AgentBankCardDO();
                    agentBankCardDO = PropertyCopyUtil.transfer(bankCardDTOS.get(i), AgentBankCardDO.class);
                    agentBankCardDO.setAgentId(agentID);//set分销商ID
                    agentBankCardDO.setCreator(agentDO.getCreator());//set创建者;创建时间由NOW()函数定义
                    agentBankCardDOS.add(agentBankCardDO);
                }
                agentBankCardMapper.insertAgentBankCard(agentBankCardDOS);
            }
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            String agentCode=agentMapper.getAgentCodeById(agentID);
            AddAgentResponseDTO addagentResponseDTO = new AddAgentResponseDTO();
            addagentResponseDTO.setAgentId(agentID);
            addagentResponseDTO.setAgentCode(agentCode);
            addagentResponseDTO.setAgentName(agentDO.getAgentName());
            responseDTO.setModel(addagentResponseDTO);
        } else {//走修改保存流程
            if (agentDO.getMerchantBM()==null || agentDO.getMerchantBM()==0){
                agentDO.setMerchantBM(Long.valueOf(-1));
            }
            if (agentDO.getMerchantPM()==null || agentDO.getMerchantPM()==0){
                agentDO.setMerchantPM(Long.valueOf(-1));
            }
            if (agentDO.getMerchantFinancer()==null || agentDO.getMerchantFinancer()==0){
                agentDO.setMerchantFinancer(Long.valueOf(-1));
            }
            agentMapper.updateAgent(agentDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            AddAgentResponseDTO addagentResponseDTO = new AddAgentResponseDTO();
            addagentResponseDTO.setAgentId(agentDO.getAgentId());
            addagentResponseDTO.setAgentCode(agentDO.getAgentCode());
            addagentResponseDTO.setAgentName(agentDO.getAgentName());
            responseDTO.setModel(addagentResponseDTO);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO setFrequentlyUse(SingleAgentRequestDTO singleAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            AgentDO agentDO = PropertyCopyUtil.transfer(singleAgentRequestDTO, AgentDO.class);
            agentMapper.setFrequentlyUse(agentDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Agent setFrequentlyUse has error", e);
            throw new ServiceException("Agent setFrequentlyUse has error", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO setActive(SingleAgentRequestDTO singleAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            AgentDO agentDO = PropertyCopyUtil.transfer(singleAgentRequestDTO, AgentDO.class);
            /** 如果设置为停用,那么同时设置为不常用 **/
            if (0 == singleAgentRequestDTO.getIsActive()) {
                agentDO.setFrequentlyUse(0);
            }
            agentMapper.setActive(agentDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Agent setActive has error", e);
            throw new ServiceException("Agent setActive has error", e);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<PaginationSupportDTO<AgentListResponseDTO>> getListForPage(@RequestBody AgentListQueryRequestDTO agentListQueryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<AgentListResponseDTO> list = new ArrayList();
        try {
            int currentPage = agentListQueryRequestDTO.getCurrentPage(); //当前页数
            int pageSize = agentListQueryRequestDTO.getPageSize(); //每页显示条数
            //通知拦截器准备分页
            PageHelper.startPage(currentPage, pageSize);
            List<AgentDO> agentDOS = agentMapper.listAgent(agentListQueryRequestDTO);
            PageInfo<AgentDO> pageInfo = new PageInfo<AgentDO>(agentDOS);
            AgentListResponseDTO agentListResponseDTO = null;
            for (AgentDO agentDO : pageInfo.getList()) {
                agentListResponseDTO = PropertyCopyUtil.transfer(agentDO, AgentListResponseDTO.class);
                //t_agent取消了remaining_amount字段,所以要手动set
                if (null != agentDO.getAmount() && null != agentDO.getUsedAmount()) {
                    agentListResponseDTO.setRemainingAmount(agentDO.getAmount().subtract(agentDO.getUsedAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (null != agentDO.getUserDO() && null != agentDO.getUserDO().getRealName()) {
                    agentListResponseDTO.setMerchantBMName(agentDO.getUserDO().getRealName());
                }
                if (null != agentDO.getAgentUserDO() && null != agentDO.getAgentUserDO().getRealName()) {
                    agentListResponseDTO.setRealName(agentDO.getAgentUserDO().getRealName());
                    //账户取消了phone字段,userName登录名即手机
                    agentListResponseDTO.setPhone(agentDO.getAgentUserDO().getUserName());
                }
                list.add(agentListResponseDTO);

            }
            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO(pageSize);
            paginationSupportDTO.setItemList(list);
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setCurrentPage(currentPage);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        } catch (Exception e) {
            log.error("Agent getListForPage has error", e);
            throw new ServiceException("Agent getListForPage has error", e);
        }

        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AddBankCardResponseDTO> saveBankCard(AddBankCardRequestDTO addBankCardRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AddBankCardResponseDTO addBankCardResponseDTO = new AddBankCardResponseDTO();
        AgentBankCardDO agentBankCardDO = null;
        /**
         * 1.判断请求参数bankCardId是否为空
         * 2.BankCardId=null -->新增保存
         * 3.BankCardId!=null -->更新保存
         */
        try {
            agentBankCardDO = PropertyCopyUtil.transfer(addBankCardRequestDTO, AgentBankCardDO.class);
            if (null == addBankCardRequestDTO.getBankCardId()) {
                /**新增保存*/
                agentBankCardMapper.insertSingleAgentBankCard(agentBankCardDO);
            } else {
                /**更新保存*/
                agentBankCardMapper.updateSingleAgentBankCard(agentBankCardDO);
            }
            if (null == agentBankCardDO.getBankCardId()) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                return responseDTO;
            }
            addBankCardResponseDTO.setBankCardId(agentBankCardDO.getBankCardId());
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(addBankCardResponseDTO);
            return responseDTO;
        } catch (Exception e) {
            log.error("Agent insertSingleAgentBankCard has error", e);
            throw new ServiceException("Agent insertSingleAgentBankCard has error", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<PaginationSupportDTO<FrequentAgentDTO>> queryCommonAgentList(CommonAgentListRequestDTO commonAgentListRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<FrequentAgentDTO> list = new ArrayList<>();
        try {
            int currentPage = commonAgentListRequestDTO.getCurrentPage(); //当前页数
            int pageSize = commonAgentListRequestDTO.getPageSize(); //每页显示条数
            /** 开始分页 **/
            PageHelper.startPage(currentPage, pageSize);
            List<AgentDO> agentDOS = agentMapper.queryCommonAgentList(commonAgentListRequestDTO);
            PageInfo<AgentDO> page = new PageInfo<>(agentDOS);
            FrequentAgentDTO frequentAgentDTO = null;
            for (AgentDO agentDO : page.getList()) {
                frequentAgentDTO = new FrequentAgentDTO();
                frequentAgentDTO = PropertyCopyUtil.transfer(agentDO, FrequentAgentDTO.class);
                if (null != agentDO.getFinanceCurrency()) {
                    frequentAgentDTO.setCurrency(agentDO.getFinanceCurrency());
                }
                list.add(frequentAgentDTO);
            }

            PaginationSupportDTO<FrequentAgentDTO> paginationSupportDTO = new PaginationSupportDTO<>(pageSize);
            paginationSupportDTO.setCurrentPage(currentPage);
            paginationSupportDTO.setTotalPage(page.getPages());
            paginationSupportDTO.setTotalCount(page.getTotal());
            paginationSupportDTO.setItemList(list);

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);

        } catch (Exception e) {
            log.error("Agent queryCommonAgentList has error", e);
            throw new ServiceException("Agent queryCommonAgentList has error", e);
        }
        return responseDTO;


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AgentAddVisitResponseDTO> addVisit(AgentAddVisitRequestDTO addVisitRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AgentAddVisitResponseDTO addVisitResponseDTO = new AgentAddVisitResponseDTO();
        try {
            AgentVisitDO agentVisitDO = PropertyCopyUtil.transfer(addVisitRequestDTO, AgentVisitDO.class);
            if (null != addVisitRequestDTO.getContent()) {
                agentVisitDO.setVisitContent(addVisitRequestDTO.getContent());
            }
            if (null == agentVisitDO.getAgentCode() || "".equals(agentVisitDO.getAgentCode())) {
                String agentCode = agentMapper.getAgentCodeById(agentVisitDO.getAgentId());
                agentVisitDO.setAgentCode(agentCode);
                agentVisitMapper.addVisit(agentVisitDO);
                if (null == agentVisitDO.getAgentVisitId()) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    return responseDTO;
                }
                addVisitResponseDTO.setAgentVisitId(agentVisitDO.getAgentVisitId());
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                responseDTO.setModel(addVisitResponseDTO);
                return responseDTO;
            } else {
                agentVisitMapper.addVisit(agentVisitDO);
                if (null == agentVisitDO.getAgentVisitId()) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    return responseDTO;

                }
                addVisitResponseDTO.setAgentVisitId(agentVisitDO.getAgentVisitId());
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                responseDTO.setModel(addVisitResponseDTO);
                return responseDTO;
            }

        } catch (Exception e) {
            log.error("Agent addVisit has error", e);
            throw new ServiceException("Agent addVisit has error", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<SingleAgentResponseDTO> getAgentById(SingleAgentRequestDTO singleAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AgentDO agentDO = null;
        SingleAgentResponseDTO singleAgentResponseDTO = null;
        try {
            if (null != singleAgentRequestDTO.getAgentId() || null != singleAgentRequestDTO.getAgentCode()) {
                agentDO = agentMapper.selectSingleAgentInfo(singleAgentRequestDTO);
                singleAgentResponseDTO = PropertyCopyUtil.transfer(agentDO, SingleAgentResponseDTO.class);
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
                        merchantOPs.add(merchantOPDTO);
                    }
                    singleAgentResponseDTO.setMerchantOPs(merchantOPs);
                }
                responseDTO.setModel(singleAgentResponseDTO);
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            }

        } catch (Exception e) {
            log.error("Agent getAgentById has error", e);
            throw new ServiceException("Agent getAgentById has error", e);
        }

        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO saveUser(AgentUserRequestDTO agentUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        //日志内容
        StringBuffer content = new StringBuffer();
        try {
            /**
             * 1.都是单个新增或者编辑保存
             * 2.如果userId=null--->新增保存 否则--->编辑保存
             */
            AgentUserDO agentUserDO = PropertyCopyUtil.transfer(agentUserRequestDTO, AgentUserDO.class);
            if (null == agentUserRequestDTO.getUserId()) {
                //新增
                /** 做账号查重判断 */
                Long userNum = agentUserMapper.selectUserByUsernameAndMerchantId(agentUserDO.getUserName(), agentUserRequestDTO.getMerchantId());
                if (null != userNum && 0 < userNum) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.USER_NAME_IDENTICAL.errorCode);
                    responseDTO.setFailReason(ErrorCodeEnum.USER_NAME_IDENTICAL.errorDesc);
                    return responseDTO;
                }

                /** 如果前端没有传phone字段,则把username赋值给phone */
                if (null == agentUserRequestDTO.getPhone() || "".equals(agentUserRequestDTO.getPhone())) {
                    agentUserDO.setPhone(agentUserDO.getUserName());
                }

                /**
                 * 密码加密存储
                 */
                agentUserDO.setPassword(MD5Util.encode(agentUserDO.getPassword()));
                agentUserMapper.insertSingleAgentUser(agentUserDO);
                content.append("创建了账号：").append(agentUserDO.getUserName());
            } else {
                /**
                 * 编辑保存
                 * 需要和数据库原有数据作比较,存日志
                 */
                //获取数据库里的原有数据
                AgentUserDO oriAgentUserDO = agentUserMapper.getUserByUserId(agentUserRequestDTO.getUserId());
                agentUserDO.setAgentUserId(agentUserRequestDTO.getUserId());
                /**
                 * 同一条日志,重置密码和修改密码只有存在一个
                 */
                if ("666666".equals(agentUserRequestDTO.getPassword())) {
                    content.append("重置了密码 ");
                    agentUserDO.setPassword(MD5Util.encode(agentUserDO.getPassword()));
                } else if (null != oriAgentUserDO.getPassword()
                        && !oriAgentUserDO.getPassword().equals(MD5Util.encode(agentUserRequestDTO.getPassword()))
                        && !oriAgentUserDO.getPassword().equals(agentUserRequestDTO.getPassword())) {
                    content.append("修改了密码 ");
                    agentUserDO.setPassword(MD5Util.encode(agentUserDO.getPassword()));
                }

                if (null != oriAgentUserDO.getRealName()
                        && null != agentUserRequestDTO.getRealName()
                        && !oriAgentUserDO.getRealName().equals(agentUserRequestDTO.getRealName())) {
                    content.append("将姓名修改为" + agentUserRequestDTO.getRealName());
                }
                agentUserMapper.updateSingleAgentUser(agentUserDO);
            }
            if (!"".equals(content.toString())) {
                AgentOperLogDO agentOperLogDO = new AgentOperLogDO();
                agentOperLogDO.setOperAgentId(agentUserRequestDTO.getAgentId());
                agentOperLogDO.setOperAgentUserId(agentUserDO.getAgentUserId());
                agentOperLogDO.setContent(content.toString());
                agentOperLogDO.setCreator(agentUserRequestDTO.getCreator());
                agentOperLogDO.setCreateTime(new Date());
                //往日志表中登记一条记录
                agentOperLogMapper.insertSelective(agentOperLogDO);
            }

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Agent saveUser has error", e);
            throw new ServiceException("Agent saveUser has error", e);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO setUserActive(SetAgentUserActiveRequestDTO setAgentUserActiveRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String content = "";
        try {
            AgentUserDO agentUserDO = PropertyCopyUtil.transfer(setAgentUserActiveRequestDTO, AgentUserDO.class);
            agentUserDO.setAgentUserId(setAgentUserActiveRequestDTO.getUserId());
            agentUserMapper.setUserActive(agentUserDO);
            /**插入日志*/
            //前端没有传agent_id,记录日志需要记录agent_id
            AgentUserDO oriAgentUserDO = agentUserMapper.getUserByUserId(setAgentUserActiveRequestDTO.getUserId());
            AgentOperLogDO agentOperLogDO = new AgentOperLogDO();
            agentOperLogDO.setOperAgentId(oriAgentUserDO.getAgentId());
            agentOperLogDO.setOperAgentUserId(agentUserDO.getAgentUserId());
            if (0 == setAgentUserActiveRequestDTO.getIsActive()) {
                content = "设置为停用";
            } else if (1 == setAgentUserActiveRequestDTO.getIsActive()) {
                content = "设置为启用";
            }
            agentOperLogDO.setContent(content);
            agentOperLogDO.setCreator(setAgentUserActiveRequestDTO.getModifier());
            agentOperLogDO.setCreateTime(new Date());
            //往日志表中登记一条记录
            agentOperLogMapper.insertSelective(agentOperLogDO);

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Agent setUserActive has error", e);
            throw new ServiceException("Agent setUserActive has error", e);
        }
        return responseDTO;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO modifyAmount(ModifyAmountRequestDTO modifyAmountRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AgentAmountLogDO agentAmountLogDO = new AgentAmountLogDO();
        try {
            /**
             * 1.获取当前分销商的总额度,已用额度
             * 2.获取设置的总额度与当前总额度的差值,并在剩余额度上加上
             * 3.update分销商额度信息
             */
            BigDecimal currentAmount = agentMapper.getAmount(modifyAmountRequestDTO.getAgentId());//当前总额度
            BigDecimal currentUsedAmount = agentMapper.getUsedAmount(modifyAmountRequestDTO.getAgentId());//当前已用额度
            /** 如果当前总额度为null,表示初始化时没有设置额度,那剩余额度也为null,没必要查 */
            if (null == currentAmount) {
                agentAmountLogDO.setAmount(new BigDecimal(0).setScale(2));//修改前的总额度为0
            } else {
                agentAmountLogDO.setAmount(currentAmount.setScale(2, BigDecimal.ROUND_HALF_UP));//修改前额度
            }
            //修改后的额度
            agentAmountLogDO.setAmoutMotified(modifyAmountRequestDTO.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            agentAmountLogDO.setCreator(modifyAmountRequestDTO.getModifier());
            agentAmountLogDO.setAgentId(modifyAmountRequestDTO.getAgentId());
            /**
             * 新增一条额度修改日志
             */
            agentAmountLogMapper.insertAmountLog(agentAmountLogDO);
            AgentDO agentDO = PropertyCopyUtil.transfer(modifyAmountRequestDTO, AgentDO.class);
            agentMapper.modifyAmount(agentDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Agent modifyAmount has error", e);
            throw new ServiceException("Agent modifyAmount has error", e);
        }
        return responseDTO;
    }

    /**
     * 上传合同文件
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AddAgentContractFileResponseDTO> uploadContractForAgent(
            MultipartFile file, AddAgentContractFileRequestDTO addAgentContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);

        if (null == file || null == addAgentContractFileRequestDTO
                || null == addAgentContractFileRequestDTO.getAgentId()
                || null == addAgentContractFileRequestDTO.getContractName()) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return responseDTO;
        }
        try {
            // 获取上传文件的原始名
            String oriName = file.getOriginalFilename();
            /* String fileDir = uploadFileConfig.getAgentContractPath();*/
            ResponseDTO<UploadResponseDTO> responseUplDTO = com.fangcang.common.util.FileUploadUtil.addFile(file, Constant.AGENT_CON_PATH, null, uploadFileConfig);
            if (responseUplDTO.getResult() == ResultCodeEnum.SUCCESS.code) {
                //合同信息写入DO
                AgentContractFileDO agentContractFileDO = new AgentContractFileDO();
                agentContractFileDO.setAgentId(addAgentContractFileRequestDTO.getAgentId());
                agentContractFileDO.setDescription(addAgentContractFileRequestDTO.getContractName());
                agentContractFileDO.setFileName(oriName);

                agentContractFileDO.setRealPath(responseUplDTO.getModel().getFilePath());
                agentContractFileDO.setFileUrl(responseUplDTO.getModel().getFileUrl());
                agentContractFileDO.setCreateTime(addAgentContractFileRequestDTO.getCreateTime());
                agentContractFileDO.setCreator(addAgentContractFileRequestDTO.getCreator());
                agentContractMapper.addAgentContractFile(agentContractFileDO);
                //返回信息写入ResponseDTO
                AddAgentContractFileResponseDTO addAgentContractFileResponseDTO = new AddAgentContractFileResponseDTO();
                addAgentContractFileResponseDTO.setContractFileId(agentContractFileDO.getAgentContractFileId());
                addAgentContractFileResponseDTO.setRealPath(agentContractFileDO.getRealPath());

                responseDTO.setModel(addAgentContractFileResponseDTO);
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                return responseDTO;
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                return responseDTO;
            }
        } catch (Exception e) {
            log.error("uploadContractForAgent", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<PaginationSupportDTO<AgentVisitInfoDTO>> getVisitList(GetVisitListQueryDTO getVisitListQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<AgentVisitInfoDTO> visitList = new ArrayList<>();
        AgentVisitInfoDTO agentVisitInfoDTO = null;
        //获取当前页
        int currentPage = getVisitListQueryDTO.getCurrentPage();
        //获取每页大小
        int pageSize = getVisitListQueryDTO.getPageSize();
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<AgentVisitDO> visitDOS = agentVisitMapper.getVisitList(getVisitListQueryDTO);
            PageInfo<AgentVisitDO> pageInfo = new PageInfo(visitDOS);
            for (AgentVisitDO agentVisitDO : pageInfo.getList()) {
                agentVisitInfoDTO = new AgentVisitInfoDTO();
                agentVisitInfoDTO = PropertyCopyUtil.transfer(agentVisitDO, AgentVisitInfoDTO.class);
                if (null != agentVisitDO.getVisitContent()) {
                    agentVisitInfoDTO.setContent(agentVisitDO.getVisitContent());
                }
                visitList.add(agentVisitInfoDTO);
            }

            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO(pageSize);
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setItemList(visitList);

            responseDTO.setModel(paginationSupportDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Agent getVisitList has error", e);
            throw new ServiceException("Agent getVisitList has error", e);
        }
        return responseDTO;
    }

    /**
     * 查看用户日志列表
     */
    @Override
    public ResponseDTO<GetAgentOperLogResponseDTO> queryAgentOperLogList(GetAgentOperLogRequestDTO getAgentOperLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            GetAgentOperLogResponseDTO getAgentOperLogResponseDTO = new GetAgentOperLogResponseDTO();
            List<AgentOperLogDO> agentOperLogList = agentOperLogMapper.queryAgentOperLogList(getAgentOperLogRequestDTO);
            if (!CollectionUtils.isEmpty(agentOperLogList)) {
                List<AgentOperLogDTO> agentOperLogDTOList = new ArrayList<>();
                agentOperLogDTOList = PropertyCopyUtil.transferArray(agentOperLogList, AgentOperLogDTO.class);
                getAgentOperLogResponseDTO.setUserAccountLogList(agentOperLogDTOList);
                responseDTO.setModel(getAgentOperLogResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryAgentOperLogList", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseEntity<byte[]> getFile(String realPath, Long contractFileId) {
        ResponseEntity<byte[]> entity = null;
        try {
            //当文件路径为空
            if (StringUtils.isEmpty(realPath)) {
                throw new IOException("File'" + realPath + "'is empty");
            } else {
                //下载后的文件名
                String fileName = agentContractMapper.getContractFileName(contractFileId);
                File file = new File(realPath);
                if (file.isDirectory()) {
                    throw new IOException("File'" + file + "'exists but is directory");
                } else {
                    //设置浏览器响应

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    HttpStatus status = HttpStatus.OK;
                    //返回字节流FileUtils.readFileToByteArray(file)
                    entity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, status);
                }
            }
        } catch (Exception e) {
            log.error("Agent getFile has error", e);
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public ResponseDTO deleteBankCardById(Long bankCardId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            agentBankCardMapper.deleteBankCardById(bankCardId);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("deleteBankCardById", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 获取信用额度列表
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<PaginationSupportDTO<GetCreditLinesListResponseDTO>> queryCreditLinesList(GetCreditLinesListRequestDTO getCreditLinesListRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            PageHelper.startPage(getCreditLinesListRequestDTO.getCurrentPage(), getCreditLinesListRequestDTO.getPageSize());
            List<AgentDO> agentDOList = agentMapper.queryCreditLinesList(getCreditLinesListRequestDTO);
            PageInfo<AgentDO> pageInfo = new PageInfo<AgentDO>(agentDOList);
            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setPageSize(pageInfo.getPageSize());

            List<AgentDO> itemList = pageInfo.getList();
            if (!CollectionUtils.isEmpty(itemList)) {
                List<GetCreditLinesListResponseDTO> getCreditLinesListResponseDTOList = new ArrayList<>();
                GetCreditLinesListResponseDTO getCreditLinesListResponseDTO = null;
                for (AgentDO agentDO : itemList) {
                    getCreditLinesListResponseDTO = PropertyCopyUtil.transfer(agentDO, GetCreditLinesListResponseDTO.class);
                    if (null != agentDO.getUserDO()) {
                        getCreditLinesListResponseDTO.setUsername(agentDO.getUserDO().getUsername());
                        getCreditLinesListResponseDTO.setRealName(agentDO.getUserDO().getRealName());
                    } else {
                        getCreditLinesListResponseDTO.setUsername("");
                        getCreditLinesListResponseDTO.setRealName("");
                    }
                    if (null != agentDO.getAgentUserDO()) {
                        getCreditLinesListResponseDTO.setAgentUserName(agentDO.getAgentUserDO().getUserName());
                        getCreditLinesListResponseDTO.setPhone(agentDO.getAgentUserDO().getPhone());
                        getCreditLinesListResponseDTO.setAgentRealName(agentDO.getAgentUserDO().getRealName());
                    } else {
                        getCreditLinesListResponseDTO.setAgentUserName("");
                        getCreditLinesListResponseDTO.setPhone("");
                        getCreditLinesListResponseDTO.setAgentRealName("");
                    }
                    if (null != agentDO.getAmount()) {
                        BigDecimal amount = agentDO.getAmount();
                        BigDecimal usedAmount = agentDO.getUsedAmount();
                        getCreditLinesListResponseDTO.setRemainingAmount(amount.subtract(usedAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    getCreditLinesListResponseDTOList.add(getCreditLinesListResponseDTO);
                }
                paginationSupportDTO.setItemList(getCreditLinesListResponseDTOList);
            }
            responseDTO.setModel(paginationSupportDTO);

        } catch (Exception e) {
            log.error("queryCreditLinesList", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<AgentUserResponseDTO> getUserInfo(AgentUserRequestDTO agentUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AgentUserResponseDTO agentUserResponseDTO = null;
        try {
            agentUserResponseDTO = agentUserMapper.getUserInfo(agentUserRequestDTO);
            responseDTO.setModel(agentUserResponseDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("geAgentUserInfo", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<GetAgentAmountLogResponseDTO> queryAgentAmountLogList(GetAgentAmountLogRequestDTO agentAmountLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        GetAgentAmountLogResponseDTO agentAmountLogResponseDTO = new GetAgentAmountLogResponseDTO();
        try {
            List<AgentAmountLogDTO> agentAmountLogDTOS = agentAmountLogMapper.queryAgentAmountLogList(agentAmountLogRequestDTO);
            agentAmountLogResponseDTO.setAmountMotifyLogList(agentAmountLogDTOS);
            responseDTO.setModel(agentAmountLogResponseDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("queryAgentAmountLogList", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除合同文件
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO deleteContract(DeleteAgentContractFileRequestDTO deleteAgentContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            if (null != deleteAgentContractFileRequestDTO.getContractFileId()) {
                AgentContractFileDO agentContractFileDO = new AgentContractFileDO();
                agentContractFileDO.setAgentContractFileId(deleteAgentContractFileRequestDTO.getContractFileId());
                agentContractMapper.delete(agentContractFileDO);
            }
        } catch (Exception e) {
            log.error("deleteContract", e);
            throw new ServiceException("deleteContract has error", e);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO deductAgentCreditLine(DeductAgentCreditLineRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        /*try {
            *//**
             * 1.判断操作类型:operateType---->1:扣除额度 2:退还额度
             * 2.获取请求中要扣除的额度,与当前分销商剩余额度作对比,必须小于等于当前剩余额度(必须足够扣除)
             * 3.将请求DTO转换成agentDO,调用deductAgentCreditLine
             *//*
            Integer operateType = agentCreditLineRequestDTO.getOperateType();//操作类型
            String agentCode = agentCreditLineRequestDTO.getAgentCode(); //所请求的分销商编码
            BigDecimal debuctAmount = agentCreditLineRequestDTO.getDebuctAmount();//请求中的需要扣除(归还)的额度
            //当前总额度
            BigDecimal currentAmount = agentMapper.getAmountByAgentCode(agentCreditLineRequestDTO.getAgentCode());
            //当前已用额度
            BigDecimal currentUsedAmout = agentMapper.getUsedAmountByAgentCode(agentCreditLineRequestDTO.getAgentCode());
            //当前剩余信用额度
            BigDecimal currentRemainingAmount = currentAmount.subtract(currentUsedAmout).setScale(2, BigDecimal.ROUND_HALF_UP);
            *//** 走扣额度流程*//*
            if (operateType == 1) {
                *//** 如果需要扣除的额度小于等于当前剩余额度 **//*
                if ((debuctAmount.compareTo(currentRemainingAmount) == -1) || (debuctAmount.compareTo(currentRemainingAmount) == 0)) {
                    BigDecimal usedAmout = currentUsedAmout.add(debuctAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                    AgentDO agentDO = PropertyCopyUtil.transfer(agentCreditLineRequestDTO, AgentDO.class);
                    agentDO.setUsedAmount(usedAmout);
                    agentMapper.deductAgentCreditLine(agentDO);
                    responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                } else {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                }
            } else if (operateType == 2) {*//** 归还额度流程*//*
                BigDecimal usedAmout = currentUsedAmout.subtract(debuctAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                AgentDO agentDO = PropertyCopyUtil.transfer(agentCreditLineRequestDTO, AgentDO.class);
                agentDO.setUsedAmount(usedAmout);
                agentMapper.deductAgentCreditLine(agentDO);
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            }
        } catch (Exception e) {
            log.error("deductAgentCreditLine", e);
            throw new ServiceException("deductAgentCreditLine has error", e);
        }*/

        SingleAgentRequestDTO singleAgentRequestDTO=new SingleAgentRequestDTO();
        singleAgentRequestDTO.setAgentCode(requestDTO.getAgentCode());
        AgentDO agentDO= agentMapper.selectSingleAgentInfo(singleAgentRequestDTO);

        if(requestDTO.getOperateType()==1){
            BigDecimal amount=agentDO.getAmount()==null?BigDecimal.ZERO:agentDO.getAmount();
            BigDecimal usedAmount=agentDO.getUsedAmount()==null?BigDecimal.ZERO:agentDO.getUsedAmount();
            if (amount.subtract(usedAmount).subtract(requestDTO.getDebuctAmount()).compareTo(BigDecimal.ZERO)<0){
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("额度不足");
                return responseDTO;
            }
            int result=agentMapper.deductAgentCreditLine(requestDTO);
            if(result<1){
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("扣额度失败");
                return responseDTO;
            }
        }else if(requestDTO.getOperateType()==2){
            int result=agentMapper.returnAgentCreditLine(requestDTO);
            if(result<1){
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("退还额度失败");
                return responseDTO;
            }
        }else{
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("参数不正确");
            return responseDTO;
        }

        //保存明细
        AgentCreditItemDO agentCreditItemDO=new AgentCreditItemDO();
        agentCreditItemDO.setAgentId(agentDO.getAgentId().intValue());
        agentCreditItemDO.setType(requestDTO.getOperateType());
        agentCreditItemDO.setAmount(requestDTO.getDebuctAmount());
        agentCreditItemDO.setContent(requestDTO.getOrderCode());
        agentCreditItemDO.setCreator(requestDTO.getOperator());
        agentCreditItemDO.setCreateTime(new Date());
        agentCreditItemMapper.insert(agentCreditItemDO);

        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @Override
    public OrderDeductCreditDTO queryOrderDeductCredit(QueryOrderDeductCreditDTO requestDTO){
        return agentCreditItemMapper.queryOrderDeductCredit(requestDTO);
    }

    @Override
    @Transactional
    public ResponseDTO saveAgentUserBind(AgentUserBindDO agentUserBindDO) {
        AgentUserBindDO agentUserBindParam = new AgentUserBindDO();
        agentUserBindParam.setMerchantCode(agentUserBindDO.getMerchantCode());
        agentUserBindParam.setOpenId(agentUserBindDO.getOpenId());
        agentUserBindMapper.delete(agentUserBindParam);
        agentUserBindMapper.insert(agentUserBindDO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public AgentUserBindDO queryAgentUserBind(QueryAgentUserBindDTO requestDTO) {
        AgentUserBindDO agentUserBindParam = new AgentUserBindDO();
        agentUserBindParam.setMerchantCode(requestDTO.getMerchantCode());
        agentUserBindParam.setOpenId(requestDTO.getOpenId());
        return agentUserBindMapper.selectOne(agentUserBindParam);
    }

    @Override
    public List<AgentBankCardDTO> queryAgentBankCardList(String agentCode) {

        if (!StringUtil.isValidString(agentCode)){
            log.error("参数不合法,agentCode{}",agentCode);
            return null;
        }
        SingleAgentRequestDTO singleAgentRequestDTO = new SingleAgentRequestDTO();
        singleAgentRequestDTO.setAgentCode(agentCode);
        AgentDO agentDO = agentMapper.selectSingleAgentInfo(singleAgentRequestDTO);

        if (null == agentDO || CollectionUtils.isEmpty(agentDO.getBankCardList())){
            log.error("没有银行卡记录,agentCode{}",agentCode);
            return null;
        }

        List<AgentBankCardDTO> agentBankCardDTOList = PropertyCopyUtil.transferArray(agentDO.getBankCardList(),AgentBankCardDTO.class);
        return agentBankCardDTOList;
    }


}
