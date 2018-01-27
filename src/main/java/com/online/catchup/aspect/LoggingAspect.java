package com.online.catchup.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void executeLogging() {

    }

    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint) {
        StringBuilder log = new StringBuilder();
        log.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            log.append("arguments = [");
            Arrays.asList(args).forEach(arg -> {
                log.append("arg = ").append(arg).append("|");
            });
        }
        LOGGER.info(log.toString());
    }
}
