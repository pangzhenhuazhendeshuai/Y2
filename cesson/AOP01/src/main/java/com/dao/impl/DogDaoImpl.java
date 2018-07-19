package com.dao.impl;

import com.dao.DogDao;
import com.entity.Dog;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Repository;

/**
 * 狗狗类Dao接口实现类
 */
@Repository("dogDao9")
public class DogDaoImpl implements DogDao {

    /**
     * 保存数据库到数据库
     */
    @Override
    public String addDogInfo(Dog dog) {
        System.out.println("已经保存狗狗信息到数据库啦！");
        return "保存成功";
    }
}
