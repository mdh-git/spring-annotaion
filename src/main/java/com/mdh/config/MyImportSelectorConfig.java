package com.mdh.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回需要导入的组件
public class MyImportSelectorConfig implements ImportSelector {

    //返回值就是导入到容器中的组件的全类名
    //返回值不能为null  否则会报空指针异常
    //importingClassMetadata 当前标注@Import注解的类的所有注解信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //importingClassMetadata.getAnnotatedMethods()
        return new String[]{"com.mdh.bean.Blue", "com.mdh.bean.Yellow"};
    }
}
