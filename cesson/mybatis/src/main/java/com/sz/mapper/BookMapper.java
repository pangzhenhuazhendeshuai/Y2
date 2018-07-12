package com.sz.mapper;
import com.sz.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<BookInfo> selectAllBook();

    int deleteBookByPrimaryKey(@Param("id") Integer id);

    int insertBook(BookInfo bookInfo);

    int updateBook(BookInfo bookInfo);

    List<BookInfo> selectBookByIdAndName(BookInfo bookInfo);

    List<BookInfo> selectBookById(@Param("id") Integer id);
}
