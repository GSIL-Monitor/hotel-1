package com.fangcang.supplier;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.merchant.response.AllRoleResponseDTO;
import com.fangcang.supplier.dto.FrequentSupplyDTO;
import com.fangcang.supplier.dto.SupplyBankCardDTO;
import com.fangcang.supplier.dto.SupplyConfirmTypeDTO;
import com.fangcang.supplier.dto.SupplyInfoDTO;
import com.fangcang.supplier.dto.SupplyVisitInfoDTO;
import com.fangcang.supplier.request.AddSupplyContractFileRequestDTO;
import com.fangcang.supplier.request.AddSupplyRequestDTO;
import com.fangcang.supplier.request.AddVisitRequestDTO;
import com.fangcang.supplier.request.CommonSupplyListResquestDTO;
import com.fangcang.supplier.request.DeleteConfirmTypeRequestDTO;
import com.fangcang.supplier.request.DeleteSupplyBanCardRequestDTO;
import com.fangcang.supplier.request.DeleteSupplyContractFileRequestDTO;
import com.fangcang.supplier.request.GetSupplyOperLogRequestDTO;
import com.fangcang.supplier.request.GetSupplyVisitListQueryDTO;
import com.fangcang.supplier.request.QueryConfirmTypeDTO;
import com.fangcang.supplier.request.QuerySupplyCashItemDTO;
import com.fangcang.supplier.request.QuerySupplyDepositItemDTO;
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
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.response.SingleUserResponseDTO;
import com.fangcang.supplier.response.SupplyCashItemDTO;
import com.fangcang.supplier.response.SupplyDepositItemDTO;
import com.fangcang.supplier.response.SupplyListResponseDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/28 09:14
 * @Description: 供应商相关Controller
 */
@RestController
@RequestMapping("/supply")
@Slf4j
public class SupplierController extends BaseController {

    @Autowired
    private SupplyService supplyService;

