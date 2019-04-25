package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.UserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-23  16:23
 * @version: 1.0
 */
@Mapper
public interface UserFriendMapper extends BaseMapper<UserFriend> {

    UserFriend checkFriendShip(@Param("userId") Long userId, @Param("friendId") Long friendId);

    void beFriendShip(@Param("userId") Long userId,@Param("friendId") Long friendId);

    void deleteFriendShip(Long userId ,Long friendId);

    UserFriend qryUserFriend(@Param("userId")Long userId);


}
