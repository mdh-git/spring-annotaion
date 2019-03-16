package com.mdh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : mdh
 * @Date: 2019/3/13
 */
@Aspect//在切面类上加上注解表示是切面类
@Component
public class LogAspects {

    /***
     * 抽取公共的切入点表达式
     * 1.本类引用  pointCut
     * 2.其他的切面引用 com.mdh.aop.LogAspects.pointCut()  方法的全类名
     */
    @Pointcut("execution(public int com.mdh.aop.MathCalculator.*(..))")
    public void pointCut(){}

    /**
     * @Before 在目标方法切入,切入点表达式(指定在那个方法切入)
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        String methodName  = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("调用后连接点方法为:"+ methodName  +",除法运行。。@Before,参数列表是:{ " + args +" }");
    }

    @After("com.mdh.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        String methodName  = joinPoint.getSignature().getName();
        System.out.println("调用后连接点方法为:"+ methodName  +",除法结束。。@After");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        String methodName  = joinPoint.getSignature().getName();
        System.out.println("调用后连接点方法为:"+ methodName  +",除法正常返回。。@AfterReturning,运行结果:{ "+ result +" }");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        String methodName  = joinPoint.getSignature().getName();
        System.out.println("调用后连接点方法为:"+ methodName  + ",除法异常。。@AfterThrowing,异常信息:{ "+ exception +"}");
    }
}
