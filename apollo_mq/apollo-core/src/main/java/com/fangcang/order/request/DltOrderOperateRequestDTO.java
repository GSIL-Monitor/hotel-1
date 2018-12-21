package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class DltOrderOperateRequestDTO extends BaseDTO {

    private static final long serialVersionUID = -6258374257449969591L;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 渠道订单号
     */
    private String customerOrderCode;

    /**
     * new:新建
     * processing:处理中
     * confirmed:已确认
     * refused:已拒绝
     * applying_cancel:申请取消中
     * applying_modify:申请修改中
     * canceled:已取消
     */
    private String orderState;

    /**
     * ctrip,ctrip_b2b,ctrip_channel_a,qunar
     */
    private String channelCode;

    /**
     * orderId（下单时返回订单ID，此处也传入ID，方便匹配）
     */
    private Integer orderCode;

    /**
     * ctrip开头渠道操作类型：
     * 0-接受（安排）,
     * 1-拒绝,
     * 2-更改确认号,
     * 11-接受取消,
     * 12-拒绝取消,
     * qunar渠道操作类型：
     * 14-有房,
     * 15-无房,
     * 16-申请退订,
     * 17-同意退订,
     * 18-拒绝退订
     */
    private Integer operateType;

    /**
     * operaterType=0时必传1：按入住人姓名；2：按确认号
     */
    private Integer confirmType;

    /**
     * operaterType=0和=2时，传入预订号
     */
    private String confirmNo;

    /**
     * operaterType=1和=12时传入1：满房；2:房价不对;3:其他原因
     * <p>
     * 携程渠道值如下1：满房封首日；2:满房封订单入住日；3：房价不对;4:其他原因
     */
    private Integer refuseType;

    /**
     * 拒绝原因
     */
    private String refuseReason;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作人
     */
    private String operator;

}
