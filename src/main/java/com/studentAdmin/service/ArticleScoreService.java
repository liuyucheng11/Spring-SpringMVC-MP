package com.studentAdmin.service;

import com.studentAdmin.domain.VOs.ArticleScoreVO;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-10  20:25
 * @version: 1.0
 */
public interface ArticleScoreService {

     List<ArticleScoreVO> getArticleScoreByUserId(long userId) ;

    void deleteScoreById(long id);
}
