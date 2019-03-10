package com.mdh.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    //使用@Value赋值
    //1.基本数值
    //2.可以使用SpEL   #{}
    //3.可以使用${}  取出配置文件(properties)的值(在运行环境变量里面的值)
    @Value("mdh")
    private String name;

    @Value("#{2 * 12}")
    private Integer age;

    //之前的方法 <context:property-placeholder location="classpath:person.properties"/>
    @Value("${person.nickName}")
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
