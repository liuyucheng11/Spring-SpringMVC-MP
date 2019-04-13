package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ArticleCollect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-13  14:54
 * @version: 1.0
 */
@Mapper
@Repository
public interface ArticleCollectMapper extends BaseMapper<ArticleCollect> {
}
