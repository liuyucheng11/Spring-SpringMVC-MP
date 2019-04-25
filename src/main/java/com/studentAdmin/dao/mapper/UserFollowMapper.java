package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.QueryParam;
import com.studentAdmin.domain.UserFollow;
import com.studentAdmin.domain.VOs.UserFollowVO;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  13:50
 * @version: 1.0
 */
public interface UserFollowMapper extends BaseMapper<UserFollow> {
      List<UserFollowVO> getUserFollowList(QueryParam param);

}
