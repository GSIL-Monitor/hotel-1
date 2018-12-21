package com.fangcang.base.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "t_exchange_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class ExchangeLogDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal oldRate;

    private BigDecimal newRate;

    private String merchantCode;
}
