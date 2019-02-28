package com.studentAdmin.service.impl;

import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.domain.Dto.ScoreDto;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.domain.VOs.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ArticleServiceImpl {
    @Autowired
    ArticleMapper articleMapper;
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
            totalSum += scoreMap.get(key)*key;
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
                avg = Double.parseDouble(decimalFormat.format(totalSum/(double)total));
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


}
