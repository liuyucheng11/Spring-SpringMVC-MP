package com.studentAdmin.domain.Dto;

import lombok.Data;

/**
 * 分析评分用
 *
 * @author: liu.yucheng
 * @Date: 2019-5-20  15:48
 * @version: 1.0
 */
@Data
public class AnalyseDto {

    private long articleId;
    /**
     * 1. 只评价 2.只收藏 3.评价 + 收藏
     */
    private int type;
    private int score;

    public AnalyseDto(long articleId, int score,int type) {
        this.articleId = articleId;
        this.score = score;
        this.type = type;
    }
}
