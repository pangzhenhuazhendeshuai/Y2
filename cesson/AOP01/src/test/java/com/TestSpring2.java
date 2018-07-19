package com;

import com.entity.Dog;
import com.entity.User;
import com.service.DogService;
import com.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring2 {

    @Test
    public void spring2(){
        ApplicationContext context=new ClassPathXmlApplicationContext("AplicationContext2.xml");
        UserService userService=(UserService)context.getBean("userService");

        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("test@xxx.com");

        //调用业务逻辑类接口打印信息
        userService.addInfo(user);
    }

    @Test
    public void spring3(){
        ApplicationContext context=new ClassPathXmlApplicationContext("AplicationContext2.xml");
        DogService dogService=(DogService)context.getBean("dogService9");

        //调用业务逻辑类接口打印信息
        dogService.addDogInfoOf(new Dog("光光",3));
    }
}
