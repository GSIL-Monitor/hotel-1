package com.fangcang.order.request;

import com.fangcang.order.dto.SupplyProductPriceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 供货单产品对象
 *
 * @author zhanwang
 */
@Data
public class CreateOrderProductRequestDTO implements Serializable {

    private static final long serialVersionUID = -912143406725069203L;
    /**
     * 房型id
     */
    private String roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划id
     */
    private Integer rateplanId;
    /**
     * 价格计划名称
     */
    private String rateplanName;
    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    private Integer breakfastType;
    /**
     * 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    private Integer quotaType;
    /**
     * 入住日期
     */
    private String checkinDate;

    /**
     * 离店日期
     */
    private String checkoutDate;

    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 配额帐号ID
     */
    private Integer quotaAccountId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 价格每日明细
     */
    private List<SupplyProductPriceDTO> dayList;


}