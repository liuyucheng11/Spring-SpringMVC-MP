package com.studentAdmin.service;

import com.common.QueryParam;
import com.common.Result;
import com.common.ResultPage;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.ArticleScore;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.domain.VOs.ArticleVO;
import com.studentAdmin.domain.common.CommonException;

import java.util.List;

/**
 * @author:liu.yucheng
 * @Data:2019-3-5 15:24
 * @version:1.0
 */
public interface ArticleService {
    public ArticleVO qryArticleInfo(Long articleId);

    public List<ArticleScoreVO> qryArticleComments(Long articleId);

    public Result publishArticle(Article article, String content);

    Object checkComment(Long articleId, Long userId);

    void publishComment(ArticleScore articleScore) throws CommonException;

    ResultPage getSelfPublishArticle(QueryParam param);

     void deleteArticle(long id);
}
