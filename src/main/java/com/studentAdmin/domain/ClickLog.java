package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("click_log")
public class ClickLog {
  @TableId("click_log_id")
  private long clickLogId;
  @TableField("user_id")
  private long userId;
  @TableField("article_id")
  private long articleId;
  @TableField("create_date")
  private java.sql.Date createDate;


  public long getClickLogId() {
    return clickLogId;
  }

  public void setClickLogId(long clickLogId) {
    this.clickLogId = clickLogId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public java.sql.Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Date createDate) {
    this.createDate = createDate;
  }

}
