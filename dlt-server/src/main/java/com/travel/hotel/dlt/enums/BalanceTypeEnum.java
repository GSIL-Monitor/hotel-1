package com.travel.hotel.dlt.enums;

public enum BalanceTypeEnum {
	FG("FG",1,"现付"), PP("PP",2,"预付"), PKG("PKG",3,"打包");
	
	public String ctripKey;
	public int fcKey;
	public String value;
	
	private BalanceTypeEnum(String ctripKey, int fcKey, String value){
		this.ctripKey = ctripKey;
		this.fcKey = fcKey;
		this.value = value;
	}

    public static String getCtripKeyByFcKey(Integer fcKey){
    	if(null != fcKey)
    	{
    		for (BalanceTypeEnum enumType : BalanceTypeEnum.values()){
                if (enumType.fcKey == fcKey){
                    return enumType.ctripKey;
                }
            }
    	}
    	//支付类型为空，则默认预付
        return "PP";
    }
}
