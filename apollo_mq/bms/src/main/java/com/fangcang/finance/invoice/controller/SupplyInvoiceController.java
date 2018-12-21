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
import com.fangcang.finance.invoice.request.QueryInvoiceSummaryDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.InvoiceSummaryDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceOrderDTO;
import com.fangcang.finance.invoice.response.UnInvoiceSupplyOrderDTO;
import com.fangcang.finance.invoice.service.SupplyInvoiceService;
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
import java.net.URLEncoder;

@RestController
@Slf4j
@RequestMapping(("/finance/invoice"))
public class SupplyInvoiceController extends BaseController{

    @Autowired
    private SupplyInvoiceService supplyInvoiceService;

    /**
     * 创建发票
     */
    @RequestMapping(value = "/createSupplyInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createSupplyInvoice(@RequestBody CreateInvoiceDTO request){
        log.info("createSupplyInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            request.setOperator(super.getFullName());
            return supplyInvoiceService.createSupplyInvoice(request);
        }catch (Exception e){
            log.error("supplyInvoiceService.createSupplyInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新发票
     */
    @RequestMapping(value = "/updateSupplyInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateSupplyInvoice(@RequestBody UpdateInvoiceDTO request){
        log.info("updateSupplyInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            return supplyInvoiceService.updateSupplyInvoice(request);
        }catch (Exception e){
            log.error("supplyInvoiceService.updateSupplyInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除发票
     */
    @RequestMapping(value = "/deleteSupplyInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteSupplyInvoice(@RequestBody DeleteInvoiceDTO request){
        log.info("deleteSupplyInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            return supplyInvoiceService.deleteSupplyInvoice(request);
        }catch (Exception e){
            log.error("supplyInvoiceService.deleteSupplyInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 清除无效发票
     */
    @RequestMapping(value = "/clearInvalidSupplyInvoice", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO clearInvalidSupplyInvoice(){
        ResponseDTO responseDTO=null;
        try{
            QueryInvoiceDTO queryInvoiceDTO=new QueryInvoiceDTO();
            queryInvoiceDTO.setIsInvalid(0);
            queryInvoiceDTO.setCurrentPage(1);
            queryInvoiceDTO.setPageSize(999999999);
            PaginationSupportDTO<InvoiceDTO> paginationSupportDTO=supplyInvoiceService.querySupplyInvoice(queryInvoiceDTO);
            for (InvoiceDTO invoiceDTO:paginationSupportDTO.getItemList()){
                DeleteInvoiceDTO deleteInvoiceDTO=new DeleteInvoiceDTO();
                deleteInvoiceDTO.setInvoiceId(invoiceDTO.getId());
                supplyInvoiceService.deleteSupplyInvoice(deleteInvoiceDTO);
            }
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        }catch (Exception e){
            log.error("supplyInvoiceService.clearInvalidSupplyInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 添加发票明细
     */
    @RequestMapping(value = "/addSupplyInvoiceItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addSupplyInvoiceItem(@RequestBody AddInvoiceItemDTO request){
        log.info("addSupplyInvoiceItem param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            return supplyInvoiceService.addSupplyInvoiceItem(request);
        }catch (Exception e){
            log.error("supplyInvoiceService.addSupplyInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除发票明细
     */
    @RequestMapping(value = "/deleteSupplyInvoiceItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteSupplyInvoiceItem(@RequestBody DeleteInvoiceItemDTO request){
        log.info("deleteSupplyInvoiceItem param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            return supplyInvoiceService.deleteSupplyInvoiceItem(request);
        }catch (Exception e){
            log.error("supplyInvoiceService.deleteSupplyInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询发票
     */
    @RequestMapping(value = "/querySupplyInvoice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplyInvoice(@RequestBody QueryInvoiceDTO request){
        log.info("querySupplyInvoice param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<InvoiceDTO> paginationSupportDTO=supplyInvoiceService.querySupplyInvoice(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyInvoiceService.querySupplyInvoice 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出发票
     */
    @RequestMapping(value = "/exportSupplyInvoice")
    public void exportSupplyInvoice(QueryInvoiceDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportSupplyInvoice param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<InvoiceDTO> paginationSupportDTO=supplyInvoiceService.querySupplyInvoice(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("供应商发票","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),InvoiceDTO.class,paginationSupportDTO.getItemList());
    }

    /**
     * 查询发票明细
     */
    @RequestMapping(value = "/querySupplyInvoiceItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplyInvoiceItem(@RequestBody QueryInvoiceItemDTO request){
        log.info("querySupplyInvoiceItem param:"+request);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO<InvoiceItemDTO> paginationSupportDTO=supplyInvoiceService.querySupplyInvoiceItem(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyInvoiceService.querySupplyInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询未开发票的订单
     */
    @RequestMapping(value = "/queryUnInvoiceSupplyOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryUnInvoiceSupplyOrder(@RequestBody QueryUnInvoiceOrderDTO request){
        log.info("queryUnInvoiceSupplyOrder param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<UnInvoiceSupplyOrderDTO> paginationSupportDTO=supplyInvoiceService.queryUnInvoiceSupplyOrder(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyInvoiceService.queryUnInvoiceSupplyOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出未开发票的订单
     */
    @RequestMapping(value = "/exportUnInvoiceSupplyOrder")
    public void exportUnInvoiceSupplyOrder(QueryUnInvoiceOrderDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportUnInvoiceSupplyOrder param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<UnInvoiceSupplyOrderDTO> paginationSupportDTO=supplyInvoiceService.queryUnInvoiceSupplyOrder(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("未开发票的供货单","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),UnInvoiceOrderDTO.class,paginationSupportDTO.getItemList());
    }

    /**
     * 查询未开发票的账单
     */
    @RequestMapping(value = "/queryUnInvoiceSupplyBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryUnInvoiceSupplyBill(@RequestBody QueryUnInvoiceBillDTO request){
        log.info("queryUnInvoiceSupplyBill param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<UnInvoiceBillDTO> paginationSupportDTO=supplyInvoiceService.queryUnInvoiceSupplyBill(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyInvoiceService.queryUnInvoiceSupplyBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询发票汇总
     */
    @RequestMapping(value = "/querySupplyInvoiceSummary", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplyInvoiceSummary(@RequestBody QueryInvoiceSummaryDTO request){
        log.info("querySupplyInvoiceSummary param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO<InvoiceSummaryDTO> paginationSupportDTO=supplyInvoiceService.querySupplyInvoiceSummary(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyInvoiceService.querySupplyInvoiceSummary 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 读取上传的excel文件并自动添加到发票明细
     */
    @RequestMapping(value = "/uploadSupplyInvoiceItem")
    public ResponseDTO uploadSupplyInvoiceItem(@RequestParam("file") MultipartFile file, Integer invoiceId){
        ResponseDTO responseDTO=null;
        try{
            return supplyInvoiceService.uploadSupplyInvoiceItem(file.getInputStream(),invoiceId,super.getFullName());
        }catch (Exception e){
            log.error("supplyInvoiceService.uploadSupplyInvoiceItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
