package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.util.Date;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  16:08
 * @version: 1.0
 */
@TableName("friend_request")
@Data
public class FriendRequest {
    @TableId("id")
    private long id;
    @TableField("sender_id")
    private long senderId;
    @TableField("receiver_id")
    private long receiverId;
    @TableField("create_date")
    private Date createDate;
    /**
     * 0.发送 1.接受 2.拒绝
     */
    @TableField("state")
    private int state;

}
