package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class AddAttachRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 1525462009591106242L;
    /**
     * 订单id
     */
    @NotNull
    private Integer orderId;
    /**
     * 附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，
     * 6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     */
    @NotNull
    private Integer attachType;

    /**
     * 上传的附件列表
     */
    private MultipartFile[] files;


}
