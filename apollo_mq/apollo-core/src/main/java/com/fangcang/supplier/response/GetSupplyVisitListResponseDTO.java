package com.fangcang.supplier.response;

import com.fangcang.supplier.dto.SupplyVisitInfoDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    获取供应商访问记录ResponseDTO
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/30 16:36
*/
@Data
public class GetSupplyVisitListResponseDTO implements Serializable {


    private static final long serialVersionUID = -7927439343647024023L;
    /**
     * 供应商的拜访记录
     */
    private List<SupplyVisitInfoDTO> visitList;
}
