package com.sz;

import com.sz.entity.Book;
import com.sz.mapper.BookDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void selectAllBook(){
        //1.定义mybatis配置文件的位置
        String resource = "mybatis.cfg.xml";
        // 2 通过mybatis的工具类加载这个文件为输入流
        InputStream is = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            is = Resources.getResourceAsStream(resource);
            // 3 创建一个会话工厂  会话工厂的建造者（一次性用品）
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 4 利用工厂生产会话
            sqlSession = sessionFactory.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookDao mapper=sqlSession.getMapper(BookDao.class);
            List<Book> books = mapper.selectAllBook();
            System.out.println(books);
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }



    @Test
    public void selectBookByIdAndName(){
        //1.定义mybatis配置文件的位置
        String resource = "mybatis.cfg.xml";
        // 2 通过mybatis的工具类加载这个文件为输入流
        InputStream is = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            is = Resources.getResourceAsStream(resource);
            // 3 创建一个会话工厂  会话工厂的建造者（一次性用品）
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 4 利用工厂生产会话
            sqlSession = sessionFactory.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookDao mapper = sqlSession.getMapper(BookDao.class);
            Book book = new Book();
            book.setId(1);
            book.setName(null);
            List<Book> books = mapper.selectBookByIdAndName(book);
            if(books.size() <= 0){
                System.out.println("没有查询到该用户的信息哦，请联系管理员或稍后再试！！！");
            }else{
                System.out.println(books);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
    }

    @Test
    public void deleteBook(){
        //1.定义mybatis配置文件的位置
        String resource = "mybatis.cfg.xml";
        // 2 通过mybatis的工具类加载这个文件为输入流
        InputStream is = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            is = Resources.getResourceAsStream(resource);
            // 3 创建一个会话工厂  会话工厂的建造者（一次性用品）
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 4 利用工厂生产会话
            sqlSession = sessionFactory.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookDao mapper = sqlSession.getMapper(BookDao.class);
            int row = mapper.deleteBookByPrimaryKey(1);
            System.out.println("影响行数:"+row);
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void insertBook(){
        //1.定义mybatis配置文件的位置
        String resource = "mybatis.cfg.xml";
        // 2 通过mybatis的工具类加载这个文件为输入流
        InputStream is = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            is = Resources.getResourceAsStream(resource);
            // 3 创建一个会话工厂  会话工厂的建造者（一次性用品）
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 4 利用工厂生产会话
            sqlSession = sessionFactory.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookDao mapper = sqlSession.getMapper(BookDao.class);
            Book book = new Book();
            book.setISBN("BILL2016_004");
            book.setName("Javaoop");
            book.setPrice(125.00);
            //book.setDiscount(1);
            //book.setPublisher("电子工业出版社");
            int row = mapper.insertBook(book);
            System.out.println("影响行数:" + row);
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateBook(){
        //1.定义mybatis配置文件的位置
        String resource = "mybatis.cfg.xml";
        // 2 通过mybatis的工具类加载这个文件为输入流
        InputStream is = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            is = Resources.getResourceAsStream(resource);
            // 3 创建一个会话工厂  会话工厂的建造者（一次性用品）
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 4 利用工厂生产会话
            sqlSession = sessionFactory.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookDao mapper = sqlSession.getMapper(BookDao.class);
            Book book = new Book();
            book.setId(2);
            book.setISBN("BILL2016_004");
            book.setName("Java");
            book.setPrice(125.00);
            book.setDiscount(1);
            book.setPublisher("电子工业出版社");
            if(book.getId() != null){
                int row = mapper.updateBook(book);
                System.out.println("影响行数:" + row);
            }else{
                System.out.println("修改失败，请联系管理员或稍后再试!!!");
            }
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
