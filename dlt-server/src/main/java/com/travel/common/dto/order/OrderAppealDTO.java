package com.travel.common.dto.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author  2018/1/10
 */
@Data
public class OrderAppealDTO implements Serializable {

    private static final long serialVersionUID = 4441588940816587646L;

    private Long appealId;

    private String applyReason;

    private String complainant;

    private Date createDate;

    private String status;

    private String handler;

    private Date handleTime;

    private Date modifyDate;

    private String modifier;

    private String orderCode;

    private String mark;

}
