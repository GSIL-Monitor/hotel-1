package com.fangcang.mapping.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
@Table(name = "t_dlt_map_hotel")
public class HotelMappingDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchant_code")
    private String merchantCode;

    @Column(name = "m_hotel_id")
    private Long hotelId;

    @Column(name = "m_hotel_name")
    private String hotelName;

    @Column(name = "dlt_hotel_id")
    private String dltHotelId;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_date")
    private Date createTime;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modify_date")
    private Date modifyTime;
}
