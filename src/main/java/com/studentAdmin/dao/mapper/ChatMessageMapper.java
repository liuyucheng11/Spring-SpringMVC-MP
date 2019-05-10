package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.studentAdmin.domain.ChatMessage;
import com.studentAdmin.domain.VOs.ChatMessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 好友间聊天信息处理 dao
 *
 * @author: liu.yucheng
 * @Date: 2019-5-5  16:28
 * @version: 1.0
 */
@Mapper
@Repository
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
    /**
     * 查询聊天消息
     * todo 后续考虑集成redis
     *
     * @param userId
     * @param receiverId
     * @return
     */
    List<ChatMessageVO> queryChatMessage(@Param("senderId") long userId, @Param("receiverId") long receiverId);

    /**
     * 发送聊天信息
     * @param userId
     * @param receiverId
     * @param message
     */
    void sendChatMessage(@Param("senderId") long userId, @Param("receiverId") long receiverId, @Param("message") String message);
}
