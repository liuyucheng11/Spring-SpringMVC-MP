package com.studentAdmin.service.impl;

import com.studentAdmin.dao.mapper.ArticleScoreMapper;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.service.ArticleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-10  20:28
 * @version: 1.0
 */
@Service
public class ArticleScoreServiceImpl implements ArticleScoreService {

    @Autowired
    ArticleScoreMapper articleScoreMapper;
    @Override
    public List<ArticleScoreVO> getArticleScoreByUserId(long userId) {
         List<ArticleScoreVO> articleScoreVOS = articleScoreMapper.getScoreByUserId(userId);
         return articleScoreVOS;
    }

    @Override
    public void deleteScoreById(long id) {
        articleScoreMapper.deleteById(id);
    }
}
