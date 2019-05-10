package com.studentAdmin.service;

import com.common.QueryParam;
import com.common.ResultPage;
import org.springframework.stereotype.Service;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  13:46
 * @version: 1.0
 */

public interface UserFollowService  {
    /**
     * 获取用户列表
     * @param param
     * @return
     */
    ResultPage getUserFollowList(QueryParam param);

    void takeOffById(long id);

}
