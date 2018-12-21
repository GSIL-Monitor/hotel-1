package com.fangcang.supplier.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_supply_user_bind")
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplyUserBindDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String merchantCode;

    private String supplyCode;

    private Long userId;

    private String openId;
}
