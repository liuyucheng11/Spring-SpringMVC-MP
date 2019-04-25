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
    private int id;
    @TableField("send_id")
    private long sendId;
    @TableField("receive_id")
    private long receiveId;
    @TableField("time")
    private Date time;
}
