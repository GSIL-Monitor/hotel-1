package com.fangcang.order.request;

import com.fangcang.order.dto.SupplyOrderCofirmMsgDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vinney on 2018/9/14.
 */
@Data
public class DeleteSupplyOrderCofirmMsgRequestDTO implements Serializable {

    private static final long serialVersionUID = 4949078735924476214L;

    private String merchantCode;

    private List<SupplyOrderCofirmMsgDTO> supplyOrderCofirmMsgDTOList;
}
