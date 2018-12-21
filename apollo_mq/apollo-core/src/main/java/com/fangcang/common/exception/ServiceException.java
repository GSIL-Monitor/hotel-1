package com.fangcang.common.exception;

import com.fangcang.common.enums.ErrorCodeEnum;

/**
 * Service层异常
 * @author ivan
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -1463598391449257172L;

	public ServiceException() {
		super();
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


	private ErrorCodeEnum errorCode;

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceException(ErrorCodeEnum errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ServiceException(ErrorCodeEnum errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
}
