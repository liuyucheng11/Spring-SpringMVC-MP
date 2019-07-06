package com.studentAdmin.service;

import com.common.QueryParam;
import com.common.ResultPage;
import com.studentAdmin.domain.VOs.MessageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-28  13:27
 * @version: 1.0
 */
public interface MessageService {

    void sendMessage(long sendId,long receiveId,String sendMsgContentMsg);

    ResultPage getMessage(QueryParam param);

    void replyMessage(long id ,String replyMsgContentMsg);

    ResultPage getMessageBySendId( QueryParam param);

    ResultPage getMessageByReceiveId(QueryParam param);

    /**
     * 更改私信为已读状态
     * @param id
     */
    void readMessageById( long id);

    /**
     * 发送者删除不可见
     * @param id
     */
    void senderDeleteById(long id);

    /**
     * 接收着删除不可见
     * @param id
     */
    void receiveDeleteById( long id);

    /**
     * 忽略消息
     * @param id
     */
    void ignoreMessageById(@Param("id") long id);


}
