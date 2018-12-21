package com.fangcang.order.response;

import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 供货单发单预览详情对象
 *
 * @author : zhanwang
 * @date : 2018/5/28
 */
@Data
public class SupplyDetailResponseDTO implements Serializable {
    private static final long serialVersionUID = 440150815763854040L;

    /**
     * 供货单id
     */
    private Integer supplyOrderId;
    /**
     * 供货单编码
     */
    private String supplyOrderCode;
    /**
     * 供应商名称
     */
    private String supplyName;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 酒店地址
     */
    private String hotelAddress;
    /**
     * 酒店电话
     */
    private String hotelPhone;
    /**
     * 入住人
     */
    private String guestNames;
    /**
     * 特殊要求
     */
    private String specialRequest;

    /**
     * 币种
     */
    private String baseCurrency;

    /**
     * 供货单总底价
     */
    private BigDecimal supplySum;
    /**
     * 供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    private Integer supplyStatus;
    /**
     * 供应产品明细
     */
    private List<ProductDetailResponseDTO> supplyProductList;
    /**
     * 供应附加项明细
     */
    private List<SupplyAdditionChargeDTO> supplyAdditionChargeList;

    /**
     * 减免政策明细
     */
    private List<DeratePolicyResponseDTO> derateList;
}
