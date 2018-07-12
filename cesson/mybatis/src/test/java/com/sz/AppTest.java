package com.sz;

import com.sz.entity.BookInfo;
import com.sz.entity.Person;
import com.sz.mapper.BookMapper;
import com.sz.mapper.PersonMapper;
import com.sz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * 查询所有图书信息
     */
    @Test
    public void selectAllBook(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookMapper mapper=sqlSession.getMapper(BookMapper.class);
            List<BookInfo> bookInfos = mapper.selectAllBook();
            System.out.println(bookInfos);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    /**
     * 根据ID或图书名查询图书信息
     */
    @Test
    public void selectBookByIdAndName(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(1);
            bookInfo.setName(null);
            List<BookInfo> books = mapper.selectBookByIdAndName(bookInfo);
            if(books.size() <= 0){
                System.out.println("没有查询到该用户的信息哦，请联系管理员或稍后再试！！！");
            }else{
                System.out.println(books);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
    }

    /**
     * 删除图书信息
     */
    @Test
    public void deleteBook(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            int row = mapper.deleteBookByPrimaryKey(1);
            System.out.println("影响行数:"+row);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 新增图书信息
     */
    @Test
    public void insertBook(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            BookInfo bookInfo = new BookInfo();
            bookInfo.setISBN(201807123);
            bookInfo.setName("Javaoop");
            bookInfo.setPrice(125.00);
            //book.setDiscount(1);
            //book.setPublisher("电子工业出版社");
            int row = mapper.insertBook(bookInfo);
            System.out.println("影响行数:" + row);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 修改图书信息
     */
    @Test
    public void updateBook(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(2);
            bookInfo.setISBN(201806123);
            bookInfo.setName("Java");
            bookInfo.setPrice(125.00);
            bookInfo.setDiscount(1);
            bookInfo.setPublisher("电子工业出版社");
            if(bookInfo.getId() != null){
                int row = mapper.updateBook(bookInfo);
                System.out.println("影响行数:" + row);
            }else{
                System.out.println("修改失败，请联系管理员或稍后再试!!!");
            }
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     *查询作者信息顺便把图书信息也查询出来
     */
    @Test
    public void selectBookInfoAndPerson(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            List<BookInfo> bookInfos = mapper.selectBookById(1);
            System.out.println(bookInfos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    /**
     * 根据图书查询出对应的作者
     */
    @Test
    public void selectPersonInfoByBookId(){
        SqlSession sqlSession = null;
        try {
            // 4 利用工厂生产会话
            sqlSession = MybatisUtil.openSession();
            // 5 使用会话进行相关的操作
            // 得到一个Mapper，mybatis本身通过动态代理帮我们去创建实例
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            List<Person> personList = mapper.selectPersonById(1);
            System.out.println(personList);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }



}
