package com.fangcang.base.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_areadata")
@Data
@EqualsAndHashCode(callSuper = false)
public class AreaDataDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer type;

    private String dataCode;

    private String dataName;

    private String pinyin;

    private String acronympinyin;

    private Integer parentId;

    private String engDataname;

    private String countryCode;
}
