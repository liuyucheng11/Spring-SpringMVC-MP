package com.studentAdmin.domain.VOs;

import com.studentAdmin.domain.Article;

import java.util.Date;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-26  9:50
 * @version: 1.0
 */
public class ArticleCollectVO extends Article {
      private Date collectDate;
      private long collectId;


    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public long getCollectId() {
        return collectId;
    }

    public void setCollectId(long collectId) {
        this.collectId = collectId;
    }
}
