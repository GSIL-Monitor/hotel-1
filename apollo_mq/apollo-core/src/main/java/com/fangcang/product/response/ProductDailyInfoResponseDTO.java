package com.fangcang.product.response;

import com.fangcang.product.dto.ProductDailyDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class ProductDailyInfoResponseDTO implements Serializable{

    private static final long serialVersionUID = -5127788731699084637L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 价格计划名称
     */
    private String pricePlanName;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 早餐类型
     * com.fangcang.common.enums.BreakFastTypeEnum
     */
    private Integer breakFastType;

    /**
     * 配额类型
     * com.fangcang.common.enums.QuotaTypeEnum
     */
    private Integer quotaType;

    /**
     *床型
     * com.fangcang.common.enums.BedTypeEnum
     */
    private String bedType;

    /**
     *是否含有附加项(1含有, 0 不含有)
     */
    private Integer isAdditional;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 支付方式
     * com.fangcang.common.enums.PayMethodEnum
     */
    private Integer payMethod;

    /**
     * 供应方式
     */
    private Integer supplyWay;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 是否有效
     */
    private Integer isActive;

    /**
     * 产品类型(1散房 2团房)
     */
    private Integer productType;

    /**
     * 面房政策(1 全陪免半 2 8免半16兔1)
     */
    private String [] freeRoomPolicy;

    /**
     * 是否共享(1 共享  ,0 不共享)
     */
    private Integer isShare;

    /**
     * 预定类型 1 预定  2部分可定   3不可订
     */
    private Integer bookType;

    private List<ProductDailyDTO> productDailyList;

    /**
     * 起价
     */
    private BigDecimal startPrice;

    /**
     * 散房价格类型  1 预订  2部分可订 3 不可订
     */
    private Integer scatteredRoomBookType;

    /**
     * 团房价格类型  1 预订  2部分可订 3 不可订
     */
    private Integer groupRoomBookType;

    /**
     * 是否有价格：0无，1有
     */
    private Integer hasPrice=0;
}
