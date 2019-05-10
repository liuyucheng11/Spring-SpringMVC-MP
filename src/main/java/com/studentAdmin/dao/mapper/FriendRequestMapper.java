package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.QueryParam;
import com.studentAdmin.domain.FriendRequest;
import com.studentAdmin.domain.VOs.FriendRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  16:15
 * @version: 1.0
 */
@Mapper
@Repository
public interface FriendRequestMapper extends BaseMapper<FriendRequest> {
    /**
     * 发送好友请求
     *
     * @param userId
     * @param receiverId
     */
    void sendRequest(@Param("sendId") long userId, @Param("receiverId") long receiverId);

    /**
     * 同意请求
     *
     * @param id
     */
    void agreeRequest(@Param("id") long id);

    /**
     * 拒绝请求
     *
     * @param id
     */
    void rejectRequest(@Param("id") long id);

    /**
     * 查询请求
     * @param param
     * @return
     */
    List<FriendRequestVO> qryRequest(Map param);


    FriendRequest checkRequestRead(@Param("senderId") long senderId,@Param("receiverId") long receiverId);


}
