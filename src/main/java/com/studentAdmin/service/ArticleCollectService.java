package com.studentAdmin.service;

import com.common.QueryParam;
import com.common.ResultPage;
import com.studentAdmin.domain.ArticleCollect;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-26  9:44
 * @version: 1.0
 */
public interface ArticleCollectService {
    /**
     * 删除收藏
     * @param id
     */
    void deleteById( long id);

    /**
     * 根据用户id获取收藏
     * @param param
     * @return
     */

    ResultPage getUserCollect(QueryParam param);

    /**
     * 添加收藏
     * @param userId
     * @param articleId
     */
    void addArticleCollect(long userId,  long articleId);

    /**
     * 校验收藏
     * @param userId
     * @param articleId
     * @return
     */
    ArticleCollect checkCollected( long userId, long articleId);


}
