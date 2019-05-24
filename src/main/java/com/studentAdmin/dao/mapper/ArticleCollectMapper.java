package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ArticleCollect;
import com.studentAdmin.domain.VOs.ArticleCollectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-13  14:54
 * @version: 1.0
 */
@Mapper
@Repository
public interface ArticleCollectMapper extends BaseMapper<ArticleCollect> {

    /**
     * 删除收藏
     * @param id
     */
    void deleteById(@Param("id") long id);

    /**
     * 根据用户id获取收藏
     * @param userId
     * @return
     */
    List<ArticleCollectVO> getUserCollect(@Param("userId") long userId);

    /**
     * 添加收藏
     * @param userId
     * @param articleId
     */
     void addArticleCollect(@Param("userId") long userId, @Param("articleId") long articleId);

    /**
     * 校验收藏
     * @param userId
     * @param articleId
     * @return
     */
     ArticleCollect checkCollected(@Param("userId") long userId,@Param("articleId") long articleId);

    /**
     * 根据id获取收藏记录
     * @param articleIds
     * @return
     */
     List<ArticleCollect> getByArticleIds(List<Long> articleIds);



}
