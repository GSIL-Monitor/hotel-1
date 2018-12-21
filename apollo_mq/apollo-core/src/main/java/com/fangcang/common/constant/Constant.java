package com.fangcang.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局常量
 *
 * @author zhanwang
 */
public class Constant {

    /**
     * 是或否
     */
    public static final Integer YES = 1;
    public static final Integer NO = 0;


    /**
     * 文件上传真实路径和URL前缀
     */
    //public static final String FILEDIRP_REFIX = "D:\\temp";
    /**
     * product
     */
    public static final String FILEDIRP_REFIX = "/data/image/apollo";
    public static final String FILEURL_REFIX = "http://apolloimage.fangcang.com/apollo";


    /**
     * 订单附件上传目录
     */
    public static final String FILE_UPLOAD_ORDER_DIR = "apollo_order";
    /**
     * 订单附件允许上传的格式
     */
    public static final String[] ORDER_ALLOW_TYPES = {".jpg", ".jpeg", ".bmp", ".png", ".JPG", ".JPEG", ".BMP", ".gif", ".GIF", ".xls", ".xlsx", ".pdf", ".doc", ".docx"};
    /**
     * 订单财务附件上传目录
     */
    public static final String FILE_UPLOAD_FINANCE_DIR = "apollo_finance";
    /**
     * 订单财务附件允许上传的格式
     */
    public static final String[] ORDER_FINANCE_ALLOW_TYPES = {".jpg", ".jpeg", ".bmp", ".png", ".JPG", ".JPEG", ".BMP", ".gif", ".GIF", ".xls", ".xlsx"};


    /**
     * session用户信息
     */
    public static final String SESSION_ATTRIBUTE_CURRENT_USER = "CURRENT_USER";

    public static final String SESSION_ATTRIBUTE_CURRENT_MERCHANTCODE = "CURRENT_MERCHANTCODE";

    public static final String SESSION_ATTRIBUTE_CURRENT_AGENTCODE = "CURRENT_AGENTCODE";

    public static final String DOMAIN_AND_USER_SEPARATOR = "|";

    public static final String DOMAIN_AND_USER_SEPARATOR_SPLIT = "\\|";

    public static final String USERNAME = "username";


    public static final String DEBUCT_QUOTA_KEY_PREFIX = "apollo:product:debuct:";

    public static final String ORDER_MESSAGE_KEY_PREFIX = "order:message:";

    public static final String SUPPLY_ORDER_CONFIRM_MESSAGE_KEY_PREFIX = "supplyorder:message:";

    /**
     * 酒店基本信息上传运行的图片
     */
    public static final String[] IMAGE_ALLOW_TYPES = {"PNG", "JPG", "JPEG"};
    /**
     * 酒店主图路径
     */
    public static final String IMAGE_PATH = "hotelInfo";
    /**
     * 供应商合同路径
     */
    public static final String SUPPLY_CON_PATH = "supply/contractFile";
    /**
     * 分销商主图路径
     */
    public static final String AGENT_CON_PATH = "agent/contractFile";
    /**
     * 宽带网络map
     */
    public final static Map<String, String> networkMap = new HashMap<String, String>();

    static {
        networkMap.put("公共区域提供wifi", null);
        networkMap.put("房间提供WiFi", null);
        networkMap.put("房间提供有线上网", null);
    }

    /**
     * 停车场map
     */
    public final static Map<String, String> stopCarMap = new HashMap<String, String>();

    static {
        stopCarMap.put("免费停车场", null);
        stopCarMap.put("收费停车场", null);

    }

    /**
     * 餐饮设施map
     */
    public final static Map<String, String> foodMap = new HashMap<String, String>();

    static {
        foodMap.put("中式餐厅", null);
        foodMap.put("西式餐厅", null);
        foodMap.put("餐厅", null);
        foodMap.put("无烟区", null);
        foodMap.put("酒吧", null);
        foodMap.put("泳池水上吧台", null);
        foodMap.put("咖啡厅", null);
        foodMap.put("自定义", null);
    }

    /**
     * 酒店服务
     */
    public final static Map<String, String> hotelMap = new HashMap<String, String>();

    static {
        hotelMap.put("免费接机/接站服务", null);
        hotelMap.put("收费接机/接站服务", null);
        hotelMap.put("商务中心", null);
        hotelMap.put("会议室", null);
        hotelMap.put("宴会厅", null);
        hotelMap.put("24小时前台接待", null);
        hotelMap.put("外币兑换设施", null);
        hotelMap.put("洗衣服务", null);
        hotelMap.put("医疗服务", null);
        hotelMap.put("叫醒服务", null);
        hotelMap.put("残疾人设施", null);
        hotelMap.put("工作时段的儿童看护中心", null);
        hotelMap.put("儿童免费住宿，就餐收费", null);
        hotelMap.put("看护小孩", null);
        hotelMap.put("自助洗衣", null);
        hotelMap.put("多语种工作人员", null);
        hotelMap.put("行李寄存", null);
        hotelMap.put("接待外宾服务", null);
        hotelMap.put("24小时安全监控", null);
        hotelMap.put("轮椅无障碍设施", null);
        hotelMap.put("升降梯", null);
        hotelMap.put("专职行李员", null);
        hotelMap.put("公用卫生间", null);
        hotelMap.put("租车服务", null);
        hotelMap.put("代客泊车", null);
        hotelMap.put("机场巴士", null);
        hotelMap.put("车送车接服务", null);
        hotelMap.put("自行车出租服务", null);
        hotelMap.put("自行车存放处", null);
        hotelMap.put("花园", null);
        hotelMap.put("露台", null);
        hotelMap.put("阳光露台", null);
        hotelMap.put("衣帽间", null);
        hotelMap.put("超市", null);
        hotelMap.put("商店", null);
        hotelMap.put("自定义", null);

    }

