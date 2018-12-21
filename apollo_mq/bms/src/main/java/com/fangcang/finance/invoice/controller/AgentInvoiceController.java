package com.fangcang.finance.invoice.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.invoice.request.AddInvoiceItemDTO;
import com.fangcang.finance.invoice.request.CreateInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceOrderDTO;
import com.fangcang.finance.invoice.service.AgentInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@Slf4j
@RequestMapping(("/finance/invoice"))
public class AgentInvoiceController extends BaseController{

    @Autowired
    private AgentInvoiceService agentInvoiceService;

    /**
     * 创建发票
     */
    @RequestMapping(value = "/createAgentInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentInvoice(@RequestBody CreateInvoiceDTO request){
        log.info("createAgentInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            request.setOperator(super.getFullName());
            return agentInvoiceService.createAgentInvoice(request);
        }catch (Exception e){
            log.error("agentInvoiceService.createAgentInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新发票
     */
    @RequestMapping(value = "/updateAgentInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateAgentInvoice(@RequestBody UpdateInvoiceDTO request){
        log.info("updateAgentInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            return agentInvoiceService.updateAgentInvoice(request);
        }catch (Exception e){
            log.error("agentInvoiceService.updateAgentInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除发票
     */
    @RequestMapping(value = "/deleteAgentInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentInvoice(@RequestBody DeleteInvoiceDTO request){
        log.info("deleteAgentInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            return agentInvoiceService.deleteAgentInvoice(request);
        }catch (Exception e){
            log.error("agentInvoiceService.deleteAgentInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 清除无效发票
     */
    @RequestMapping(value = "/clearInvalidAgentInvoice", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO clearInvalidAgentInvoice(){
        ResponseDTO responseDTO=null;
        try{
            QueryInvoiceDTO queryInvoiceDTO=new QueryInvoiceDTO();
            queryInvoiceDTO.setIsInvalid(0);
            queryInvoiceDTO.setCurrentPage(1);
            queryInvoiceDTO.setPageSize(999999999);
            PaginationSupportDTO<InvoiceDTO> paginationSupportDTO=agentInvoiceService.queryAgentInvoice(queryInvoiceDTO);
            for (InvoiceDTO invoiceDTO:paginationSupportDTO.getItemList()){
                DeleteInvoiceDTO deleteInvoiceDTO=new DeleteInvoiceDTO();
                deleteInvoiceDTO.setInvoiceId(invoiceDTO.getId());
                agentInvoiceService.deleteAgentInvoice(deleteInvoiceDTO);
            }
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        }catch (Exception e){
            log.error("agentInvoiceService.clearInvalidAgentInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 添加发票明细
     */
    @RequestMapping(value = "/addAgentInvoiceItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addAgentInvoiceItem(@RequestBody AddInvoiceItemDTO request){
        log.info("addAgentInvoiceItem param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            return agentInvoiceService.addAgentInvoiceItem(request);
        }catch (Exception e){
            log.error("agentInvoiceService.addAgentInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除发票明细
     */
    @RequestMapping(value = "/deleteAgentInvoiceItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentInvoiceItem(@RequestBody DeleteInvoiceItemDTO request){
        log.info("deleteAgentInvoiceItem param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            return agentInvoiceService.deleteAgentInvoiceItem(request);
        }catch (Exception e){
            log.error("agentInvoiceService.deleteAgentInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询发票
     */
    @RequestMapping(value = "/queryAgentInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentInvoice(@RequestBody QueryInvoiceDTO request){
        log.info("queryAgentInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<InvoiceDTO> paginationSupportDTO=agentInvoiceService.queryAgentInvoice(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentInvoiceService.queryAgentInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出发票
     */
    @RequestMapping(value = "/exportAgentInvoice")
    public void exportAgentInvoice(QueryInvoiceDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportAgentInvoice param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<InvoiceDTO> paginationSupportDTO=agentInvoiceService.queryAgentInvoice(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("分销商发票","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),InvoiceDTO.class,paginationSupportDTO.getItemList());
    }

    /**
     * 查询发票明细
     */
    @RequestMapping(value = "/queryAgentInvoiceItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentInvoiceItem(@RequestBody QueryInvoiceItemDTO request){
        log.info("queryAgentInvoiceItem param:"+request);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO<InvoiceItemDTO> paginationSupportDTO=agentInvoiceService.queryAgentInvoiceItem(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentInvoiceService.queryAgentInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询未开发票的订单
     */
    @RequestMapping(value = "/queryUnInvoiceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryUnInvoiceOrder(@RequestBody QueryUnInvoiceOrderDTO request){
        log.info("queryUnInvoiceOrder param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<UnInvoiceOrderDTO> paginationSupportDTO=agentInvoiceService.queryUnInvoiceOrder(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentInvoiceService.queryUnInvoiceOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询未开发票的账单
     */
    @RequestMapping(value = "/queryUnInvoiceAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryUnInvoiceAgentBill(@RequestBody QueryUnInvoiceBillDTO request){
        log.info("queryUnInvoiceAgentBill param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<UnInvoiceBillDTO> paginationSupportDTO=agentInvoiceService.queryUnInvoiceAgentBill(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentInvoiceService.queryUnInvoiceAgentBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
