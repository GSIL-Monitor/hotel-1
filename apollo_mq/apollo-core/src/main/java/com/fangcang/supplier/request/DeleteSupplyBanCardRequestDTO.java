package com.fangcang.supplier.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/7 20:41
 * @Description: 删除供应商银行卡信息RequestDTO
 */
@Data
public class DeleteSupplyBanCardRequestDTO implements Serializable {

    private static final long serialVersionUID = -5323175701427751569L;

    @NotNull(message = "银行卡id不能为null")
    private Long bankCardId;
}
