package com.mdh.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是Windows系统
 */
public class WindowsCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1.获取IOC当前使用的Bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        System.out.println("获取IOC当前使用的Bean工厂:" + beanFactory);
        //2.获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        System.out.println("类加载器:" + classLoader);
        //3.获取当前环境信息
        Environment environment = context.getEnvironment();
        System.out.println("获取当前环境信息:" + environment);
        //获取当前的操作系统
        String property = environment.getProperty("os.name");
        System.out.println("获取当前的操作系统:" + property);
        //4.获取到Bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        //判断容器中是否包含某个类
        boolean definition = registry.containsBeanDefinition("person");
        System.out.println("判断当前类是否包含person:" + definition);
        //registry.registerBeanDefinition(); 给容器中注册Bean
        System.out.println("获取到Bean定义的注册类:" + registry);
        if(property.contains("Windows")){
            return true;
        }
        return false;
    }
}
