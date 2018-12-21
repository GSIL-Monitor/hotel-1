package com.travel.common.enums;

/**
 * 代理通渠道订单状态，不同渠道有不同的状态，大体分为Ctrip、Qunar、B2B、ChannelA
 *   2018/5/22.
 */
public enum ChannelOrderStateEnum {

    /** 代理通——携程渠道订单状态 */
    CTRIP_UNREAD_UNPROCESSED(ChannelEnum.CTRIP, "0", "未读未处理"),
    CTRIP_READ_UNPROCESSED(ChannelEnum.CTRIP, "01", "已读未处理"),
    CTRIP_PROCESSED(ChannelEnum.CTRIP, "100", "已处理"),
    CTRIP_REFUSED(ChannelEnum.CTRIP, "101", "已拒绝"),

    /** 代理通——携程B2B渠道订单状态 */
    CTRIP_B2B_NEW_ORDER(ChannelEnum.CTRIP_B2B, "600", "新订单"),
    CTRIP_B2B_UNPROCESSED(ChannelEnum.CTRIP_B2B, "1", "新订单"),
    CTRIP_B2B_PROCESSED(ChannelEnum.CTRIP_B2B, "100", "新订单"),
    CTRIP_B2B_REFUSED(ChannelEnum.CTRIP_B2B, "101", "新订单"),
    CTRIP_B2B_CANCELED(ChannelEnum.CTRIP_B2B, "500", "新订单"),

    /** 代理通——携程ChannelA（代分销）渠道订单状态 */
    CTRIP_CHANNEL_A_TO_BE_CONFIRM(ChannelEnum.CTRIP_CHANNEL_A, "1", "待确认"),
    CTRIP_CHANNEL_A_CONFIRMED_AND_UNPROCESSED(ChannelEnum.CTRIP_CHANNEL_A, "2", "已确认-待处理"),
    CTRIP_CHANNEL_A_CONFIRMED_AND_PROCESSED(ChannelEnum.CTRIP_CHANNEL_A, "3", "已确认-已处理"),
    CTRIP_CHANNEL_A_CANCELING_UNPROCESSED(ChannelEnum.CTRIP_CHANNEL_A, "4", "取消中-待处理"),
    CTRIP_CHANNEL_A_CANCELED_PROCESSED(ChannelEnum.CTRIP_CHANNEL_A, "5", "已取消-已处理"),
    CTRIP_CHANNEL_A_UNPROCESSED(ChannelEnum.CTRIP_CHANNEL_A, "6", "未处理"),

    /** 代理通——Qunar渠道订单状态 */
    QUNAR_TO_BE_RESERVED_AND_NOT_PAID(ChannelEnum.QUNAR, "1", "待预留（待支付）"),
    QUNAR_TO_BE_ARRANGED(ChannelEnum.QUNAR, "2", "待安排"),
    QUNAR_CONFIRMED(ChannelEnum.QUNAR, "3", "已确认"),
    QUNAR_CHECKED_IN(ChannelEnum.QUNAR, "4", "已入住"),
    QUNAR_TO_BE_RESERVED_AND_PAID(ChannelEnum.QUNAR, "5", "待预留（已支付）"),
    QUNAR_TO_BE_PAID(ChannelEnum.QUNAR, "6", "待支付"),
    QUNAR_CANCELED_OTA_REFUSED(ChannelEnum.QUNAR, "7", "已取消（OTA拒绝）"),
    QUNAR_TO_BE_REFUND(ChannelEnum.QUNAR, "8", "待退款"),
    QUNAR_CANCELED_PROCESSED(ChannelEnum.QUNAR, "9", "已取消-已处理"),
    QUNAR_REFUSED_CANCEL(ChannelEnum.QUNAR, "10", "拒绝退订"),
    QUNAR_CANCELED_UNPROCESSED(ChannelEnum.QUNAR, "11", "已取消-未处理"),
    QUNAR_UNPROCESSED(ChannelEnum.QUNAR, "13", "未处理");

    public ChannelEnum channel;
    public String orderStateCode;
    public String orderStateName;

    ChannelOrderStateEnum(ChannelEnum channel, String orderStateCode, String orderStateName) {
        this.channel = channel;
        this.orderStateCode = orderStateCode;
        this.orderStateName = orderStateName;
    }

    public static ChannelOrderStateEnum getChannelOrderStateEnum(String channelCode, String orderStateCode) {
        ChannelOrderStateEnum[] values = values();
        for (ChannelOrderStateEnum channelOrderState : values) {
            if (channelOrderState.channel.key.equals(channelCode)
                    && channelOrderState.orderStateCode.equals(orderStateCode)) {
                return channelOrderState;
            }
        }
        return null;
    }

    public static String getOrderStateName(String channelCode, String orderStateCode) {
        ChannelOrderStateEnum channelOrderStateEnum = getChannelOrderStateEnum(channelCode, orderStateCode);
        return null == channelOrderStateEnum ? null : channelOrderStateEnum.orderStateName;
    }
}
