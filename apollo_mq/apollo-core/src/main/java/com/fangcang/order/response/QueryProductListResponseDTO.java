package com.fangcang.order.response;

import com.fangcang.order.dto.SupplyProductPriceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class QueryProductListResponseDTO implements Serializable {
    private static final long serialVersionUID = -9127206464771262588L;

    /**
     * 房型id
     */
    private Integer roomTypeId;
    /**
     * 房型名称
     */
    private String roomTypeName;
    /**
     * 价格计划id
     */
    private Integer ratePlanId;
    /**
     * 价格计划名称
     */
    private String ratePlanName;
    /**
     * 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    private Integer quotaType;
    /**
     * 床型，1：大床，2：双床，3：三床，4：四床，5：单床，多个床型用逗号隔开
     */
    private String bedtype;
    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    private Integer breakfastType;
    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 币种
     */
    private String currency;

    /**
     * 每日价格明细
     */
    private List<SupplyProductPriceDTO> dayList;


}
