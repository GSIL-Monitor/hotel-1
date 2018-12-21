package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.order.dto.OrderNoteDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class AddNoteRequestDTO extends OrderNoteDTO implements Serializable {
    private static final long serialVersionUID = 1752056359008990360L;

}
