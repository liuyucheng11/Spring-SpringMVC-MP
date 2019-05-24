package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ArticleScore;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author:liu.yucheng
 * @Date:2019-4-10 10:48
 * @version:1.0
 */
@Mapper
@Repository
public interface ArticleScoreMapper extends BaseMapper<ArticleScore> {

    List<ArticleScoreVO> getScoreByUserId(long userId);

    void deleteById(long id);

    List<ArticleScore> getByArticleIds(List<Long> ids);

    /**
     * 获取点击记录
     */
    List<Long>  getClickLog(Long userId);

}
