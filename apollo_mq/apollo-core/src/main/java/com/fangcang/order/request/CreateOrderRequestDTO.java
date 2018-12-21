package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class CreateOrderRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 7270438525086051993L;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 渠道编码
     */
    @NotBlank
    private String channelCode;

    /**
     * 子渠道编码
     */
    private String channelSubCode;

    /**
     * 订单总价
     */
    @NotNull
    private BigDecimal orderSum;

    /**
     * 分销商编码
     */
    @NotBlank
    private String agentCode;

    /**
     * 联系人名称
     */
    private String contractName;

    /**
     * 联系人电话
     */
    private String contractPhone;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 是否团房订单
     */
    @NotNull
    private Integer isGroupRoom;

    /**
     * 入住人
     */
    private List<String> guestList;

    /**
     * 是否手工单
     */
    private Integer isManualOrder;

    /**
     * 客户订单号
     */
    private String customerOrderCode;

    /**
     * 客人特殊要求
     */
    private String specialRequest;

    /**
     * 团号，团房订单才有值
     */
    private String groupNo;

    /**
     * 产品明细
     */
    @NotEmpty
    private List<CreateOrderProductRequestDTO> ratePlanList;

    /**
     * 附加费明细
     */
    private List<CreateOrderAdditionChargeRequestDTO> additionChargeList;

    /**
     * 订单减免政策
     */
    private List<DeratePolicyRequestDTO> deratePolicyList;


    /**
     * 是否保留房。1-保留房，0-非保留房
     */
    private String isHoldRoom;

    /**
     * 分销商备注
     */
    private String note;

    /**
     * 是否导入重复订单
     */
    private Integer isImportRepeat=0;
}
