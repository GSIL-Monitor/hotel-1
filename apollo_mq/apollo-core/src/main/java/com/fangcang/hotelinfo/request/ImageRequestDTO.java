package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.hotelinfo.dto.ImageInfoDTO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 09:43
 * @Description:图片上传
 */
@Data
public class ImageRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 5306325986236121363L;

    /**
     * 图片ID
     */
    private Long imageId;

    /**
     * realPath
     */
    private String realPath;

    /**
     * 文件对象
     */
    private MultipartFile file;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 1.外观图 2.房型图 3.大堂图 4.设施图 5.宴会厅 6.会议厅 7.其它
     */
    private Integer imageType;

    /**
     * 当图片类型为房型时，对应房型ID
     * 当图片类型为宴会厅时，对应宴会厅ID
     * 如果是外观图、内景图、其他图，则此ID存酒店ID
     */
    private Long extId;

    /**
     * 批量操作图片列表
     */
    private List<ImageInfoDTO> imageList;


}
