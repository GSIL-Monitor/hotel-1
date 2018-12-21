package com.fangcang.product.response;

import com.fangcang.product.dto.MerchantChannelDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class MerchantChannelInfoResponseDTO implements Serializable {
    private static final long serialVersionUID = -6442294051427433956L;

    /**
     * 商家已配置的所有渠道
     */
    private List<MerchantChannelDTO> merchantChannelList;
}
