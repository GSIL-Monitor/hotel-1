package com.fangcang.merchant.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_user_bank_card")
@Data
public class UserBankCardDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_card_id")
    private Long bankCardId;

    /**
     * 账户类型(1为银行卡 2为支付宝  3微信)
     */
    private Integer type;

    /**
     * 开户行
     */
    private String openingBank;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 银行账户或者URL
     */
    private String accountNumber;

    private Long userId;

    /**
     * 真实路径
     */
    private String realPath;

}
