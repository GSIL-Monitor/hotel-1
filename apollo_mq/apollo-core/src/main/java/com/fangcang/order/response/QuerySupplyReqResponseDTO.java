package com.fangcang.order.response;

import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyRequestDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class QuerySupplyReqResponseDTO implements Serializable {
    private static final long serialVersionUID = -1496566365397943979L;

    /**
     * 发单请求列表
     */
    private List<SupplyRequestDTO> supplyRequestList;
}
