package com.dao;

import com.entity.User;

/**
 * 用户表Dao接口
 */
public interface UserDao {

    /**
     * 保存数据库到数据库
     */
    public void insert(User user);
}
