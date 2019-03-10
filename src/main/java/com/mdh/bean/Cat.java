package com.mdh.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cat implements InitializingBean, DisposableBean{

    public Cat(){
        System.out.println("cat  constructor...");
    }

    //初始化方法
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat...  afterPropertiesSet...");
    }

    //销毁方法
    public void destroy() throws Exception {
        System.out.println("cat...  destroy...");
    }


}
