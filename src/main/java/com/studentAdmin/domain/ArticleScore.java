package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("article_score")
public class ArticleScore {
 @TableId("evaluate_id")
  private long evaluateId;
 @TableField("user_id")
  private Long userId;
 @TableField("article_id")
  private Long articleId;
 @TableField("score")
  private long score;
 @TableField("comment")
  private String comment;
 @TableField("create_date")
  private java.sql.Date createDate;
 @TableField("like_number")
  private long likeNumber;


  public long getEvaluateId() {
    return evaluateId;
  }

  public void setEvaluateId(long evaluateId) {
    this.evaluateId = evaluateId;
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


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public java.sql.Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Date createDate) {
    this.createDate = createDate;
  }


  public long getLikeNumber() {
    return likeNumber;
  }

  public void setLikeNumber(long likeNumber) {
    this.likeNumber = likeNumber;
  }

}
