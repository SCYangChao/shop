package com.yqkj.log;

import com.alibaba.fastjson.JSONObject;
import com.yqkj.constant.CommonConstant;
import com.yqkj.dto.LogDto;
import com.yqkj.utile.IpUtile;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


/**
 *
  * class_name: ControllerAop
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午6:25
  *
 **/

@Slf4j
@Aspect
@Component
public class LogAop {


    @Autowired(required = false)
    private ILogPersistentService  logPersistentService;


    @Pointcut("execution(public com.yqkj.dto.Response *(..))")
    public void LogAop() {
    }

    /**
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("LogAop()")
    private Object methodHandler(ProceedingJoinPoint pjp) throws  Throwable {
        Thread.currentThread().setName(String.format("api-%s" , RandomStringUtils.random(10, CommonConstant.RANDOM_CHAR)));
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Method methods = signature.getMethod();
        WriteLog writeLog = methods.getAnnotation(WriteLog.class);
        if (!Objects.isNull(writeLog) && !writeLog.isWrite()) {
            return  pjp.proceed();
        }
        String url = "" ,ip = null ,method = null,param = JSONObject.toJSONString(pjp.getArgs());
        if (null != attributes ) {
            HttpServletRequest request = attributes.getRequest();
            ip = IpUtile.getIpAddr(request);
            method = request.getMethod();
            url = request.getRequestURL().toString();
            log.info("URL : " + url);
            log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            log.info("ARGS : " + Arrays.toString(pjp.getArgs()));
        }

        Object result;
        Long coustTime = 0L;
        Integer httpStatus = 200;
        String exception = "";


        try {
            result = pjp.proceed();
            coustTime = System.currentTimeMillis() - startTime;
            log.info(pjp.getSignature() + "use time:" + coustTime);
        } catch (Throwable e) {
            httpStatus = 500;
            exception = e.getMessage();
            throw  e;

        } finally {
            if (!Objects.isNull(writeLog) && !writeLog.isWrite()) {

            }else {
                //日誌寫入
                LogDto logDto = new LogDto();
                logDto.setLogTime(new Date());

                logDto.setCostTime(coustTime.intValue());
                logDto.setException(exception);
                logDto.setMethod(method);
                logDto.setParams(param);
                logDto.setRequestUri(url);
                logDto.setIp(ip);
                logDto.setResultStatus(httpStatus);
                logDto.setModel("系统模块");

                if (!Objects.isNull(writeLog)) {
                    logDto.setModel(writeLog.model());
                    logDto.setTitle(writeLog.tile());
                }
                if (StringUtils.isBlank(logDto.getTitle())) {
                    ApiOperation apiOperation = methods.getAnnotation(ApiOperation.class);

                    if (!Objects.isNull(apiOperation)) {

                        logDto.setTitle(apiOperation.value());

                    }


                }
                writeLog(logDto);
            }
        }

        return result;

    }

    /**
     * @param logDto
     */
    private  void writeLog(LogDto logDto) {


        if (null != this.logPersistentService) {
            try {

                this.logPersistentService.excute(logDto);

            }catch (Exception e) {

                log.error("writeLog:{};error" ,e);

            }
        }


    }
}