    /**
     * 保存供应商（添加/编辑时使用）
     *
     * @param addSupplyRequestDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddSupplyResponseDTO> saveSupply(@RequestBody AddSupplyRequestDTO addSupplyRequestDTO) {
        log.info("保存供应商请求参数：{}",JSON.toJSONString(addSupplyRequestDTO));
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addSupplyRequestDTO.setCreator(this.getCacheUser().getUsername());
            addSupplyRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            addSupplyRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = supplyService.saveSupply(addSupplyRequestDTO);
        } catch (Exception e) {
            log.error("保存供应商异常：{}"+JSON.toJSONString(addSupplyRequestDTO),e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 查询供应商列表
     *
     * @param supplyListQueryRequestDTO
     * @return
     */
    @RequestMapping(value = "/getListForPage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<SupplyListResponseDTO>> getListForPage(@RequestBody SupplyListQueryRequestDTO supplyListQueryRequestDTO) {
        log.info("供应商列表页请求参数：{}",JSON.toJSONString(supplyListQueryRequestDTO));
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            supplyListQueryRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = supplyService.getListForPage(supplyListQueryRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        log.info("供应商列表页返回请求参数：{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }

    /**
     * 设置常用供应商1-常用，0-不常用
     *
     * @param supplyInfoDTO
     * @return
     */
    @RequestMapping(value = "/setFrequentlyUse", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setFrequentlyUse(@RequestBody SupplyInfoDTO supplyInfoDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            supplyInfoDTO.setModifier(this.getCacheUser().getUsername());
            responseDTO = supplyService.setFrequentlyUse(supplyInfoDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 设置供应商是否停用
     * 1-启用；0-停用
     *
     * @param supplyInfoDTO
     * @return
     */
    @RequestMapping(value = "/setActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setActive(@RequestBody SupplyInfoDTO supplyInfoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            supplyInfoDTO.setModifier(this.getCacheUser().getUsername());
            responseDTO = supplyService.setActive(supplyInfoDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 查询单个供应商详情
     *
     * @param singleUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/getSupplyById", method = RequestMethod.POST)
    public ResponseDTO<SingleSupplyInfoResponseDTO> getSupplyById(@RequestBody SingleUserRequestDTO singleUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyService.getSupplyById(singleUserRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        log.info("供应商详情返回：{}",JSON.toJSONString(responseDTO.getModel()));
        return responseDTO;

    }

    /**
     * 添加拜访记录
     *
     * @param addVisitRequestDTO
     * @return
     */
    @RequestMapping(value = "/addVisit", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddVisitResponseDTO> addVisit(@RequestBody @Valid AddVisitRequestDTO addVisitRequestDTO) {

        AddVisitResponseDTO addVisitResponseDTO = new AddVisitResponseDTO();
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addVisitRequestDTO.setCreator(this.getCacheUser().getUsername());
            responseDTO = supplyService.addVisit(addVisitRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 常用供应商列表
     * 入参的商家从Session里面取
     *
     * @return
     */
    @RequestMapping(value = "/queryCommonSupplyList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<FrequentSupplyDTO>> queryCommonSupplyList(@RequestBody CommonSupplyListResquestDTO commonSupplyListResquestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            commonSupplyListResquestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = supplyService.queryCommonSupplyList(commonSupplyListResquestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 添加供应商银行卡信息
     *
     * @param supplyBankCardDTO
     * @return
     */
    @RequestMapping(value = "/saveBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddSupplyBankCardResponseDTO> saveBankCard(@RequestBody SupplyBankCardDTO supplyBankCardDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            supplyBankCardDTO.setCreator(this.getCacheUser().getUsername());
            responseDTO = supplyService.saveBankCard(supplyBankCardDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 保存用户（编辑使用）
     *
     * @param singleUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveUser(@RequestBody @Valid SingleUserRequestDTO singleUserRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            singleUserRequestDTO.setCreator(this.getCacheUser().getUsername());
            singleUserRequestDTO.setModifier(this.getCacheUser().getUsername());
            singleUserRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = supplyService.saveUser(singleUserRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 设置供应商用户是否启用
     *
     * @param setSupplyUserActiveRequestDTO
     * @return
     */
    @RequestMapping(value = "/setUserActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setUserActive(@RequestBody @Valid SetSupplyUserActiveRequestDTO setSupplyUserActiveRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            setSupplyUserActiveRequestDTO.setModifier(this.getCacheUser().getUsername());
            responseDTO = supplyService.setUserActive(setSupplyUserActiveRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    /**
     * 合同文件上传
     *
     * @param addSupplyContractFileRequestDTO
     * @return
     */
    @RequestMapping(value = "/uploadContract")
    public ResponseDTO<AddSupplyContractFileResponseDTO> uploadContract(@RequestParam(required = false) MultipartFile file,
                                                                        AddSupplyContractFileRequestDTO addSupplyContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addSupplyContractFileRequestDTO.setCreateTime(new Date());
            addSupplyContractFileRequestDTO.setCreator(this.getCacheUser().getUsername());
            responseDTO = supplyService.uploadContractForSupply(file, addSupplyContractFileRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    /**
	 * 删除合同文件
	 * 
	 * @param deleteSupplyContractFileRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/deleteContract")
	public ResponseDTO deleteContract(@RequestBody DeleteSupplyContractFileRequestDTO deleteSupplyContractFileRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			responseDTO = supplyService.deleteContract(deleteSupplyContractFileRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
	
    /**
     * 获取供应商拜访记录列表
     *
     * @param getSupplyVisitListQueryDTO
     * @return
     */
    @RequestMapping(value = "/getVisitList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<SupplyVisitInfoDTO>> getVisitList(@RequestBody GetSupplyVisitListQueryDTO getSupplyVisitListQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            getSupplyVisitListQueryDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = supplyService.getVisitList(getSupplyVisitListQueryDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 查询用户日志列表
     *
     * @return
     */
    @RequestMapping(value = "/getUserLog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AllRoleResponseDTO> getAllRoleList(@RequestBody GetSupplyOperLogRequestDTO getSupplyOperLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyService.querySupplyOperLogList(getSupplyOperLogRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 供应商下载合同文件
     *
     * @param realPath 合同文件真实路径
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadContract", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@RequestParam(required = true) String realPath,@RequestParam(required = true) Long contractFileId) throws IOException {
        ResponseEntity<byte[]> entity = null;
        if (!"".equals(realPath)) {
            entity = supplyService.getFile(realPath,contractFileId);
        }
        return entity;
    }

    /**
     * 根据bankCardId删除银行卡信息
     *
     * @param supplyBanCardRequestDTO
     * @return
     */
    @RequestMapping(value = "/deleteBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteBankCardById(@RequestBody DeleteSupplyBanCardRequestDTO supplyBanCardRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != supplyBanCardRequestDTO.getBankCardId()) {
            Long bankCardId = supplyBanCardRequestDTO.getBankCardId();
            responseDTO = supplyService.deleteBankCardById(bankCardId);
        } else {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
        }
        return responseDTO;
    }

    /**
     * 查询单个用户
     *
     * @param singleUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SingleUserResponseDTO> getUserInfo(@RequestBody SingleUserRequestDTO singleUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != singleUserRequestDTO.getUserId()) {
            responseDTO = supplyService.getUserInfo(singleUserRequestDTO);
        } else {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
        }
        return responseDTO;
    }

    /**
     * 删除确认方式
     * @param deleteConfirmTypeRequestDTO
     * @return
     */
    @RequestMapping(value = "/deleteConfirmType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteConfirmType(@RequestBody DeleteConfirmTypeRequestDTO deleteConfirmTypeRequestDTO){
        ResponseDTO responseDTO = null;
        try {
            responseDTO = supplyService.deleteConfirmType(deleteConfirmTypeRequestDTO);
        } catch (Exception e) {
            log.error("删除确认方式失败,参数：{}", JSON.toJSONString(deleteConfirmTypeRequestDTO),e);
            responseDTO = new ResponseDTO(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }

    /**
     * 保存确认方式
     * @param saveConfirmTypeRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveConfirmType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveConfirmType(@RequestBody SaveConfirmTypeRequestDTO saveConfirmTypeRequestDTO){

        ResponseDTO responseDTO = null;
        try {
            responseDTO = supplyService.saveConfirmType(saveConfirmTypeRequestDTO);
        } catch (Exception e) {
            log.error("保存确认方式异常：{}",JSON.toJSONString(saveConfirmTypeRequestDTO),e);
            responseDTO = new ResponseDTO(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }

    /**
     * 查询当前确认方式
     * @return
     */
    @RequestMapping(value = "/queryCurrentConfirmType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO queryCurrentConfirmType(@RequestBody QueryConfirmTypeDTO queryConfirmTypeDTO){
        ResponseDTO responseDTO = null;
        try {
            queryConfirmTypeDTO.setWeek(String.valueOf(DateUtil.getWeekOfDate(new Date())));
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            queryConfirmTypeDTO.setTime(hour*100+minute);
            List<SupplyConfirmTypeDTO> list= supplyService.queryCurrentConfirmType(queryConfirmTypeDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(list);
        } catch (Exception e) {
            log.error("查询当前确认方式",JSON.toJSONString(queryConfirmTypeDTO),e);
            responseDTO = new ResponseDTO(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }

    /**
     * 设置合作预警
     * @param setCooperationRequestDTO
     * @return
     */
    @RequestMapping(value = "/setCooperation", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setCooperation(@RequestBody SetCooperationRequestDTO setCooperationRequestDTO){
        log.info("供应商设置合作预警页返回请求参数：{}",JSON.toJSONString(setCooperationRequestDTO));
        ResponseDTO responseDTO = null;
        try {
            responseDTO = supplyService.setCooperation(setCooperationRequestDTO);
        } catch (Exception e) {
            log.error("设置合作预警异常：{}",JSON.toJSONString(setCooperationRequestDTO),e);
            responseDTO = new ResponseDTO(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }
    /**
     * 供应商现金充值
     */
    @RequestMapping(value = "/supplyCashRecharge", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO supplyCashRecharge(@RequestBody SupplyCashRechargeDTO requestDTO) {
        log.info("supplyCashRecharge param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            return supplyService.supplyCashRecharge(requestDTO);
        }catch (Exception e){
            log.error("supplyService.supplyCashRecharge 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 供应商押金充值
     */
    @RequestMapping(value = "/supplyDepositRecharge", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO supplyDepositRecharge(@RequestBody SupplyDepositRechargeDTO requestDTO) {
        log.info("supplyDepositRecharge param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            return supplyService.supplyDepositRecharge(requestDTO);
        }catch (Exception e){
            log.error("supplyService.supplyDepositRecharge 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询供应商现金账户明细
     */
    @RequestMapping(value = "/querySupplyCashItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplyCashItem(@RequestBody QuerySupplyCashItemDTO requestDTO) {
        log.info("querySupplyCashItem param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO<SupplyCashItemDTO> paginationSupportDTO=supplyService.querySupplyCashItem(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyService.querySupplyCashItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出现金账户明细
     */
    @RequestMapping(value = "/exportSupplyCashItem")
    public void exportSupplyCashItem(QuerySupplyCashItemDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportSupplyCashItem param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        PaginationSupportDTO<SupplyCashItemDTO> paginationSupportDTO=supplyService.querySupplyCashItem(requestDTO);

        SingleUserRequestDTO singleUserRequestDTO=new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(Long.valueOf(requestDTO.getSupplyId()));
        ResponseDTO<SingleSupplyInfoResponseDTO> supplyInfoResponseDTO=supplyService.getSupplyById(singleUserRequestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(supplyInfoResponseDTO.getModel().getSupplyName()+"现金账户明细","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),SupplyCashItemDTO.class,paginationSupportDTO.getItemList());
    }

    /**
     * 查询供应商押金账户明细
     */
    @RequestMapping(value = "/querySupplyDepositItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplyDepositItem(@RequestBody QuerySupplyDepositItemDTO requestDTO) {
        log.info("querySupplyDepositItem param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO<SupplyDepositItemDTO> paginationSupportDTO=supplyService.querySupplyDepositItem(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyService.querySupplyDepositItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
