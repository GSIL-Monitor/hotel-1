package com.fangcang.finance.bill.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.request.AddBillOrderDTO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.BillOrderIdDTO;
import com.fangcang.finance.bill.request.CreateBillDTO;
import com.fangcang.finance.bill.request.CreateBillFinanceOrderDTO;
import com.fangcang.finance.bill.request.DeleteBillOrderDTO;
import com.fangcang.finance.bill.request.QueryBillDTO;
import com.fangcang.finance.bill.request.QueryBillImportDTO;
import com.fangcang.finance.bill.request.QueryBillOrderDTO;
import com.fangcang.finance.bill.request.UpdateBillNameDTO;
import com.fangcang.finance.bill.request.UploadBillOrderDTO;
import com.fangcang.finance.bill.response.BillDTO;
import com.fangcang.finance.bill.response.BillFinanceOrderDTO;
import com.fangcang.finance.bill.response.BillImportDTO;
import com.fangcang.finance.bill.response.BillOrderDTO;
import com.fangcang.finance.bill.response.BillOrderItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(("/test/finance/bill"))
public class TestAgentBillController extends BaseController {

    /**
     * 查询分销商账单
     */
    @RequestMapping(value = "/queryAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBill(@RequestBody QueryBillDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BillDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
//            BillDTO billSimpleDTO=new BillDTO(13,"BA3223332","春秋旅游18年06月账单",1,"A321321"+i,"春秋旅游商","2018-5-1","2018-5-31","李小红","2017-03-09 14:50:20",
//                    Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(34223L),null,null)),
//                    Arrays.asList(new BillFinanceOrderDTO(12,"F32123",1,1,"A321321"+i,"春秋旅游商",Arrays.asList("线下支付"),"aaaa",
//                            Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(34223L),null,null)))));
//            list.add(billSimpleDTO);
//            i++;
//            BillDTO billSimpleDTO1=new BillDTO(23,"BA343243","B2B悦享旅行18年06月",3,"A321321"+i,"环球旅游行家","2018-6-1","2018-6-31","李小红","2017-03-09 14:50:20",
//                    Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(453L),null,null)),null);
//            list.add(billSimpleDTO1);
        }
        PaginationSupportDTO<BillDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setTotalCount(30);
        paginationSupportDTO.setTotalPage(2);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(15);
        responseDTO.setModel(paginationSupportDTO);
        paginationSupportDTO.setItemList(list);
        return responseDTO;
    }

    /**
     * 查询分销商账单对应的财务工单
     */
    @RequestMapping(value = "/queryAgentBillFinanceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillFinanceOrder(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BillFinanceOrderDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
            /*BillFinanceOrderDTO billFinanceOrderDTO=new BillFinanceOrderDTO(12,"F32123",1,1,"A321321"+i,"春秋旅游商",Arrays.asList("线下支付"),"aaaa",
                    Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(34223L),null,null)));
            list.add(billFinanceOrderDTO);
            i++;
            BillFinanceOrderDTO billFinanceOrderDTO1=new BillFinanceOrderDTO(12,"F433534",1,2,"A321321"+i,"环球旅游行家",Arrays.asList("线下支付"),"bbbb",
                    Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(453L),null,null)));
            list.add(billFinanceOrderDTO1);*/
        }
        responseDTO.setModel(list);
        return responseDTO;
    }

    /**
     * 查询分销商账单对应的订单
     */
    @RequestMapping(value = "/queryAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillOrder(@RequestBody QueryBillOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BillOrderDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
//            BillOrderDTO billOrderDTO=new BillOrderDTO(32,321,"D323123","深圳喜马拉雅大酒店","大床房","无早限时优惠","李雷、韩梅梅","2016/03/06","2016/03/08",
//                    5,"CNY","BA3223332",BigDecimal.valueOf(288),"2017-03-09 14:50:20",1);
//            list.add(billOrderDTO);
//            i++;
//            BillOrderDTO billOrderDTO1=new BillOrderDTO(34,322,"D767773","桔子酒店精选","大床房","无早","韩梅梅","2016/03/06","2016/03/08",
//                    2,"CNY","BA3276767",BigDecimal.valueOf(460),"2017-03-09 14:50:20",2);
//            list.add(billOrderDTO1);
        }
        PaginationSupportDTO<BillOrderDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setTotalCount(30);
        paginationSupportDTO.setTotalPage(2);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(15);
        responseDTO.setModel(paginationSupportDTO);
        paginationSupportDTO.setItemList(list);
        return responseDTO;
    }

    /**
     * 查询分销商账单对应的订单明细
     */
    @RequestMapping(value = "/queryAgentBillOrderItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillOrderItem(@RequestBody BillOrderIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BillOrderItemDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
            /*BillOrderItemDTO billOrderItemDTO=new BillOrderItemDTO(1,"客房","大床房","无早限时优惠","韩梅梅","2016/03/06","2016/03/08",2,1,"CNY",
                    BigDecimal.valueOf(100),BigDecimal.valueOf(200));
            list.add(billOrderItemDTO);
            i++;
            BillOrderItemDTO billOrderItemDTO1=new BillOrderItemDTO(2,"加床",null,null,null,null,null,3,1,"CNY",
                    BigDecimal.valueOf(10),BigDecimal.valueOf(30));
            list.add(billOrderItemDTO1);*/
        }
        responseDTO.setModel(list);
        return responseDTO;
    }

    /**
     * 创建账单
     */
    @RequestMapping(value = "/createAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentBill(@RequestBody CreateBillDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel("B323213232");
        return responseDTO;
    }

    /**
     * 更新账单名称
     */
    @RequestMapping(value = "/updateAgentBillName", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateAgentBillName(@RequestBody UpdateBillNameDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 上传订单明细
     */
    @RequestMapping(value = "/uploadAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO uploadAgentBillOrder(@RequestBody UploadBillOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 自动对账结果查询
     */
    @RequestMapping(value = "/queryAgentBillImport", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentBillImport(@RequestBody QueryBillImportDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BillImportDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
//            BillImportDTO billImportDTO=new BillImportDTO(32,"B2B","3135452223",BigDecimal.valueOf(288),"CNY",1,"D323123","深圳喜马拉雅大酒店","大床房","无早限时优惠","李雷、韩梅梅","2016/03/06","2016/03/08",
//                    5,3,1,"");
//            list.add(billImportDTO);
//            i++;
//            BillImportDTO billImportDTO1=new BillImportDTO(32,"B2B","3135452223",BigDecimal.valueOf(460),"CNY",2,"D323123","桔子酒店精选","大床房","无早","韩梅梅","2016/03/06","2016/03/08",
//                    5,3,2,"价格不符");
//            list.add(billImportDTO1);
        }
        PaginationSupportDTO<BillImportDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setTotalCount(30);
        paginationSupportDTO.setTotalPage(2);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(15);
        responseDTO.setModel(paginationSupportDTO);
        paginationSupportDTO.setItemList(list);
        return responseDTO;
    }

    /**
     * 添加账单明细
     */
    @RequestMapping(value = "/addAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO addAgentBillOrder(@RequestBody AddBillOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 删除账单明细
     */
    @RequestMapping(value = "/deleteAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentBillOrder(@RequestBody DeleteBillOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 清空账单明细
     */
    @RequestMapping(value = "/clearAgentBillOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO clearAgentBillOrder(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 导出账单
     */
    @RequestMapping(value = "/exportAgentBill")
    public void exportAgentBill(BillIdDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
    }

    /**
     * 确认账单
     */
    @RequestMapping(value = "/confirmAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO confirmAgentBill(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 更新账单
     */
    @RequestMapping(value = "/updateAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updateAgentBill(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 删除账单
     */
    @RequestMapping(value = "/deleteAgentBill", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO deleteAgentBill(@RequestBody BillIdDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 账单通知财务收款
     */
    @RequestMapping(value = "/createAgentBillFinanceOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentBillFinanceOrder(@RequestBody CreateBillFinanceOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * 账单财务直接收款
     */
    @RequestMapping(value = "/createAgentBillFinanceOrderWithConfirm", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO createAgentBillFinanceOrderWithConfirm(@RequestBody CreateBillFinanceOrderDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
