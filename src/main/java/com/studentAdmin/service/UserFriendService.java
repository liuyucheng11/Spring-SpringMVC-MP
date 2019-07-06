package com.studentAdmin.service;

import com.common.QueryParam;
import com.studentAdmin.domain.UserFriend;
import com.studentAdmin.domain.common.CommonException;

import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  20:56
 * @version: 1.0
 */
public interface UserFriendService {

    void beFriendShip(Long id , Long userId, Long friendId) throws CommonException;

    void deleteFriendShip(Long id);

    List<UserFriend> qryUserFriend(Map param);

}
