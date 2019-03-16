package com.mdh.config;


import com.mdh.aop.LogAspects;
import com.mdh.aop.MathCalculator;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * AOP  [动态代理]
 *      指在程序运行期间动态的将某段代码切入到指定方法位置进行运行的编程方式
 *
 * 1)、导入AOP模块: spring AOP
 *      导入pom文件
 * 2)、定义一个业务逻辑(MathCalculator),在业务逻辑运行的时候日志进行打印(方法运行之前、方法运行之后、方法出现异常)
 * 3)、定义一个日志切面类(LogAspects),切面类里面的方法需要动态MathCalculator.div运行步骤
 *          通知方法:
 *              前置通知(@Before):Before——在方法调用之前调用通知
 *                      LogAspects.logStart():在目标方法(div)运行前运行
 *
 *              后置通知(@After):After——在方法完成之后调用通知，无论方法执行成功与否
 *                      LogAspects.logEnd():在目标方法(div)运行结束之后运行
 *
 *              返回通知(@AfterReturning):After-returning——在方法执行成功之后调用通知
 *                      LogAspects.logReturn():在目标方法(div)正常返回之后运行
 *
 *              异常通知(@AfterThrowing):After-throwing——在方法抛出异常后进行通知
 *                      LogAspects.logException():在目标方法(div)异常返回之后运行
 *
 *              环绕通知(@Around):Around——通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为
 *                      动态代理,手动推进目标方法运行(joinPoint.proceed())
 * 4)、给切面类的目标方法标注(通知注解)
 * 5)、将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 * 6)、必须告诉Spring哪个是切面类(给切面类上加一个注解 @Aspect:告诉Spring当前类是切面类)
 * 7)、配置类加入 @EnableAspectJAutoProxy 开启基于注解的AOP模式
 *      xml: <aop:aspectj-autoproxy></aop:aspectj-autoproxy> 开启基于注解的切面功能
 *
 *      JoinPoint参数  一定要在参数列表的第一位
 */
@Component
@EnableAspectJAutoProxy
public class MyConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }


    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
