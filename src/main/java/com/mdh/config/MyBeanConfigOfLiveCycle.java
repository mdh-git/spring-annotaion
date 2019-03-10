package com.mdh.config;

import com.mdh.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 的生命周期
 *      bean创建---初始化---销毁的过程
 * 容器管理bean的生命周期
 * 自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用自定义的初始化和销毁
 *
 *  构造(对象创建)
 *         单实例: 在容器启动的时候创建对象
 *         多实例: 在每次获取的时候创建对象
 *
 *  BeanPostProcessor.postProcessBeforeInitialization
 *
 * 初始化
 *          对象创建完成,并赋值好,调用初始化方法..
 *
 * BeanPostProcessor.postProcessAfterInitialization
 *
 * 销毁
 *         单实例: 容器关闭的时候调用销毁
 *         多实例: 容器不会管理这个Bean, 不会调用销毁
 *
 *
 * ****************************************************************************************************************************
 *      BeanPostProcessor原理
 *      遍历得到容器中所有的BeanPostProcessor:逐个执行BeanPostProcessor.postProcessBeforeInitialization(result, beanName);
 *      一但返回返回null，就跳出for循环，不会执行后面的BeanPostProcessor
 *
 *        Object result = existingBean;
 *        for (BeanPostProcessor processor : getBeanPostProcessors()) {
 *        Object current = processor.postProcessBeforeInitialization(result, beanName);
 *            if (current == null) {
 *                return result;
 *            }
 *            result = current;
 *        }
 *       return result;
 *
 *
 *     populateBean(beanName, mbd, instanceWrapper);给Bean属性赋值
 *     exposedObject = initializeBean(beanName, exposedObject, mbd);{
 *          initializeBean方法的调用的简单过程
 *          applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *          invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 *          applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *     }
 *
 ******************************************************************************************************************
 *
 *  1)、指定初始化和销毁
 *          在原来xml指定 <bean init-method="" destroy-method=""> <bean/>
 *          现在  在@Bean指定 @Bean(initMethod = "init", destroyMethod = "destroy")
 *  2)、Bean  初始化:实现InitializingBean接口(定义初始化)
 *            销毁:  实现DisposableBean接口(定义销毁)
 *  3)、可以使用JSR250
 *            @PostConstruct:  在bean创建完成并且属性赋值完成，来执行初始化方法
 *            @PreDestroy:  在容器销毁bean之前通知进行清理工作
 *  4)、BeanPostProcessor(接口): bean的后置处理器
 *            在bean初始化前后进行处理工作
 *            postProcessBeforeInitialization: 在初始化之前
 *            postProcessAfterInitialization: 在初始化之后
 *
 *
 ******************************************************************************************************************
 *      spring底层对 BeanPostProcessor的使用
 *              Bean的赋值，注入其他组件，@Autowired,生命周期注解功能，@Async 使用BeanPostProcessor子类来实现
 */

@ComponentScan(value = {"com.mdh"})
@Configuration
public class MyBeanConfigOfLiveCycle {

    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
