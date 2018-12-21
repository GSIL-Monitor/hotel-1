package com.fangcang.finance.prepay.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class PrepayContractTargetDTO implements Serializable {
    private static final long serialVersionUID = 4018019327024411318L;
    private Integer id;

    /**
     * 合约id
     */
    private Integer contractId;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 目标间夜
     */
    private Integer roomNight;

    /**
     * 已完成间夜
     */
    private Integer doneRoomNight;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}