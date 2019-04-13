package com.studentAdmin.dao.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:18
 * @version:1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User doUserLogin(String userName);

    public User findUserByName(String userName);

    /**
     * 收藏
     * @param ArticleId
     * @param userId
     */
    void collectArticle(@Param("articleId") Long ArticleId ,@Param("userId") Long userId);



}
