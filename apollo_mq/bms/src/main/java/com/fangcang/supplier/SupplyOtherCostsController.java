package com.fangcang.supplier;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.supplier.dto.SupplyOtherCostDTO;
import com.fangcang.supplier.request.AddSupplyCostRequestDTO;
import com.fangcang.supplier.request.QueryOtherCostRequestDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Vinney on 2018/10/21.
 */
@Slf4j
@RestController
@RequestMapping("/supplyCost")
public class SupplyOtherCostsController extends BaseController {

    @Autowired
    private SupplyService supplyService ;


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<Integer> saveSupplyCost(@RequestBody AddSupplyCostRequestDTO addSupplyCostRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        //设置创建人和创建时间
        addSupplyCostRequestDTO.setCreator(getFullName());
        addSupplyCostRequestDTO.setCreateTime(new Date());
        addSupplyCostRequestDTO.setModifier(getFullName());
        addSupplyCostRequestDTO.setModifyTime(new Date());
        try{
            responseDTO = supplyService.saveOtherCost(addSupplyCostRequestDTO);
        } catch (Exception e){
            log.error("保存其他成本出错：{}", JSON.toJSONString(addSupplyCostRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }

        return responseDTO;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<SupplyOtherCostDTO>> list(@RequestBody QueryOtherCostRequestDTO queryOtherCostRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        try{
            PaginationSupportDTO<SupplyOtherCostDTO> paginationSupportDTO =supplyService.queryOtherCostForPage(queryOtherCostRequestDTO);
            responseDTO.setModel(paginationSupportDTO);

        } catch (Exception e){
            log.error("查询成本列表页报错：{}",JSON.toJSONString(queryOtherCostRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }

        return responseDTO;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SupplyOtherCostDTO> detail(@RequestBody QueryOtherCostRequestDTO queryOtherCostRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        if (null == queryOtherCostRequestDTO.getId()){
            log.error("查询详情页失败,ID不能为空：{}",JSON.toJSONString(queryOtherCostRequestDTO));
            responseDTO.setErrorCode(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }

        try{
            responseDTO = supplyService.queryOtherCostById(queryOtherCostRequestDTO);
        } catch (Exception e){
            log.error("查询成本详情页报错：{}",JSON.toJSONString(queryOtherCostRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/setIsActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setIsActive(@RequestBody SupplyOtherCostDTO supplyOtherCostDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        if (null == supplyOtherCostDTO.getId() || null == supplyOtherCostDTO.getIsActive()){
            log.error("设置是否启用失败，参数为空：{}",JSON.toJSONString(supplyOtherCostDTO));
            responseDTO.setErrorCode(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }
        try{
            responseDTO = supplyService.setOtherCostIsActive(supplyOtherCostDTO);
        } catch (Exception e){
            log.error("查询成本详情页报错：{}",JSON.toJSONString(supplyOtherCostDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }

}
