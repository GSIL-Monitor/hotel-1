package com.fangcang.ebk.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "t_ebk_request")
@Data
@EqualsAndHashCode(callSuper = false)
public class EbkRequestDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer requestType;

    private Integer requestStatus;

    private String supplyOrderCode;

    private Long supplyRequestId;

    private String merchantRemark;

    private String confirmNo;

    private String refuseReason;

    private String supplyRemark;

    private Integer existSnap;
}
