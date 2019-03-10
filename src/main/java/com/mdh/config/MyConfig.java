package com.mdh.config;

import com.mdh.bean.Person;
import com.mdh.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration//配置类的注解  配置类 == 配置文件
@ComponentScan(value = "com.mdh",includeFilters = {
        //@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { Controller.class }),
        //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { BookService.class }),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = { MyTypeFilter.class })
        },useDefaultFilters=false)

//@ComponentScan   value: 扫描报的路径
//@ComponentScans(value = { })  包含多个ComponentScan 扫描多个组件
//excludeFilters == Filter[]  指定扫描的时候按照什么规则排除那些组件
// includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})}
//includeFilters == Filter[]  指定扫描的时候包含那些组件
//FilterType.ANNOTATION  按照注解的方式  经常使用
//FilterType.ASSIGNABLE_TYPE  按照指定的类型,子类实现类都能加载进来
//FilterType.ASPECTJ   使用ASPECTJ表达式 不常用
//FilterType.REGEX   正则表达式
//FilterType.CUSTOM  自定义规则
public class MyConfig {

    //给容器注册一个Bean 类型为返回值类型  id为默认方法的名字
    //可以通过value修改id
    @Bean(value = "person")
    public Person person_name(){
        return new Person("李四", 22);
    }
}
