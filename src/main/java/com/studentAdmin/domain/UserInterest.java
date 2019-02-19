package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("user_interest")
public class UserInterest {
  @TableId("interest_id")
  private long interestId;
  @TableField("user_id")
  private long userId;
  @TableField("article_type_id")
  private long articleTypeId;

  public long getInterestId() {
    return interestId;
  }

  public void setInterestId(long interestId) {
    this.interestId = interestId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getArticleTypeId() {
    return articleTypeId;
  }

  public void setArticleTypeId(long articleTypeId) {
    this.articleTypeId = articleTypeId;
  }

}
