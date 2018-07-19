package com.service.impl;

import com.dao.DogDao;
import com.entity.Dog;
import com.service.DogService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 狗狗表业务逻辑接口实现类
 */
@Service("dogService9")
public class DogServiceImpl implements DogService {
    //声明狗狗类Dao接口
    @Autowired
    @Qualifier("dogDao9")
    public DogDao dogDao;

    /**
     * 声明狗狗类Dao接口setter方法
     * @param dogDao
     */
    public void setDogDao(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    /**
     * 添加狗狗信息到数据库
     * @param dog
     * @return
     */
    @Override
    public String addDogInfoOf(Dog dog) {
        //调用Dao接口方法
        return dogDao.addDogInfo(dog);
    }

}
