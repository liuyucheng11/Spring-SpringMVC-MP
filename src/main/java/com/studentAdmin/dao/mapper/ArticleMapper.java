package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.Dto.ScoreDto;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.domain.VOs.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:17
 * @version:1.0
 */
@Mapper
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    public void publishArticle(Article article);

    public List<Article> searchArticle(String articleTitle);

    public List<ScoreDto> qryScoreMap(Long articleId);

    public ArticleVO searchArticleById(Long articleId);

    public List<ArticleScoreVO> qryArticleComments(@Param("articleId") Long articleId);


}
