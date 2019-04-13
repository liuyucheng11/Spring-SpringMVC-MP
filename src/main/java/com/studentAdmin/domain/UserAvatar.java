package com.studentAdmin.domain;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.sql.Timestamp;

@TableName("user_avatar")
public class UserAvatar {
  @TableId("id")
  private long id;
  @TableField("user_id")
  private long userId;
  @TableField("avatar_url")
  private String avatarUrl;
  @TableField("create_date")
  private java.sql.Date createDate;
  @TableField("modify_date")
  private java.sql.Date modifyDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }


  public java.sql.Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Date createDate) {
    this.createDate = createDate;
  }

  public java.sql.Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Date modifyDate) {
    this.modifyDate = modifyDate;
  }
}
