package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.ArticleScore;
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
     void publishArticle(Article article);

     List<Article> searchArticle(Map param);

     List<ScoreDto> qryScoreMap(Long articleId);

     ArticleVO searchArticleById(Long articleId);

     List<ArticleScoreVO> qryArticleComments(@Param("articleId") Long articleId);

     ArticleScore queryCommentByMap(@Param("articleId") Long articleId,@Param("userId") Long userId);

     Long queryClickNum(@Param("articleId") Long articleId);

     Long queryCollectNum(@Param("articleId") Long articleId);




}
