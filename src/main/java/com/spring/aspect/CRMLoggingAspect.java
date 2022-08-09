package com.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private final Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.spring.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.spring.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.spring.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        // display method
        logger.info("============>@Before \n method call: " + joinPoint.getSignature().toShortString());

        // display arguments
        Object[] args = joinPoint.getArgs();

        for(Object arg: args){
            logger.info("============>@Before \n arguments: " + arg);
        }

    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result){
        logger.info("============>@AfterReturning \n method call: " + joinPoint.getSignature().toShortString());

        logger.info("============>@AfterReturning \n result: " + result);

    }

}
