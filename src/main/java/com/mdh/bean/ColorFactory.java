package com.mdh.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

//创建一个spring定义的FactoryBean
public class ColorFactory implements FactoryBean<Color> {

    //返回一个Color对象，这个对象会被添加到容器中
    @Nullable
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean ----->");
        return new Color();
    }

    //返回值类型
    @Nullable
    public Class<?> getObjectType() {
        return Color.class;
    }

    //是单例吗?
    //true: 这个bean是单实例的，在容器中保存一份
    //false:多实例，每次创建新的
    public boolean isSingleton() {
        return false;
    }
}
