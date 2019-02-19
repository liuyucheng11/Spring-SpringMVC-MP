package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:17
 * @version:1.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    public void publishArticle(Article article);
    public List<Article> searchArticle(String articleTitle);

}
