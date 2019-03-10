import com.mdh.config.MyConfigOfAutowired;
import com.mdh.config.MyConfigOfProfile;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class ProfileTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigOfProfile.class);

    @Test
    public void profileTest() {

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }
}
