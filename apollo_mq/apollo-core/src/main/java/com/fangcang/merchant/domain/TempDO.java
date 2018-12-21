package com.fangcang.merchant.domain;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "temp")
public class TempDO {

    private String content;
}
