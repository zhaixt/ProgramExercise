package com.spring.exercize.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by zhaixiaotong on 2016-7-18.
 */
@Aspect
@Component
public class AopAction {
//    @Around("execution(public * com.spring.exercize.service.impl.ActionTest.*(..))")
//    public void aopExecution(){
//        System.out.println("AOP PRINT AROUND!");
//    }

    //环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型，在通知实现方法内部使用ProceedingJoinPoint的proceed()方法使目标方法执行，proceed 方法可以传入可选的Object[]数组，该数组的值将被作为目标方法执行时的参数。
    @Around("execution(public * com.spring.exercize.service.impl.ActionTest.*(..))")
    public void aopFilter(final ProceedingJoinPoint proceedingJoinPoint){
        try {
            System.out.println("AOP PRINT AROUND Begin!");
            proceedingJoinPoint.proceed();//就是被包围的函数的执行
            System.out.println("AOP PRINT AROUND End!");

            System.out.println(proceedingJoinPoint.getSignature().getDeclaringType());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    //这个是ok的，before，joinPoint.getSignature().getName()的结果是test
//    @Before("execution(public * com.spring.exercize.service.impl.ActionTest.*(..))")
//    public void aopFilter(JoinPoint joinPoint){
//        System.out.println("AOP PRINT Before!");
//        System.out.println("inserted before:"+joinPoint.getSignature().getName());
//    }

}
