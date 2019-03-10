import com.mdh.bean.Car;
import com.mdh.config.MyBeanConfigOfLiveCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestLiveCycle {

    @Test
    public void testLiveCycle(){
        //1.创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBeanConfigOfLiveCycle.class);
        System.out.println("容器创建完成...");

        //
        //Car bean1 = applicationContext.getBean(Car.class);
        //关闭容器
        applicationContext.registerShutdownHook();
    }

}
