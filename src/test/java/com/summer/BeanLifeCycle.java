package com.summer;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by p2p on 2017/8/9.
 */
public class BeanLifeCycle {
    private static void LifeCycleInBeanFactory(){
        //装在配置文件 并启动容器
        Resource res = new ClassPathResource("beanfactory/beans.xml");
        BeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory)bf);
        reader.loadBeanDefinitions(res);

        //注册自定义的类
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyBeanPostProcessor());
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        Car car1 = (Car) bf.getBean("car");
        car1.introduce();
        car1.setColor("红色");

        Car car2 = (Car) bf.getBean("car");

        System.out.println("car1==car2:" + (car1==car2));


        //关闭容器
        ((DefaultListableBeanFactory)bf).destroySingletons();

    }

    public static void main(String[] args){
        LifeCycleInBeanFactory();
    }

}
