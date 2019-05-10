package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.util.Date;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  0:36
 * @version: 1.0
 */
@TableName("thumbs_log")
@Data
public class Thumbs {
    @TableId("id")
    private long id;
    @TableField("user_id")
    private long userId;
    @TableField("thumb_id")
    private long thumbId;
    @TableField("create_time")
    private Date createTime;
}
