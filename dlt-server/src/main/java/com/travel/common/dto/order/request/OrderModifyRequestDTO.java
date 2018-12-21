package com.travel.common.dto.order.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.travel.common.dto.ModifyDTO;
import com.travel.common.validate.AddValidate;
import com.travel.common.validate.CreateOrderValidate;

import lombok.Data;

/**
 * @Description : 订单请求参数
 * @author : Zhiping Sun
 * @date : 2018年1月29日上午9:53:19
 */
@Data
public class OrderModifyRequestDTO extends ModifyDTO {

	private static final long serialVersionUID = 4732278148503258866L;
	
	/**
	 * 订单id
	 */
	private Long orderId;
	
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	/**
	 * 酒店id
	 */
	@NotNull(message = "酒店id不能为空", groups = { AddValidate.class })
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	@NotBlank(message = "酒店名称不能为空", groups = { AddValidate.class })
	private String hotelName;
	
	/**
	 * 渠道编码
	 */
	@NotBlank(message = "渠道编码不能为空", groups = { AddValidate.class })
	private String channelCode;
	
	/**
	 * 分销商编码
	 */
	@NotBlank(message = "分销商编码不能为空", groups = { AddValidate.class })
	private String agentCode;
	
	/**
	 * 分销商名称
	 */
	@NotBlank(message = "分销商名称不能为空", groups = { AddValidate.class })
	private String agentName;
	
	/**
	 * 供应商编码
	 */
	@NotBlank(message = "供应商编码不能为空", groups = { AddValidate.class })
	private String supplyCode;
	
	/**
	 * 供应商名称
	 */
	@NotBlank(message = "供应商名称不能为空", groups = { AddValidate.class })
	private String supplyName;
	
	/**
	 * 客户单号
	 */
	private String customerOrderCode;
	
	/**
	 * 确认号
	 */
	private String confirmNo;
	
	/**
	 * 房号
	 */
	private String roomNo;
	
	/**
	 * 入住人
	 */
	@NotBlank(message = "入住人不能为空", groups = { AddValidate.class })
	private String guestName;
	
	/**
	 * 支付方式
	 */
	private String payMethod;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 产品信息
	 */
	@NotBlank(message = "产品信息不能为空", groups = { AddValidate.class })
	private List<OrderDayPriceRequestDTO> orderDayPriceList;
	
	/**
	 * 附加费信息
	 */
	private List<OrderAdditionalRequestDTO> orderAdditionalList;

}
