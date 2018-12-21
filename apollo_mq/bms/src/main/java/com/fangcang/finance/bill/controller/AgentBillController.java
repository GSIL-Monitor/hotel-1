package com.fangcang.finance.bill.controller;

import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.agent.response.SingleAgentResponseDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.ExcelHelper;
import com.fangcang.common.util.ReportTemplateConfig;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.bill.request.AddBillOrderDTO;
import com.fangcang.finance.bill.request.AddMatchedOrderDTO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.BillOrderIdDTO;
import com.fangcang.finance.bill.request.CreateBillDTO;
import com.fangcang.finance.bill.request.CreateBillFinanceOrderDTO;
import com.fangcang.finance.bill.request.DeleteBillImportDTO;
import com.fangcang.finance.bill.request.DeleteBillOrderDTO;
import com.fangcang.finance.bill.request.QueryBillDTO;
import com.fangcang.finance.bill.request.QueryBillImportDTO;
import com.fangcang.finance.bill.request.QueryBillOrderDTO;
import com.fangcang.finance.bill.request.UpdateBillNameDTO;
import com.fangcang.finance.bill.response.BillDTO;
import com.fangcang.finance.bill.response.BillImportDTO;
import com.fangcang.finance.bill.response.BillOrderItemDTO;
import com.fangcang.finance.bill.response.BillOrderItemExportDTO;
import com.fangcang.finance.bill.service.AgentBillAutoMatchService;
import com.fangcang.finance.bill.service.AgentBillService;
import com.fangcang.merchant.dto.MerchantDTO;
import com.fangcang.merchant.request.QueryMerchantDTO;
import com.fangcang.merchant.response.UserBankCardResponseDTO;
import com.fangcang.merchant.service.MerchantService;
import com.fangcang.merchant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(("/finance/bill"))
public class AgentBillController extends BaseController {

    @Autowired
    private AgentBillAutoMatchService agentBillAutoMatchService;

    @Autowired
    private AgentBillService agentBillService;

    @Autowired
    private ReportTemplateConfig reportTemplateConfig;

