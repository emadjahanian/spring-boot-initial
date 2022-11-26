package com.example.springboot.Configuration;
import com.example.springboot.service.WebServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
@Aspect
public class AspectCrudConfiguration{
    private final Logger log = LogManager.getLogger(this.getClass());
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.example.springboot.*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.printf("Exception in %s.%s() with cause = %s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    //@Around("execution(* com.example.springboot.controller.WebController.getCustomerInfoByNationalID(..))")
    @Around("within(com.example.springboot.controller.*)")
    public Object userAdvice(ProceedingJoinPoint joinPoint)throws Throwable{

        System.out.printf("request: %s.%s() with argument[s] = %s ", joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),joinPoint.getArgs().toString() +"\n");
        Object result =joinPoint.proceed();
        System.out.printf("response: %s.%s() with argument[s] = %s ", joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),joinPoint.getArgs().toString()+"\n");
        return result;
//        watch.stop();
//        System.out.println("Date : "+simpleDateFormat.format(date));


    }




}
