package com.fangcang.message.remote.request.weixin.templatemsg;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.remote.enums.WxTemplateMsgConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderProcessTemplateMsgRequestDTO extends WxSendTemplateMsgRequestDTO{
    private static final long serialVersionUID = -8312820143650330550L;

    /**
     * 订单编号
     */
    @NotNull
    private String orderCode;

    /**
     * 入住日期
     */
    @NotNull
    private String checkInDate;

    /**
     * 入住人
     */
    @NotNull
    private String guest;


    /**
     * 订单编号 字体颜色
     */
    private String orderCodeColor = WxTemplateMsgConstants.DEFAULT_COLOR;
    /**
     * 入住日期 字体颜色
     */
    private String checkInDateColor = WxTemplateMsgConstants.DEFAULT_COLOR;
    /**
     * 入住人 字体颜色
     */
    private String guestColor = WxTemplateMsgConstants.DEFAULT_COLOR;


    public OrderProcessTemplateMsgRequestDTO(){
        this.setTemplateCode(WxTemplateMsgConstants.DEAL_ORDER_NOTICE_TEMPLATECODE);
    }

    @Override
    public JSONObject getData() {
        JSONObject data = new JSONObject();

        JSONObject first = new JSONObject();
        first.put("value", this.getTitle()+"\n");
        first.put("color", this.getTitleColor());

        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", this.orderCode);
        keyword1.put("color", this.orderCodeColor);

        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", this.checkInDate);
        keyword2.put("color", this.checkInDateColor);

        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", this.guest);
        keyword3.put("color", this.guestColor);

        JSONObject remark = new JSONObject();
        remark.put("value", this.getRemark()==null?null:"\n"+this.getRemark());
        remark.put("color", this.getRemarkColor());

        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("remark", remark);
        return data;
    }
}
