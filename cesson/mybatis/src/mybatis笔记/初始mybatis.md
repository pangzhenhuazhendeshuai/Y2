一、 mybatis简介

> MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。 
>
> ----

- 解放传统JDBC的模板代码（不可以缺少）
- 将结果集进行自定义的丰富的映射
- 半自动的ORM（Object relation mapping）
  - java里面的类映射到数据库的表
  - java里面的属性映射到数据库里面的表里面的字段



# 二、mabatis入门体验

环境约束：

- win10
- JDK8（必须）
- maven3.5（必须）
- idea2018
- mysql5
- UTF-8





## 一 新建maven项目



在中央仓库里面找我们的依赖

```xml
maven repository
```

- 添加依赖
- 编写mybatis的核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>


    <!--编写一下配置文件-->

    <!--配置环境们，配置环境
            生产环境
            测试环境
            开发环境
    -->

    <!--引入外部的属性文件
        在外的属性文件当中的优先级高于
        property子节点
    -->
    <properties resource="db.properties">
        <property name="username" value="root1"></property>
    </properties>

    <environments default="dev">
        <environment id="dev">
            <transactionManager type="JDBC"/>
            <!--支持三种类型 UNPOOLED  POOLED  JNDI-->
            <dataSource type="POOLED">
                <!--数据库连接的四项基本属性-->
                <property name="url" value="${url}"></property>
                <property name="driver" value="${driver}"></property>
                <property name="username" value="${username}"></property>
                <property name="password" value="${password}"></property>
            </dataSource>
        </environment>
    </environments>


</configuration>
```



## 准备测试数据库和表



```sql
DROP DATABASE IF EXISTS `bookmanage`;
/*创建超市订单管理系统数据库*/
CREATE DATABASE `bookmanage`;
USE `bookmanage`;


/*创建地址信息表*/
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ISBN` VARCHAR(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出版商一套书定的编号',
  `name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '书名',
  `price` DOUBLE COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '价格',
  `discount` DOUBLE COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '折扣',
  `publisher` VARCHAR(20) DEFAULT NULL COMMENT '出版社',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `book`(`ISBN`,`name`,`price`,`discount`,`publisher`) VALUES('BILL2016_001','使用SSM框架开发企业级应用','125.00','1','电子工业出版社'),
('BILL2016_002','基于SSH框架的企业级应用开发','130.00','1','电子工业出版社'),
('BILL2016_003','基于Hadoop生态系统的大数据解决方案','150.00','1','电子工业出版社');

SELECT * FROM `book`;
```



## 提供java里面的model(pojo)

- model
- pojo
- domain
- entity
- vo
- dto



```java
package com.sz.entity;

public class Book {
     private Integer id;        //主键ID
     private String ISBN;       //出版商一套书定的编号
     private String name;       //书名
     private double price;      //价格
     private double discount;   //折扣
    private String publisher;  //出版社

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

```



## 编写对应（实体类）的dao接口以及Mapper.xml文件

```java
package com.sz.mapper;
import com.sz.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    List<Book> selectAllBook();

    int deleteBookByPrimayKey(@Param("id") Integer id);

    int insertBook(Book book);

    int updateBook(Book book);
}

```



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--
mybatis的具体的dao对应的配置文件，类似于那个impl

namespace要和我们的dao的接口进行绑定
-->
<mapper namespace="com.sz.mapper.BookDao">


    <!--CRUD都提供了标准的标签
        C:create
        R:retrieve
        U:update
        D:delete
        增删改查
    -->

    <!--对于select而言，我们要通知mybatis，你帮我封装成我指定的数据类型
        list集合（如果你要查询的结果是一个集合，你描述集合当中的元素的是java类型就可以了。）
        如果你要查询的结果就是一个普通的java对象，resultType直接写对应的pojo就可以了。
    -->
    <select id="selectAllBook" resultType="com.sz.entity.Book">
      SELECT * FROM book
    </select>

    <!--删除-->
    <delete id="deleteBookByPrimayKey">
      delete from book where id=#{id}
    </delete>

    <!--新增-->
    <insert id="insertBook" parameterType="com.sz.entity.Book">
        insert into book(ISBN,name,price,discount,publisher)
        values (#{ISBN},#{name},#{price},#{discount},#{publisher})
    </insert>

    <!--修改-->
    <update id="updateBook" parameterType="com.sz.entity.Book">
        update book set ISBN=#{ISBN},name=#{name},price=#{price},discount=#{discount},publisher=#{publisher}
        where id=#{id}
    </update>
</mapper>
```



![1531150429322](E:\Y2\素材\images\1531118250472.png)



## 使用mybatis的核心API进行操作

- 会话工厂
  - 重量级（能不能随意的创建又销毁，一个程序里面有一个就行了）
- 会话
  - 来一次就创建一次。





```java
package com.sz;

import com.sz.dao.StudentDao;
import com.sz.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test1 {


    @Test
    public void m1(){
        // 1 定义mybatis核心配置文件的位置
        String resource = "mybatis.cfg.xml";
        // 2 通过mybatis的工具类加载这个文件为输入流
        InputStream in = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            in = Resources.getResourceAsStream(resource);
            // 3 创建一个会话工厂  会话工厂的建造者（一次性用品）
            sessionFactory = new SqlSessionFactoryBuilder().build(in);
            // 4 利用工厂生产会话
            sqlSession = sessionFactory.openSession();
            // 5 使用会话进行相关的操作

            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            StudentDao mapper = sqlSession.getMapper(StudentDao.class);
            // 
            List<Student> students = mapper.listAll();
            System.out.println(students);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

```





## 要记得在mybatis的核心配置文件当中引入mapper.xml

```xml
<mappers>
    <mapper resource="mappers/StudentMapper.xml"></mapper>
</mappers>
```





## 对mybatis进行封装







## mybatis里面的参数传递的问题

- 简单的属性（单个值）

  写任意值都可以，最好有意义

- 多个简单的属性值传递

  默认是采用

  arg0 arg1    param1 param2

- 如果是一个封装好的javabean(Student)

  以它内部的属性名称为准

- 如果说有多个参数的情况下，建议都要使用@param注解来完成。（任何场景都可以使用）





















 





 























































