package com.mdh.config;

import com.mdh.bean.Color;
import com.mdh.bean.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import sun.awt.SunHints;


@Configuration
public class MyScopeConfig {

    /**
     *  @Scope 调整作用域
     *  默认是单例的
     *  ConfigurableBeanFactory#SCOPE_PROTOTYPE   prototype  多例
     *  ConfigurableBeanFactory#SCOPE_SINGLETON  singleton   单例
     *  org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST  request
     *  org.springframework.web.context.WebApplicationContext#SCOPE_SESSION  session
     *  prototype   多实例   IOC容器不会调用方法创建对象放在容器中，而是在每次获取对象的才会调用方法创建对象
     *  singleton   单实例(默认值)  IOC容器在启动时就会创建对象放到IOC容器中，以后每次获取都是从容器中拿
     *  request     同一次请求创建一个实例
     *  session     同一次session创建一个实例
     *
     *  懒加载：
     *        单实例bean，默认在容器启动的时候创建对象
     *        可以做到容器启动时不加载对象，在第一次使用（创建）Bean对象，并初始化
     */
    //@Scope
    //@Lazy
    @Bean
    public Person person(){
        System.out.println("给容器中添加Person....");
        return new Person("王五", 25);
    }

    /**
     * @Conditional({Condition})   按照一定的条件进行判断，满足条件给容器中注册bean
     *               类中组件统一设置，放到类上面，当满足条件时这个类的配置的所有Bean才能生效
     *
     * 如果操作系统是Windows，给容器中注册("bill")
     * 如果是Linux，给容器中注册("linus")
     */

    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person_1(){
        System.out.println("给容器中添加Person....");
        return new Person("Bill Gates", 62);
    }

    //修改VM参数  -Dos.name=linux
    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person_2(){
        System.out.println("给容器中添加Person....");
        return new Person("linus", 48);
    }

}
