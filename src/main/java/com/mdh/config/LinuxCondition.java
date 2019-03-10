package com.mdh.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是Linux系统
 */
public class LinuxCondition implements Condition {

    /**
     *
     * @param context  判断条件能使用的上下文（环境）
     * @param metadata 当前标注了Condition的注释信息
     * @return
     */
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
        if(property.contains("linux")){
            return true;
        }
        //4.获取到Bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        System.out.println("获取到Bean定义的注册类:" + registry);
        return false;
    }
}
