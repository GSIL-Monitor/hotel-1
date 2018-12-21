package com.fangcang.finance.prepay.response;

import com.fangcang.finance.prepay.dto.PrepayContractTargetDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/4
 */
@Data
public class QueryAllPrepayContractResponseDTO implements Serializable {


    private static final long serialVersionUID = -7454527596968585227L;
    /**
     * 合约id
     */
    private Integer contractId;
    /**
     * 供应商编码
     */
    private String supplyCode;
    /**
     * 合同有效开始日期
     */
    private Date validBeginDate;

    /**
     * 合同有效结束日期
     */
    private Date validEndDate;

    /**
     * 目标间夜总数
     */
    private Integer targetRoomNightSum;
    /**
     * 合约目标间夜明细
     */
    private List<PrepayContractTargetDTO> contractTargetDTOList;

}
