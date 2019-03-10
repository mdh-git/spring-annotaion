package com.mdh.config;

import com.mdh.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportBeanDefinitionRegistrar创建bean
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     *  AnnotationMetadata : 当前类的注解信息
     *  BeanDefinitionRegistry : BeanDefinition注册类
     *                  把所有需要添加到容器中的bean;调用BeanDefinitionRegistry.registerBeanDefinition手工注册
     *
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean red = registry.containsBeanDefinition("com.mdh.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.mdh.bean.Blue");
        if(red && blue){
            //指定Bean定义信息，(Bean的类型，bean的作用域)
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个Bean,指定bean名
            registry.registerBeanDefinition("rainBow", rootBeanDefinition);
        }
    }
}
