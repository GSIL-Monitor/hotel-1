package com.travel.common.exception;

import com.travel.common.enums.ResultTypeEnum;
import lombok.Data;

/**
 * @Description 业务异常
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日 下午10:04:15
 */
@Data
public class ServiceException extends RuntimeException {

	private ResultTypeEnum resultTypeEnum;

	private static final long serialVersionUID = -8714429274522915288L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(ResultTypeEnum resultTypeEnum) {
		super(resultTypeEnum.desc);
		this.resultTypeEnum = resultTypeEnum;
	}

	public ServiceException(ResultTypeEnum resultTypeEnum, String message) {
		super(message);
		this.resultTypeEnum = resultTypeEnum;
	}

	public ServiceException(ResultTypeEnum resultTypeEnum, Throwable cause) {
		super(cause);
		this.resultTypeEnum = resultTypeEnum;
	}

	public ServiceException(ResultTypeEnum resultTypeEnum, String message, Throwable cause) {
		super(message, cause);
		this.resultTypeEnum = resultTypeEnum;
	}
}
