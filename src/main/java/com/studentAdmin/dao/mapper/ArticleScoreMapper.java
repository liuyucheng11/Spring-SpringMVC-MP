package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ArticleScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author:liu.yucheng
 * @Date:2019-4-10 10:48
 * @version:1.0
 */
@Mapper
@Repository
public interface ArticleScoreMapper extends BaseMapper<ArticleScore> {
}
