package com.studentAdmin.service.impl;

import com.studentAdmin.dao.mapper.ChatMessageMapper;
import com.studentAdmin.domain.ChatMessage;
import com.studentAdmin.domain.VOs.ChatMessageVO;
import com.studentAdmin.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  17:10
 * @version: 1.0
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    ChatMessageMapper chatMessageMapper;

    @Override
    public List<ChatMessageVO> queryChatMessage(long userId, long receiverId) {
        return chatMessageMapper.queryChatMessage(userId, receiverId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendChatMessage(long userId, long receiverId, String message) {
        chatMessageMapper.sendChatMessage(userId, receiverId, message);
    }
}
