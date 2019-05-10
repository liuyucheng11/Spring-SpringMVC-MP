package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 聊天实体类
 *
 * @author: liu.yucheng
 * @Date: 2019-5-5  15:45
 * @version: 1.0
 */
@TableName("chat_message")
@Data
public class ChatMessage {

    @TableId("id")
    private long id;
    @TableField("sender_id")
    private long senderId;
    @TableField("receiver_Id")
    private long receiverId;
    @TableField("message")
    private String message;
    @TableField("create_date")
    private Date createDate;


}
