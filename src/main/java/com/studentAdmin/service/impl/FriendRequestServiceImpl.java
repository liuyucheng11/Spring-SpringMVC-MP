package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.studentAdmin.dao.mapper.FriendRequestMapper;
import com.studentAdmin.domain.FriendRequest;
import com.studentAdmin.domain.VOs.FriendRequestVO;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  21:53
 * @version: 1.0
 */
@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    FriendRequestMapper friendRequestMapper;

    @Override
    @Transactional
    public void sendRequest(long userId, long receiverId) throws CommonException {
        //先查询请求表中的记录
        FriendRequest friendRequest = friendRequestMapper.checkRequestRead(userId,receiverId);
        //不为空表示曾经添加过
        if(friendRequest!=null){
            //未读消息
            if(friendRequest.getState() == 0){
               throw new CommonException("您的上次好友请求待确认请耐心等待!");
            }else if(friendRequest.getState() == 1 ){
                throw new CommonException(Common.getCode_17(),Common.getMsg_17());
            }
            else{
                //重新发送请求
                friendRequestMapper.sendRequest(userId,receiverId);
            }
        }
        //为空则直接发送请求
        else{
            friendRequestMapper.sendRequest(userId,receiverId);
        }
    }
    @Override
    public void agreeRequest(long id) {
         friendRequestMapper.agreeRequest(id);
    }

    @Override
    public void rejectRequest(long id) {
         friendRequestMapper.rejectRequest(id);
    }

    @Override
    public List<FriendRequestVO> qryRequest(Map  param) {
        return friendRequestMapper.qryRequest(param);
    }
}

