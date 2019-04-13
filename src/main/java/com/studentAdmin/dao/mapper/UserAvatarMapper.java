package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.UserAvatar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-11  10:47
 * @version: 1.0
 */
@Mapper
@Repository
public interface UserAvatarMapper extends BaseMapper<UserAvatar> {

}
