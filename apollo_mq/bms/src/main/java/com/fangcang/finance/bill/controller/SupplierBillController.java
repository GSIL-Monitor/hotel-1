package com.fangcang.finance.bill.controller;

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
import com.fangcang.finance.bill.request.QueryBillOrderDTO;
import com.fangcang.finance.bill.request.QuerySupplyBillImportDTO;
import com.fangcang.finance.bill.request.UpdateBillNameDTO;
import com.fangcang.finance.bill.response.BillDTO;
import com.fangcang.finance.bill.response.BillOrderItemDTO;
import com.fangcang.finance.bill.response.BillOrderItemExportDTO;
import com.fangcang.finance.bill.response.SupplyBillImportDTO;
import com.fangcang.finance.bill.service.SupplyBillAutoMatchService;
import com.fangcang.finance.bill.service.SupplyBillService;
import com.fangcang.merchant.dto.MerchantDTO;
import com.fangcang.merchant.request.QueryMerchantDTO;
import com.fangcang.merchant.response.UserBankCardResponseDTO;
import com.fangcang.merchant.service.MerchantService;
import com.fangcang.merchant.service.UserService;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.service.SupplyService;
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
public class SupplierBillController extends BaseController {

    @Autowired
    private SupplyBillAutoMatchService supplyBillAutoMatchService;

    @Autowired
    private SupplyBillService supplyBillService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportTemplateConfig reportTemplateConfig;

