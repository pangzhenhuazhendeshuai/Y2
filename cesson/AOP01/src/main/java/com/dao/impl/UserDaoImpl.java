package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    /**
     * 保存数据库到数据库
     */
    @Override
    public void insert(User user) {
        System.out.println("数据已经保存到数据库啦!");

        throw new RuntimeException("为测试程序运行效果抛出的异常!!!!");
    }
}
