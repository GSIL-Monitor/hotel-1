package com.fangcang.hotelinfo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 09:50
 * @Description:
 */

@Data
public class ImageReponseDTO implements Serializable {

    private static final long serialVersionUID = 4908355935631330956L;

    /**
     * 图片ID
     */
    private Long imageId;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 真实地址
     */
    private String realPath;

}
