package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * 保存与用户推荐给好友的文章实体
 * @author: liu.yucheng
 * @Date: 2019-5-18  15:03
 * @version: 1.0
 */
@TableName("friend_recommend")
public class FriendRecommend {
    @TableId("id")
    private long id;
    /**
     * 推荐好友id
     */
    @TableField("user_id")
    private long friendId;
    @TableField("article_id")
    private long articleId;
    @TableField("create_date")
    private Date createDate;
    /**
     * 用户 id
     */
    @TableField("recommend_id")
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