    /**
     * 房间设施map
     */
    public final static Map<String, String> roomMap = new HashMap<String, String>();

    static {
        roomMap.put("洗漱用品", null);
        roomMap.put("互联网接口", null);
        roomMap.put("无线网络", null);
        roomMap.put("书桌", null);
        roomMap.put("熨衣套件", null);
        roomMap.put("浴室", null);
        roomMap.put("浴缸", null);
        roomMap.put("拖鞋", null);
        roomMap.put("浴袍", null);
        roomMap.put("淋浴", null);
        roomMap.put("吹风机", null);
        roomMap.put("化妆镜", null);
        roomMap.put("迷你吧", null);
        roomMap.put("厨房", null);
        roomMap.put("小厨房", null);
        roomMap.put("厨具、盘子和杯子", null);
        roomMap.put("泡茶机/咖啡机", null);
        roomMap.put("微波炉", null);
        roomMap.put("冰箱", null);
        roomMap.put("迷你冰箱", null);
        roomMap.put("烹饪机", null);
        roomMap.put("大床（King-size）", null);
        roomMap.put("双人床", null);
        roomMap.put("超大床（Queen-size）", null);
        roomMap.put("加床按需提供", null);
        roomMap.put("枕头多样可选", null);
        roomMap.put("折叠床按需提供", null);
        roomMap.put("沙发床", null);
        roomMap.put("双层床", null);
        roomMap.put("小孩与父母同床", null);
        roomMap.put("中央空调", null);
        roomMap.put("可自调空调", null);
        roomMap.put("中央暖气", null);
        roomMap.put("可自调暖气", null);
        roomMap.put("风扇", null);
        roomMap.put("壁炉", null);
        roomMap.put("卫星电视", null);
        roomMap.put("闭路电视", null);
        roomMap.put("付费电影", null);
        roomMap.put("DVD影碟机", null);
        roomMap.put("光盘播放机", null);
        roomMap.put("220伏电压介入", null);
        roomMap.put("插头适配器", null);
        roomMap.put("直拨电话", null);
        roomMap.put("电话机", null);
        roomMap.put("吸烟室", null);
        roomMap.put("保险柜", null);
        roomMap.put("无障碍卫生间", null);
        roomMap.put("轮椅无障碍设施", null);
        roomMap.put("闹钟", null);
        roomMap.put("烟雾报警器", null);
        roomMap.put("阳台", null);
        roomMap.put("露台", null);
        roomMap.put("熨裤板", null);
        roomMap.put("水疗按摩浴", null);
        roomMap.put("起居室", null);
        roomMap.put("房间面积(平方米)", null);
        roomMap.put("卧室数量", null);
        roomMap.put("自定义", null);
    }

    /**
     * 餐型map
     */
    public final static Map<String, String> foodTypeMap = new HashMap<String, String>();

    static {
        foodTypeMap.put("早餐", null);
        foodTypeMap.put("自助早餐", null);
        foodTypeMap.put("热早餐", null);
        foodTypeMap.put("点餐早餐", null);
        foodTypeMap.put("餐桌早餐", null);
        foodTypeMap.put("欧陆式早餐", null);
        foodTypeMap.put("自助午餐", null);
        foodTypeMap.put("点餐午餐", null);
        foodTypeMap.put("套餐午餐", null);
        foodTypeMap.put("自助晚餐", null);
        foodTypeMap.put("点餐晚餐", null);
        foodTypeMap.put("套餐晚餐", null);
        foodTypeMap.put("全包", null);
        foodTypeMap.put("全包并含非酒类饮品", null);
        foodTypeMap.put("全包但不含饮料", null);
        foodTypeMap.put("全包并含自有品牌酒类饮品", null);
        foodTypeMap.put("全包并含高端品牌酒类饮品", null);
        foodTypeMap.put("全食宿", null);
        foodTypeMap.put("全日餐并含饮料", null);
        foodTypeMap.put("全日餐不含饮料", null);
        foodTypeMap.put("半日餐不含饮料", null);
        foodTypeMap.put("半日餐并含非酒类饮品", null);
        foodTypeMap.put("半日餐并含饮料", null);
        foodTypeMap.put("半食宿", null);
        foodTypeMap.put("早午餐", null);
        foodTypeMap.put("早餐加午餐", null);
        foodTypeMap.put("早餐加晚餐", null);
        foodTypeMap.put("点心", null);
        foodTypeMap.put("生日餐并含非酒类饮品", null);
        foodTypeMap.put("晚宴", null);
        foodTypeMap.put("特殊饮食选择", null);
        foodTypeMap.put("自定义", null);
    }
}