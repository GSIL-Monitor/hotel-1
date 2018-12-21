package com.fangcang.order;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyProductDTO;
import com.fangcang.order.request.AddAdditionChargeRequestDTO;
import com.fangcang.order.request.AddDeratePolicyRequestDTO;
import com.fangcang.order.request.AddProductRequestDTO;
import com.fangcang.order.request.ChangeAdditionChargeRequestDTO;
import com.fangcang.order.request.ChangeDeratePolicyRequestDTO;
import com.fangcang.order.request.ChangeProductRequestDTO;
import com.fangcang.order.request.DeleteAdditionChargeRequestDTO;
import com.fangcang.order.request.DeleteDeratePolicyRequestDTO;
import com.fangcang.order.request.DeleteProductRequestDTO;
import com.fangcang.order.request.NotifySupplierRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.ProductDetailRequestDTO;
import com.fangcang.order.request.QueryProductListRequestDTO;
import com.fangcang.order.request.QueryProductPriceRequestDTO;
import com.fangcang.order.request.SaveSupplyResultRequestDTO;
import com.fangcang.order.request.SendToSupplierRequestDTO;
import com.fangcang.order.request.SupplyDetailRequestDTO;
import com.fangcang.order.request.UpdateSupplyOrderExceptionAmountDTO;
import com.fangcang.order.response.PriceDetailResponseDTO;
import com.fangcang.order.response.QueryProductListResponseDTO;
import com.fangcang.order.response.QuerySupplyReqResponseDTO;
import com.fangcang.order.response.SupplyDetailResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import com.fangcang.order.service.SupplyOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class SupplyOrderController extends BaseController {

    @Resource
    private SupplyOrderService supplyOrderService;

    @RequestMapping(value = "/updateSupplyOrderExceptionAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateSupplyOrderExceptionAmount(@RequestBody UpdateSupplyOrderExceptionAmountDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setOperator(getFullName());
            responseDTO = supplyOrderService.updateSupplyOrderExceptionAmount(requestDTO);
        } catch (Exception e) {
            log.error("updateSupplyOrderExceptionAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addProduct(@RequestBody @Valid AddProductRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.addProduct(requestDTO);
        } catch (Exception e) {
            log.error("addProduct error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeProduct(@RequestBody @Valid ChangeProductRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.changeProduct(requestDTO);
        } catch (Exception e) {
            log.error("changeProduct error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteProduct(@RequestBody @Valid DeleteProductRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.deleteProduct(requestDTO);
        } catch (Exception e) {
            log.error("deleteProduct error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/priceDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PriceDetailResponseDTO> priceDetail(@RequestBody @Valid ProductDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyOrderService.priceDetail(requestDTO);
        } catch (Exception e) {
            log.error("priceDetail error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addAdditionCharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addAdditionCharge(@RequestBody @Valid AddAdditionChargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.addAdditionCharge(requestDTO);
        } catch (Exception e) {
            log.error("addAdditionCharge error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeAdditionCharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeAdditionCharge(@RequestBody @Valid ChangeAdditionChargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.changeAdditionCharge(requestDTO);
        } catch (Exception e) {
            log.error("changeAdditionCharge error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/deleteAdditionCharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteAdditionCharge(@RequestBody @Valid DeleteAdditionChargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.deleteAdditionCharge(requestDTO);
        } catch (Exception e) {
            log.error("deleteAdditionCharge error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/sendToSupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO sendToSupplier(@RequestBody @Valid SendToSupplierRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.sendToSupplier(requestDTO);
        } catch (Exception e) {
            log.error("sendToSupplier error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/saveSupplyResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO saveSupplyResult(@RequestBody @Valid SaveSupplyResultRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = supplyOrderService.saveSupplyResult(requestDTO);
        } catch (Exception e) {
            log.error("saveSupplyResult error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/querySupplyRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<QuerySupplyReqResponseDTO> querySupplyRequest(@RequestBody @Valid SupplyDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyOrderService.querySupplyRequest(requestDTO);
        } catch (Exception e) {
            log.error("querySupplyRequest error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/notifySupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifySupplier(@RequestBody @Valid NotifySupplierRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.notifySupplier(requestDTO);
        } catch (Exception e) {
            log.error("notifySupplier error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }


    @RequestMapping(value = "/queryProductList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<QueryProductListResponseDTO>> queryProductList(@RequestBody @Valid QueryProductListRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            PaginationSupportDTO<QueryProductListResponseDTO> page = supplyOrderService.queryProductList(requestDTO);
            responseDTO.setModel(page);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("queryProductList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<SupplyProductDTO> productDetail(@RequestBody @Valid ProductDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyOrderService.productDetail(requestDTO);
        } catch (Exception e) {
            log.error("productDetail error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryProductPrice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<SupplyProductPriceResponseDTO>> queryProductPrice(@RequestBody @Valid QueryProductPriceRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyOrderService.queryProductPrice(requestDTO);
        } catch (Exception e) {
            log.error("queryProductPrice error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/supplyDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<SupplyDetailResponseDTO> supplyDetail(@RequestBody @Valid SupplyDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyOrderService.supplyDetail(requestDTO);
        } catch (Exception e) {
            log.error("supplyDetail error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/querySupplyList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<SupplyOrderDTO>> querySupplyList(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = supplyOrderService.querySupplyList(requestDTO);
        } catch (Exception e) {
            log.error("querySupplyList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addDeratePolicy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addDeratePolicy(@RequestBody @Valid AddDeratePolicyRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.addDeratePolicy(requestDTO);
        } catch (Exception e) {
            log.error("addDeratePolicy error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeDeratePolicy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeDeratePolicy(@RequestBody @Valid ChangeDeratePolicyRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.changeDeratePolicy(requestDTO);
        } catch (Exception e) {
            log.error("changeDeratePolicy error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/deleteDeratePolicy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteDeratePolicy(@RequestBody @Valid DeleteDeratePolicyRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            responseDTO = supplyOrderService.deleteDeratePolicy(requestDTO);
        } catch (Exception e) {
            log.error("deleteDeratePolicy error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

}