    /**
     * 查询供应商账单
     */
    @RequestMapping(value = "/querySupplierBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplierBill(@RequestBody QueryBillDTO requestDTO) {
        log.info("querySupplierBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=supplyBillService.querySupplyBill(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyBillService.querySupplyBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询供应商账单对应的财务工单
     */
    @RequestMapping(value = "/querySupplierBillFinanceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplierBillFinanceOrder(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 查询供应商账单对应的订单
     */
    @RequestMapping(value = "/querySupplierBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplierBillOrder(@RequestBody QueryBillOrderDTO requestDTO) {
        log.info("querySupplierBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO paginationSupportDTO=supplyBillService.querySupplyBillOrder(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyBillService.querySupplyBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询供应商账单对应的订单明细
     */
    @RequestMapping(value = "/querySupplierBillOrderItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplierBillOrderItem(@RequestBody BillOrderIdDTO requestDTO) {
        log.info("querySupplierBillOrderItem param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            List<BillOrderItemDTO> list=supplyBillService.querySupplyBillOrderItem(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(list);
        }catch (Exception e){
            log.error("supplyBillService.querySupplyBillOrderItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 创建账单
     */
    @RequestMapping(value = "/createSupplierBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createSupplierBill(@RequestBody CreateBillDTO requestDTO) {
        log.info("createSupplierBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.createSupplyBill(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.createSupplyBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新账单名称
     */
    @RequestMapping(value = "/updateSupplierBillName", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateSupplierBillName(@RequestBody UpdateBillNameDTO requestDTO) {
        log.info("updateSupplierBillName param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.updateSupplyBillName(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.updateSupplyBillName 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 上传订单明细
     */
    @RequestMapping(value = "/uploadSupplierBillOrder")
    public ResponseDTO uploadSupplierBillOrder(@RequestParam(required = false) MultipartFile file) {
        ResponseDTO responseDTO=null;
        try{
            responseDTO=supplyBillAutoMatchService.autoMatchCustomerOrder(file.getInputStream(),super.getFullName());
        }catch (Exception e){
            log.error("supplyBillAutoMatchService.autoMatchCustomerOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 自动对账结果查询
     */
    @RequestMapping(value = "/querySupplierBillImport", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplierBillImport(@RequestBody QuerySupplyBillImportDTO requestDTO) {
        log.info("querySupplierBillImport param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            PaginationSupportDTO<SupplyBillImportDTO> paginationSupportDTO=supplyBillAutoMatchService.querySupplyBillImport(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("supplyBillAutoMatchService.querySupplyBillImport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 将已匹配订单添加到账单
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/addMatchedSupplierOrderToBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addMatchedSupplierOrderToBill(@RequestBody AddMatchedOrderDTO requestDTO){
        log.info("addMatchedSupplierOrderToBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillAutoMatchService.addMatchedOrderToBill(requestDTO);
        }catch (Exception e){
            log.error("supplyBillAutoMatchService.addMatchedOrderToBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出自动匹配的订单
     */
    @RequestMapping(value = "/exportSupplierBillImport")
    public void exportSupplierBillImport(QuerySupplyBillImportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportSupplierBillImport param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMatchStatus(2);
        PaginationSupportDTO<SupplyBillImportDTO> paginationSupportDTO=supplyBillAutoMatchService.querySupplyBillImport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("自动对账异常单","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        ExcelUtils.exportExcel(response.getOutputStream(),SupplyBillImportDTO.class, paginationSupportDTO.getItemList());
    }

    /**
     * 删除导入订单
     */
    @RequestMapping(value = "/deleteSupplierBillImport", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteSupplierBillImport(@RequestBody DeleteBillImportDTO request){
        log.info("deleteSupplierBillImport param:"+request);
        ResponseDTO responseDTO=null;
        try{
            request.setOperator(super.getFullName());
            responseDTO=supplyBillAutoMatchService.deleteSupplyBillImport(request);
        }catch (Exception e){
            log.error("supplyBillAutoMatchService.deleteSupplyBillImport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 添加账单明细
     */
    @RequestMapping(value = "/addSupplierBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addSupplierBillOrder(@RequestBody AddBillOrderDTO requestDTO) {
        log.info("addSupplierBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            requestDTO.setMerchantCode(super.getMerchantCode());
            responseDTO=supplyBillService.addSupplyBillOrder(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.addSupplyBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除账单明细
     */
    @RequestMapping(value = "/deleteSupplierBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteSupplierBillOrder(@RequestBody DeleteBillOrderDTO requestDTO) {
        log.info("deleteSupplierBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.deleteSupplyBillOrder(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.deleteSupplyBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 清空账单明细
     */
    @RequestMapping(value = "/clearSupplierBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO clearSupplierBillOrder(@RequestBody BillIdDTO requestDTO) {
        log.info("clearSupplierBillOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.clearSupplyBillOrder(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.clearSupplyBillOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出账单
     */
    @RequestMapping(value = "/exportSupplierBill")
    public void exportSupplierBill(BillIdDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportSupplierBill param:"+requestDTO);
        QueryBillDTO queryBillDTO=new QueryBillDTO();
        queryBillDTO.setBillId(requestDTO.getBillId());
        queryBillDTO.setMerchantCode(super.getMerchantCode());
        PaginationSupportDTO<BillDTO> billPaginationSupportDTO=supplyBillService.querySupplyBill(queryBillDTO);
        BillDTO billDTO=billPaginationSupportDTO.getItemList().get(0);
        List<BillOrderItemExportDTO> billOrderItemExportDTOList=supplyBillService.exportBillOrderItem(requestDTO);

        SingleUserRequestDTO singleUserRequestDTO=new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyCode(billDTO.getOrgCode());
        ResponseDTO<SingleSupplyInfoResponseDTO> supplyInfoResponseDTO=supplyService.getSupplyById(singleUserRequestDTO);

        QueryMerchantDTO queryMerchantDTO=new QueryMerchantDTO();
        queryMerchantDTO.setMerchantCode(super.getMerchantCode());
        MerchantDTO merchantDTO=merchantService.queryMerchant(queryMerchantDTO);

        ResponseDTO<UserBankCardResponseDTO> userBankCardResponseDTO=userService.queryUserBankCardList(merchantDTO.getMerchantId());

        Map data = new HashMap();
        data.put("bill", billDTO);
        data.put("billItemList", billOrderItemExportDTOList);
        data.put("merchant", merchantDTO);
        data.put("bankCardList",userBankCardResponseDTO.getModel().getBankCardList());
        data.put("supply",supplyInfoResponseDTO.getModel());
        data.put("balanceMethod", BalanceMethodEnum.getValueByKey(supplyInfoResponseDTO.getModel().getBillingMethod()));
        data.put("sender",super.getCacheUser().getRealName());

        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getSupplyBillReportUrl());
            //告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("供应商账单.xls","UTF-8"));

            output = response.getOutputStream();
            bis = new BufferedInputStream(byteArrayInputStream);
            bos = new BufferedOutputStream(output);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("exportSupplyBill has error",e);
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
    @RequestMapping(value = "/confirmSupplierBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO confirmSupplierBill(@RequestBody BillIdDTO requestDTO) {
        log.info("confirmSupplierBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.confirmSupplyBill(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.confirmSupplyBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 更新账单
     */
    @RequestMapping(value = "/updateSupplierBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateSupplierBill(@RequestBody BillIdDTO requestDTO) {
        log.info("updateSupplierBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.updateSupplyBill(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.updateSupplyBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除账单
     */
    @RequestMapping(value = "/deleteSupplierBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteSupplierBill(@RequestBody BillIdDTO requestDTO) {
        log.info("deleteSupplierBill param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.deleteSupplyBill(requestDTO);
        }catch (Exception e){
            log.error("supplyBillService.deleteSupplyBill 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 账单通知财务收款
     */
    @RequestMapping(value = "/createSupplierBillFinanceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createSupplierBillFinanceOrder(@RequestBody CreateBillFinanceOrderDTO requestDTO) {
        log.info("createSupplierBillFinanceOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.createSupplyBillFinanceOrder(requestDTO);
        }catch (ServiceException e){
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, e.getMessage());
        }catch (Exception e){
            log.error("supplyBillService.createSupplyBillFinanceOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 账单财务直接收款
     */
    @RequestMapping(value = "/createSupplierBillFinanceOrderWithConfirm", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createSupplierBillFinanceOrderWithConfirm(@RequestBody CreateBillFinanceOrderDTO requestDTO) {
        log.info("createSupplierBillFinanceOrderWithConfirm param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setOperator(super.getFullName());
            responseDTO=supplyBillService.createSupplyBillFinanceOrderWithConfirm(requestDTO);
        }catch (ServiceException e){
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, e.getMessage());
        }catch (Exception e){
            log.error("supplyBillService.createSupplyBillFinanceOrderWithConfirm 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
