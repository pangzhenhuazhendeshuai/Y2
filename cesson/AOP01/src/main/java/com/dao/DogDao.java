package com.dao;

import com.entity.Dog;

/**
 * 狗狗类Dao接口
 */
public interface DogDao {

    /**
     * 添加狗狗信息到数据库
     * @param dog
     * @return
     */
    public String addDogInfo(Dog dog);
}
