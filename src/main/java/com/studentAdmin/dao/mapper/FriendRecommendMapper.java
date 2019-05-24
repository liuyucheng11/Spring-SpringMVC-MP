package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.FriendRecommend;
import com.studentAdmin.domain.VOs.FriendRecommendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-18  15:08
 * @version: 1.0
 */
@Mapper
public interface FriendRecommendMapper extends BaseMapper<FriendRecommend> {

    /**
     * 批量插入
     * @param list
     */
    void  insertRecommend(@Param("list")List<FriendRecommend> list);

    /**
     * 检测是否推荐
     * @param userId
     * @param recommendId
     * @return
     */
    List<FriendRecommend>  checkRecommend(@Param("userId") long userId ,@Param("recommendId") long recommendId);

    /**
     * 获取好友推荐列表
     * @param userId
     * @return
     */
    List<FriendRecommendVO> getFriendRecommends(@Param("userId") long userId);




}
