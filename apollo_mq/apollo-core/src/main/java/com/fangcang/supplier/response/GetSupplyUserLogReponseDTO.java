package com.fangcang.supplier.response;

import com.fangcang.supplier.dto.SupplyUserLogDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/30 16:11
 * @Description:
 */
@Data
public class GetSupplyUserLogReponseDTO implements Serializable {

    private static final long serialVersionUID = 5472797185580247691L;

    private List<SupplyUserLogDTO> operateList;
}
