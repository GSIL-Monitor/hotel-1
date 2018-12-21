package com.fangcang.common.systemlog;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.constant.Constant;
import com.fangcang.merchant.domain.SystemLogDO;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.merchant.mapper.SystemLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class SystemLogAspect {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Pointcut("@annotation(com.fangcang.common.systemlog.SystemLog)")
    public void controllerAspect() {}

    @Around(value = "controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Date start = new Date();
        Object result = null;
        String resultString=null;
        Integer status=1;
        try {
            result = joinPoint.proceed();
            if (result != null) {
                resultString = JSON.toJSONString(result);
            } else {
                resultString = "SUCCESS";
            }
            return result;
        } catch (Throwable t) {
            resultString = "EXCEPTION";
            status=0;
            throw t;
        } finally {
            try {
               saveSystemLog(joinPoint,resultString,start,new Date(),status);
            } catch (Throwable t) {
                log.error("save systemLog error", t);
            }
        }
    }

    private void saveSystemLog(ProceedingJoinPoint joinPoint, String resultString, Date start,Date end,Integer status){
        // 获得方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        UserDTO user=(UserDTO)request.getSession().getAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER);
        SystemLogDO systemLogDO=new SystemLogDO();
        systemLogDO.setClassName(className);
        systemLogDO.setMethod(methodName);
        systemLogDO.setRequest(JSON.toJSONString(args));
        systemLogDO.setResponse(resultString);
        systemLogDO.setStart(start);
        systemLogDO.setEnd(end);
        systemLogDO.setCost(end.getTime()-start.getTime());
        systemLogDO.setStatus(status);
        systemLogDO.setCreator(user.getRealName() + "(" + user.getUsername() + ")");
        systemLogDO.setCreateTime(new Date());
        systemLogMapper.insert(systemLogDO);
    }
}
