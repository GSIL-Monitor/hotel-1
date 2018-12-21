package com.fangcang.order.response;

import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.dto.SupplyFinanceDTO;
import com.fangcang.order.dto.SupplyOrderDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 供货单对象
 *
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class SupplyOrderResponseDTO extends SupplyOrderDTO implements Serializable {

    private static final long serialVersionUID = 1914310501007777867L;
    /**
     * 供应产品明细
     */
    private List<SupplyProductResponseDTO> supplyProductList;

    /**
     * 供应附加项明细
     */
    private List<SupplyAdditionChargeDTO> supplyAdditionChargeList;

    /**
     * 减免政策明细
     */
    private List<DeratePolicyResponseDTO> derateList;

    /**
     * 供货单财务信息
     */
    private SupplyFinanceDTO supplyFinanceDTO;


}