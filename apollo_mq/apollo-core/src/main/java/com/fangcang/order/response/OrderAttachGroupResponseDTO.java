package com.fangcang.order.response;

import com.fangcang.order.dto.OrderAttachDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/23
 */
@Data
public class OrderAttachGroupResponseDTO implements Serializable {

    private static final long serialVersionUID = -1045145008221076631L;
    /**
     * 附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，
     * 5:分销商预订单，6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     */
    private Integer type;
    /**
     * 附件列表
     */
    private List<OrderAttachDTO> orderAttachList;
}
