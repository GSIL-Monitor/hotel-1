package com.fangcang.common.exception.handle;

import com.fangcang.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 异常处理类，负责抛出各类异常，获取异常信息。 <br>
 */
@Slf4j
public class ExceptionHandle {

	/**
	 * 根据抛出ServiceException(业务服务模块异常)
	 * 
	 * @param e
	 *            捕获的异常
	 * @param errorCode
	 *            异常编码
	 * @throws ServiceException
	 */
	public static void throwServiceException(Exception e, String errorCode){
		log.error(errorCode);
		
		// 抛出新封装的异常
		if (errorCode != null) {
			if(e == null){
				throw new ServiceException(errorCode);
			}else{
				if (e.getCause() != null) {
					log.error(e.getCause().getMessage());
				} else if (e.getMessage() != null) {
					log.error(e.getMessage());
				}
				throw new ServiceException(errorCode ,e);
			}
		} else {
			throw new ServiceException("ServiceException");
		}
	}
}
