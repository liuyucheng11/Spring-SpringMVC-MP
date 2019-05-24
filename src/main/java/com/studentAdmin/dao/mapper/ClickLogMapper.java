package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ClickLog;
import org.apache.ibatis.annotations.Param;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-22  20:10
 * @version: 1.0
 */
public interface ClickLogMapper extends BaseMapper<ClickLog> {

    void insertClickLog(@Param("userId") long userId , @Param("articleId") long articleId);

    ClickLog checkHasRead(@Param("userId") long userId , @Param("articleId") long articleId);


}
