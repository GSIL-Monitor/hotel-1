package com.fangcang.product.response;

import com.fangcang.product.dto.NeedSharedPoolRoomTypeDTO;
import com.fangcang.product.dto.PricePlanDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class NeedSharedProductResponseDTO implements Serializable{
    private static final long serialVersionUID = 8406961858838029835L;

    /**
     * 房型集合
     */
    private List<NeedSharedPoolRoomTypeDTO> roomTypeList;

}
