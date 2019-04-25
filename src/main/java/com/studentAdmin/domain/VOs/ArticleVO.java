package com.studentAdmin.domain.VOs;

import com.studentAdmin.domain.Article;
import lombok.Data;

import java.util.Map;

/** 本类用于统计Article的具体数据
 * @author:liu.yucheng
 * @Data:2019-2-22 15:25
 * @version:1.0
 */
@Data
public class ArticleVO extends Article {
    //存储文章评价信息
    Map<Integer,Double> articleScoreMap;
    //评价总数
    Long total;
    //平均分
    Double avgScore;

    Long scoreCount;

    Long collectCount;

}
