package com.fangcang.ebk.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_ebk_order_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class EbkOrderLogDO implements Serializable {
    private static final long serialVersionUID = 7718384325192044788L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long ebkOrderId;

    private String content;

    private Long ebkRequestId;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;
}
