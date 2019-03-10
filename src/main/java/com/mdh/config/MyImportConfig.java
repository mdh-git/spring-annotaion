package com.mdh.config;

import com.mdh.bean.Color;
import com.mdh.bean.ColorFactory;
import com.mdh.bean.Red;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Color.class, Red.class, MyImportSelectorConfig.class, MyImportBeanDefinitionRegistrar.class})
public class MyImportConfig {

    /**
     * 给容器中注入组件
     * 1）、包扫描+ 组件标注注解(@Controller、@Service、@Repository、@Configuration)  局限于自己写的类
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]  @Import导入组件，id默认是组件的全类名
     *          1)、@Import(要导入到容器中的组件)，容器就会自动注册这个组件，id默认是全类名
     *          2)、ImportSelector:返回需要导入的组建的全类名数组
     *              String[] selectImports(AnnotationMetadata importingClassMetadata);
     *          3)、ImportBeanDefinitionRegistrar:手动注册bean
     * 4）、使用Spring提供的FactoryBean(工厂Bean)
     *          1)、默认获取到的是工厂Bean调用getObject创建的对象
     *          2)、获取工厂Bean本身,需要在id前面加一个&  : &colorFactoryBean
     */

    @Bean
    public ColorFactory colorFactoryBean(){
        return new ColorFactory();
    }
}
