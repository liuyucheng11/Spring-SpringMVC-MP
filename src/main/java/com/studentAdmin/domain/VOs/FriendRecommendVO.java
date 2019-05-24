package com.studentAdmin.domain.VOs;

import com.studentAdmin.domain.FriendRecommend;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-18  15:13
 * @version: 1.0
 */
public class FriendRecommendVO extends FriendRecommend {
    /**
     * 推荐好友名
     */
    private String friendName;
    private String articleName;
    private String authorName;
    private String authorId;
    private String userName;

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
