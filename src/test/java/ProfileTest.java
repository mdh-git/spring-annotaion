import com.mdh.bean.Yellow;
import com.mdh.config.MyConfigOfProfile;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class ProfileTest {

    //1.创建一个applicationContext
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    @Test
    public void profileTest() {

        //2.applicationContext 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        //3.注册主配置类
        applicationContext.register(MyConfigOfProfile.class);
        //4.启动刷新容器
        applicationContext.refresh();

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);
    }
}
