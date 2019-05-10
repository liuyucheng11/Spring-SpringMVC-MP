package com.studentAdmin.service;

import com.studentAdmin.domain.VOs.ChatMessageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  17:06
 * @version: 1.0
 */
public interface ChatMessageService {
    /**
     * 查询聊天消息
     * todo 后续考虑集成redis
     *
     * @param userId
     * @param receiverId
     * @return
     */
    List<ChatMessageVO> queryChatMessage( long userId,  long receiverId);

    /**
     * 发送聊天信息
     * @param userId
     * @param receiverId
     * @param message
     */
    void sendChatMessage(long userId, long receiverId,  String message);
}
