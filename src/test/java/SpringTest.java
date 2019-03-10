import com.mdh.bean.Person;
import com.mdh.config.MyConfig;
import com.mdh.config.MyScopeConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class SpringTest {

    @Test
    public void spring(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        Person person = (Person)context.getBean("person");
//        System.out.println(person);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] forType = applicationContext.getBeanNamesForType(Person.class);
        for (String name: forType) {
            System.out.println(name);
        }
    }

    @SuppressWarnings("resource")
    @Test
    public void IocTest(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name:beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void Scope(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyScopeConfig.class);
        System.out.println("IOC容器创建完成...");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name:beanDefinitionNames) {
            System.out.println(name);
        }
        //当设置@Scope("prototype")时为多实例    默认是单实例
        Person person = (Person)applicationContext.getBean("person");
        Person person1 = (Person)applicationContext.getBean("person");
        System.out.println(person == person1);
    }

    @Test
    public void conditional(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyScopeConfig.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println("操作系统：" + property);
        String[] forType = applicationContext.getBeanNamesForType(Person.class);
        for (String name:forType) {
            System.out.println(name);
        }

        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);

    }
}
