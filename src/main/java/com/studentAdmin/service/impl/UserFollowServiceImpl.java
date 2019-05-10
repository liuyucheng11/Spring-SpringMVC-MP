package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.common.ResultPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.studentAdmin.dao.mapper.UserFollowMapper;
import com.studentAdmin.domain.UserFollow;
import com.studentAdmin.domain.VOs.UserFollowVO;
import com.studentAdmin.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  13:47
 * @version: 1.0
 */
@Service
public class UserFollowServiceImpl implements UserFollowService {
    @Autowired
    UserFollowMapper  userFollowMapper;


    @Override
    public ResultPage getUserFollowList(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(),param.getLimit());
        List<UserFollowVO> list =  userFollowMapper.getUserFollowList(param);
        return  new ResultPage((int) page.getTotal(),param.getLimit(),page.getPages(),param.getPage(),list);
    }

    @Override
    public void takeOffById(long id) {
          userFollowMapper.deleteFollowById(id);
    }
}
