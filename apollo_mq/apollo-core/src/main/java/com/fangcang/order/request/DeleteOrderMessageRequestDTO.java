package com.fangcang.order.request;

import com.fangcang.order.dto.OrderMessageRedisCacheDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/26
 */
@Data
public class DeleteOrderMessageRequestDTO implements Serializable {

    private static final long serialVersionUID = 5561927275731090061L;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 订单消息列表
     */
    @NotEmpty
    private List<OrderMessageRedisCacheDTO> orderMessageList;

}
