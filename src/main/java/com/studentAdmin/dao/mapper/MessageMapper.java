package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  11:24
 * @version: 1.0
 */
@Mapper
@Repository
public interface MessageMapper extends BaseMapper<Message> {

}
