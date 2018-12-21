package com.fangcang.merchant.dto;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryUserConditionDTO extends BaseQueryConditionDTO {

    private Long userId;

    private String userName;

    private String realName;

    private Long department;

    private String tel;

    private Integer isActive;

    private String domain;
}
