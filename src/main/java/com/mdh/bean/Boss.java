package com.mdh.bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//默认加载在IOC容器中的组件，容器启动会默认调用无参构造器创建对象赋值操作
@Component
public class Boss {


    private Car car;

    //构造器要用的组件，都是从IOC中获取
    //@Autowired
    public Boss(@Autowired Car car) {
        this.car = car;
        System.out.println("Boos的有参构造器");
    }

    public Car getCar() {
        return car;
    }

    //标注在方法上，Spring容器创建当前对象，完成赋值
    //方法使用的参数，自定义类型的值从IOC容器中获取
    //@Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
