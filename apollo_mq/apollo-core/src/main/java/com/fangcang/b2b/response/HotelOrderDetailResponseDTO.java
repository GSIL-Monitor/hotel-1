package com.fangcang.b2b.response;

import com.fangcang.b2b.dto.AdditionChargeDTO;
import com.fangcang.b2b.dto.HotelOrderDerateDTO;
import com.fangcang.b2b.dto.HotelOrderNoteDTO;
import com.fangcang.b2b.dto.HotelOrderRequestDTO;
import com.fangcang.b2b.dto.RatePricePlanDTO;
import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class HotelOrderDetailResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -6931266211461820719L;

    /**
     * 主键
     */
    private Long orderId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Integer orderStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    private Integer payStatus;

    /**
     * 支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     */
    private Integer payMethod;

    /**
     * 分销商币种
     */
    private String saleCurrency;

    /**
     * 渠道编码
     */
    private String channelCode;
    private String channelName;

    /**
     * 订单总价
     */
    private BigDecimal orderSum;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Integer balanceMethod;

    /**
     * 订单确认号（冗余字段）
     */
    private String confirmNo;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;

    /**
     * 实收金额
     */
    private BigDecimal paidInAmount;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 客人特殊要求
     */
    private String specialRequest;

    /**
     * 联系人名称
     */
    private String contractName;

    /**
     * 联系人电话
     */
    private String contractPhone;

    /**
     * 订单归属人帐号
     */
    private String belongUser;

    /**
     * 订单归属人名称
     */
    private String belongName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;


    /**
     * 是否团房订单
     */
    private Integer isGroupRoom;

    /**
     * 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    private String guestNames;

    /**
     * 团号，团房订单才有值
     */
    private String groupNo;

    /**
     * 向导
     */
    private String guide;

    /**
     * 向导电话
     */
    private String guidePhone;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 是否展示取消 1 展示 0不展示
     */
    private Integer showCancel;

    /**
     * 不够取消的原因
     */
    private String canNotCancelReason;

    /**
     * 优惠之前的订单总额
     */
    private BigDecimal beforeOrderSum;

    /**
     * 主图
     */
    private String imageUrl;

    /**
     * 酒店地址
     */
    private String hotelAddress;

    /**
     * 产品明细
     */
    private List<RatePricePlanDTO> ratePlanList;

    /**
     * 附加项
     */
    private List<AdditionChargeDTO> additionChargeList;

    /**
     * 减免政策
     */
    private List<HotelOrderDerateDTO> orderDerateList;

    /**
     * 申请取消、修改记录
     */
    private List<HotelOrderRequestDTO> hotelOrderRequestList;

    /**
     * 备注
     */
    private List<HotelOrderNoteDTO> orderNoteList;
}
