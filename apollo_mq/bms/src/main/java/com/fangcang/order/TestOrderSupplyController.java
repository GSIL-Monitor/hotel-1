package com.fangcang.order;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.QuotaTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.order.dto.OrderCheckDetailDTO;
import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyProductDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.dto.SupplyRequestDTO;
import com.fangcang.order.request.AddAdditionChargeRequestDTO;
import com.fangcang.order.request.AddNoteRequestDTO;
import com.fangcang.order.request.AddProductRequestDTO;
import com.fangcang.order.request.ChangeAdditionChargeRequestDTO;
import com.fangcang.order.request.ChangeProductRequestDTO;
import com.fangcang.order.request.DeleteAdditionChargeRequestDTO;
import com.fangcang.order.request.DeleteAttachRequestDTO;
import com.fangcang.order.request.DeleteProductRequestDTO;
import com.fangcang.order.request.NotifySupplierRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.ProductDetailRequestDTO;
import com.fangcang.order.request.QueryProductListRequestDTO;
import com.fangcang.order.request.QueryProductPriceRequestDTO;
import com.fangcang.order.request.SaveSupplyResultRequestDTO;
import com.fangcang.order.request.SendToSupplierRequestDTO;
import com.fangcang.order.request.SupplyDetailRequestDTO;
import com.fangcang.order.response.PriceDetailResponseDTO;
import com.fangcang.order.response.ProductDetailResponseDTO;
import com.fangcang.order.response.QueryProductListResponseDTO;
import com.fangcang.order.response.QuerySupplyReqResponseDTO;
import com.fangcang.order.response.SupplyDetailResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@RequestMapping("/test/order")
public class TestOrderSupplyController {

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addProduct(@RequestBody @Valid AddProductRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/changeProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeProduct(@RequestBody @Valid ChangeProductRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteProduct(@RequestBody @Valid DeleteProductRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/priceDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PriceDetailResponseDTO> priceDetail(@RequestBody @Valid ProductDetailRequestDTO requestDTO) {
        PriceDetailResponseDTO priceDetail = new PriceDetailResponseDTO();
        List<SupplyProductPriceResponseDTO> dayList = new ArrayList<>();
        SupplyProductPriceResponseDTO price = new SupplyProductPriceResponseDTO();
        price.setBasePrice(new BigDecimal(500));
        price.setSaleDate("2018-05-28");
        price.setSalePrice(new BigDecimal(699));
        price.setRealQuota(1);
        price.setQuotaNum(10);
        dayList.add(price);
        priceDetail.setDayList(dayList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(priceDetail);
        return responseDTO;
    }

    @RequestMapping(value = "/addAdditionCharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addAdditionCharge(@RequestBody @Valid AddAdditionChargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/changeAdditionCharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeAdditionCharge(@RequestBody @Valid ChangeAdditionChargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/deleteAdditionCharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteAdditionCharge(@RequestBody @Valid DeleteAdditionChargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/sendToSupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO sendToSupplier(@RequestBody @Valid SendToSupplierRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/saveSupplyResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO saveSupplyResult(@RequestBody @Valid SaveSupplyResultRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/querySupplyRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<QuerySupplyReqResponseDTO> querySupplyRequest(@RequestBody @Valid SupplyDetailRequestDTO requestDTO) {

        List<SupplyRequestDTO> supplyRequestList = new ArrayList<>();
        SupplyRequestDTO supplyRequestDTO = new SupplyRequestDTO();
        supplyRequestDTO.setSupplyType(1);
        supplyRequestDTO.setThisConfirmType(1);
        supplyRequestDTO.setThisConfirmNo("123456");
        supplyRequestDTO.setThisConfirmName("小李");
        supplyRequestDTO.setNote("请尽快入住");
        supplyRequestDTO.setCreator("张三");
        supplyRequestDTO.setCreateTime("2018-05-22 12:00:00");
        supplyRequestList.add(supplyRequestDTO);


        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(supplyRequestList);
        return responseDTO;
    }

    @RequestMapping(value = "/notifySupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifySupplier(@RequestBody @Valid NotifySupplierRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/deleteAttach", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteAttach(@RequestBody @Valid DeleteAttachRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addNote(@RequestBody @Valid AddNoteRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }


    @RequestMapping(value = "/queryProductList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO queryProductList(@RequestBody @Valid QueryProductListRequestDTO requestDTO) {
        List<QueryProductListResponseDTO> list = new ArrayList<>();
        QueryProductListResponseDTO product = new QueryProductListResponseDTO();
        product.setRoomTypeId(1000);
        product.setRoomTypeName("大床房");
        product.setRatePlanId(100);
        product.setRatePlanName("双早");
        product.setQuotaType(QuotaTypeEnum.QUOTA_ROOM.key);
        product.setBedtype("1");
        product.setBreakfastType(1);
        product.setSupplyName("月结供应商");
        List<SupplyProductPriceDTO> dayList = new ArrayList<>();
        SupplyProductPriceDTO day = new SupplyProductPriceDTO();
        day.setSaleDate("2018-06-28");
        day.setSalePrice(new BigDecimal(499));
        day.setBasePrice(new BigDecimal(399));
        dayList.add(day);
        product.setDayList(dayList);
        list.add(product);

        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(5);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setCurrentPage(requestDTO.getCurrentPage());
        paginationSupportDTO.setPageSize(requestDTO.getPageSize());
        paginationSupportDTO.setItemList(list);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<SupplyProductDTO> productDetail(@RequestBody @Valid ProductDetailRequestDTO requestDTO) {
        SupplyProductDTO product = new SupplyProductDTO();
        product.setSupplyProductId(1000);
        product.setRoomTypeId(1000);
        product.setRoomTypeName("大床房");
        product.setRateplanId(1000);
        product.setRateplanName("优惠早餐");
        product.setQuotaType(QuotaTypeEnum.QUOTA_ROOM.key);
        product.setBedtype("1");
        product.setBreakfastType(1);
        product.setCheckinDate("2018-5-28");
        product.setCheckoutDate("2018-5-29");
        product.setRoomNum(1);
        product.setSalePriceSum(new BigDecimal(699));
        product.setBasePriceSum(new BigDecimal(500));
        product.setQuotaAccountId(1000);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(product);
        return responseDTO;
    }

    @RequestMapping(value = "/queryProductPrice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO queryProductPrice(@RequestBody @Valid QueryProductPriceRequestDTO requestDTO) {
        List<SupplyProductPriceResponseDTO> dayList = new ArrayList<>();
        SupplyProductPriceResponseDTO price = new SupplyProductPriceResponseDTO();
        price.setRealQuota(10);
        price.setQuotaNum(20);
        price.setSalePrice(new BigDecimal(699));
        price.setBasePrice(new BigDecimal(200));
        price.setSaleDate("2018-05-28");
        dayList.add(price);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(dayList);
        return responseDTO;
    }

    @RequestMapping(value = "/supplyDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<SupplyDetailResponseDTO> supplyDetail(@RequestBody @Valid SupplyDetailRequestDTO requestDTO) {
        SupplyDetailResponseDTO supplyDetail = new SupplyDetailResponseDTO();
        supplyDetail.setSupplyOrderId(1000);
        supplyDetail.setSupplyOrderCode("S0000001");
        supplyDetail.setSupplyName("月结供应商");
        supplyDetail.setHotelName("深圳国际小酒店");
        supplyDetail.setHotelAddress("深圳龙华民治");
        supplyDetail.setHotelPhone("0511-12345678");
        supplyDetail.setGuestNames("张三、李四");
        supplyDetail.setSpecialRequest("特殊要求");
        supplyDetail.setSupplySum(new BigDecimal(200));

        // 产品
        List<ProductDetailResponseDTO> supplyProductList = new ArrayList<>();
        ProductDetailResponseDTO product = new ProductDetailResponseDTO();
        product.setRoomTypeName("大床房");
        product.setRateplanName("双早");
        List<OrderCheckDetailDTO> dateSegmentList = new ArrayList<>();
        OrderCheckDetailDTO checkDetailDTO = new OrderCheckDetailDTO();
        checkDetailDTO.setCheckinDate("2018-06-27");
        checkDetailDTO.setCheckoutDate("2018-06-28");
        checkDetailDTO.setRoomNum(1);
        checkDetailDTO.setBasePrice(new BigDecimal(399));
        dateSegmentList.add(checkDetailDTO);
        OrderCheckDetailDTO checkDetailDTO2 = new OrderCheckDetailDTO();
        checkDetailDTO2.setCheckinDate("2018-06-28");
        checkDetailDTO2.setCheckoutDate("2018-06-29");
        checkDetailDTO2.setRoomNum(2);
        checkDetailDTO2.setBasePrice(new BigDecimal(499));
        dateSegmentList.add(checkDetailDTO2);
        product.setDateSegmentList(dateSegmentList);
        supplyProductList.add(product);
        supplyDetail.setSupplyProductList(supplyProductList);
        // 附加项
        List<SupplyAdditionChargeDTO> supplyAdditionChargeList = new ArrayList<>();
        SupplyAdditionChargeDTO additionChargeDTO = new SupplyAdditionChargeDTO();
        additionChargeDTO.setName("加床");
        additionChargeDTO.setBasePriceSum(new BigDecimal(100));
        additionChargeDTO.setNum(1);
        supplyAdditionChargeList.add(additionChargeDTO);
        supplyDetail.setSupplyAdditionChargeList(supplyAdditionChargeList);


        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(supplyDetail);
        return responseDTO;
    }

    @RequestMapping(value = "/querySupplyList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO querySupplyList(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        List<SupplyOrderDTO> supplyOrderList = new ArrayList<>();
        SupplyOrderDTO supplyOrder = new SupplyOrderDTO();
        supplyOrder.setSupplyOrderCode("S123456789");
        supplyOrder.setSupplyName("月结供应商");
        supplyOrderList.add(supplyOrder);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(supplyOrderList);
        return responseDTO;
    }


}
