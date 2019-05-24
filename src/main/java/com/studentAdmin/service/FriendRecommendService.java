package com.studentAdmin.service;

import com.common.QueryParam;
import com.common.ResultPage;
import com.studentAdmin.domain.FriendRecommend;
import com.studentAdmin.domain.VOs.FriendRecommendVO;


import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-18  15:47
 * @version: 1.0
 */
public interface FriendRecommendService {
    void  insertRecommend( List<FriendRecommend> list);

    /**
     * 检测推荐
     * @param userId
     * @param recommendId
     * @return
     */
    List<FriendRecommend>  checkRecommend(long userId , long recommendId);

    /**
     * 获取好友推荐列表
     * @param param
     * @return
     */
    ResultPage getFriendRecommends(QueryParam param);

}
