package com.fangcang.order.response;

import com.fangcang.order.dto.OrderNoteDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/23
 */
@Data
public class OrderNoteGroupResponseDTO implements Serializable {
    private static final long serialVersionUID = -3673194834186790022L;

    /**
     * 备注类型：1: 分销商备注，2：供应商备注 ，3：内部备注
     */
    private Integer noteType;
    /**
     * 备注列表
     */
    private List<OrderNoteDTO> orderNoteList;
}
