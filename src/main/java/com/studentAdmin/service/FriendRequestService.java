package com.studentAdmin.service;

import com.common.QueryParam;
import com.studentAdmin.domain.VOs.FriendRequestVO;
import com.studentAdmin.domain.common.CommonException;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  21:49
 * @version: 1.0
 */
public interface FriendRequestService {

    void sendRequest( long userId, long receiverId) throws CommonException;
    /**
     * 同意请求
     *
     * @param id
     */
    void agreeRequest( long id);

    /**
     * 拒绝请求
     *
     * @param id
     */
    void rejectRequest( long id);

    /**
     * 查询请求
     * @param param
     * @return
     */
    List<FriendRequestVO> qryRequest(Map param);
}
