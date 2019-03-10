import com.mdh.bean.Boss;
import com.mdh.bean.Car;
import com.mdh.bean.Color;
import com.mdh.config.MyConfigOfAutowired;
import com.mdh.dao.BookDao;
import com.mdh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigOfAutowired.class);

    @Test
    public void autowiredTest(){
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        //BookDao bookDao = applicationContext.getBean(BookDao.class);
        //System.out.println(bookDao);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);

        System.out.println("IOC容器:" + applicationContext);
        applicationContext.close();
    }
}
