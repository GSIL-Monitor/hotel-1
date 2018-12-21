package com.fangcang.product.response;

import com.fangcang.product.dto.ShowChannelPriceDTO;
import com.fangcang.product.dto.ShowSaleStateDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/28.
 */
@Data
public class ShowChannelPriceResponseDTO implements Serializable {

    private List<ShowChannelPriceDTO> channelPriceList;

    private List<ShowSaleStateDTO> saleStateList;
}
