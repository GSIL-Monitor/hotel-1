package com.travel.common.enums;
/**
 * @Description : 早餐枚举类
 * @author : Zhiping Sun
 * @date : 2018年1月22日下午4:59:43
 */
public enum BreakfastEnum {

	BED("bed", -1, "床位早"),NONE("none", 0, "无早"), ONE("one", 1, "单早"), TWO("two", 2, "双早"),THREE("three", 3, "三早"),FOUR("four", 4, "四早");
	/*FIVE(5,"五早"),SIX(6,"六早"),SEVEN(7,"七早"),EIGHT(8,"八早"),NINE(9,"九早"),
	MANY(10,"多早"),AMERICAN(11,"美式早"),FULLBOARD(12,"全食宿"),SPECIAL(13,"特惠全食宿"),HAVE(14,"含早"),
	ONEHALF(15,"含1早1份半餐"),MORNINGAFTER(16,"早午餐"),EUROPE(17,"欧陆早"),BUFFET(18,"自助早餐"),
	FULL(19,"全食宿"),ONEFULL(20,"含1份半餐1份全餐"),ENG(21,"英式早"),HALF(22,"半食宿"),IRELAND(23,"爱尔兰早"),
	HALFDRINK(24,"半食宿+饮料"),FULLDRINK(25,"全食宿+饮料"),NOT(26,"不含早"),SCOTLAND(27,"苏格兰早"),SELF(28,"膳食自理"),
	TEN(29,"十早"),ELEVEN(30,"十一早"),TWELVE(31,"十二早"),THIRTEEN(32,"十三早"),FOURTEEN(33,"十四早"),FIFTEEN(34,"十五早");*/

	public String key;
	public Integer num;
	public String value;

	private BreakfastEnum(String key, Integer num, String value) {
		this.num = num;
		this.key = key;
		this.value = value;
	}
	
	public static String getKeyByValue(String value) {
		String key = "";
		for(BreakfastEnum breakfastNumEnum : BreakfastEnum.values()) {
			if(breakfastNumEnum.value.equals(value)) {
				key = breakfastNumEnum.key;
				break;
			}
		}
		return key;
	}

	public static String getKeyByNum(Integer num) {
		String key = "none";
		for(BreakfastEnum breakfastNumEnum : BreakfastEnum.values()) {
			if(breakfastNumEnum.num.intValue() == num) {
				key = breakfastNumEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(String key) {
		String value = null;
		for(BreakfastEnum breakfastNumEnum : BreakfastEnum.values()) {
			if(breakfastNumEnum.key.equals(key)) {
				value = breakfastNumEnum.value;
				break;
			}
		}
		return value;
	}

	public static Integer getNumByKey(String key) {
		Integer num = null;
		for(BreakfastEnum breakfastNumEnum : BreakfastEnum.values()) {
			if(breakfastNumEnum.key.equals(key)) {
				num = breakfastNumEnum.num;
				break;
			}
		}
		return num;
	}
	
	public static BreakfastEnum getEnumByKey(int key){
		BreakfastEnum breakfastNumEnum = null;
		for(BreakfastEnum breakfastNum : BreakfastEnum.values()) {
			if(breakfastNum.key.equals(key)) {
				breakfastNumEnum = breakfastNum;
				break;
			}
		}
		return breakfastNumEnum;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
