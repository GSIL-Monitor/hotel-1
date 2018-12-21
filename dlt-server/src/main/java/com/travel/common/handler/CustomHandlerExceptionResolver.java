package com.travel.common.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.travel.common.enums.ResultTypeEnum;
import com.travel.common.exception.ParameterException;

/**
 * @Description 全局异常处理
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日下午12:50:45
 */
@ControllerAdvice
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

	@Override
	@ExceptionHandler(value = Exception.class)
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		//使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常
		FastJsonJsonView view = new FastJsonJsonView();
		Map<String, Object> attributes = new ModelMap();
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			attributes = new HashMap<String, Object>();
		}
		attributes.put("result", ResultTypeEnum.FAIL.key);
		//json格式参数注解验证异常信息获取
		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException man = (MethodArgumentNotValidException) ex;
			BindingResult br = man.getBindingResult();
			if (null != br && br.hasErrors()) {
				StringBuilder sb = new StringBuilder();
				for (ObjectError objectError : br.getAllErrors()) {
					sb.append(objectError.getDefaultMessage());
				}
				attributes.put("errorReason", sb.toString());
			} else {
				attributes.put("errorReason", "参数校验异常");
			}
		} else if (ex instanceof ParameterException) {
			//自定义参数校验异常信息获取
			ParameterException pe = (ParameterException) ex;
			attributes.put("errorReason", pe.getMessage());
		} else if (ex instanceof ServiceException) {
			//自定义业务异常信息获取
			ServiceException se = (ServiceException) ex;
			attributes.put("errorReason", se.getMessage());
		} else {
			//未知异常信息获取(如空指针异常、数组下表越界...)
			attributes.put("errorReason", "服务器繁忙，请稍后重试...");
		}
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			view.setAttributesMap(attributes);
			mv.setView(view);
		} else {
			mv.addAllObjects(attributes);
			mv.setViewName("500");
		}
		logger.error("异常:{}{}", ex.getMessage(), ex);
		return mv;
	}

}
