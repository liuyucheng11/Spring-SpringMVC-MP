package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.common.ResultPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.studentAdmin.dao.mapper.FriendRecommendMapper;
import com.studentAdmin.domain.FriendRecommend;
import com.studentAdmin.domain.VOs.FriendRecommendVO;
import com.studentAdmin.service.FriendRecommendService;
import com.studentAdmin.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-18  15:50
 * @version: 1.0
 */
@Service
public class FriendRecommendServiceImpl implements FriendRecommendService {

    @Autowired
    FriendRecommendMapper friendRecommendMapper;

    /**
     * 过滤掉已经推荐的文章插入表
     *
     * @param list
     */
    @Override
    public void insertRecommend(List<FriendRecommend> list) {
        long userId = list.get(0).getUserId();
        long recommendId = list.get(0).getFriendId();
        List<FriendRecommend> hasRecommends = this.checkRecommend(userId, recommendId);
        if (hasRecommends == null || hasRecommends.size() == 0) {
            friendRecommendMapper.insertRecommend(list);
        } else {
            Iterator<FriendRecommend> it = list.iterator();
            while (it.hasNext()) {
                FriendRecommend now = it.next();
                boolean flag = false;
                for (FriendRecommend friendRecommend : hasRecommends) {
                    if (friendRecommend.getArticleId() == now.getArticleId()) {
                        flag = true;
                    }
                }
                if (flag) {
                    it.remove();
                }
            }
            friendRecommendMapper.insertRecommend(list);
        }

    }

    @Override
    public List<FriendRecommend> checkRecommend(long userId, long recommendId) {
        return friendRecommendMapper.checkRecommend(userId, recommendId);
    }

    @Override
    public ResultPage getFriendRecommends(QueryParam param) {
        Page<?> page =  PageHelper.startPage(param.getPage(),param.getLimit());
        List<FriendRecommendVO> list = friendRecommendMapper.getFriendRecommends((long)param.get("userId"));
        return  new ResultPage((int) page.getTotal(),param.getLimit(),page.getPages(),param.getPage(),list);
    }
}
