package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.Thumbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  0:41
 * @version: 1.0
 */
@Mapper
@Repository
public interface ThumbsMapper extends BaseMapper<Thumbs> {

    void insertThumbs(@Param("userId") long userId, @Param("thumbId") long thumbId);

    Thumbs checkThumbs(@Param("userId") long userId, @Param("thumbId") long thumbId);

    long countThumbsByUserId(@Param("userId") long userId);



}
