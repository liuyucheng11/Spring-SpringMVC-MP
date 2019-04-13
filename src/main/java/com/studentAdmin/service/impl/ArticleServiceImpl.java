package com.studentAdmin.service.impl;

import com.common.Result;
import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.dao.mapper.ArticleScoreMapper;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.ArticleScore;
import com.studentAdmin.domain.Dto.ScoreDto;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.domain.VOs.ArticleVO;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.ArticleService;
import com.studentAdmin.util.SessionUtil;
import com.studentAdmin.util.UrlGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:26
 * @version:1.0
 */
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    final String catalog = "d:\\Article_recommend_catalog\\";

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleScoreMapper articleScoreMapper;
    final int[] base = {1, 2, 3, 4, 5};
    final DecimalFormat decimalFormat = new DecimalFormat(".00");

    /**
     * 查看文章评分信息
     *
     * @param articleId
     * @return
     */
    @Transactional
    public ArticleVO qryArticleInfo(Long articleId) {
        List<ScoreDto> scoreMapList = articleMapper.qryScoreMap(articleId);
        ArticleVO articleVO = articleMapper.searchArticleById(articleId);
        Map<Integer, Long> scoreMap = new HashMap<>();
        for (ScoreDto scoreDto : scoreMapList) {
            Integer aKey;
            Long aValue;
            aKey = scoreDto.getScore();
            aValue = scoreDto.getCount();
            scoreMap.put(aKey, aValue);
        }
        //处理评价信息以浮点数两位统计
        long total = 0;
        double avg = 0;
        long totalSum = 0;
        for (Integer key : scoreMap.keySet()) {
            totalSum += scoreMap.get(key) * key;
            total += scoreMap.get(key);
        }
        //判断scoreMap是否包含1~5区间，没有的话设为0
        for (int i = 0; i < base.length; i++) {
            if (scoreMap.get(base[i]) == null) {
                scoreMap.put(base[i], 0L);
            }
        }
        Map<Integer, Double> scoreStatistics = new HashMap<>();
        //防止评价为 0 时出现异常
        if (total != 0) {
            for (Integer key : scoreMap.keySet()) {
                Double percent = scoreMap.get(key) / (double) total;
                percent = Double.parseDouble(decimalFormat.format(percent));
                scoreStatistics.put(key, percent);
                avg = Double.parseDouble(decimalFormat.format(totalSum / (double) total));
            }
        } else {
            for (int i = 0; i < base.length; i++) {
                scoreStatistics.put(base[i], (Double) 0.00);
                avg = 0.0;
            }
        }
        articleVO.setArticleScoreMap(scoreStatistics);
        articleVO.setTotal(total);
        articleVO.setAvgScore(avg);
        return articleVO;
    }

    /**
     * 查看文章所有评价 分页显示
     *
     * @param articleId
     * @return
     */
    public List<ArticleScoreVO> qryArticleComments(Long articleId) {
        List<ArticleScoreVO> list = articleMapper.qryArticleComments(articleId);
        return list;
    }

    /**
     * 发表文章
     *
     * @param article
     */
    @Override
    public Result publishArticle(Article article, String content) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        if (user == null) {
            //获取用户信息失败错误
            return Result.error(Common.getCode_1(), Common.getMsg_1());
        }
        String homeUrl = UrlGenerationUtil.generateUrl(catalog, user.getUserId());
        try {
            File file = new File(homeUrl);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.error(Common.getCode_2(), Common.getMsg_2());
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(Common.getCode_2(), Common.getMsg_2());
        }
        article.setHomeUrl(homeUrl);
        article.setUserId(user.getUserId());
        article.setPublishDate(new Date());
        try {
            articleMapper.insert(article);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(Common.getCode_2(), Common.getMsg_2());
        }
        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishComment(ArticleScore articleScore) throws CommonException {
        Long articleId = articleScore.getArticleId();
        Long userId = articleScore.getUserId();
        articleScore.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        if (articleId == null || userId == null){
            throw new CommonException("传入参数异常！");
        }
            if(checkComment(articleId,userId)!=null){
                throw new CommonException("您已经评价过此文章了");
            }
             articleScoreMapper.insert(articleScore);
    }

    @Override
    public Object checkComment(Long articleId, Long userId) {
       return articleMapper.queryCommentByMap(articleId,userId);
     }

}
