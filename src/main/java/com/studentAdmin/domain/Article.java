package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName(value = "article")
public class Article {
    @TableId("article_id")
    private long articleId;

    @TableField("article_name")
    private String articleName;

    @TableField("user_id")
    private long userId;

    @TableField("homeUrl")
    private String homeUrl;

    @TableField("articleType")
    private long articleType;

    @TableField("article_desc")
    private String articleDesc;

    @TableField("publish_date")
    private java.sql.Date publishDate;

    private String userName;



    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }


    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }


    public long getArticleType() {
        return articleType;
    }

    public void setArticleType(long articleType) {
        this.articleType = articleType;
    }


    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }


    public java.sql.Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(java.sql.Date publishDate) {
        this.publishDate = publishDate;
    }

}
