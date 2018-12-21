package com.fangcang.supplier.service.imp;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.AccountItemTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.MD5Util;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.UploadFileConfig;
import com.fangcang.finance.dto.PayItemDTO;
import com.fangcang.finance.financeorder.service.PayItemService;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.mapper.UserMapper;
import com.fangcang.merchant.request.StaffListQueryDTO;
import com.fangcang.supplier.domain.SupplyBankCardDO;
import com.fangcang.supplier.domain.SupplyCashItemDO;
import com.fangcang.supplier.domain.SupplyConfirmTypeDO;
import com.fangcang.supplier.domain.SupplyContractFileDO;
import com.fangcang.supplier.domain.SupplyCostDO;
import com.fangcang.supplier.domain.SupplyCreditItemDO;
import com.fangcang.supplier.domain.SupplyDO;
import com.fangcang.supplier.domain.SupplyDepositItemDO;
import com.fangcang.supplier.domain.SupplyOperLogDO;
import com.fangcang.supplier.domain.SupplyUserBindDO;
import com.fangcang.supplier.domain.SupplyUserDO;
import com.fangcang.supplier.domain.SupplyVisitDO;
import com.fangcang.supplier.dto.AccountDTO;
import com.fangcang.supplier.dto.FrequentSupplyDTO;
import com.fangcang.supplier.dto.MerchantOPDTO;
import com.fangcang.supplier.dto.SupplyBankCardDTO;
import com.fangcang.supplier.dto.SupplyConfirmTypeDTO;
import com.fangcang.supplier.dto.SupplyDTO;
import com.fangcang.supplier.dto.SupplyInfoDTO;
import com.fangcang.supplier.dto.SupplyOperLogDTO;
import com.fangcang.supplier.dto.SupplyOtherCostDTO;
import com.fangcang.supplier.dto.SupplyVisitInfoDTO;
import com.fangcang.supplier.mapper.SupplyBankCardMapper;
import com.fangcang.supplier.mapper.SupplyCashItemMapper;
import com.fangcang.supplier.mapper.SupplyConfirmTypeMapper;
import com.fangcang.supplier.mapper.SupplyContractMapper;
import com.fangcang.supplier.mapper.SupplyCostMapper;
import com.fangcang.supplier.mapper.SupplyCreditItemMapper;
import com.fangcang.supplier.mapper.SupplyDepositItemMapper;
import com.fangcang.supplier.mapper.SupplyMapper;
import com.fangcang.supplier.mapper.SupplyOperLogMapper;
import com.fangcang.supplier.mapper.SupplyUserBindMapper;
import com.fangcang.supplier.mapper.SupplyUserMapper;
import com.fangcang.supplier.mapper.SupplyVisitMapper;
import com.fangcang.supplier.request.AddSupplyContractFileRequestDTO;
import com.fangcang.supplier.request.AddSupplyCostRequestDTO;
import com.fangcang.supplier.request.AddSupplyRequestDTO;
import com.fangcang.supplier.request.AddVisitRequestDTO;
import com.fangcang.supplier.request.CommonSupplyListResquestDTO;
import com.fangcang.supplier.request.DeductSupplierCreditLineRequestDTO;
import com.fangcang.supplier.request.DeductSupplyCashDTO;
import com.fangcang.supplier.request.DeleteConfirmTypeRequestDTO;
import com.fangcang.supplier.request.DeleteSupplyContractFileRequestDTO;
import com.fangcang.supplier.request.GetSupplyOperLogRequestDTO;
import com.fangcang.supplier.request.GetSupplyVisitListQueryDTO;
import com.fangcang.supplier.request.QueryConfirmTypeDTO;
import com.fangcang.supplier.request.QueryOtherCostRequestDTO;
import com.fangcang.supplier.request.QuerySupplyCashItemDTO;
import com.fangcang.supplier.request.QuerySupplyDepositItemDTO;
import com.fangcang.supplier.request.QuerySupplyOrderDeductCreditDTO;
import com.fangcang.supplier.request.QuerySupplyUserBindDTO;
import com.fangcang.supplier.request.SaveConfirmTypeRequestDTO;
import com.fangcang.supplier.request.SetCooperationRequestDTO;
import com.fangcang.supplier.request.SetSupplyUserActiveRequestDTO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.request.SupplyCashRechargeDTO;
import com.fangcang.supplier.request.SupplyDepositRechargeDTO;
import com.fangcang.supplier.request.SupplyListQueryRequestDTO;
import com.fangcang.supplier.response.AddSupplyBankCardResponseDTO;
import com.fangcang.supplier.response.AddSupplyContractFileResponseDTO;
import com.fangcang.supplier.response.AddSupplyResponseDTO;
import com.fangcang.supplier.response.AddVisitResponseDTO;
import com.fangcang.supplier.response.GetSupplyOperLogResponseDTO;
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.response.SingleUserResponseDTO;
import com.fangcang.supplier.response.SupplyCashItemDTO;
import com.fangcang.supplier.response.SupplyDepositItemDTO;
import com.fangcang.supplier.response.SupplyOrderDeductCreditDTO;
import com.fangcang.supplier.service.SupplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
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
 * @Description: 供应商接口实现类
 */
@Service
@Slf4j
public class SupplyServiceImpl implements SupplyService {


    @Autowired
    private SupplyMapper supplyMapper;

    @Autowired
    private SupplyUserMapper supplyUserMapper;

    @Autowired
    private SupplyBankCardMapper supplyBankCardMapper;

    @Autowired
    private SupplyVisitMapper supplyVisitMapper;

    @Autowired
    private SupplyContractMapper supplyContractMapper;

    @Autowired
    private SupplyOperLogMapper supplyOperLogMapper;

    @Autowired
    private UploadFileConfig uploadFileConfig;

    @Autowired
    private SupplyUserBindMapper supplyUserBindMapper;

    @Autowired
    private SupplyCreditItemMapper supplyCreditItemMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SupplyConfirmTypeMapper supplyConfirmTypeMapper;

    @Autowired
    private SupplyCashItemMapper supplyCashItemMapper;

    @Autowired
    private SupplyDepositItemMapper supplyDepositItemMapper;

    @Autowired
    private PayItemService payItemService;

