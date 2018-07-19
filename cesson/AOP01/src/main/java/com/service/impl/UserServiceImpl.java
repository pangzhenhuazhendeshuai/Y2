package com.service.impl;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    //申明Dao接口
    @Autowired
    @Qualifier("userDao")
    public UserDao dao;

    /**
     * 声明setter方法方便XML文件调用
     * @param dao
     */
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    /**
     * 保存数据到数据库
     */
    @Override
    public void addInfo(User user) {
        //调用Dao层的保存数据方法
        dao.insert(user);
    }
}
