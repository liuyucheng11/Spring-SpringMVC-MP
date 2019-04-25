package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.studentAdmin.domain.VOs.UserVO;
import lombok.Data;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-23  16:11
 * @version: 1.0
 */
@TableName("user_friend")
@Data
public class UserFriend extends UserVO {
    @TableId("id")
    private Long id;
    @TableField("user_id")
    private Long friendId_2;
    @TableField("friend_id")
    private Long friendId;
}
