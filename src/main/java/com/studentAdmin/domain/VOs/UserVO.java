package com.studentAdmin.domain.VOs;

/**
 * 用户分页列表用
 */
public class UserVO {

    private long userId;
    private String userName;
    private long sex;
    private String userOccupation;
    private java.sql.Timestamp registerDate;
    private String userAddress;
    private long userAge;
    private String userAvatarUrl;

    private Long followCount;

    private Long articleCount;
    private long thumbsCount;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public long getSex() {
        return sex;
    }

    public void setSex(long sex) {
        this.sex = sex;
    }


    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }


    public java.sql.Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(java.sql.Timestamp registerDate) {
        this.registerDate = registerDate;
    }


    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


    public long getUserAge() {
        return userAge;
    }

    public void setUserAge(long userAge) {
        this.userAge = userAge;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public Long getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }

    public Long getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Long articleCount) {
        this.articleCount = articleCount;
    }

    public long getThumbsCount() {
        return thumbsCount;
    }

    public void setThumbsCount(long thumbsCount) {
        this.thumbsCount = thumbsCount;
    }
}
