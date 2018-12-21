package com.travel.common.exception;
/**
 * @Description 参数异常类
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日 下午10:07:53
 */
public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = 6291174592480851243L;

	public ParameterException() {
		super();
	}

	public ParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}

}
