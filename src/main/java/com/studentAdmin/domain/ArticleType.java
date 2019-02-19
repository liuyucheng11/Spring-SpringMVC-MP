package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("article_type")
public class ArticleType {
   @TableId("article_type_id")
  private long articleTypeId;
   @TableId("typeName")
  private String typeName;


  public long getArticleTypeId() {
    return articleTypeId;
  }

  public void setArticleTypeId(long articleTypeId) {
    this.articleTypeId = articleTypeId;
  }


  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

}
