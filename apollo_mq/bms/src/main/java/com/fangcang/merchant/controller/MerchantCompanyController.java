package com.fangcang.merchant.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.merchant.request.AddMerchantCompanyDTO;
import com.fangcang.merchant.request.QueryMerchantCompanyDTO;
import com.fangcang.merchant.request.UpdateMerchantCompanyApplyDTO;
import com.fangcang.merchant.request.UpdateMerchantCompanyDTO;
import com.fangcang.merchant.response.MerchantCompanyDTO;
import com.fangcang.merchant.service.MerchantCompanyService;
import com.fangcang.message.remote.EmailRemote;
import com.fangcang.message.remote.request.email.QueryEmailConfigDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(("/merchant"))
public class MerchantCompanyController extends BaseController {

    @Autowired
    private MerchantCompanyService merchantCompanyService;

    @Autowired
    private EmailRemote emailRemote;

    /**
     * 查询商家公司文件
     */
    @RequestMapping(value = "/queryMerchantCompany", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryMerchantCompany(@RequestBody QueryMerchantCompanyDTO requestDTO) {
        log.info("queryMerchantCompany param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            List<MerchantCompanyDTO> merchantCompanyDTOList=merchantCompanyService.queryMerchantCompany(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(merchantCompanyDTOList);
        }catch (Exception e){
            log.error("merchantCompanyService.queryMerchantCompany 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 添加商家公司文件
     */
    @RequestMapping(value = "/addMerchantCompany", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addMerchantCompany(@RequestBody AddMerchantCompanyDTO requestDTO) {
        log.info("addMerchantCompany param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            requestDTO.setOperator(super.getFullName());
            return merchantCompanyService.addMerchantCompany(requestDTO);
        }catch (Exception e){
            log.error("merchantCompanyService.addMerchantCompany 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新商家公司文件
     */
    @RequestMapping(value = "/updateMerchantCompany", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateMerchantCompany(@RequestBody UpdateMerchantCompanyDTO requestDTO) {
        log.info("updateMerchantCompany param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            return merchantCompanyService.updateMerchantCompany(requestDTO);
        }catch (Exception e){
            log.error("merchantCompanyService.updateMerchantCompany 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 修改适用供应商
     */
    @RequestMapping(value = "/updateMerchantCompanyApply", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateMerchantCompanyApply(@RequestBody UpdateMerchantCompanyApplyDTO requestDTO) {
        log.info("updateMerchantCompanyApply param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            return merchantCompanyService.updateMerchantCompanyApply(requestDTO);
        }catch (Exception e){
            log.error("merchantCompanyService.updateMerchantCompanyApply 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询商家邮件服务器配置
     */
    @RequestMapping(value = "/queryEmailConfig", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryEmailConfig(@RequestBody QueryEmailConfigDTO requestDTO) {
        log.info("queryEmailConfig param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            responseDTO=emailRemote.queryEmailConfig(requestDTO);
        }catch (Exception e){
            log.error("emailRemote.queryEmailConfig 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
