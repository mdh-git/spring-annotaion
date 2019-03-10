import com.mdh.bean.Blue;
import com.mdh.bean.Yellow;
import com.mdh.config.MyImportConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyImportConfig.class);

    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println(name);
        }
    }

    @Test
    public void importTest(){

        printBeans(applicationContext);
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        //工厂Bean获取的是调用ColorFactory.getObject创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        Object colorFactoryBean1 = applicationContext.getBean("colorFactoryBean");
        System.out.println("colorFactoryBean的类型" + colorFactoryBean.getClass());
        System.out.println(colorFactoryBean == colorFactoryBean1);

        Object colorFactoryBean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean3.getClass());
    }
}
