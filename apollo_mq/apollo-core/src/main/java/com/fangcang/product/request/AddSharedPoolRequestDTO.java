package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.PricePlanDTO;
import com.fangcang.product.dto.SharedPoolDailyDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class AddSharedPoolRequestDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = 5305079141392259058L;
    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为Null")
    private Long hotelId;

    /**
     * 供应商编码
     */
    @NotEmpty(message = "supplyCode不能为Null")
    private String supplyCode;

    /**
     * 是否共享(1 共享 0不共享)
     */
    @NotNull(message = "isShare不能为Null")
    private Integer isShare;

    /**
     * 配额账号名称
     */
    @NotNull(message = "quotaAccountName不能为Null")
    private String quotaAccountName;

    @NotNull(message = "sharedPoolDailyList不能为Null")
    private List<SharedPoolDailyDTO> sharedPoolDailyList;

    /**
     * 商家编码
     */
    private String merchantCode;

    /*******保存选中哪些价格计划，就是之前的/addProductToSharedPool接口的字段******************************/
    private List<PricePlanDTO> pricePlanList;


}
