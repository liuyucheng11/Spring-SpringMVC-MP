package com.studentAdmin.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.QueryParam;
import com.studentAdmin.domain.Message;
import com.studentAdmin.domain.VOs.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  11:24
 * @version: 1.0
 */
@Mapper
@Repository
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 发送消息
     * @param sendId
     * @param receiveId
     * @param msgContent
     */
      void sendMessage(@Param("sendId") long sendId, @Param("receiveId") long receiveId, @Param("sendMsgContent") String msgContent);

    /**
     * 回复私信
     * @param id
     * @param replyMessageContent
     */
    void  replyMessage(@Param("id") long id, @Param("replyMessageContent") String replyMessageContent );


    List<MessageVO> getMessageByUserId(@Param("receiveId") long receivedId);


    /**
     * 获取所有发送私信
     * @param param
     * @return
     */
    List<MessageVO> getMessageBySendId(QueryParam param);

    /**
     * 获取所有接收私信
     * @param param
     * @return
     */
    List<MessageVO> getMessageByReceiveId(QueryParam param);

    /**
     * 更改私信为已读状态
     * @param id
     */
    void readMessageById(@Param("id") long id);

    /**
     * 发送者删除不可见
     * @param id
     */
    void senderDeleteById(@Param("id") long id);

    /**
     * 接收着删除不可见
     * @param id
     */
    void receiveDeleteById(@Param("id") long id);

    void ignoreMessageById(@Param("id") long id);


}
