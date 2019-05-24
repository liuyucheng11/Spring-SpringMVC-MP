package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ThumbComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-10  19:17
 * @version: 1.0
 */
@Mapper
public interface ThumbScoreMapper extends BaseMapper<ThumbComment> {

    void insertThumbScore(long scoreId,long userId);

    ThumbComment checkIsThumbed(long scoreId,long userId);

     void deleteThumb( long id);

}
