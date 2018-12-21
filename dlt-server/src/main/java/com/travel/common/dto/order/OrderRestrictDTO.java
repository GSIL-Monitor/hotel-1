package com.travel.common.dto.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author  2018/1/10
 */
@Data
public class OrderRestrictDTO implements Serializable {

    private static final long serialVersionUID = -4950980300644633086L;

    private Long restrictId;

    private Long priceplanId;

    private Date saleDate;

    private Integer bookDays;

    private Integer occupancyType;

    private Integer occupancyDays;

    private Integer cancelType;

    private Integer cancelDays;

    private String cancelTime;

    private String cancelRemark;

    private Integer bookRooms;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private String bookTime;

    private String orderCode;

}
