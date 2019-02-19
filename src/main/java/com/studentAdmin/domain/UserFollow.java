package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("user_follow")
public class UserFollow {
 @TableId("follow_id")
  private long followId;
 @TableField("user_id")
  private long userId;
 @TableField("followed_id")
  private long followedId;

  public long getFollowId() {
    return followId;
  }

  public void setFollowId(long followId) {
    this.followId = followId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getFollowedId() {
    return followedId;
  }

  public void setFollowedId(long followedId) {
    this.followedId = followedId;
  }

}
