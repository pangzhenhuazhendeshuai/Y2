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