    @Autowired
    private SupplyCostMapper supplyCostMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AddSupplyResponseDTO> saveSupply(AddSupplyRequestDTO addSupplyRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        /**
         * 1.保存供应商信息返回自增主键supplyID(如果新增)
         * 2.保存供应商账号信息(保存操作不需要)
         * 3.保存供应商银行卡信息(保存操作不需要)
         **/
        SupplyDO supplyDO = new SupplyDO();
        supplyDO = PropertyCopyUtil.transfer(addSupplyRequestDTO, SupplyDO.class);
        if(null != supplyDO.getMerchantOPs()){
            StringBuilder merchantOPStr = new StringBuilder();
            for (String merchantOp : supplyDO.getMerchantOPs()) {
                merchantOPStr.append(merchantOp).append(",");
            }
            if (merchantOPStr.length()>0){
                supplyDO.setMerchantOPStr(merchantOPStr.toString().substring(0,merchantOPStr.length() -1));
            }else{
                supplyDO.setMerchantOPStr("");
            }
        }

        List<SupplyUserDO> supplyUserDOS = new ArrayList<>();
        List<SupplyBankCardDO> supplyBankCardDOS = new ArrayList<>();

        //获取Request里账号列表
        List<AccountDTO> accountDTOS = addSupplyRequestDTO.getUserList();
        //获取Request里银行卡信息列表
        List<SupplyBankCardDTO> bankCardDTOS = addSupplyRequestDTO.getBankCardList();

        //如果supplyId为空,走新增流程
        if (null == addSupplyRequestDTO.getSupplyId()) {
            Long supplyID = null;
            //1.保存供应商信息
            supplyMapper.insertSupply(supplyDO);
            //获取供应商ID
            supplyID = supplyDO.getSupplyId();

            //2.保存供应商账号信息
            if (accountDTOS!=null && !CollectionUtils.isEmpty(accountDTOS)) {
                /** 做账号查重判断 */
                //用户账号集合
                List<String> usernameList = new ArrayList<>();
                for (int i = 0; i < accountDTOS.size(); i++) {
                    usernameList.add(accountDTOS.get(i).getUserName());
                }
                //userNum:根据username和商家id查到的用户数量
                Long userNum = supplyUserMapper.selectUserByUsernameListAndMerchantId(usernameList, addSupplyRequestDTO.getMerchantId());
                if (null != userNum && userNum > 0) {
                    throw new ServiceException(ErrorCodeEnum.USER_NAME_IDENTICAL);
                }

                SupplyUserDO supplyUserDO = null;
                for (int i = 0; i < accountDTOS.size(); i++) {
                    supplyUserDO = new SupplyUserDO();
                    //新增时设置第一个账号为master
                    if (i == 0) {
                        supplyUserDO = PropertyCopyUtil.transfer(accountDTOS.get(i), SupplyUserDO.class);
                        supplyUserDO.setMaster(1);
                    } else {
                        supplyUserDO = PropertyCopyUtil.transfer(accountDTOS.get(i), SupplyUserDO.class);
                        supplyUserDO.setMaster(0);
                    }
                    /**如果前端没传phone字段,则把username赋值给phone*/
                    if(null==supplyUserDO.getPhone()||"".equals(supplyUserDO.getPhone())){
                        supplyUserDO.setPhone(supplyUserDO.getUserName());
                    }
                    supplyUserDO.setIsActive(1);//默认启用
                    supplyUserDO.setSupplyId(supplyID);//set供应商ID
                    supplyUserDO.setCreator(supplyDO.getCreator());//set创建者;创建时间由NOW()函数定义

                    //对密码加密
                    supplyUserDO.setPassword(MD5Util.encode(supplyUserDO.getPassword()));

                    supplyUserDOS.add(supplyUserDO);

                }
                supplyUserMapper.insertUsers(supplyUserDOS);
            }

            //3.保存供应商银行卡信息
            if (bankCardDTOS!=null && !CollectionUtils.isEmpty(bankCardDTOS)) {
                SupplyBankCardDO supplyBankCardDO = null;
                for (int i = 0; i < bankCardDTOS.size(); i++) {
                    supplyBankCardDO = new SupplyBankCardDO();
                    supplyBankCardDO = PropertyCopyUtil.transfer(bankCardDTOS.get(i), SupplyBankCardDO.class);
                    supplyBankCardDO.setSupplyId(supplyID);//set供应商ID
                    supplyBankCardDO.setCreator(supplyDO.getCreator());//set创建者;创建时间由NOW()函数定义
                    supplyBankCardDOS.add(supplyBankCardDO);
                }
                supplyBankCardMapper.insertSupplyBankCard(supplyBankCardDOS);
            }

            //保存确认方式，modify by zhengxiongwei
            if (addSupplyRequestDTO.getSupplyConfirmTypeDTOList()!=null && !CollectionUtils.isEmpty(addSupplyRequestDTO.getSupplyConfirmTypeDTOList())){
                List<SupplyConfirmTypeDO> supplyConfirmTypeDOList = PropertyCopyUtil.transferArray(addSupplyRequestDTO.getSupplyConfirmTypeDTOList(),SupplyConfirmTypeDO.class);
                for (SupplyConfirmTypeDO supplyConfirmTypeDO : supplyConfirmTypeDOList){
                    supplyConfirmTypeDO.setSupplyId(supplyID.intValue());
                    supplyConfirmTypeDO.setCreator(addSupplyRequestDTO.getCreator());
                    supplyConfirmTypeDO.setCreateDate(new Date());
                    supplyConfirmTypeDO.setModifier(addSupplyRequestDTO.getCreator());
                    supplyConfirmTypeDO.setModifyDate(new Date());
                }
                supplyConfirmTypeMapper.insertList(supplyConfirmTypeDOList);
            }

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            String supplyCode=supplyMapper.getSupplyCodeById(supplyID);
            AddSupplyResponseDTO addSupplyResponseDTO = new AddSupplyResponseDTO();
            addSupplyResponseDTO.setSupplyId(supplyID);
            addSupplyResponseDTO.setSupplyCode(supplyCode);
            addSupplyResponseDTO.setSupplyName(addSupplyRequestDTO.getSupplyName());
            responseDTO.setModel(addSupplyResponseDTO);
        } else {
            //走修改保存流程
            //修改供应商信息
            if (supplyDO.getMerchantBM()==null || supplyDO.getMerchantBM()==0){
                supplyDO.setMerchantBM(Long.valueOf(-1));
            }
            if (supplyDO.getMerchantPM()==null || supplyDO.getMerchantPM()==0){
                supplyDO.setMerchantPM(Long.valueOf(-1));
            }
            if (supplyDO.getMerchantFinancer()==null || supplyDO.getMerchantFinancer()==0){
                supplyDO.setMerchantFinancer(Long.valueOf(-1));
            }
            supplyMapper.updateSupply(supplyDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            AddSupplyResponseDTO addSupplyResponseDTO = new AddSupplyResponseDTO();
            addSupplyResponseDTO.setSupplyId(supplyDO.getSupplyId());
            addSupplyResponseDTO.setSupplyCode(supplyDO.getSupplyCode());
            addSupplyResponseDTO.setSupplyName(supplyDO.getSupplyName());
            responseDTO.setModel(addSupplyResponseDTO);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<PaginationSupportDTO<SupplyInfoDTO>> getListForPage(SupplyListQueryRequestDTO supplyListQueryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<SupplyInfoDTO> list = new ArrayList();
        try {
            int currentPage = supplyListQueryRequestDTO.getCurrentPage(); //当前页数
            int pageSize = supplyListQueryRequestDTO.getPageSize(); //每页显示条数
            //通知拦截器准备分页
            PageHelper.startPage(currentPage, pageSize);
            List<SupplyDO> supplyDOS = supplyMapper.listSupply(supplyListQueryRequestDTO);
            PageInfo<SupplyDO> page = new PageInfo<SupplyDO>(supplyDOS);
            SupplyInfoDTO supplyInfoDTO = null;
            for (SupplyDO supplyDO : page.getList()) {
                supplyInfoDTO = new SupplyInfoDTO();
                supplyInfoDTO = PropertyCopyUtil.transfer(supplyDO, SupplyInfoDTO.class);
                if (null != supplyDO.getUserDO() && null != supplyDO.getUserDO().getRealName()) {
                    supplyInfoDTO.setMerchantBMName(supplyDO.getUserDO().getRealName());
                }
                if (null != supplyDO.getSupplyUserDO() && null != supplyDO.getSupplyUserDO().getRealName()) {
                    supplyInfoDTO.setRealName(supplyDO.getSupplyUserDO().getRealName());
                    //账户取消了phone字段,userName登录名即手机
                    supplyInfoDTO.setPhone(supplyDO.getSupplyUserDO().getUserName());
                }
                list.add(supplyInfoDTO);

            }
            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO(pageSize);
            paginationSupportDTO.setItemList(list);
            paginationSupportDTO.setTotalCount(page.getTotal());
            paginationSupportDTO.setTotalPage(page.getPages());
            paginationSupportDTO.setCurrentPage(currentPage);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        } catch (Exception e) {
            log.error("Supply getListForPage has error", e);
            throw new ServiceException("Supply getListForPage has error", e);
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO<List<SupplyDTO>> queryAllSupplyList(SupplyListQueryRequestDTO supplyListQueryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<SupplyDTO> supplyDTOList = null;
        try {
            List<SupplyDO> supplyDOS = supplyMapper.queryAllSupplyList(supplyListQueryRequestDTO);
            supplyDTOList = null;
            if (null != supplyDOS) {
                supplyDTOList = new ArrayList<>();
                supplyDTOList = PropertyCopyUtil.transferArray(supplyDOS, SupplyDTO.class);
            }
            responseDTO.setModel(supplyDTOList);
        } catch (Exception e) {
            log.error("queryAllSupplyList has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO setFrequentlyUse(SupplyInfoDTO supplyInfoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            SupplyDO supplyDO = PropertyCopyUtil.transfer(supplyInfoDTO, SupplyDO.class);
            supplyMapper.setFrequentlyUse(supplyDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Supply setFrequentlyUse has error", e);
            throw new ServiceException("Supply setFrequentlyUse has error", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO setActive(SupplyInfoDTO supplyInfoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            SupplyDO supplyDO = PropertyCopyUtil.transfer(supplyInfoDTO, SupplyDO.class);
            /** 如果设置为停用,那么同时设置为不常用 **/
            if (0 == supplyInfoDTO.getIsActive()) {
                supplyDO.setFrequentlyUse(0);
            }
            supplyMapper.setActive(supplyDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Supply setActive has error", e);
            throw new ServiceException("Supply setActive has error", e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<AddSupplyBankCardResponseDTO> saveBankCard(SupplyBankCardDTO supplyBankCardDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AddSupplyBankCardResponseDTO addSupplyBankCardResponseDTO = new AddSupplyBankCardResponseDTO();
        SupplyBankCardDO supplyBankCardDO = null;
        /**
         * 1.判断请求参数bankCardId是否为空
         * 2.BankCardId=null -->新增保存
         * 3.BankCardId!=null -->更新保存
         */
        try {
            supplyBankCardDO = PropertyCopyUtil.transfer(supplyBankCardDTO, SupplyBankCardDO.class);
            if (null == supplyBankCardDTO.getBankCardId()) {
                /**新增流程*/
                supplyBankCardMapper.insertSingleSupplyBankCard(supplyBankCardDO);
            } else {
                /**更新流程*/
                supplyBankCardMapper.updateSingleSupplyBankCard(supplyBankCardDO);
            }
            if (null == supplyBankCardDO.getBankCardId()) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                return responseDTO;
            }
            addSupplyBankCardResponseDTO.setBankCardId(supplyBankCardDO.getBankCardId());
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(addSupplyBankCardResponseDTO);
            return responseDTO;
        } catch (Exception e) {
            log.error("Supply insertSingleSupplyBankCard has error", e);
            throw new ServiceException("Supply insertSingleSupplyBankCard has error", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<PaginationSupportDTO<FrequentSupplyDTO>> queryCommonSupplyList(CommonSupplyListResquestDTO commonSupplyListResquestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<FrequentSupplyDTO> list = new ArrayList<>();
        try {
            int currentPage = commonSupplyListResquestDTO.getCurrentPage(); //当前页数
            int pageSize = commonSupplyListResquestDTO.getPageSize(); //每页显示条数
            /** 开始分页 **/
            PageHelper.startPage(currentPage, pageSize);
            List<SupplyDO> supplyDOS = supplyMapper.queryCommonSupplyList(commonSupplyListResquestDTO);
            PageInfo<SupplyDO> page = new PageInfo<>(supplyDOS);
            FrequentSupplyDTO frequentSupplyDTO = null;
            for (SupplyDO supplyDO : page.getList()) {
                frequentSupplyDTO = new FrequentSupplyDTO();
                frequentSupplyDTO = PropertyCopyUtil.transfer(supplyDO, FrequentSupplyDTO.class);
                if (null != supplyDO.getBaseCurrency()) {
                    frequentSupplyDTO.setCurrency(supplyDO.getBaseCurrency());
                }
                list.add(frequentSupplyDTO);
            }

            PaginationSupportDTO<FrequentSupplyDTO> paginationSupportDTO = new PaginationSupportDTO<>(pageSize);
            paginationSupportDTO.setCurrentPage(currentPage);
            paginationSupportDTO.setTotalPage(page.getPages());
            paginationSupportDTO.setTotalCount(page.getTotal());
            paginationSupportDTO.setItemList(list);

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);

        } catch (Exception e) {
            log.error("Supply queryCommonSupplyList has error", e);
            throw new ServiceException("Supply queryCommonSupplyList has error", e);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AddVisitResponseDTO> addVisit(AddVisitRequestDTO addVisitRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        AddVisitResponseDTO addVisitResponseDTO = new AddVisitResponseDTO();
        try {
            SupplyVisitDO supplyVisitDO = PropertyCopyUtil.transfer(addVisitRequestDTO, SupplyVisitDO.class);
            if (null != addVisitRequestDTO.getContent()) {
                supplyVisitDO.setVisitContent(addVisitRequestDTO.getContent());
            }
            if (null == supplyVisitDO.getSupplyCode()) {
                String supplyCode = supplyMapper.getSupplyCodeById(supplyVisitDO.getSupplyId());
                supplyVisitDO.setSupplyCode(supplyCode);
                supplyVisitMapper.addVisit(supplyVisitDO);
                if (null == supplyVisitDO.getSupplyVisitId()) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    return responseDTO;
                }
                addVisitResponseDTO.setSupplyVisitId(supplyVisitDO.getSupplyVisitId());
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                responseDTO.setModel(addVisitResponseDTO);
                return responseDTO;
            } else {
                supplyVisitMapper.addVisit(supplyVisitDO);
                if (null == supplyVisitDO.getSupplyVisitId()) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    return responseDTO;

                }
                addVisitResponseDTO.setSupplyVisitId(supplyVisitDO.getSupplyVisitId());
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                responseDTO.setModel(addVisitResponseDTO);
                return responseDTO;
            }

        } catch (Exception e) {
            log.error("Supply addVisit has error", e);
            throw new ServiceException("Supply addVisit has error", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO saveUser(SingleUserRequestDTO singleUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        //日志内容
        StringBuffer content = new StringBuffer();
        try {
            /**
             * 1.都是单个新增或者编辑保存
             * 2.如果userId=null--->新增保存 否则--->编辑保存
             */
            SupplyUserDO supplyUserDO = PropertyCopyUtil.transfer(singleUserRequestDTO, SupplyUserDO.class);
            if (null == singleUserRequestDTO.getUserId()) {
                /** 做账号查重判断 */
                if (null != supplyUserMapper.selectUserByUsernameAndMerchantId(supplyUserDO.getUserName(), singleUserRequestDTO.getMerchantId()) &&
                        0 < supplyUserMapper.selectUserByUsernameAndMerchantId(supplyUserDO.getUserName(), singleUserRequestDTO.getMerchantId())) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.USER_NAME_IDENTICAL.errorCode);
                    responseDTO.setFailReason(ErrorCodeEnum.USER_NAME_IDENTICAL.errorDesc);
                    return responseDTO;
                }
                /** 如果前端没有传phone字段,则把username赋值给phone */
                if (null==singleUserRequestDTO.getPhone()||"".equals(singleUserRequestDTO.getPhone())){
                    supplyUserDO.setPhone(supplyUserDO.getUserName());
                }

                /**
                 * 密码加密存储
                 */
                supplyUserDO.setPassword(MD5Util.encode(supplyUserDO.getPassword()));
                supplyUserMapper.insertSingleSupplyUser(supplyUserDO);
                content.append("创建了账号：").append(supplyUserDO.getUserName());
            } else {
                /**
                 * 编辑保存
                 * 需要和数据库原有数据作比较,存日志
                 */
                //获取数据库里的原有数据
                SupplyUserDO oriSupplyUserDO = supplyUserMapper.getUserByUserId(singleUserRequestDTO.getUserId());

                supplyUserDO.setSupplyUserId(singleUserRequestDTO.getUserId());
                /**
                 * 同一条日志,重置密码和修改密码只有存在一个
                 */
                if ("666666".equals(singleUserRequestDTO.getPassword())) {
                    content.append("重置了密码 ");
                    supplyUserDO.setPassword(MD5Util.encode(supplyUserDO.getPassword()));
                } else if (null != oriSupplyUserDO.getPassword()
                        && !oriSupplyUserDO.getPassword().equals(MD5Util.encode(singleUserRequestDTO.getPassword()))
                        && !oriSupplyUserDO.getPassword().equals(singleUserRequestDTO.getPassword())) {
                    content.append("修改了密码 ");
                    supplyUserDO.setPassword(MD5Util.encode(supplyUserDO.getPassword()));
                }

                if (null != oriSupplyUserDO.getRealName()
                        && null != singleUserRequestDTO.getRealName()
                        && !oriSupplyUserDO.getRealName().equals(singleUserRequestDTO.getRealName())) {
                    content.append("将姓名修改为" + singleUserRequestDTO.getRealName());
                }
                supplyUserMapper.updateSingleSupplyUser(supplyUserDO);
            }

            if (!"".equals(content.toString())) {
                SupplyOperLogDO supplyOperLogDO = new SupplyOperLogDO();
                supplyOperLogDO.setOperSupplyId(singleUserRequestDTO.getSupplyId());
                supplyOperLogDO.setOperSupplyUserId(supplyUserDO.getSupplyUserId());
                supplyOperLogDO.setContent(content.toString());
                supplyOperLogDO.setCreator(singleUserRequestDTO.getCreator());
                supplyOperLogDO.setCreateTime(new Date());
                //往日志表中登记一条记录
                supplyOperLogMapper.insertSelective(supplyOperLogDO);
            }

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);

        } catch (Exception e) {
            log.error("Supply saveUser has error", e);
            throw new ServiceException("Supply saveUser has error", e);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO setUserActive(SetSupplyUserActiveRequestDTO setSupplyUserActiveRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String content = "";
        try {
            SupplyUserDO supplyUserDO = PropertyCopyUtil.transfer(setSupplyUserActiveRequestDTO, SupplyUserDO.class);
            supplyUserDO.setSupplyUserId(setSupplyUserActiveRequestDTO.getUserId());
            supplyUserMapper.setUserActive(supplyUserDO);
            /**插入日志*/
            //前端没有传supply_id,记录日志需要记录supply_id
            SupplyUserDO oriSupplyUserDO = supplyUserMapper.getUserByUserId(setSupplyUserActiveRequestDTO.getUserId());
            SupplyOperLogDO supplyOperLogDO = new SupplyOperLogDO();
            supplyOperLogDO.setOperSupplyId(oriSupplyUserDO.getSupplyId());
            supplyOperLogDO.setOperSupplyUserId(supplyUserDO.getSupplyUserId());
            if (0 == setSupplyUserActiveRequestDTO.getIsActive()) {
                content = "设置为停用";
            } else if (1 == setSupplyUserActiveRequestDTO.getIsActive()) {
                content = "设置为启用";
            }
            supplyOperLogDO.setContent(content);
            supplyOperLogDO.setCreator(setSupplyUserActiveRequestDTO.getModifier());
            supplyOperLogDO.setCreateTime(new Date());
            //往日志表中登记一条记录
            supplyOperLogMapper.insertSelective(supplyOperLogDO);

            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Supply setUserActive has error", e);
            throw new ServiceException("Supply setUserActive has error", e);

        }

        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<SingleSupplyInfoResponseDTO> getSupplyById(SingleUserRequestDTO singleUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        SupplyDO supplyDO = null;
        SingleSupplyInfoResponseDTO singleSupplyInfoResponseDTO = null;
        try {
            if (null != singleUserRequestDTO.getSupplyId() || null != singleUserRequestDTO.getSupplyCode()) {
                supplyDO = supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
                singleSupplyInfoResponseDTO = PropertyCopyUtil.transfer(supplyDO, SingleSupplyInfoResponseDTO.class);

                SupplyConfirmTypeDO confirmTypeQueryDO = new SupplyConfirmTypeDO();
                confirmTypeQueryDO.setSupplyId(supplyDO.getSupplyId().intValue());
                List<SupplyConfirmTypeDO> supplyConfirmTypeDOList = supplyConfirmTypeMapper.select(confirmTypeQueryDO);
                List<SupplyConfirmTypeDTO> supplyConfirmTypeDTOList = PropertyCopyUtil.transferArray(supplyConfirmTypeDOList,SupplyConfirmTypeDTO.class);
                singleSupplyInfoResponseDTO.setSupplyConfirmTypeDTOList(supplyConfirmTypeDTOList);

                List<MerchantOPDTO> merchantOPs = new ArrayList<>();
                if(StringUtil.isValidString(supplyDO.getMerchantOPStr())){
                    String [] merchantOPIds = supplyDO.getMerchantOPStr().split(",");
                    List<Long> merchantOPIdList = new ArrayList<>();
                    for (String s : merchantOPIds) {
                        merchantOPIdList.add(Long.valueOf(s));
                    }
                    StaffListQueryDTO staffListQueryDTO = new StaffListQueryDTO();
                    staffListQueryDTO.setUserIds(merchantOPIdList);
                    List<UserDO> userDOS = userMapper.getUserListByUserId(staffListQueryDTO);

                    for (UserDO userDO : userDOS) {
                        MerchantOPDTO merchantOPDTO = new MerchantOPDTO();
                        merchantOPDTO.setMerchantOP(userDO.getUserId());
                        merchantOPDTO.setMerchantOPName(userDO.getRealName());
                        merchantOPs.add(merchantOPDTO);
                    }
                }
                singleSupplyInfoResponseDTO.setMerchantOPs(merchantOPs);
                responseDTO.setModel(singleSupplyInfoResponseDTO);
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            }
        } catch (Exception e) {
            log.error("Supply getSupplyById has error", e);
            throw new ServiceException("Supply getSupplyById has error", e);

        }
        return responseDTO;
    }

    /**
     * 上传合同文件
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<AddSupplyContractFileResponseDTO> uploadContractForSupply(
            MultipartFile file, AddSupplyContractFileRequestDTO addSupplyContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);

        if (null == file || null == addSupplyContractFileRequestDTO
                || null == addSupplyContractFileRequestDTO.getSupplyId()
                || null == addSupplyContractFileRequestDTO.getContractName()) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return responseDTO;
        }
        try {
            // 获取上传文件的原始名
            String oriName = file.getOriginalFilename();
            /*String fileDir = uploadFileConfig.getSupplyContractPath();*/
            ResponseDTO<UploadResponseDTO> responseUplDTO = com.fangcang.common.util.FileUploadUtil.addFile(file, Constant.SUPPLY_CON_PATH, null, uploadFileConfig);
            if (responseUplDTO.getResult() == ResultCodeEnum.SUCCESS.code) {
                //合同信息写入DO
                SupplyContractFileDO supplyContractFileDO = new SupplyContractFileDO();
                supplyContractFileDO.setSupplyId(addSupplyContractFileRequestDTO.getSupplyId());
                supplyContractFileDO.setDescription(addSupplyContractFileRequestDTO.getContractName());
                supplyContractFileDO.setFileName(oriName);

                supplyContractFileDO.setRealPath(responseUplDTO.getModel().getFilePath());
                supplyContractFileDO.setFileUrl(responseUplDTO.getModel().getFileUrl());
                supplyContractFileDO.setCreateTime(addSupplyContractFileRequestDTO.getCreateTime());
                supplyContractFileDO.setCreator(addSupplyContractFileRequestDTO.getCreator());
                supplyContractMapper.addSupplyContractFile(supplyContractFileDO);
                //返回信息写入ResponseDTO
                AddSupplyContractFileResponseDTO addSupplyContractFileResponseDTO = new AddSupplyContractFileResponseDTO();
                addSupplyContractFileResponseDTO.setContractFileId(supplyContractFileDO.getSupplyContractFileId());
                addSupplyContractFileResponseDTO.setRealPath(supplyContractFileDO.getRealPath());

                responseDTO.setModel(addSupplyContractFileResponseDTO);
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                return responseDTO;
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                return responseDTO;
            }
        } catch (Exception e) {
            log.error("uploadContractForSupply", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<SupplyVisitInfoDTO> getVisitList(GetSupplyVisitListQueryDTO getSupplyVisitListQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<SupplyVisitInfoDTO> visitList = new ArrayList<>();
        SupplyVisitInfoDTO supplyVisitInfoDTO = null;
        try {
            //获取当前页
            int currentPage = getSupplyVisitListQueryDTO.getCurrentPage();
            //获取每页大小
            int pageSize = getSupplyVisitListQueryDTO.getPageSize();
            PageHelper.startPage(currentPage, pageSize);
            List<SupplyVisitDO> visitDOS = supplyVisitMapper.getVisitList(getSupplyVisitListQueryDTO);
            PageInfo<SupplyVisitDO> pageInfo = new PageInfo<>(visitDOS);
            for (SupplyVisitDO supplyVisitDO : pageInfo.getList()) {
                supplyVisitInfoDTO = new SupplyVisitInfoDTO();
                supplyVisitInfoDTO = PropertyCopyUtil.transfer(supplyVisitDO, SupplyVisitInfoDTO.class);
                if (null != supplyVisitDO.getVisitContent()) {
                    supplyVisitInfoDTO.setContent(supplyVisitDO.getVisitContent());
                }
                visitList.add(supplyVisitInfoDTO);
            }
            PaginationSupportDTO<SupplyVisitInfoDTO> paginationSupportDTO = new PaginationSupportDTO<>(pageInfo.getPageSize());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setItemList(visitList);

            responseDTO.setModel(paginationSupportDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("Supply getVisitList has error", e);
            throw new ServiceException("Supply getVisitList has error", e);
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
                String fileName = supplyContractMapper.getContractFileName(contractFileId);
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
            log.error("Supply getFile has error", e);
            e.printStackTrace();
        }
        return entity;
    }


    /**
     * 查看用户日志列表
     */
    @Override
    public ResponseDTO<GetSupplyOperLogResponseDTO> querySupplyOperLogList(GetSupplyOperLogRequestDTO getSupplyOperLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            GetSupplyOperLogResponseDTO getSupplyOperLogResponseDTO = new GetSupplyOperLogResponseDTO();
            List<SupplyOperLogDO> supplyOperLogList = supplyOperLogMapper.querySupplyOperLogList(getSupplyOperLogRequestDTO);
            if (!CollectionUtils.isEmpty(supplyOperLogList)) {
                List<SupplyOperLogDTO> supplyOperLogDTOList = new ArrayList<>();
                supplyOperLogDTOList = PropertyCopyUtil.transferArray(supplyOperLogList, SupplyOperLogDTO.class);
                getSupplyOperLogResponseDTO.setOperateList(supplyOperLogDTOList);
                responseDTO.setModel(getSupplyOperLogResponseDTO);
            }
        } catch (Exception e) {
            log.error("querySupplyOperLogList", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteBankCardById(Long bankCardId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            supplyBankCardMapper.deleteBankCardById(bankCardId);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("deleteBankCardById", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<SingleUserResponseDTO> getUserInfo(SingleUserRequestDTO singleUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        SingleUserResponseDTO singleUserResponseDTO = null;
        try {
            singleUserResponseDTO = supplyUserMapper.getUserInfo(singleUserRequestDTO);
            responseDTO.setModel(singleUserResponseDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("getUserInfo", e);
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
    public ResponseDTO deleteContract(DeleteSupplyContractFileRequestDTO deleteSupplyContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            if (null != deleteSupplyContractFileRequestDTO.getContractFileId()) {
                SupplyContractFileDO supplyContractFileDO = new SupplyContractFileDO();
                supplyContractFileDO.setSupplyContractFileId(deleteSupplyContractFileRequestDTO.getContractFileId());
                supplyContractMapper.delete(supplyContractFileDO);
            }
        } catch (Exception e) {
            log.error("deleteContract", e);
            throw new ServiceException("deleteContract has error", e);
        }
        return responseDTO;
    }

    @Override
    public List<SupplyUserBindDO> querySupplyUserBind(QuerySupplyUserBindDTO requestDTO) {
        SupplyUserBindDO supplyUserBindParam=new SupplyUserBindDO();
        supplyUserBindParam.setMerchantCode(requestDTO.getMerchantCode());
        supplyUserBindParam.setOpenId(requestDTO.getOpenId());
        supplyUserBindParam.setSupplyCode(requestDTO.getSupplyCode());
        return supplyUserBindMapper.select(supplyUserBindParam);
    }

    @Override
    @Transactional
    public ResponseDTO saveSupplyUserBind(SupplyUserBindDO supplyUserBindDO) {
        SupplyUserBindDO supplyUserBindParam=new SupplyUserBindDO();
        supplyUserBindParam.setMerchantCode(supplyUserBindDO.getMerchantCode());
        supplyUserBindParam.setOpenId(supplyUserBindDO.getOpenId());
        supplyUserBindMapper.delete(supplyUserBindParam);
        supplyUserBindMapper.insert(supplyUserBindDO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO deductSupplierCreditLine(DeductSupplierCreditLineRequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO();

        SingleUserRequestDTO singleUserRequestDTO=new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyCode(requestDTO.getSupplierCode());
        SupplyDO supplyDO = supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);

        //保存明细
        SupplyCreditItemDO supplyCreditItemDO=new SupplyCreditItemDO();
        supplyCreditItemDO.setSupplyId(supplyDO.getSupplyId().intValue());
        supplyCreditItemDO.setType(requestDTO.getOperateType());
        supplyCreditItemDO.setAmount(requestDTO.getDebuctAmount());
        supplyCreditItemDO.setContent(requestDTO.getOrderCode());
        supplyCreditItemDO.setCreator(requestDTO.getOperator());
        supplyCreditItemDO.setCreateTime(new Date());
        supplyCreditItemMapper.insert(supplyCreditItemDO);

        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @Override
    public List<SupplyBankCardDTO> querySupplyBankCardList(@NotNull(message = "供应商编码不能为空") @NotNull String supplyCode) {

        if (!StringUtil.isValidString(supplyCode)){
            log.error("参数不合法,supplyCode{}",supplyCode);
            return null;
        }
        SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyCode(supplyCode);
        SupplyDO supplyDO = supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);

        if (null == supplyDO || CollectionUtils.isEmpty(supplyDO.getBankCardList())){
            log.error("没有银行卡记录,supplyCode{}",supplyCode);
            return null;
        }

        List<SupplyBankCardDTO> supplyBankCardDTOList = PropertyCopyUtil.transferArray(supplyDO.getBankCardList(),SupplyBankCardDTO.class);
        return supplyBankCardDTOList;
    }
    @Override
    public SupplyOrderDeductCreditDTO querySupplyOrderDeductCredit(QuerySupplyOrderDeductCreditDTO requestDTO){
        return supplyCreditItemMapper.querySupplyOrderDeductCredit(requestDTO);
    }

    @Override
    public ResponseDTO deleteConfirmType(DeleteConfirmTypeRequestDTO deleteConfirmTypeRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        supplyConfirmTypeMapper.deleteByPrimaryKey(deleteConfirmTypeRequestDTO.getConfirmTypeId());
        return responseDTO;
    }

    @Override
    public ResponseDTO saveConfirmType(SaveConfirmTypeRequestDTO saveConfirmTypeRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        if (null == saveConfirmTypeRequestDTO.getSupplyId()){
            log.error("保存确认方式失败：供应商ID为空：{}", JSON.toJSONString(saveConfirmTypeRequestDTO));
            responseDTO = new ResponseDTO(ErrorCodeEnum.INVALID_INPUTPARAM);
            return  responseDTO;
        }

        SupplyConfirmTypeDO supplyConfirmTypeDO = PropertyCopyUtil.transfer(saveConfirmTypeRequestDTO,SupplyConfirmTypeDO.class);
        //有主键就是更新
        if (null != saveConfirmTypeRequestDTO.getId() && saveConfirmTypeRequestDTO.getId() > 0){
            supplyConfirmTypeMapper.updateByPrimaryKeySelective(supplyConfirmTypeDO);
        } else {
            //无ID就是新增
            supplyConfirmTypeMapper.insertSelective(supplyConfirmTypeDO);
        }
        responseDTO.setModel(supplyConfirmTypeDO.getId());
        return responseDTO;
    }

    @Override
    public List<SupplyConfirmTypeDTO> queryCurrentConfirmType(QueryConfirmTypeDTO queryConfirmTypeDTO) {
        return supplyConfirmTypeMapper.queryCurrentConfirmType(queryConfirmTypeDTO);
    }

    @Override
    public ResponseDTO setCooperation(SetCooperationRequestDTO setCooperationRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        SupplyDO supplyDO = new SupplyDO();
        supplyDO.setSupplyId(setCooperationRequestDTO.getSupplyId());
        supplyDO.setCooperationStatus(setCooperationRequestDTO.getCooperationStatus());
        supplyMapper.updateSupply(supplyDO);
        return responseDTO;
    }

    @Override
    public ResponseDTO saveOtherCost(AddSupplyCostRequestDTO addSupplyCostRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        SupplyCostDO supplyCostDO = PropertyCopyUtil.transfer(addSupplyCostRequestDTO,SupplyCostDO.class);
        if (isAdd(addSupplyCostRequestDTO.getId())){
            supplyCostMapper.insert(supplyCostDO);
        }else {
            supplyCostMapper.updateByPrimaryKeySelective(supplyCostDO);
        }
        responseDTO.setModel(supplyCostDO.getId());
        return responseDTO;
    }

    /**
     * 是否新增：true:新增，false：修改
     * @param id
     * @return
     */
    private Boolean isAdd(Integer id){
        return null == id;
    }


    @Override
    @Transactional
    public ResponseDTO supplyCashRecharge(SupplyCashRechargeDTO requestDTO) {
        //现金账户充值
        supplyMapper.supplyCashRecharge(requestDTO.getSupplyId(),requestDTO.getAmount());
        SingleUserRequestDTO singleUserRequestDTO=new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(requestDTO.getSupplyId());
        SupplyDO supplyDO=supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
        //保存现金账户明细
        SupplyCashItemDO supplyCashItemDO=new SupplyCashItemDO();
        supplyCashItemDO.setSupplyId(requestDTO.getSupplyId().intValue());
        supplyCashItemDO.setType(AccountItemTypeEnum.TRANSFER_IN.key);
        supplyCashItemDO.setRechargeType(requestDTO.getRechargeType());
        supplyCashItemDO.setAmount(requestDTO.getAmount());
        supplyCashItemDO.setBalance(supplyDO.getCashAmount());
        if (requestDTO.getRechargeType()==1){
            supplyCashItemDO.setContent("线下转入");
        }else if(requestDTO.getRechargeType()==2){
            supplyCashItemDO.setContent("押金转入");
        }
        supplyCashItemDO.setNote(requestDTO.getNote());
        supplyCashItemDO.setCreator(requestDTO.getOperator());
        supplyCashItemDO.setCreateTime(new Date());
        supplyCashItemMapper.insert(supplyCashItemDO);
        if (requestDTO.getRechargeType()==1){
            //保存付款凭证
            PayItemDTO payItemDTO=new PayItemDTO();
            BeanUtils.copyProperties(requestDTO,payItemDTO);
            payItemDTO.setPayItemType(3);
            payItemDTO.setRelationId(supplyCashItemDO.getId());
            payItemDTO.setPayItemAttchDTOList(requestDTO.getPayItemAttchDTOList());
            payItemService.savePayItem(payItemDTO);
        }else if(requestDTO.getRechargeType()==2){
            //押金转入，则扣除押金
            supplyMapper.supplyDepositRecharge(requestDTO.getSupplyId(),BigDecimal.ZERO.subtract(requestDTO.getAmount()));
            SupplyDepositItemDO supplyDepositItemDO=new SupplyDepositItemDO();
            supplyDepositItemDO.setSupplyId(requestDTO.getSupplyId().intValue());
            supplyDepositItemDO.setType(AccountItemTypeEnum.TRANSFER_OUT.key);
            supplyDepositItemDO.setAmount(BigDecimal.ZERO.subtract(requestDTO.getAmount()));
            supplyDepositItemDO.setContent("转出到余额");
            supplyDepositItemDO.setNote(requestDTO.getNote());
            supplyDepositItemDO.setCreator(requestDTO.getOperator());
            supplyDepositItemDO.setCreateTime(new Date());
            supplyDepositItemMapper.insert(supplyDepositItemDO);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO deductSupplyCash(DeductSupplyCashDTO requestDTO) {
        if (requestDTO.getOperatorType()==1){
            //扣除现金余额
            supplyMapper.supplyCashRecharge(requestDTO.getSupplyId(),BigDecimal.ZERO.subtract(requestDTO.getAmount()));
        }else if(requestDTO.getOperatorType()==2){
            //退还现金余额
            supplyMapper.supplyCashRecharge(requestDTO.getSupplyId(),requestDTO.getAmount());
        }
        SingleUserRequestDTO singleUserRequestDTO=new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(requestDTO.getSupplyId());
        SupplyDO supplyDO=supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
        //保存现金账户明细
        SupplyCashItemDO supplyCashItemDO=new SupplyCashItemDO();
        supplyCashItemDO.setSupplyId(requestDTO.getSupplyId().intValue());
        supplyCashItemDO.setType(AccountItemTypeEnum.CHECKOUT.key);
        if (requestDTO.getOperatorType()==1){
            supplyCashItemDO.setAmount(BigDecimal.ZERO.subtract(requestDTO.getAmount()));
        }else if(requestDTO.getOperatorType()==2){
            supplyCashItemDO.setAmount(requestDTO.getAmount());
        }
        supplyCashItemDO.setBalance(supplyDO.getCashAmount());
        supplyCashItemDO.setContent(requestDTO.getOrderCode());
        supplyCashItemDO.setNote(requestDTO.getNote());
        supplyCashItemDO.setCreator(requestDTO.getOperator());
        supplyCashItemDO.setCreateTime(new Date());
        supplyCashItemMapper.insert(supplyCashItemDO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO supplyDepositRecharge(SupplyDepositRechargeDTO requestDTO) {
        //押金充值
        supplyMapper.supplyDepositRecharge(requestDTO.getSupplyId(),requestDTO.getAmount());
        SingleUserRequestDTO singleUserRequestDTO=new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(requestDTO.getSupplyId());
        SupplyDO supplyDO=supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
        //保存押金明细
        SupplyDepositItemDO supplyDepositItemDO=new SupplyDepositItemDO();
        supplyDepositItemDO.setSupplyId(requestDTO.getSupplyId().intValue());
        supplyDepositItemDO.setType(AccountItemTypeEnum.TRANSFER_IN.key);
        supplyDepositItemDO.setRechargeType(requestDTO.getRechargeType());
        supplyDepositItemDO.setAmount(requestDTO.getAmount());
        supplyDepositItemDO.setBalance(supplyDO.getDepositAmount());
        if (requestDTO.getRechargeType()==1){
            supplyDepositItemDO.setContent("线下转入");
        }else if(requestDTO.getRechargeType()==2){
            supplyDepositItemDO.setContent("余额转入");
        }
        supplyDepositItemDO.setNote(requestDTO.getNote());
        supplyDepositItemDO.setCreator(requestDTO.getOperator());
        supplyDepositItemDO.setCreateTime(new Date());
        supplyDepositItemMapper.insert(supplyDepositItemDO);
        if (requestDTO.getRechargeType()==1){
            //保存付款凭证
            PayItemDTO payItemDTO=new PayItemDTO();
            BeanUtils.copyProperties(requestDTO,payItemDTO);
            payItemDTO.setPayItemType(4);
            payItemDTO.setRelationId(supplyDepositItemDO.getId());
            payItemDTO.setPayItemAttchDTOList(requestDTO.getPayItemAttchDTOList());
            payItemService.savePayItem(payItemDTO);
        }else if(requestDTO.getRechargeType()==2){
            //现金余额转入，则扣除现金余额
            supplyMapper.supplyCashRecharge(requestDTO.getSupplyId(),BigDecimal.ZERO.subtract(requestDTO.getAmount()));
            SupplyCashItemDO supplyCashItemDO=new SupplyCashItemDO();
            supplyCashItemDO.setSupplyId(requestDTO.getSupplyId().intValue());
            supplyCashItemDO.setType(AccountItemTypeEnum.TRANSFER_OUT.key);
            supplyCashItemDO.setAmount(BigDecimal.ZERO.subtract(requestDTO.getAmount()));
            supplyCashItemDO.setContent("转出到押金");
            supplyCashItemDO.setNote(requestDTO.getNote());
            supplyCashItemDO.setCreator(requestDTO.getOperator());
            supplyCashItemDO.setCreateTime(new Date());
            supplyCashItemMapper.insert(supplyCashItemDO);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public PaginationSupportDTO<SupplyCashItemDTO> querySupplyCashItem(QuerySupplyCashItemDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<SupplyCashItemDTO> list =supplyCashItemMapper.querySupplyCashItem(requestDTO);
        PageInfo<SupplyCashItemDTO> page = new PageInfo<SupplyCashItemDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<SupplyDepositItemDTO> querySupplyDepositItem(QuerySupplyDepositItemDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<SupplyDepositItemDTO> list =supplyDepositItemMapper.querySupplyDepositItem(requestDTO);
        PageInfo<SupplyDepositItemDTO> page = new PageInfo<SupplyDepositItemDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<SupplyOtherCostDTO> queryOtherCostForPage(QueryOtherCostRequestDTO queryOtherCostRequestDTO) {
        PageHelper.startPage(queryOtherCostRequestDTO.getCurrentPage(),queryOtherCostRequestDTO.getPageSize());
        Example queryCostExample = new Example(SupplyCostDO.class);
        Example.Criteria queryCostCriteria = queryCostExample.createCriteria();
        //如果不传入，表示查全部，如果选了启用和不启用传入-1
        queryCostCriteria.andEqualTo("supplyId",queryOtherCostRequestDTO.getSupplyId());
        if (null != queryOtherCostRequestDTO.getIsActive() && -1 != queryOtherCostRequestDTO.getIsActive()){
            queryCostCriteria.andEqualTo("isActive",queryOtherCostRequestDTO.getIsActive());
        }
        if (null != queryOtherCostRequestDTO.getCreateTimeBegin() && null != queryOtherCostRequestDTO.getCreateTimeEnd()){
            queryCostCriteria.andBetween("createTime",queryOtherCostRequestDTO.getCreateTimeBegin(),queryOtherCostRequestDTO.getCreateTimeEnd());
        }

        List<SupplyCostDO> supplyCostDOList = supplyCostMapper.selectByExample(queryCostExample);
        PageInfo<SupplyCostDO> pageInfo = new PageInfo<SupplyCostDO>(supplyCostDOList);

        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setItemList(PropertyCopyUtil.transferArray(supplyCostDOList,SupplyOtherCostDTO.class));
        paginationSupportDTO.setPageSize(pageInfo.getPageSize());
        paginationSupportDTO.setTotalCount(pageInfo.getTotal());
        paginationSupportDTO.setTotalPage(pageInfo.getPages());
        paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
        return paginationSupportDTO;
    }

    @Override
    public ResponseDTO<SupplyOtherCostDTO> queryOtherCostById(QueryOtherCostRequestDTO queryOtherCostRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        if (null == queryOtherCostRequestDTO.getId()){
            responseDTO = new ResponseDTO(ErrorCodeEnum.INVALID_INPUTPARAM);
            log.error("查询其他成本详情失败,ID为空：{}",JSON.toJSONString(queryOtherCostRequestDTO));
            return responseDTO;
        }

        SupplyCostDO querySupplyCostDO = new SupplyCostDO();
        querySupplyCostDO.setId(queryOtherCostRequestDTO.getId());
        SupplyCostDO supplyCostDO = supplyCostMapper.selectByPrimaryKey(querySupplyCostDO);
        SupplyOtherCostDTO supplyOtherCostDTO = PropertyCopyUtil.transfer(supplyCostDO,SupplyOtherCostDTO.class);
        responseDTO.setModel(supplyOtherCostDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO setOtherCostIsActive(SupplyOtherCostDTO supplyOtherCostDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        if (null == supplyOtherCostDTO.getId() || null == supplyOtherCostDTO.getIsActive()){
            log.error("设置是否启用失败，参数为空：{}",JSON.toJSONString(supplyOtherCostDTO));
            responseDTO.setErrorCode(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }
        SupplyCostDO updateCostDO = new SupplyCostDO();
        updateCostDO.setId(supplyOtherCostDTO.getId());
        updateCostDO.setIsActive(supplyOtherCostDTO.getIsActive());
        supplyCostMapper.updateByPrimaryKeySelective(updateCostDO);

        responseDTO.setModel(1);
        return responseDTO;
    }

    @Override
    public List<SupplyOtherCostDTO> queryOtherCost(Date date) {
        Example queryCostExample = new Example(SupplyCostDO.class);
        Example.Criteria queryCostCriteria = queryCostExample.createCriteria();
        queryCostCriteria.andGreaterThanOrEqualTo("beginUseDate",date);
        queryCostCriteria.andLessThanOrEqualTo("endUseDate",date);
        List<SupplyCostDO> supplyCostDOList = supplyCostMapper.selectByExample(queryCostExample);
        List<SupplyOtherCostDTO> supplyOtherCostDTOList = PropertyCopyUtil.transferArray(supplyCostDOList,SupplyOtherCostDTO.class);
        return supplyOtherCostDTOList;
    }
}
