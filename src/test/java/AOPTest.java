import com.mdh.aop.MathCalculator;
import com.mdh.config.MyConfigOfAOP;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : mdh
 * @Date: 2019/3/13
 */
public class AOPTest {

    @Test
    public void AopTest(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigOfAOP.class);
        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        calculator.div(5, 0);
        applicationContext.close();
    }
}
