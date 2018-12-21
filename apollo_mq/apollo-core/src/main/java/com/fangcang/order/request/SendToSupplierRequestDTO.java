package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class SendToSupplierRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3332432464491269399L;

    /**
     * 供货单id
     */
    @NotNull
    private Integer supplyOrderId;
    /**
     * 发供货单类型：1：预定单，2:重发预订单， 3：修改单，4：取消单
     */
    @NotNull
    private Integer supplyType;
    /**
     * 发单备注
     */
    private String note;


    /*********MQ新增***************************/
    /**
     * 发送方式：supplySendTypeEnum
     */
    private Integer sendType;

    /**
     * 商家文件id
     */
    private Integer merchantCompanyId;

    /**
     * 发送邮件的服务器的IP
     */
    private String emailServerHost;

    /**
     * 发送邮件的服务器的端口
     */
    private String emailServerPort;

    private String userName;

    private String password;

    /**
     * 邮件接收者的地址
     */
    private String receiver;;

    /**
     * 抄送者的地址,以";"隔开
     */
    private String copyTo;

    /**
     *  邮件主题
     */
    private String subject;
}
