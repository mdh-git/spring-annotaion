package com.mdh.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mdh.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Profile:
 *      Spring提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 *
 *          开发环境、测试环境、生产环境
 *  数据库  (/A)       (/B)      (/C)
 *
 *  @Profile : 指定组件在那个环境的情况下才能被注册到容器中，不指定任何环境下都能注册这个组件
 *
 *  1)、加了环境标识的bean,只有这个环境被激活的时候才能注册到容器中，默认设置@Profile("default")才能激活
 *
 *          1)、使用参数命令行动态参数  VM 加上 : -Dspring.profile.active = dev
 *          2)、使用代码的方式某个环境
 *                    1.创建一个applicationContext(注意这里使用的无参构造)(步骤和有参构造器一样)
 *                           AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
 *
 *                              与有参构造比较
 *                              public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
 *                                              this();
 *                                              register(annotatedClasses);
 *                                              refresh();
 *                                              }
 *                    2.applicationContext 设置需要激活的环境
 *                           applicationContext.getEnvironment().setActiveProfiles("dev");
 *                    3.注册主配置类
 *                           applicationContext.register(MyConfigOfProfile.class);
 *                    4.启动刷新容器
 *                          applicationContext.refresh();
 *
 *  2)、写在配置类上，只有是指定的环境的时候，整个配置里面的所有的配置才能生效
 *  3)、没有标注环境标识的Bean，在每个环境下面都是加载的
 */
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MyConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver resolver;

    private String value;


    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
        value = resolver.resolveStringValue("${db.driverClass}");
    }

    @Bean("yellow")
    public Yellow yellow(){
        return new Yellow();
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        dataSource.setDriverClass(value);
        return dataSource;
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(value);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
        dataSource.setDriverClass(value);
        return dataSource;
    }
}
