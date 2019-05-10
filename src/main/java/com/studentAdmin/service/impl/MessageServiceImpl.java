package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.common.ResultPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.studentAdmin.dao.mapper.MessageMapper;
import com.studentAdmin.domain.VOs.MessageVO;
import com.studentAdmin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-28  13:29
 * @version: 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(long sendId, long receiveId, String sendMsgContentMsg) {
        messageMapper.sendMessage(sendId, receiveId, sendMsgContentMsg);
    }

    @Override
    public ResultPage getMessage(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(), param.getLimit());
        List<MessageVO> messageVOList = messageMapper.getMessageByUserId((int) param.get("receiveId"));
        return new ResultPage((int) page.getTotal(), param.getLimit(), page.getPages(), param.getPage(), messageVOList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void replyMessage(long id, String replyMsgContentMsg) {
        messageMapper.replyMessage(id, replyMsgContentMsg);

    }

    @Override
    public ResultPage getMessageBySendId(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(), param.getLimit());
        List<MessageVO> messageVOList = messageMapper.getMessageBySendId(param);
        return new ResultPage((int) page.getTotal(), param.getLimit(), page.getPages(), param.getPage(), messageVOList);
    }

    @Override
    public ResultPage getMessageByReceiveId(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(), param.getLimit());
        List<MessageVO> messageVOList = messageMapper.getMessageByReceiveId(param);
        return new ResultPage((int) page.getTotal(), param.getLimit(), page.getPages(), param.getPage(), messageVOList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readMessageById(long id) {
        messageMapper.readMessageById(id);
    }

    @Override
    public void senderDeleteById(long id) {
        messageMapper.senderDeleteById(id);

    }

    @Override
    public void receiveDeleteById(long id) {
         messageMapper.readMessageById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ignoreMessageById(long id) {
         messageMapper.ignoreMessageById(id);
    }
}
