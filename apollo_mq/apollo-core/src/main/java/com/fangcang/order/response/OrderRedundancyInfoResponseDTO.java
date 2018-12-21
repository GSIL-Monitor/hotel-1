package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 订单冗余信息
 *
 * @author : zhanwang
 * @date : 2018/7/19
 */
@Data
public class OrderRedundancyInfoResponseDTO implements Serializable {
    private static final long serialVersionUID = -8504333975726719227L;

    /**
     * 房型名称
     */
    private Set<String> roomtypeNameSet;
    /**
     * 供货单编码
     */
    private Set<String> supplyOrderCodeSet;
    /**
     * 供货单状态
     */
    private Set<String> supplyStatusSet;
    /**
     * 供应商编码
     */
    private Set<String> supplyCodeSet;
    /**
     * 供货单确认号
     */
    private Set<String> supplyConfirmNoSet;

}
