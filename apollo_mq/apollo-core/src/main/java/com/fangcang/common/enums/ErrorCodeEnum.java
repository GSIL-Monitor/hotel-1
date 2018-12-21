package com.fangcang.common.enums;

public enum ErrorCodeEnum {

    INVALID_INPUTPARAM("001","INVALID_INPUTPARAM","无效的入参对象"),

    LOGIN_EXCEPTION("100","LOGIN_EXCEPTION","系统繁忙，请您稍后再试。"),

    /****** 成功返回 ***/
    SUCCESS("200","SUCCESS","成功"),

    /********登陆异常 以1开头***/
    LOGIN_FAILED("101","LOGIN_FAILED","登录失败，重新登陆"),
    LOGIN_ERROR_USERNAME_OR_PASSWORD_ERROR("102","LOGIN_USERNAME_OR_PASSWORD_ERROR","用户名密码错误"),
    LOGIN_ERROR_USERNAME_NOT_EXIST("103","LOGIN_USERNAME_NOT_EXIST","用户不存在"),
    LOGIN_ERROR_NOT_LOGIN("104","NOT_LOGIN","还未登录，请先登录"),
    LOGIN_ERROR_HAS_NO_RIGHT("104","LOGIN_ERROR_HAS_NO_RIGHT","权限不足"),

    GET_REDIS_LOCK_FAILED("300","GET_REDIS_LOCK_FAILED","获取锁失败"),
    RETREAT_QUOTA_FAILED("301","RETREAT_QUOTA_FAILED","退配额失败"),

    INVALID_IMAGE_TYPE("400","INVALID_IMAGE_TYPE","无效的图片类型"),
    MAIN_IMAGE_HAS_EXIST("401","MAIN_IMAGE_HAS_EXIST","主图已存在"),

    PRE_BOOKING_FAILED("500","PRE_BOOKING_FAILED","试预定失败"),

    EBK_REBOOK("501","EBK_REBOOK","商家重发预订单，内容有修改，请核对后再确认！"),
    EBK_ORDER_CANCELED("502","EBK_ORDER_CANCELED","商家已取消订单"),

    /********库存异常 以2开头***/
    PRICEPLAN_DELETE_HAS_MAPPINGED("201","PRICEPLAN_DELETE_HAS_MAPPINGED","价格计划已被映射，请先断开映射"),
    ROOMTYPE_DELETE_HAS_PRICEPLANS("202","ROOMTYPE_DELETE_HAS_PRICEPLANS","此房型下还有价格计划。"),


    /***********财务异常 以6开头***********/
    FINANCE_PARAM_IS_NULL("600","FINANCE_PARAM_IS_NULL","参数为空"),
    FINANCE_ORDER_CODE_CANNOT_NULL("601","ORDER_CODE_CANNOT_NULL","业务单号不能为空"),
    FINANCE_ORDER_ID_CREATE_ERROR("602","FINANCE_ORDER_ID_CREATE_ERROR","工单创建失败"),
    FINANCE_ORDER_GET_UNRECEIVED_ERROR("603","FINANCE_ORDER_GET_UNRECEIVED","查询待收款失败"),
    FINANCE_ORDER_GET_RECEIVED_ERROR("604","FINANCE_ORDER_GET_RECEIVED","查询已收款失败"),
    FINANCE_ORDER_GET_UNFINISHED_ERROR("605","FINANCE_ORDER_GET_UNFINISHED","查询未完成失败"),
    FINANCE_ORDER_CANCEL_ERROR("606","FINANCE_ORDER_CANCEL_ERROR","取消失败"),
    FINANCE_ORDER_CONFIRM_ERROR("607","FINANCE_ORDER_CONFIRM_ERROR","收款失败"),
    FINANCE_ORDER_QUERY_DETAIL_ERROR("608","FINANCE_ORDER_CONFIRM_ERROR","查看收款凭证失败"),

    /***********酒店映射异常 以7开头***********/
    MAPPING_HOTEL_RELAITON_EXIST_ERROR("701","MAPPING_HOTEL_RELAITON_EXIST_ERROR","酒店已被映射"),
    MAPPING_HOTEL_RATEPLAN_NOT_EXIST_ERROR("702","MAPPING_HOTEL_RATEPLAN_NOT_EXIST_ERROR","酒店下没有产品，请先去库存添加"),
    MAPPING_HOTEL_DELETE_ERROR("703","MAPPING_HOTEL_DELETE_ERROR","删除映射异常"),

    /***********BMS订单异常 以8开头***********/
    ORDER_BOOK_SAME_ORDER_ERROR("801","ORDER_BOOK_SAME_ORDER_ERROR","客人重复"),


    /***********系统异常类错误代码 以9开头***********/
    SYSTEM_EXCEPTION("900","SYSTEM_EXCEPTION","系统异常"),
    OUTER_IF_EXCEPTION("901","OUTER_IF_EXCEPTION","外部接口调用异常"),
    UNKNOWN_EXCEPTION("902","UNKNOWN_EXCEPTION","未知异常"),
    CONNECT_TIME_OUT("903","CONNECT_TIME_OUT","连接超时"),
    READ_TIME_OUT("904","READ_TIME_OUT","访问超时"),
	PASSWORD_NOT_EQUAL_CONFIRMPASSWD("201","PASSWORD_NOT_EQUAL_CONFIRMPASSWD","新密码和确认新密码不一致"),
	USER_NAME_IDENTICAL("202","USER_NAME_IDENTICAL","登录名重复"),
	ROLE_LIST_ISNULL("204","ROLE_LIST_ISNULL","用户权限列表不可为空"),
	REAL_NAME_ISNULL("203","REAL_NAME_ISNULL","员工姓名不可为空"),
    ORDER_INTERFACE_ISNULL("501","ORDER_INTERFACE_ISNULL","订单接口未配置");


    public String errorNo;
    public String errorCode;//业务场景编号
    public String errorDesc;//业务场景描述

    private ErrorCodeEnum(String errorNo,String errorCode,String errorDesc)
    {
        this.errorNo = errorNo;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public static String getKeyByValue(String errorCode) {
        String key = "000";
        for(ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if(errorCodeEnum.errorCode.equals(errorCode)) {
                key = errorCodeEnum.errorNo;
                break;
            }
        }
        return key;
    }

    public static String getDescByValue(String errorCode) {
        String desc = "";
        for(ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if(errorCodeEnum.errorCode.equals(errorCode)) {
                desc = errorCodeEnum.errorDesc;
                break;
            }
        }
        return desc;
    }

    public static String getErrorCodeByKey(String key) {
        String errorCode = "";
        for(ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if(errorCodeEnum.errorNo.equals(key)) {
                errorCode = errorCodeEnum.errorCode;
                break;
            }
        }
        return errorCode;
    }
}
