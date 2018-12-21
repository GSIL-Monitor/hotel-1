package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class OrderCountResponseDTO implements Serializable {
    private static final long serialVersionUID = -2671642332790694277L;


    /**
     * 快捷类型，1:我的订单，2：今日入住，3：未处理订单，4：取消订单，5：今日新订单，6：手工单
     */
    private Integer quickQueryType;
    /**
     * 订单数量
     */
    private Integer counts;
    /**
     * 渠道订单数统计， 只有OTA订单列表需要显示渠道订单数
     */
    private List<OrderChannelCountResponseDTO> channelList;
}
