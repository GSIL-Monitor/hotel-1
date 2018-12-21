package com.travel.hotel.dlt.dao;

public enum ChannelOrderStatusEnum {
	CTRIP_NO_READ_NO_HANDLE("ebk",0,"未读未处理",0),
	CTRIP_READ_NO_HANDLE("ebk",1,"已读未处理",0),
	CTRIP_HANDLED("ebk",100,"已处理",1),
	CTRIP_REFUSED("ebk",101,"已拒绝",2),
	QUNAR_NO_PAY("qunar",1,"待预留（待支付）",3),
	QUNAR_PROCESSING("qunar",2,"待安排",0),
	QUNAR_TRADE("qunar",3,"已确认",0),
	QUNAR_STAY("qunar",4,"已入住",0),
	QUNAR_PAY("qunar",5,"待预留（已支付）",0),
	QUNAR_WAIT_PAY("qunar",6,"待支付",3),
	QUNAR_CANCEL("qunar",7,"已取消（OTA拒绝）",1),
	QUNAR_REFUND("qunar",8,"待退款",0),
	QUNAR_CANCEL_NO_HANDLE("qunar",11,"已取消-未处理",0),
	QUNAR_CANCEL_HANDLE("qunar",9,"已取消-已处理",1),
	QUNAR_REFUSE_CANCEL("qunar",10,"拒绝退订",2),
	QUNAR_NO_HANDLE("qunar",13,"未处理",0),
	B2B_NEW("b2b",600,"新订单",3),
	B2B_NO_HANDLE("b2b",1,"未处理",0),
	B2B_HANDLE("b2b",100,"已处理",1),
	B2B_REFUSE("b2b",101,"已拒绝",2),
	B2B_CANCEL("b2b",500,"已取消",1),
	B2BOFFLINE_WAIT_HANDLE("b2boffline",1,"待处理",0),
	B2BOFFLINE_ACCEPT("b2boffline",2,"已接受",1),
	B2BOFFLINE_REFUSE("b2boffline",3,"已拒绝",2),
	B2BOFFLINE_CANCEL("b2boffline",4,"已取消",1),
	B2BOFFLINE_REFUSE_CANCEL("b2boffline",5,"拒绝取消",2),
	B2BOFFLINE_NO_HANDLE("b2boffline",6,"未处理",0),
	CHANNELA_WAIT_HANDLE("tc",1,"待确认",0),
	CHANNELA_TRADE_WAIT_HANDLE("tc",2,"已确认-待处理",0),
	CHANNELA_CANCELING_WAIT_HANDLE("tc",3,"取消中-待处理",0),
	CHANNELA_TRADE_HANDLE("tc",4,"已确认-已处理",1),
	CHANNELA_CANCEL_HANDLE("tc",5,"已取消-已处理",1),
	CHANNELA_NO_HANDLE("tc",6,"未处理",0);

	public String channel;
	public int orderStatus;
	public String value;
	/**
	 * 处理状态：0未处理，1已处理，2已拒绝，3未支付，不需要处理
	 */
	public int processState;

	private ChannelOrderStatusEnum(String channel, int orderStatus, String value,int processState){
		this.channel = channel;
		this.orderStatus = orderStatus;
		this.value = value;
		this.processState=processState;
	}

    public static String getValueByChannelAndStatus(String channel,Integer orderStatus){
    	if(null != channel && null != orderStatus)
    	{
    		for (ChannelOrderStatusEnum enumType : ChannelOrderStatusEnum.values()){
                if (enumType.channel.equalsIgnoreCase(channel) && enumType.orderStatus == orderStatus){
                    return enumType.value;
                }
            }
    	}

        return null;
    }

	public static Integer getProcessStateByChannelAndStatus(String channel,Integer orderStatus){
		if(null != channel && null != orderStatus)
		{
			for (ChannelOrderStatusEnum enumType : ChannelOrderStatusEnum.values()){
				if (enumType.channel.equalsIgnoreCase(channel) && enumType.orderStatus == orderStatus){
					return enumType.processState;
				}
			}
		}
		return null;
	}
}
