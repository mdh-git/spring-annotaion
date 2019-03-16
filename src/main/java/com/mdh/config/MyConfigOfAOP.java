package com.mdh.config;


import com.mdh.aop.LogAspects;
import com.mdh.aop.MathCalculator;
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
 *
 *
 *
 *  AOP原理:【给容器中注册了什么组件,这个组件什么时候工作,这个组件工作时候的功能是什么?】
 *          @EnableAspectJAutoProxy :
 *      1、@EnableAspectJAutoProxy 是什么？
 *              @Import(AspectJAutoProxyRegistrar.class) 给容器中导入 AspectJAutoProxyRegistrar
 *                  利用AspectJAutoProxyRegistrar自定义给容器中注册Bean, BeanDefinition
 *                  internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *
 *              给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *     2、AnnotationAwareAspectJAutoProxyCreator:
 *          AnnotationAwareAspectJAutoProxyCreator
 *              父类-> AspectJAwareAdvisorAutoProxyCreator
 *                  父类-> AbstractAdvisorAutoProxyCreator
 *                      父类-> AbstractAutoProxyCreator
 *                          implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      关注后置处理器(Bean初始化完成前后做事情)、自动装配
 *       AbstractAutoProxyCreator.setBeanFactory()
 *       AbstractAutoProxyCreator.postProcessBeforeInstantiation() 有后置处理器的逻辑
 *                               .postProcessAfterInitialization()
 *
 *       AbstractAdvisorAutoProxyCreator.setBeanFactory() {
 *           initBeanFactory
 *       }重写了这个方法
 *
 *       AspectJAwareAdvisorAutoProxyCreator
 *
 *       AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 * 流程:
 *      1、掺入配置类,创建IOC容器
 *      2、注册配置类,调用refresh() 刷新容器
 *      3、registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
 *              1)、先获取ioc容器中已经定义了的需要创建对象的所有BeanPostProcessor
 *              2)、给容器中加别的BeanPostProcessor
 *              3)、优先实现了PriorityOrdered接口的BeanPostProcessor ---PostProcessorRegistrationDelegate.registerBeanPostProcessors(){}
 *              4)、再给容器中注册了实现了Ordered接口的BeanPostProcessor
 *              5)、注册没实现优先级接口的BeanPostProcessor
 *              6)、注册BeanPostProcessor,实际上就是创建BeanPostProcessor对象,保存在容器中
 *                  创建internalAutoProxyCreator的Bean的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *                  1)、创建Bean的实例
 *                  2)、populateBean: 给bean的各种属性赋值
 *                  3)、initializeBean: 初始化Bean
 *                      1)、 invokeAwareMethods():处理Aware接口方法的回调
 *                      2)、 applyBeanPostProcessorsBeforeInitialization() : 应用后置处理器postBeforeInitialization()
 *                      3)、 invokeInitMethods() : 执行自定义的初始化方法
 *                      3)、 applyBeanPostProcessorsAfterInitialization() : 应用后置处理器postAfterInitialization()
 *                  4)、BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator) 创建成功: aspectJAdvisorBuilder
 *              7)、把BeanPostProcessor注册到BeanFactory中
 *                  beanFactory.addBeanPostProcessor(postProcessor);
 * ==========================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程============================
 *                  AnnotationAwareAspectJAutoProxyCreator -> InstantiationAwareBeanPostProcessor 后置处理器
 *      4、finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例Bean
 *          1)、遍历获取容器中所有的Bean,依次创建对象 getBean(beanName)
 *                  getBean -> doGetBean -> getSingleton
 *          2)、创建bean
 *              [AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截,InstantiationAwareBeanPostProcessor,会调用postProcessBeforeInstantiation()  ]
 *              1)、先从缓存中获取当前bean,如果能获取到,说明bean是之前被创建过的,直接使用; 否则再创建
 *                      只要创建好的bean都会被缓存起来
 *              2)、createBean(): 创建bean:
 *                   AnnotationAwareAspectJAutoProxyCreator会在任何Bean创建之前先尝试返回bean的实例
 *                  [BeanPostProcessor是在Bean对象创建完成初始化前后调用的]
 *                  [InstantiationAwareBeanPostProcessor 是在创建Bean实例之前先尝试使用后置处理器返回对象]
 *                      1)、 resolveBeforeInstantiation(beanName, mbdToUse);解析bean
 *                          希望后置处理器在此返回一个代理对象,如果能返回代理对象就使用,如果不能就继续
 *                          1)、后置处理器先尝试返回对象
 *                              if (targetType != null) {
 * 					                bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 					                       拿到所有后置处理器,如果是 instanceof InstantiationAwareBeanPostProcessor
 * 					                       就执行postProcessBeforeInstantiation方法
 * 					                if (bean != null) {
 * 						                bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 * 					                }
 * 				                }
 *                      2)、doCreateBean(beanName, mbdToUse, args); 真正的去创建一个bean实例, 和上面3.6流程相同
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
