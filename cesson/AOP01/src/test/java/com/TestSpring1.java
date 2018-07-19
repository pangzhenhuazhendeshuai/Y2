package com;

import com.entity.TestEntity;
import com.entity.User;
import com.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring1 {

    @Test
    public void spring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AplicationContext1.xml");
        User user=(User)context.getBean("constructorTest");

        System.out.println(user);
    }

    @Test
    public void spring2(){
        ApplicationContext context=new ClassPathXmlApplicationContext("AplicationContext1.xml");
        TestEntity testEntity=(TestEntity)context.getBean("testEntity");

        //测试方法在此
        testEntity.showValue();
    }


    @Test
    public void spring3(){
        ApplicationContext context=new ClassPathXmlApplicationContext("AplicationContext1.xml");
        UserService userService=(UserService)context.getBean("userService");

        User user=null;

        userService.addInfo(user);
    }
}
