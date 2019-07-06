package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.QueryParam;
import com.studentAdmin.domain.UserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-23  16:23
 * @version: 1.0
 */
@Mapper
public interface UserFriendMapper extends BaseMapper<UserFriend> {

    UserFriend checkFriendShip(@Param("userId") Long userId, @Param("friendId") Long friendId);

    void beFriendShip(@Param("userId") Long userId,@Param("friendId") Long friendId);

    void deleteFriendShip(@Param("id") Long id);

    List<UserFriend> qryUserFriend(Map param);


}
