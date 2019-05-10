package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.studentAdmin.dao.mapper.FriendRequestMapper;
import com.studentAdmin.dao.mapper.UserFriendMapper;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.UserFriend;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  20:59
 * @version: 1.0
 */
@Service
public class UserFriendServiceImpl implements UserFriendService {
    @Autowired
    UserFriendMapper userFriendMapper;
    @Autowired
    FriendRequestMapper friendRequestMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void beFriendShip(Long id, Long userId, Long friendId) throws CommonException {

        UserFriend userFriend = userFriendMapper.checkFriendShip(userId, friendId);
        if (userFriend == null) {
            friendRequestMapper.agreeRequest(id);
            userFriendMapper.beFriendShip(userId, friendId);
        } else {
            throw new CommonException(Common.getCode_17(),Common.getMsg_17());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFriendShip(Long userId, Long friendId) {
        userFriendMapper.deleteFriendShip(userId, friendId);
    }

    @Override
    public List<UserFriend> qryUserFriend(Map param) {
        return  userFriendMapper.qryUserFriend(param);
     }
}
