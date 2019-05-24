package com.studentAdmin.domain;

import java.util.Date;

/**
 * 点赞评论用实体
 * @author: liu.yucheng
 * @Date: 2019-5-10  19:13
 * @version: 1.0
 */
public class ThumbComment {
    private long id;
    private long userId;
    private long scoreId;
    private Date createDate;

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

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
