package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 统一消息处理类
 * @author: liu.yucheng
 * @Date: 2019-4-25  11:16
 * @version: 1.0
 */
@TableName("message")
@Data
public class Message {
    @TableId("id")
    private long id;
    @TableField("send_id")
    private long sendId;
    @TableField("receive_id")
    private long receiveId;
    @TableField("send_time")
    private Date sendTime;
    /**
     * 0.未读 1.已读
     */
    @TableField("has_read")
    private int hasRead;
    @TableField("reply_time")
    private Date replyTime;
    @TableField("send_msg_content")
    private String sendMsgContent;
    @TableField("reply_msg_content")
    private String replyMsgContent;
    /**
     * 0.不忽略 1.忽略
     */
    @TableField("is_ignore")
    private int is_ignore;
    /**
     * 0.已经回复 2.未回复
     *
     */
    @TableField("is_reply")
    private int isReply;

    /**
     *  发送者删除
     */
    @TableField("sender_delete")
    private int senderDelete;

    /**
     * 接收着删除
     */
    @TableField("receiver_delete")
    private int receiverDelete;



}