    @Autowired
    private AgentService agentService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    /**
     * 查询分销商账单
     */
    @RequestMapping(value = "/queryAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBill(@RequestBody QueryBillDTO requestDTO) {
        log.info("queryAgentBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=agentBillService.queryAgentBill(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentBillService.queryAgentBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询分销商账单对应的财务工单
     */
    @RequestMapping(value = "/queryAgentBillFinanceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillFinanceOrder(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 查询分销商账单对应的订单
     */
    @RequestMapping(value = "/queryAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillOrder(@RequestBody QueryBillOrderDTO requestDTO) {
        log.info("queryAgentBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO paginationSupportDTO=agentBillService.queryAgentBillOrder(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentBillService.queryAgentBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询分销商账单对应的订单明细
     */
    @RequestMapping(value = "/queryAgentBillOrderItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillOrderItem(@RequestBody BillOrderIdDTO requestDTO) {
        log.info("queryAgentBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            List<BillOrderItemDTO> list=agentBillService.queryAgentBillOrderItem(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(list);
        }catch (Exception e){
            log.error("agentBillService.queryAgentBillOrderItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 创建账单
     */
    @RequestMapping(value = "/createAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentBill(@RequestBody CreateBillDTO requestDTO) {
        log.info("createAgentBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.createAgentBill(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.createAgentBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新账单名称
     */
    @RequestMapping(value = "/updateAgentBillName", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateAgentBillName(@RequestBody UpdateBillNameDTO requestDTO) {
        log.info("updateAgentBillName param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.updateAgentBillName(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.updateAgentBillName 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 上传订单明细
     */
    @RequestMapping(value = "/uploadAgentBillOrder")
    public ResponseDTO uploadAgentBillOrder(@RequestParam(required = false) MultipartFile file) {
        ResponseDTO responseDTO=null;
        try{
            responseDTO=agentBillAutoMatchService.autoMatchCustomerOrder(file.getInputStream(),super.getFullName());
        }catch (Exception e){
            log.error("agentBillAutoMatchService.autoMatchCustomerOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 自动对账结果查询
     */
    @RequestMapping(value = "/queryAgentBillImport", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillImport(@RequestBody QueryBillImportDTO requestDTO) {
        log.info("queryAgentBillImport param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO<BillImportDTO> paginationSupportDTO=agentBillAutoMatchService.queryAgentBillImport(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentBillAutoMatchService.queryAgentBillImport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 将已匹配订单添加到账单
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/addMatchedOrderToBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addMatchedOrderToBill(@RequestBody AddMatchedOrderDTO requestDTO){
        log.info("addMatchedOrderToBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillAutoMatchService.addMatchedOrderToBill(requestDTO);
        }catch (Exception e){
            log.error("agentBillAutoMatchService.addMatchedOrderToBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出自动匹配的订单
     */
    @RequestMapping(value = "/exportAgentBillImport")
    public void exportAgentBillImport(QueryBillImportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportAgentBillImport param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMatchStatus(2);
        PaginationSupportDTO<BillImportDTO> paginationSupportDTO=agentBillAutoMatchService.queryAgentBillImport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("自动对账异常单","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        ExcelUtils.exportExcel(response.getOutputStream(),BillImportDTO.class, paginationSupportDTO.getItemList());
    }

    /**
     * 删除导入订单
     */
    @RequestMapping(value = "/deleteAgentBillImport", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentBillImport(@RequestBody DeleteBillImportDTO request){
        log.info("deleteAgentBillImport param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            responseDTO=agentBillAutoMatchService.deleteAgentBillImport(request);
        }catch (Exception e){
            log.error("agentBillAutoMatchService.deleteAgentBillImport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 添加账单明细
     */
    @RequestMapping(value = "/addAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addAgentBillOrder(@RequestBody AddBillOrderDTO requestDTO) {
        log.info("addAgentBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            requestDTO.setMerchantCode(super.getMerchantCode());
            responseDTO=agentBillService.addAgentBillOrder(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.addAgentBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除账单明细
     */
    @RequestMapping(value = "/deleteAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentBillOrder(@RequestBody DeleteBillOrderDTO requestDTO) {
        log.info("deleteAgentBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.deleteAgentBillOrder(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.deleteAgentBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 清空账单明细
     */
    @RequestMapping(value = "/clearAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO clearAgentBillOrder(@RequestBody BillIdDTO requestDTO) {
        log.info("clearAgentBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.clearAgentBillOrder(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.clearAgentBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出账单
     */
    @RequestMapping(value = "/exportAgentBill")
    public void exportAgentBill(BillIdDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportAgentBill param:"+requestDTO);
        QueryBillDTO queryBillDTO=new QueryBillDTO();
        queryBillDTO.setBillId(requestDTO.getBillId());
        queryBillDTO.setMerchantCode(super.getMerchantCode());
        PaginationSupportDTO<BillDTO> billPaginationSupportDTO=agentBillService.queryAgentBill(queryBillDTO);
        BillDTO billDTO=billPaginationSupportDTO.getItemList().get(0);
        List<BillOrderItemExportDTO> billOrderItemExportDTOList=agentBillService.exportBillOrderItem(requestDTO);

        SingleAgentRequestDTO singleAgentRequestDTO=new SingleAgentRequestDTO();
        singleAgentRequestDTO.setAgentCode(billDTO.getOrgCode());
        ResponseDTO<SingleAgentResponseDTO> agentResponseDTO=agentService.getAgentById(singleAgentRequestDTO);

        QueryMerchantDTO queryMerchantDTO=new QueryMerchantDTO();
        queryMerchantDTO.setMerchantCode(super.getMerchantCode());
        MerchantDTO merchantDTO=merchantService.queryMerchant(queryMerchantDTO);

        ResponseDTO<UserBankCardResponseDTO> userBankCardResponseDTO=userService.queryUserBankCardList(merchantDTO.getMerchantId());

        Map data = new HashMap();
        data.put("bill", billDTO);
        data.put("billItemList", billOrderItemExportDTOList);
        data.put("merchant", merchantDTO);
        data.put("bankCardList",userBankCardResponseDTO.getModel().getBankCardList());
        data.put("agent",agentResponseDTO.getModel());
        data.put("balanceMethod", BalanceMethodEnum.getValueByKey(agentResponseDTO.getModel().getBillingMethod()));
        data.put("sender",super.getCacheUser().getRealName());

        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getAgentBillReportUrl());
            //告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("分销商账单.xls","UTF-8"));

            output = response.getOutputStream();
            bis = new BufferedInputStream(byteArrayInputStream);
            bos = new BufferedOutputStream(output);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("exportAgentBill has error",e);
        }finally {
            if (null != bos) {
                bos.close();
            }
            if (null != bis) {
                bis.close();
            }
            if (null != output) {
                output.close();
            }
        }
    }

    /**
     * 确认账单
     */
    @RequestMapping(value = "/confirmAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO confirmAgentBill(@RequestBody BillIdDTO requestDTO) {
        log.info("confirmAgentBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.confirmAgentBill(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.confirmAgentBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新账单
     */
    @RequestMapping(value = "/updateAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateAgentBill(@RequestBody BillIdDTO requestDTO) {
        log.info("updateAgentBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.updateAgentBill(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.updateAgentBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除账单
     */
    @RequestMapping(value = "/deleteAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentBill(@RequestBody BillIdDTO requestDTO) {
        log.info("deleteAgentBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.deleteAgentBill(requestDTO);
        }catch (Exception e){
            log.error("agentBillService.deleteAgentBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 账单通知财务收款
     */
    @RequestMapping(value = "/createAgentBillFinanceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentBillFinanceOrder(@RequestBody CreateBillFinanceOrderDTO requestDTO) {
        log.info("createAgentBillFinanceOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.createAgentBillFinanceOrder(requestDTO);
        }catch (ServiceException e){
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, e.getMessage());
        }catch (Exception e){
            log.error("agentBillService.createAgentBillFinanceOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 账单财务直接收款
     */
    @RequestMapping(value = "/createAgentBillFinanceOrderWithConfirm", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentBillFinanceOrderWithConfirm(@RequestBody CreateBillFinanceOrderDTO requestDTO) {
        log.info("createAgentBillFinanceOrderWithConfirm param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=agentBillService.createAgentBillFinanceOrderWithConfirm(requestDTO);
        }catch (ServiceException e){
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, e.getMessage());
        }catch (Exception e){
            log.error("agentBillService.createAgentBillFinanceOrderWithConfirm 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
