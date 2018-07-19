package com.service;

import com.entity.User;

/**
 * 用户表业务逻辑接口
 */
public interface UserService {

    /**
     * 保存数据到数据库
     */
    public void addInfo(User user);
}
