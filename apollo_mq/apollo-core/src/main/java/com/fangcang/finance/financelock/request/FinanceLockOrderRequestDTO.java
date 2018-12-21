package com.fangcang.finance.financelock.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class FinanceLockOrderRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 6696747254630969414L;
    /**
     * 订单列表， 支持批量加解锁
     */
    @NotEmpty
    private List<LockOrderItemRequestDTO> orderList;


}
