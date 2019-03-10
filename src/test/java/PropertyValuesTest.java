import com.mdh.bean.Person;
import com.mdh.config.MyBeanConfigOfLiveCycle;
import com.mdh.config.MyConfigOfPropertyValues;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class PropertyValuesTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigOfPropertyValues.class);

    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println(name);
        }
    }

    @Test
    public void testLiveCycle(){
        printBeans(applicationContext);
        System.out.println("----------------------------------------------");

        Person person = (Person)applicationContext.getBean("person");
        System.out.println(person);

        //运行时的环境
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println("environment运行时环境变量加载的值:" + property);
        applicationContext.close();
    }

}
