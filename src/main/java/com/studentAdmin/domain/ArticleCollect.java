package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("article_collect")
public class ArticleCollect {
  @TableField("user_id")
  private long userId;
  @TableId("collect_id")
  private long collectId;
  @TableField("article_id")
  private long articleId;
  @TableField("collect_date")
  private java.util.Date collectDate;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getCollectId() {
    return collectId;
  }

  public void setCollectId(long collectId) {
    this.collectId = collectId;
  }


  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public java.util.Date getCollectDate() {
    return collectDate;
  }

  public void setCollectDate(java.sql.Date collectDate) {
    this.collectDate = collectDate;
  }

}
