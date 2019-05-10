package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.common.ResultPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.studentAdmin.dao.mapper.ArticleCollectMapper;
import com.studentAdmin.domain.ArticleCollect;
import com.studentAdmin.domain.VOs.ArticleCollectVO;
import com.studentAdmin.service.ArticleCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-26  9:45
 * @version: 1.0
 */
@Service
public class ArticleCollectServiceImpl implements ArticleCollectService {

    @Autowired
    ArticleCollectMapper articleCollectMapper;

    @Override
    public void deleteById(long id) {
        articleCollectMapper.deleteById(id);
    }

    @Override
    public ResultPage getUserCollect(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(),param.getLimit());
        List<ArticleCollectVO> articleCollects = articleCollectMapper.getUserCollect((long)param.get("userId"));
        return new ResultPage((int) page.getTotal(), param.getLimit(), page.getPages(), param.getPage(), articleCollects);

    }

    @Override
    public void addArticleCollect(long userId, long articleId) {
           articleCollectMapper.addArticleCollect(userId,articleId);
    }

    @Override
    public ArticleCollect checkCollected(long userId, long articleId) {
        return articleCollectMapper.checkCollected(userId,articleId);
    }
}
