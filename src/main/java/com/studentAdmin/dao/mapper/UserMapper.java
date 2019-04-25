package com.studentAdmin.dao.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.QueryParam;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.UserFollow;
import com.studentAdmin.domain.VOs.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 获取用户信息
     * @param param
     * @return
     */
    List<UserVO> getOtherUser(QueryParam param);


    UserFollow  checkIsFriend(@Param("userId") Long userId ,@Param("followedId") Long followedId);

    void followUser(@Param("userId") Long userId,@Param("followedId") Long followedId);

    List<UserFollow> qryUserFollowByUserId(@Param("userId") Long userId);





}
