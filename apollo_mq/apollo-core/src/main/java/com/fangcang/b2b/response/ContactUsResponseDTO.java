package com.fangcang.b2b.response;

import com.fangcang.supplier.dto.MerchantOPDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class ContactUsResponseDTO implements Serializable {
    private static final long serialVersionUID = -4417687591696224528L;

    /**
     * 投诉联系人
     */
    private String merchantBMName;

    /**
     * 投诉联系电话
     */
    private String merchantBMPhone;

    /**
     * 业务员
     */
    private List<MerchantOPDTO> merchantOPList;
}
